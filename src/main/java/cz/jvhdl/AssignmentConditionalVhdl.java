/**
 * VYSOKÉ UČENÍ TECHNICKÉ V BRNĚ BRNO UNIVERSITY OF TECHNOLOGY
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
package cz.jvhdl;

import cz.jvhdl.datatypes.DataTypeVhdl;
import cz.jvhdl.datatypes.VariableVhdl;

/**
 * Candidate how extend lib
 *
 * @author Martin Janyš
 */
public class AssignmentConditionalVhdl extends AssignmentVhdl {

    public AssignmentConditionalVhdl(VariableVhdl lvalue, String rvalue, ObjectVhdl parent) {
        super(lvalue, rvalue, parent);
        
        throw new UnsupportedOperationException();
    }

    public AssignmentConditionalVhdl(VariableVhdl lvalue, DataTypeVhdl rvalue, ObjectVhdl parent) {
        super(lvalue, rvalue, parent);
        
        throw new UnsupportedOperationException();
    }

}
