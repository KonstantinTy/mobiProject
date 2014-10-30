import mobiSchemes.Scheme;
import mobiSchemes.SchemeItem;
import mobiSchemes.Schemes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

        int recordNumber = (int) ((long) this.<Long>getValue("number of records", palmDB));
        this.records = new HeaderReader.Record[recordNumber];
        for (int i=0; i < recordNumber; i++) {
            parseRecordInfo(i);
        }

        parsePalmDOCHeader();
    }

    public void parsePalmDB() throws Exception {
        Scheme scheme = Schemes.palmDB;
        this.palmDB = scheme.parseScheme(fileStream);
    }

    public void parseRecordInfo(int n) throws Exception{
        Scheme scheme = Schemes.recordInfo;
        this.records[n] = new HeaderReader.Record(scheme.parseScheme(fileStream));
        fileStream.skip(2); //traditionally zeros, not parsed by schemes
    }

    public void parsePalmDOCHeader() throws Exception{
        Scheme scheme = Schemes.palmDOCHeader;
        this.palmDOCHeader = scheme.parseScheme(fileStream);
    }
}
