import mobiSchemes.Scheme;
import mobiSchemes.SchemeItem;
import mobiSchemes.Schemes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by КТ1 on 28.10.2014.
 */
public class MobiBook {
    public FileInputStream fileStream;
    public File file;
    public HeaderReader.Record[] records;

    public HashMap<String, Long> palmDB;
    public HashMap<String, Long> palmDOCHeader;

    public MobiBook () {

    }

    public void setFile (String f) throws FileNotFoundException {
        file = new File(f);
        fileStream = new FileInputStream(file);
    }
    public void parse (String s) throws FileNotFoundException, IOException {
        this.file = new File(s);
        this.fileStream = new FileInputStream(file);
        parsePalmDB();

        int recordNumber = (int) ((long) palmDB.get("number of records"));
        this.records = new HeaderReader.Record[recordNumber];
        for (int i=0; i < recordNumber; i++) {
            parseRecordInfo(i);
        }

        parsePalmDOCHeader();
    }

    public void parsePalmDB() throws IOException {
        Scheme scheme = Schemes.palmDB;
        this.palmDB = scheme.parseScheme(fileStream);
    }

    public void parseRecordInfo(int n) throws IOException{
        Scheme scheme = Schemes.recordInfo;
        this.records[n] = new HeaderReader.Record(scheme.parseScheme(fileStream));
        fileStream.skip(2); //traditionally zeros, not parsed by schemes
    }

    public void parsePalmDOCHeader() throws IOException{
        Scheme scheme = Schemes.palmDOCHeader;
        this.palmDOCHeader = scheme.parseScheme(fileStream);
    }
}
