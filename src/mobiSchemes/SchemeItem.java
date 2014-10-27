package mobiSchemes;

/**
 * Created by КТ1 on 27.10.2014.
 */

/**
 * Represents one item of scheme to parse
 *
 */
public class SchemeItem {
    SchemeItemType type;
    int size;
    String name;
    public SchemeItem(SchemeItemType type, int size, String name) {
        this.type = type;
        this.size = size;
        this.name = name;
    }
}
