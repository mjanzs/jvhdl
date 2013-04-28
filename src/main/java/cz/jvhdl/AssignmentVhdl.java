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
package cz.jvhdl;

import cz.jvhdl.datatypes.VariableVhdl;
import cz.jvhdl.datatypes.DataTypeVhdl;
import cz.jvhdl.datatypes.SignalVhdl;

/**
 * VHDL assignment syntax.
 *
 * <pre>
 * -- for signal lvalue 
 * <signal_name> <= <expression>;
 * </pre>
 * <pre>
 * -- for variable lvalue 
 * <variable_name> := <expression>
 * </pre>
 *
 * @author Martin Janyš
 */
public class AssignmentVhdl extends ObjectVhdl {

    /**
     * Left value.
     */
    protected DataTypeVhdl lvalue;
    /**
     * Right value.
     */
    protected String rvalue;

    private AssignmentVhdl() {
        this.commentEnd = "\n";
    }

    /**
     * Vhdl assigment.
     *
     * @param lvalue Left value
     * @param rvalue Right value
     */
    public AssignmentVhdl(VariableVhdl lvalue, String rvalue, ObjectVhdl parent) {
        this();

        this.lvalue = lvalue;
        this.rvalue = rvalue;
        this.parent = parent;
    }

    /**
     * Vhdl assigment.
     *
     * @param lvalue Left value
     * @param rvalue Right value
     */
    public AssignmentVhdl(VariableVhdl lvalue, DataTypeVhdl rvalue, ObjectVhdl parent) {
        this();

        this.lvalue = lvalue;
        this.rvalue = rvalue.toString();
        this.parent = parent;
    }
    
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.indent()).append(lvalue.getId());

        if (lvalue instanceof SignalVhdl) {
            sb.append(" <= ");
        }
        else if (lvalue instanceof VariableVhdl) {
            sb.append(" := ");
        }

        sb.append(rvalue).append(";").append(commentEnd());

        return sb.toString();
    }
}
