package mobiSchemes;

/**
 * Created by КТ1 on 27.10.2014.
 */

/**
 * Basic schemes of MOBI file
 */
public class Schemes {
    static Scheme palmDB;
    static Scheme palmDOCHeader;
    static Scheme recordInfo;
    public Schemes () {
        Schemes.palmDB = new Scheme(
                new SchemeItem[] {
                        new SchemeItem(SchemeItemType.STRING, 32, "name"),
                        new SchemeItem(SchemeItemType.BITFIELD, 2, "attributes"),
                        new SchemeItem(SchemeItemType.INT, 2, "version"),
                        new SchemeItem(SchemeItemType.LONG, 4, "creation date"),
                        new SchemeItem(SchemeItemType.LONG, 4, "modification date"),
                        new SchemeItem(SchemeItemType.LONG, 4, "last backup date"),
                        new SchemeItem(SchemeItemType.LONG, 4, "modificationNumber"),
                        


                }
        );
    }
}
