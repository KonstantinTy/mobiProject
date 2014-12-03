import lz77.LZ77InputStream;
import mobiSchemes.Scheme;
import mobiSchemes.SchemeItem;
import mobiSchemes.SchemeItemType;
import mobiSchemes.Schemes;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by КТ1 on 28.10.2014.
 */
public class MobiBook {
    public FileInputStream fileStream;
    public File file;
    public HeaderReader.Record[] records;

    public HashMap<String, Object> palmDB;
    public HashMap<String, Object> palmDOCHeader;
    public HashMap<String, Object> mobiHeader;
    public HashMap<String, Object> EXTHHeader;
    public long record0Start;

    public String bookName;

    public class EXTHRecord {
        long recordType;
        long recordLength;
        byte[] data;
    }

    public ArrayList<EXTHRecord> EXTHRecords;
    public MobiBook () {

    }

    public <V> V getValue(String key, HashMap<String, Object> map) {
        //TODO: maybe add some checks or exceptions
        V value = (V) map.get(key);
        return value;
    }

    public void setFile (String f) throws FileNotFoundException {
        file = new File(f);
        fileStream = new FileInputStream(file);
    }
    public void parse (String s) throws Exception {
        this.file = new File(s);
        this.fileStream = new FileInputStream(file);
        parsePalmDB();

        int recordNumber = (this.<Integer>getValue("number of records", palmDB));
        this.records = new HeaderReader.Record[(int)recordNumber];
        for (int i=0; i < recordNumber; i++) {
            parseRecordInfo(i);
        }
        fileStream.skip(2); //traditionally zeros, not parsed by schemes
        record0Start = fileStream.getChannel().position();
        parsePalmDOCHeader();

        parseMobiHeader();

   //     System.out.println("cur offset " + fileStream.getChannel().position());
        if (this.hasEXTHHeader()) {
            parseEXTHHeader();
        } else {
            System.out.println("No EXTH!!!");
        }
        parseBookName();
        skipToRecord1Start();
        int recordsCount = records.length - 1; // Количество рекордов, корме нулевого
        int curRecLength;
        LZ77InputStream lz77;
        byte[] curRecord;
        ByteArrayInputStream curRecStream;
        for (int i=1; i < recordsCount; i++) {
            //TODO: length of last record
            PrintWriter outRec = new PrintWriter(new File("recs/rec" + i + ".txt"));
            curRecLength = (int)((Long)records[i+1].recordInfo.get("record Data Offset") - (Long)records[i].recordInfo.get("record Data Offset"));
            curRecord = new byte[curRecLength];
            fileStream.read(curRecord);
            curRecStream = new ByteArrayInputStream(curRecord);
            lz77 = new LZ77InputStream(curRecStream);
            parseRecord(lz77, outRec, curRecLength);

        }

    }

    public boolean hasEXTHHeader() {
        return ((Long)this.mobiHeader.get("EXTH flags") & (0x40)) != 0;
    }

  // for tests only
    public String finallyGetName() throws Exception{
        FileInputStream newInp = new FileInputStream(this.file);
        long offset = this.<Long>getValue("Full Name Offset", mobiHeader);
        long len = this.<Long>getValue("Full Name Length", mobiHeader);
        System.out.println(offset);
        System.out.println(len);
        byte[] b = new byte[(int)len];
        newInp.skip(record0Start + offset);
        newInp.read(b);
        return new String(b);
    }

    public void parseBookName() throws Exception{
        long len = this.<Long>getValue("Full Name Length", mobiHeader);
        long offset = this.<Long>getValue("Full Name Offset", mobiHeader);
        // TODO: понять, почему здесь иногда скипятся 0-4 байта.
        //System.out.println(record0Start + offset - this.fileStream.getChannel().position());
        this.fileStream.skip(record0Start + offset - this.fileStream.getChannel().position());
        byte[] b = new byte[(int)len];
        this.fileStream.read(b);
        this.bookName = new String(b);
    }

    public void skipToRecord1Start() throws Exception{
        this.fileStream.skip(((Long)this.records[1].recordInfo.get("record Data Offset")) - (this.fileStream.getChannel().position()));
    }
    public void parsePalmDB() throws Exception {
        Scheme scheme = Schemes.palmDB;
        this.palmDB = scheme.parseScheme(fileStream);
    }

    public void parseRecordInfo(int n) throws Exception{
        Scheme scheme = Schemes.recordInfo;
        this.records[n] = new HeaderReader.Record(scheme.parseScheme(fileStream));
    }

    public void parsePalmDOCHeader() throws Exception{
        Scheme scheme = Schemes.palmDOCHeader;
        this.palmDOCHeader = scheme.parseScheme(fileStream);
    }

    public void parseMobiHeader() throws Exception{
        System.out.print(file.getName());
        Scheme scheme = Schemes.mobiHeader;
        this.mobiHeader = new HashMap<String, Object>();
        Object parsed;
        int headerlength = 1000; //simply random big number; will be rewrited
        int parsedLength = 0;
        for (SchemeItem item : scheme.items) {
            if (parsedLength < headerlength) {
                if (item.name.equals("header length")) {
                    headerlength = (int)(long)(Long)item.parseItem(fileStream);
                    System.out.println("  " + headerlength);
                    this.mobiHeader.put(item.name, headerlength);
                    parsedLength += item.size;
                } else {
                    if (item.name.equals("?")) {
                        if (headerlength >= 232) {
                            this.mobiHeader.put(item.name, item.parseItem(fileStream));
                            parsedLength += item.size;
                        } else {
                            this.mobiHeader.put(item.name, null);
                        }
                    } else {
                        this.mobiHeader.put(item.name, item.parseItem(fileStream));
                        parsedLength += item.size;
                    }
                }
            } else {
//                System.out.println("  abandoned: " + item.name);
            }
        }
        //TODO: There are some bytes that i don't know why them exists
        this.fileStream.skip(headerlength - parsedLength);
    }


    public void parseEXTHHeader() throws Exception {
        Scheme scheme = Schemes.EXTHHeader;
        this.EXTHHeader = scheme.parseScheme(fileStream);
        long EXTHRecordsCount = this.<Long>getValue("record Count", this.EXTHHeader);
        this.EXTHRecords = new ArrayList<EXTHRecord>();
        EXTHRecord cur;
        for (int i = 0; i < EXTHRecordsCount; i++) {
            cur = new EXTHRecord();
            cur.recordType = (Long)(new SchemeItem(SchemeItemType.LONG, 4, "")).parseItem(fileStream);
            cur.recordLength = (Long)(new SchemeItem(SchemeItemType.LONG, 4, "")).parseItem(fileStream);
            cur.data = (byte[])((new SchemeItem(SchemeItemType.UNKNOWN, (int)cur.recordLength - 8, "")).parseItem(fileStream));
            this.EXTHRecords.add(cur);
        }
        this.fileStream.skip(3 - ((this.<Long>getValue("header length", this.EXTHHeader) - 1)&3));
    }

    public void parseRecord (LZ77InputStream lz77,  PrintWriter out, int len) throws Exception{
        int length = Math.min(len, fileStream.available());
        byte[] data = new byte[length];
        try {
            lz77.read(data, 0, length);
        } catch (Exception eee) {

        }
        out.print(new String(data));
        out.close();
    }
}
