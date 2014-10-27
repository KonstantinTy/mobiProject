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
                        new SchemeItem(SchemeItemType.LONG, 4, "appInfoId"),
                        new SchemeItem(SchemeItemType.LONG, 4, "sortInfoId"),
                        new SchemeItem(SchemeItemType.LONG, 4, "type"),
                        new SchemeItem(SchemeItemType.LONG, 4, "creator"),
                        new SchemeItem(SchemeItemType.LONG, 4, "uniqueIDseed"),
                        new SchemeItem(SchemeItemType.LONG, 4, "nextRecordListID"),
                        new SchemeItem(SchemeItemType.INT, 2, "number of records")
                }
        );
        Schemes.palmDOCHeader = new Scheme(
                new SchemeItem[]{
                        new SchemeItem(SchemeItemType.INT, 2, "compression"),
                        new SchemeItem(SchemeItemType.INT, 2, "unused"),  //Always zero
                        new SchemeItem(SchemeItemType.LONG, 4, "text length"),
                        new SchemeItem(SchemeItemType.INT, 2, "record count"),
                        new SchemeItem(SchemeItemType.INT, 2, "record size"),
                        new SchemeItem(SchemeItemType.INT, 2, "encryption type"),
                        new SchemeItem(SchemeItemType.INT, 2, "unknown")
                }
        );
        Schemes.recordInfo = new Scheme(
                new SchemeItem[]{
                        new SchemeItem(SchemeItemType.LONG, 4, "record Data Offset"),
                        new SchemeItem(SchemeItemType.BITFIELD, 1, "record Attributes"),
                        new SchemeItem(SchemeItemType.INT, 3, "UniqueID"),
                }
        );
    }
}
