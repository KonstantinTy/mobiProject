package lz77.test;

import com.sun.xml.internal.ws.util.StreamUtils;
import lz77.LZ77InputStream;
import org.junit.Test;
import sun.misc.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class LZ77Test {

    @Test
    public void decode() throws IOException {
        FileInputStream in = new FileInputStream(new File("test-lz77.txt"));
        LZ77InputStream lz77 = new LZ77InputStream(in);

        int len;
        int bufferSize = 2048;
        byte[] data = new byte[bufferSize];
        while ((len = lz77.read(data, 0, bufferSize)) != -1) {
            System.out.println(new String(data, 0, len));
        }
    }
}
