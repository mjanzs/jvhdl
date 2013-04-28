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

import java.util.ArrayList;
import java.util.List;

/**
 * VHDL if syntax.
 * 
 * <pre>
 * if <condition> then
 *  statements
 *  ...
 *
 * [
 *  elsif <condition> then
 *      statements
 *      ...
 *
 *  else
 *      statements
 *      ...
 *
 * ]
 * endif;
 * </pre>
 *
 * @author Martin Janyš
 */
class IfVhdl extends ControlFlowStatementVhdl {

    /**
     * Next branch.
     */
    protected IfVhdl next = null;
    /**
     * Condition expression.
     */
    private ConditionVhdl cond = null;
    /**
     * Body of if, elsif, else.
     */
    protected List<ObjectVhdl> body = null;
    protected boolean elseif = false;

    /**
     * Constructor of if with condition.
     *
     * @param cond Condition
     */
    public IfVhdl(ConditionVhdl cond, ObjectVhdl parent) {
        this.cond = cond;
        this.parent = parent;
    }

    @Override
    public void body(ObjectVhdl elem) {
        if (this.body == null) {
            this.body = new ArrayList<>();
        }
        this.body.add(elem);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        String thisIndent = this.indent();
        sb.append(thisIndent);

        if (this.elseif) {
            sb.append("elsif (");
        }
        else {
            sb.append("if (");
        }
        sb.append(this.cond).append(") then\n");

        if (this.body != null) {
            for (ObjectVhdl e : this.body) {
                sb.append(e.toString());

            }
        }
        else {
            sb.append("\n");
        }

        if (this.next != null) {
            sb.append(this.next);
        }
        else {
            sb.append(thisIndent);
            sb.append("end if;\n");
        }

        return sb.toString();
    }
}
