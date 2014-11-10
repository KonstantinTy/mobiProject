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
            for (MobiBook.EXTHRecord rec : mobibook.EXTHRecords) {
                if (rec.recordType == 100)  {
                    System.out.println(new String(rec.data));
                }
            }
            // Тест для познания истин. Проверяет, чтo в record0 после EXTH и имени книги ничего нет. На текущих примерах - и правда, нет.
            // TODO: убрать эту строку после допиливания парса до чтения имени.
            mobibook.fileStream.read(new byte[(int)(long)(Long)mobibook.mobiHeader.get("Full Name Length") + 5]);
            while (mobibook.fileStream.read() == 0) {}
            Assert.assertEquals((long)(Long)mobibook.records[1].recordInfo.get("record Data Offset"), mobibook.fileStream.getChannel().position() - 1);
        }
    }

}
