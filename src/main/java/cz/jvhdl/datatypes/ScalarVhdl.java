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

import static cz.jvhdl.datatypes.DataTypeVhdl.Direction.*;

/**
 * Parent for scalar types.
 * 
 * @author Martin Janyš
 */
public abstract class ScalarVhdl extends NumberVhdl {

    /**
     * Parrent of all scalars.
     * 
     * @param id Identificator
     * @param arg1 First argument
     * @param arg2 Second argument
     * @param d Direction
     */
    public ScalarVhdl(String id, Float arg1, Float arg2, Direction d) {
        super(id, arg1, arg2, d);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(commentStart);
        
        sb.append("type ").append(this.getId());
        sb.append(" is range ");
        
        sb.append(arg1 - arg1.intValue() == 0.0 ? arg1.intValue() : arg1.toString());
        if (d == TO) {
            sb.append(" to ");
        }
        else if (d == DOWNTO) {
            sb.append(" downto ");
        }
        
        sb.append(arg2 - arg2.intValue() == 0.0 ? arg2.intValue() : arg2.toString());
        
        sb.append(";\n");
        
        sb.append(commentEnd);
        
        return sb.toString();
    }
    
    
}
