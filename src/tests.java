import org.junit.Assert;
import org.junit.Test;

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


}
