/**
 * Created by IntelliJ IDEA.
 * User: КТ1
 * Date: 30.09.14
 * Time: 13:39
 * To change this template use File | Settings | File Templates.
 */
import utils.ByteUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class HeaderReader {

    public static byte[] readBytes(FileInputStream in, int count) {
        byte[] res = new byte[count];
        try {
            in.read(res);
        } catch (Exception e) {
            System.out.println("Troubles with in.read()");
        }
        return res;
    }

    public static class Scheme{
        String[] fields;
        int[] sizes;
        int length;
        public Scheme(String[] fieldsA, int[] sizesA) throws Exception{
            this.fields = fieldsA;
            this.sizes = sizesA;
            if (fieldsA.length != sizesA.length) {
                throw new Exception("lengths are different, can't create scheme");
            } else {
                this.length = fieldsA.length;
            }
        }
    }

    public static class Record {
        public HashMap<String, Object> recordInfo;
        public Record(HashMap<String, Object> info) {
            this.recordInfo = info;
        }
    }

    public static Scheme palmDOCHeaderScheme;
    public static Scheme palmDBScheme;
    public static Scheme recordInfoScheme;
    public static void createScheme() {
        String[] palmDBFields = {
                "name",
                "attributes",
                "version",
                "creation date",
                "modification date",
                "modificationNumber",
                "last backup date",
                "appInfoID",
                "sortInfoID",
                "type",
                "creator",
                "uniqueIDseed",
                "nextRecordListID",
                "number of Records"
        };
        int[] palmDBSizes = {
                32,
                2,
                2,
                4,
                4,
                4,
                4,
                4,
                4,
                4,
                4,
                4,
                4,
                2
        };
        String[] palmDOCHeaderFields = {
                "Compression",
                "Unused",
                "text length",
                "record count",
                "record size",
                "Encryption Type",
                "Unknown"
        };

        int[] palmDOCHeaderSizes = {
                2,
                2,
                4,
                2,
                2,
                2,
                2
        };
        String[] recordInfoSchemeFields = {
                "record Data Offset",
                "record Attributes",
                "UniqueID"
        };
        int[] recordInfoSchemeSizes = {
                4,
                1,
                3
        };
        try {
            palmDOCHeaderScheme = new Scheme(palmDOCHeaderFields, palmDOCHeaderSizes);
            palmDBScheme = new Scheme(palmDBFields, palmDBSizes);
            recordInfoScheme = new Scheme(recordInfoSchemeFields, recordInfoSchemeSizes);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static HashMap<String, Integer> parseByScheme(Scheme scheme, FileInputStream in) {
        HashMap<String, Integer> res = new HashMap<String, Integer>();
        for (int i=0; i < scheme.length; i++) {
            res.put(scheme.fields[i], ByteUtils.parseIntFromBytesBigEndian(readBytes(in, scheme.sizes[i])));
        }
        return res;
    }

    public static HashMap<String, Integer> readPDH(FileInputStream in) {
        HashMap<String, Integer> res = parseByScheme(palmDBScheme, in);
        return res;
    }

//    public static Record readNextRecordsInfo(FileInputStream in) {
//        Record res = new Record();
//        res.recordInfo = parseByScheme(recordInfoScheme, in);
//        return res;
///    }


    public static HashMap<String, Integer> readPalmDOCHeader(FileInputStream in) {
        HashMap<String, Integer> res = parseByScheme(palmDOCHeaderScheme, in);
        return res;
    }


    public static void main (String argz[]) throws Exception {
        createScheme();
        FileInputStream in = new FileInputStream(new File("test.mobi"));
/**        for (int i=0; i<100; i++) {
            read4bytes(in, length);
            System.out.println(4*i + " " + length[0]);
            System.out.println((4*i + 1) + " " + length[1]);
            System.out.println((4*i + 2) + " " + length[2]);
            System.out.println((4*i + 3) + " " + length[3]);
        }
 */
        HashMap<String, Integer> PalmDatabaseHeader = readPDH(in);
//        byte[] test = PalmDatabaseHeader.get("sortInfoID");
//        System.out.println(parseIntFromBytesBigEndian(test));
//        Record rec;
/**        for (int i=0; i < 110; i++) {
            rec = readNextRecordsInfo(in);
            System.out.println(rec.recordInfo.get("record Data Offset"));
        }
 */
        int numberOfRecords = (PalmDatabaseHeader.get("number of Records"));
        Record[] records = new Record[numberOfRecords];
        for (int i = 0; i < numberOfRecords; i++) {
 //           records[i] = readNextRecordsInfo(in);
        }
//        System.out.println(records[0].recordInfo.get("record Data Offset"));
        in.skip(2); // 2 unused bytes
//        HashMap<String, Integer> PalmDOCHeader = readPalmDOCHeader(in);
//        System.out.println(PalmDOCHeader.get("record size"));
        MobiBook book = new MobiBook();
        book.parse("test.mobi");
        System.out.println(book.palmDB.get("number of records"));
        System.out.println((String)book.mobiHeader.get("identifier"));
        System.out.println("name: " + (String)book.finallyGetName());
    }
}
