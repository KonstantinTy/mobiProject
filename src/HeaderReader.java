/**
 * Created by IntelliJ IDEA.
 * User: КТ1
 * Date: 30.09.14
 * Time: 13:39
 * To change this template use File | Settings | File Templates.
 */
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

    public static HashMap<String, byte[]> readPDH(FileInputStream in) {
        HashMap<String, byte[]> res = new HashMap<String, byte[]>();
        res.put("name", readBytes(in, 32));
        res.put("attributes", readBytes(in, 2));
        res.put("version", readBytes(in, 2));
        res.put("creation date", readBytes(in, 4));
        res.put("modification date", readBytes(in, 4));
        res.put("modificationNumber", readBytes(in, 4));
        res.put("last backup date", readBytes(in, 4));
        res.put("appInfoID", readBytes(in, 4));
        res.put("sortInfoID", readBytes(in, 4));
        res.put("type", readBytes(in, 4));
        res.put("creator", readBytes(in, 4));
        res.put("uniqueIDseed", readBytes(in, 4));
        res.put("nextRecordListID", readBytes(in, 4));
        res.put("number of Records", readBytes(in, 2));
        return res;
    }

    public static HashMap<String, Integer> readPalmDOCHeader(FileInputStream in) {
        HashMap<String, Integer> res = new HashMap<String, Integer>();
        res.put("Compression", parseIntFromBytesBigEndian(readBytes(in, 2)));
        res.put("Unused", parseIntFromBytesBigEndian(readBytes(in, 2)));
        res.put("text length", parseIntFromBytesBigEndian(readBytes(in, 4)));
        res.put("record count", parseIntFromBytesBigEndian(readBytes(in, 2)));
        res.put("record size", parseIntFromBytesBigEndian(readBytes(in, 2)));
        // Здесь может быть неверное считывание, вики сообщает о двух вариантах
        res.put("Encryption Type", parseIntFromBytesBigEndian(readBytes(in, 2)));
        res.put("Unknown", parseIntFromBytesBigEndian(readBytes(in, 2)));
        return res;
    }
    public static int parseIntFromBytesBigEndian (byte[] b) {
        int res = 0;
        int k = 0;
        int bi;
        for (int i = b.length - 1; i >= 0; i--) {
            bi = b[i];
            res |= (bi < 0 ? (bi + 256) : bi) << (k << 3);
            k++;
        }
        return res;
    }
    public static Record readNextRecordsInfo(FileInputStream in) {
        Record res = new Record();
        res.recordInfo.put("record Data Offset", parseIntFromBytesBigEndian(readBytes(in, 4)));
        res.recordInfo.put("record Attributes", parseIntFromBytesBigEndian(readBytes(in, 1)));
        res.recordInfo.put("UniqueID", parseIntFromBytesBigEndian(readBytes(in, 3)));

        return res;
    }
    public static class Record {
        public HashMap<String, Integer> recordInfo;
        public Record() {
            this.recordInfo= new HashMap<String, Integer>();
        }
    }

    public static HashMap<String, Integer> parseByScheme(String[] schemeFields, String[] schemeSizes, FileInputStream in) {
        
    }
    public static void main (String argz[]) throws Exception {
        FileInputStream in = new FileInputStream(new File("test.mobi"));
/**        for (int i=0; i<100; i++) {
            read4bytes(in, length);
            System.out.println(4*i + " " + length[0]);
            System.out.println((4*i + 1) + " " + length[1]);
            System.out.println((4*i + 2) + " " + length[2]);
            System.out.println((4*i + 3) + " " + length[3]);
        }
 */
        HashMap<String, byte[]> PalmDatabaseHeader = readPDH(in);
//        byte[] test = PalmDatabaseHeader.get("sortInfoID");
//        System.out.println(parseIntFromBytesBigEndian(test));
//        Record rec;
/**        for (int i=0; i < 110; i++) {
            rec = readNextRecordsInfo(in);
            System.out.println(rec.recordInfo.get("record Data Offset"));
        }
 */
        int numberOfRecords = parseIntFromBytesBigEndian(PalmDatabaseHeader.get("number of Records"));
        Record[] records = new Record[numberOfRecords];
        for (int i = 0; i < numberOfRecords; i++) {
            records[i] = readNextRecordsInfo(in);
        }
//        System.out.println(records[0].recordInfo.get("record Data Offset"));
        in.skip(2);
        HashMap<String, Integer> PalmDOCHeader = readPalmDOCHeader(in);
//        System.out.println(PalmDOCHeader.get("record size"));
    }
}
