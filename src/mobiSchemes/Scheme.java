package mobiSchemes;

/**
 * Created by КТ1 on 27.10.2014.
 */

import utils.ByteUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * One scheme, simple list of SchemeItem's
 */
public class Scheme {
    public SchemeItem[] items;

    public Scheme(SchemeItem[] items) {
        this.items = items;
    }

    public HashMap<String, Long> parseScheme (FileInputStream in) throws IOException{
        HashMap<String, Long> res = new HashMap<String, Long>();
        byte[] b;
        for (SchemeItem item : items) {
            b = new byte[item.size];
            in.read(b);
            res.put(item.name, ByteUtils.parseLongFromBytesBigEndian(b));
            //TODO: other itemType cases
        }
        return res;
    }
}
