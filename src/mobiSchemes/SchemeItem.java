package mobiSchemes;

/**
 * Created by КТ1 on 27.10.2014.
 */

import utils.ByteUtils;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Represents one item of scheme to parse
 *
 */
public class SchemeItem {
    public SchemeItemType type;
    public int size;
    public String name;
    public SchemeItem(SchemeItemType type, int size, String name) {
        this.type = type;
        this.size = size;
        this.name = name;
    }
    public Object parseItem (FileInputStream in) throws Exception{
        byte[] b = new byte[this.size];
        in.read(b);
        switch (this.type) {
            case INT :
                return ByteUtils.parseIntFromBytesBigEndian(b);
            case LONG :
                return ByteUtils.parseLongFromBytesBigEndian(b);
            case STRING :
                return new String(b);
            default : //for BITFIELD
                return 239;
        }
    }
}
