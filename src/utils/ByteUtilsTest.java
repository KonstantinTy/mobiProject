package utils;

import org.junit.Assert;
import org.junit.Test;

public class ByteUtilsTest {

    @Test
    public void parseIntFromBytesBigEndian() {
        Assert.assertEquals(ByteUtils.parseIntFromBytesBigEndian(new byte[]{1, 2}), 258);
        Assert.assertEquals(ByteUtils.parseIntFromBytesBigEndian(new byte[]{-1, 2}), 65282);
    }
}