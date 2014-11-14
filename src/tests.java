import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by КТ1 on 28.10.2014.
 */
public class tests {

    @Test
    public void parsePalmDB() {
        MobiBook book = new MobiBook();
        String fileToTest = "test.mobi";
        try {
            book.setFile(fileToTest);
        } catch (FileNotFoundException e) {
            System.out.println("test file not found");
            System.out.println(e.toString());
        }
        try {
            book.parsePalmDB();
            Assert.assertEquals((int)book.<Integer>getValue("number of records", book.palmDB), 110);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void parseMobiHeader() throws Exception{
        File dir = new File("ustBooks");
        File[] books = dir.listFiles();
        MobiBook mobibook;
        String s;
        byte[] b;
        for (File book : books) {
            mobibook = new MobiBook();
            mobibook.parse("ustBooks/" + book.getName());
            s = (String)mobibook.EXTHHeader.get("identifier");
            Assert.assertEquals(s, "EXTH");
            Assert.assertEquals((String)mobibook.mobiHeader.get("identifier"), "MOBI");
            // Наглядный тест для людей. Проверит, разумно ли считался автор. На текущих примерах работает хорошо.
            boolean authorKnown = false;
            for (MobiBook.EXTHRecord rec : mobibook.EXTHRecords) {
                if (rec.recordType == 100)  {
                    System.out.println("  " + new String(rec.data));
                    authorKnown = true;
                }
            }
            if (!authorKnown) {
                System.out.println("  Unknown author");
            }
            System.out.println("  " + mobibook.bookName);
            //System.out.println("  Compression:  " + mobibook.<Integer>getValue("compression", mobibook.palmDOCHeader));
            // Интересно, кто-нибудь использует не LZ77?
            Assert.assertEquals((Integer)2, mobibook.<Integer>getValue("compression", mobibook.palmDOCHeader));
        }
    }

}
