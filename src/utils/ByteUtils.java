package utils;

/**
 * @author akirakozov
 */
public class ByteUtils {
    /**
     * Parse integer value from byta array; 0'th byte is most significant byte.
     * @param b Byte array to parse
     * @return parsed value
     */
    public static int parseIntFromBytesBigEndian(byte[] b) {
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

    /**
     * Analogue, but parse LONG value
     */
    public static long parseLongFromBytesBigEndian(byte[] b) {
        long res = 0;
        int k = 0;
        int bi;
        for (int i = b.length - 1; i >= 0; i--) {
            bi = b[i];
            res |= ((long)(bi < 0 ? (bi + 256) : bi)) << (k << 3);
            k++;
        }
        return res;
    }

    // removed by String constructor :)
/**
    public static String parseString (byte[] b) throws Exception {
        String res;
        if ((b.length & 1) == 1) {
            throw new Exception("Cannot parse string from byte array with odd length");
        } else {
            res = "";
            int count = (b.length >> 1);
            char c;
            int temp1, temp2;
            for (int i = 0; i < count; i++) {
                temp1 = b[i << 1];
                temp1 = (temp1 < 0) ? (temp1 + 256) : temp1;
                temp2 = b[(i << 1) | 1];
                temp2 = (temp2 < 0) ? (temp2 + 256) : temp2;
                c = (char)((temp1 << 8) + temp2);
                res += c;
            }
        }
        return res;
    }
*/
}
