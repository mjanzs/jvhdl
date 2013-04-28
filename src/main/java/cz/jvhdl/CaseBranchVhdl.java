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

import java.util.*;

/**
 * This class is used like one branch in switch (case) block of code.
 * 
 * @author Martin Janyš
 */
public class CaseBranchVhdl extends ControlFlowStatementVhdl {

    private ConditionVhdl cond;
    private List<ObjectVhdl> body;

    /**
     * Constructor of case branch
     * 
     * @param var Condition string of case
     * @param parent Parent case
     */
    public CaseBranchVhdl(String var, ObjectVhdl parent) {
        this.cond = new ConditionVhdl(new ExprVhdl(var));
        this.body = new LinkedList<>();
        this.parent = parent;
    }

    /**
     * Adds element to content of case branch.
     * 
     * @param elem Added element
     */
    @Override
    public void body(ObjectVhdl elem) {
        this.body.add(elem);
    }

    /**
     * Begin of case branch
     */
    public void begin() {
        this.body = new LinkedList<>();
    }

    /**
     * Returns output VHDL string.
     * 
     * @return Return output VHDL string
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.indent());
        sb.append("when ").append(this.cond).append(" =>\n");

        if (!this.body.isEmpty()) {
            for (ObjectVhdl e : this.body) {
                if (e instanceof ExprVhdl) {
                    sb.append(this.indent()).append(this.indent);
                }

                sb.append(e);

                if (e instanceof ExprVhdl) {
                    sb.append(";\n");
                }
            }
        }
        else {
            sb.append(indent()).append(indent).append("null\n");
        }

        return sb.toString();
    }
}
