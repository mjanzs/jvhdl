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

import java.util.LinkedList;
import java.util.List;
import cz.jvhdl.datatypes.VariableVhdl;

/**
 * VHDL case syntax.
 *
 * <pre>
 * case <expression> is
 *
 *  when <choice(s)> =>  *
 *      <expression>;
 *      ...
 *
 *  when ...
 *
 *      [when others => ... ]
 *
 * end case;
 * </pre>
 *
 * @author Martin Janyš
 */
class CaseVhdl extends ControlFlowStatementVhdl {

    protected List<CaseBranchVhdl> cases;
    private String cond;

    /**
     * Constructs VHDL case (switch) control flow statement.
     * 
     * @param condVariable Signal of variable in condition
     * @param parent Parent object
     */
    public CaseVhdl(VariableVhdl condVariable, ObjectVhdl parent) {
        this.cond = condVariable.getId();
        this.cases = new LinkedList<>();

        this.parent = parent;
    }

    /**
     * Returns VHDL output string.
     * 
     * @return VHDL output string 
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.indent());
        sb.append("case ").append(this.cond).append(" is\n");

        for (CaseBranchVhdl c : this.cases) {
//            sb.append(c.indent());
            sb.append(c);
        }

        sb.append(this.indent());//.append(this.indent);

        sb.append("end case;\n");

        return sb.toString();
    }

    /**
     * Not supported add in this statement.
     * 
     * @param elem 
     */
    @Override
    public void body(ObjectVhdl elem) {
        throw new UnsupportedOperationException("Not supported add in this statement.");
    }
}
