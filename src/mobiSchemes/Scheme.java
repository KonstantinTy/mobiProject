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

    public HashMap<String, Object> parseScheme (FileInputStream in) throws Exception{
        HashMap<String, Object> res = new HashMap<String, Object>();
        byte[] b;
        for (SchemeItem item : items) {
            res.put(item.name, item.parseItem(in));
        }
        return res;
    }
}
