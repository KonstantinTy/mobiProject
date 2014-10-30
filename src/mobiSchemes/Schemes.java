package mobiSchemes;

/**
 * Created by КТ1 on 27.10.2014.
 */

/**
 * Basic schemes of MOBI file
 */
public class Schemes {
    public static final Scheme palmDB = new Scheme(
            new SchemeItem[]{
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
    public static final Scheme palmDOCHeader = new Scheme(
            new SchemeItem[]{
                    new SchemeItem(SchemeItemType.INT, 2, "compression"),
                    new SchemeItem(SchemeItemType.INT, 2, "unused"),  //Always zero
                    new SchemeItem(SchemeItemType.LONG, 4, "text length"),
                    new SchemeItem(SchemeItemType.INT, 2, "record count"),
                    new SchemeItem(SchemeItemType.INT, 2, "record size"),
                    new SchemeItem(SchemeItemType.INT, 2, "encryption type"),
                    new SchemeItem(SchemeItemType.INT, 2, "unknown")
            }
    );;
    public static final Scheme recordInfo = new Scheme(
            new SchemeItem[]{
                    new SchemeItem(SchemeItemType.LONG, 4, "record Data Offset"),
                    new SchemeItem(SchemeItemType.BITFIELD, 1, "record Attributes"),
                    new SchemeItem(SchemeItemType.INT, 3, "UniqueID"),
            }
    );;
    public static final Scheme mobiHeader = new Scheme(
            new SchemeItem[]{
                    new SchemeItem(SchemeItemType.STRING, 4, "identifier"),
                    new SchemeItem(SchemeItemType.LONG, 4, "header length"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Mobi type"),
                    new SchemeItem(SchemeItemType.LONG, 4, "text Encoding"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Unique-ID"),
                    new SchemeItem(SchemeItemType.LONG, 4, "File version"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Ortographic index"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Inflection index"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Index names"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Index keys"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Extra index 0"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Extra index 1"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Extra index 2"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Extra index 3"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Extra index 4"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Extra index 5"),
                    new SchemeItem(SchemeItemType.LONG, 4, "First Non-book index?"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Full Name Offset"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Full Name Length"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Locale"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Input Language"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Output Language"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Min version"),
                    new SchemeItem(SchemeItemType.LONG, 4, "First Image index"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Huffman Record Offset"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Huffman Record Count"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Huffman Table Offset"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Huffman Table Length"),
                    new SchemeItem(SchemeItemType.LONG, 4, "EXTH flags"),
                    new SchemeItem(SchemeItemType.UNKNOWN, 32, "?"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Unknown1"),
                    new SchemeItem(SchemeItemType.LONG, 4, "DRM Offset"),
                    new SchemeItem(SchemeItemType.LONG, 4, "DRM Count"),
                    new SchemeItem(SchemeItemType.LONG, 4, "DRM Size"),
                    new SchemeItem(SchemeItemType.LONG, 4, "DRM Flags"),
                    new SchemeItem(SchemeItemType.UNKNOWN, 8, "Unknown"),
                    new SchemeItem(SchemeItemType.INT, 2, "First content record number"),
                    new SchemeItem(SchemeItemType.INT, 2, "Last content record number"),
                    new SchemeItem(SchemeItemType.UNKNOWN, 4, "Unknown2"),
                    new SchemeItem(SchemeItemType.LONG, 4, "FCIS record number"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Unknown (FCIS record count?)"),
                    new SchemeItem(SchemeItemType.LONG, 4, "FLIS record number"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Unknown (FLIS record count?)"),
                    new SchemeItem(SchemeItemType.UNKNOWN, 8, "Unknown3"),
                    new SchemeItem(SchemeItemType.UNKNOWN, 4, "Unknown4"),
                    new SchemeItem(SchemeItemType.LONG, 4, "First Compilation data section count"),
                    new SchemeItem(SchemeItemType.LONG, 4, "Number of Compilation data sections"),
                    new SchemeItem(SchemeItemType.UNKNOWN, 4, "Unknown5"),
                    new SchemeItem(SchemeItemType.BITFIELD, 4, "Extra Record Data Flags"),
                    new SchemeItem(SchemeItemType.LONG, 4, "INDX Record Offset"),
                    new SchemeItem(SchemeItemType.UNKNOWN, 4, "Unknown6"),
                    new SchemeItem(SchemeItemType.UNKNOWN, 4, "Unknown7"),
                    new SchemeItem(SchemeItemType.UNKNOWN, 4, "Unknown8"),
                    new SchemeItem(SchemeItemType.UNKNOWN, 4, "Unknown9"),
                    new SchemeItem(SchemeItemType.UNKNOWN, 4, "Unknown10"),
                    new SchemeItem(SchemeItemType.UNKNOWN, 4, "Unknown11")
            }
    );
    public Schemes () {
    }
}
