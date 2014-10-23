package utils;

/**
 * @author akirakozov
 */
public class ByteUtils {

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
}
