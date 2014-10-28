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

}
