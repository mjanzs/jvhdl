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
package cz.jvhdl.datatypes.std;

import cz.jvhdl.datatypes.ArrayVhdl;
import cz.jvhdl.datatypes.DataTypeVhdl;

/**
 * @author Martin Janyš
 */
public class StdLogicVectorVhdl extends ArrayVhdl {

    private Type t = null;

    /**
     * Creates vhdl array code.
     *
     * First and second argument is used to create interval, arg1 to/downto
     * arg2.
     *
     * @param arg1 First argument
     * @param arg2 Second argument
     * @param d Direction between arguments.
     */
    public StdLogicVectorVhdl(Integer arg1, Integer arg2, Direction d) {
        super("", arg1, arg2, d, null);
    }

    /**
     * Creates vhdl array code.
     *
     * First and second argument is used to create interval, arg1 to/downto
     * arg2.
     *
     * Constructor is used to create ports of entity.
     *
     * @param arg1 First argument
     * @param arg2 Second argument
     * @param d Direction between arguments.
     * @param t Type of std_logic_vector
     */
    public StdLogicVectorVhdl(Integer arg1, Integer arg2, Direction d, Type t) {
        super("", arg1, arg2, d, null);
        this.t = t;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (t != null) {
            sb.append(t).append(" ");
        }

        sb.append("STD_LOGIC_VECTOR(");
        sb.append(arg1 - arg1.intValue() == 0 ? arg1.intValue()
                : arg1.toString());
        sb.append(" ").append(d).append(" ");
        sb.append(arg2 - arg2.intValue() == 0 ? arg2.intValue()
                : arg2.toString());
        sb.append(")");

        return sb.toString();
    }

    @Override
    public DataTypeVhdl getUndirected() {
        return new StdLogicVectorVhdl(arg1.intValue(), arg2.intValue(), d);
    }
}
