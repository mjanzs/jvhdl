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

import cz.jvhdl.datatypes.DataTypeVhdl;

/**
 * @author Martin Janyš
 */
public class StdLogicVhdl extends DataTypeVhdl {

    private DataTypeVhdl.Type t = null;

    public StdLogicVhdl() {
    }

    /**
     * Constructor is used to create ports of entity.
     *
     * @param t Type of std_logic
     */
    public StdLogicVhdl(Type t) {
        this.t = t;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (t != null) {
            sb.append(t).append(" ");
        }

        return sb.append("STD_LOGIC").toString();
    }
    
    @Override
    public DataTypeVhdl getUndirected() {
        return new StdLogicVhdl();
    }
}
