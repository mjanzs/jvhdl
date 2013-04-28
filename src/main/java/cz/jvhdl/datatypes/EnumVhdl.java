/**
 * VYSOKÉ UČENÍ TECHNICKÉ V BRNÉ BRNO UNIVERSITY OF TECHNOLOGY
 *
 * FAKULTA INFORMAČNÍCH TECHNOLOGIÍ
 *
 * Baklářská práce
 *
 * Generátor konečných automatů z grafického popisu pro jazyk VHDL
 *
 * Author: Martin Janyš
 *
 * Brno 2013
 */
package cz.jvhdl.datatypes;

import java.util.ArrayList;

/**
 * VHDL enum. 
 * 
 * Syntax: "type FSMstate is (SXX, SX1, S10);".
 * <br/>
 * Syntax: <b>type</b> <i>typeid</i> (id {, id});
 *
 * @author Martin Janyš
 */
public class EnumVhdl extends DataTypeVhdl {

    /**
     * Values.
     *
     * Example: type 'FSMstate is (SXX, SX1, S10);' values are SXX, SX1, S10.
     */
    protected ArrayList<String> values;

    /**
     *
     * VhdlEnum constructor.
     *
     * @param id Identificator
     * @param values Array of identificators.
     */
    public EnumVhdl(String id, Object[] values) {
        this.setId(id);
        this.values = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            this.values.add(values[i].toString().trim());
        }
    }

    /**
     *
     * VhdlEnum constructor.
     *
     * @param id Identificator
     * @param values Values separated by separator
     * @param separator Separator character
     */
    public EnumVhdl(String id, String values, Character separator) {
        this(id, values.split(separator.toString()));
    }

    /**
     *
     * VhdlEnum constructor.
     *
     * @param id Identificator
     * @param values Comma separated values.
     */
    public EnumVhdl(String id, String values) {
        this(id, values, ',');
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(commentStart());

        sb.append("type ");
        sb.append(this.getId());
        sb.append(" is (");

        boolean hasValue = false;
        for (String v : this.values) {
            if (!v.isEmpty()) {
                hasValue = true;
                sb.append(v);
                sb.append(", ");
            }
        }
        if (!hasValue) {
            sb.append("INIT");
        }
        int index = sb.lastIndexOf(",");
        if (index > 0) {
            sb.delete(index, index + 2);
        }

        sb.append(");\n");

        sb.append(commentEnd());

        return sb.toString();
    }

    /**
     *
     * @return comment before code
     */
    @Override
    public String commentStart() {
        return "";//"-- Enum " + this.getId() + "\n";
    }

    /**
     *
     * @return commnet after code
     */
    @Override
    public String commentEnd() {
        return "";
    }
}
