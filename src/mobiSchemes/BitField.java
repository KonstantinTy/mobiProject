package mobiSchemes;

import java.util.HashMap;

/**
 * Created by КТ1 on 30.10.2014.
 */
public class BitField {
    public HashMap<String, Boolean> fields;

    public BitField() {
        this.fields = new HashMap<String, Boolean>();
    }

    public void parseBitScheme(BitFieldScheme scheme, byte[] byteArr) {
        int q;
        int r;
        int n;
        boolean b;
        for (String attr : scheme.scheme.keySet()) {
            n = scheme.scheme.get(attr);
            q = (n >> 3);
            r = (n & 7);
            b = ((byteArr[q] & (1 << r)) != 0);
            this.fields.put(attr, b);
        }
    }
}
