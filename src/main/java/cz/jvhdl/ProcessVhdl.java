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

import cz.jvhdl.exception.SyntaxErrorVhdl;
import cz.jvhdl.exception.InvalidVhdlTypeException;
import cz.jvhdl.datatypes.VariableVhdl;
import java.util.*;
import cz.jvhdl.datatypes.DataTypeVhdl;
import cz.jvhdl.datatypes.literals.BitVhdl;

/**
 * Class of vhdl process code.
 *
 * Contains methods for building vhdl process. <br/> VHDL process syntax.
 * <pre>
 * [<process_name>:] process (<sensitive signals>)
 *  signal declarations
 *  ...
 *  begin
 *
 *      statements
 *      ...
 * end process;
 * </pre>
 *
 * @author Martin Janyš
 */
public class ProcessVhdl extends BlockCodeRecursiveVhdl {

    /**
     * Sensitive list.
     *
     * Example: For "pstatereg: process(RST, CLK)" sensitive list is "(RST,
     * CLK)".
     */
    protected List<Object> sensitivityList = null;
    /**
     * Count if and endif.
     */
    private int ifCount = 0;
    /**
     * End of process tag.
     */
    private boolean endTag = false;

    /**
     * Vhdl procces constructor.
     *
     * @param id Indetificator
     */
    public ProcessVhdl(String id) {
        super(id);
        this.commentStart = "-- process " + id;
        this.commentEnd = "\n";
    }

    /**
     * Vhdl procces constructor.
     *
     * @param id Indetificator
     * @param sensitivityList Sensitove list
     */
    public ProcessVhdl(String id, Object[] sensitivityList) {
        this(id);
        if (sensitivityList != null) {
            this.sensitivityList = new LinkedList<>();
            
            for (Object o : sensitivityList) {
                if (o instanceof DataTypeVhdl) {
                    this.sensitivityList.add(((DataTypeVhdl)o).getId());
                }
                else if (o instanceof String) {
                    this.sensitivityList.add(o);
                }
                else {
                    this.sensitivityList.add(o.toString());
                }
            }
        }
    }

    /**
     * End process code. Nulls abstract tree variable. Check syntax. Withou
     * using this method you will lose control of module.
     *
     * @throws SyntaxErrorVhdl
     */
    @Override
    public void end() throws SyntaxErrorVhdl {
        if (!this.syntaxStack.isEmpty()) {
            throw new SyntaxErrorVhdl("Bad using of if/endif, when/endWhen or cases/endCases");
        }
        this.syntaxStack = null;

    }

    /**
     * Create assigment lvalue &lt;= rvalue.
     *
     * @param lvalue Left value
     * @param rvalue Right value
     */
    public void assignment(VariableVhdl lvalue, String rvalue) {
        this.appendBody(
                new AssignmentVhdl(lvalue, rvalue, this.currentParent()));
    }

    /**
     * Create assigment lvalue &lt;= rvalue.
     *
     * @param lvalue Left value
     * @param rvalue Right value
     * @throws InvalidVhdlTypeException
     */
    public void assignment(VariableVhdl lvalue, int rvalue) throws InvalidVhdlTypeException {
        this.appendBody(
                new AssignmentVhdl(
                lvalue,
                new BitVhdl(rvalue),
                this.abstractTreePeek()));
    }

    /**
     * Starts cases(case - in vhdl) statement.
     *
     * @param statment Signal in condition that will be tested.
     */
    public void cases(VariableVhdl statment) {
        CaseVhdl s = new CaseVhdl(statment, this.currentParent());

        this.appendBody(s);
        this.syntaxStack.push(s);
    }

    /**
     * Ends cases statement.
     */
    public void endCases() {
        if (this.syntaxStack.peek() instanceof CaseVhdl) {
            this.syntaxStack.pop();
        }
    }

    /**
     * Appends if statement.
     *
     * @param cond Condition exception.
     */
    public void if_(ConditionVhdl cond) {
        this.ifCount++;

        IfVhdl i = new IfVhdl(cond, this.currentParent());
        this.appendBody(i);
        this.syntaxStack.push(i);
    }

    /**
     * Else.
     */
    public void else_() {
        IfVhdl i = (IfVhdl) this.syntaxStack.pop();
        i.next = new ElseVhdl(this.currentParent());
        this.syntaxStack.push(i.next);
    }

    /**
     * Appends else if statement.
     *
     * @param cond Condition exception.
     */
    public void elseif(ConditionVhdl cond) {
        this.ifCount--;

        IfVhdl i = (IfVhdl) this.syntaxStack.pop();
        i.next = new IfVhdl(cond, this.currentParent());
        i.next.elseif = true;
        this.syntaxStack.push(i.next);
    }

    /**
     * Ends if statement.
     */
    public void endif() {
        if (this.syntaxStack.peek() instanceof IfVhdl) {
            this.syntaxStack.pop();
        }
    }

    /**
     * In cases statement appends when branch.
     *
     * @param var Condition of when
     * @return new branch
     *
     * @throws InvalidVhdlTypeException
     */
    public ControlFlowStatementVhdl when(String var) throws SyntaxErrorVhdl {
        // pop current case
        if (this.syntaxStack.peek() instanceof CaseBranchVhdl) {
            this.syntaxStack.pop();
        }

        if (!(this.syntaxStack.peek() instanceof CaseVhdl)) {
            throw new SyntaxErrorVhdl("When not used in cases context");
        }

        // create new case to parrent (cases)
        CaseBranchVhdl c = new CaseBranchVhdl(var, ((CaseVhdl) this.syntaxStack.peek()));
        ((CaseVhdl) this.syntaxStack.peek()).cases.add(c);
        // push new case
        this.syntaxStack.push(c);
        return c;
    }

    /**
     *
     */
    public void endWhen() {
        if (this.syntaxStack.peek() instanceof CaseBranchVhdl) {
            this.syntaxStack.pop();
        }
    }

    /**
     *
     */
    protected void endWhenWithOthers() {
        this.endWhenWithOthers(ConstantsVhdl.others, ConstantsVhdl.nul);
    }

    /**
     *
     * @param others
     * @param nul
     */
    protected void endWhenWithOthers(String others, String nul) {
        if (this.syntaxStack.peek() instanceof CaseBranchVhdl) {
            this.syntaxStack.pop();
        }

        CaseBranchVhdl c = new CaseBranchVhdl(others, this.syntaxStack.peek());
        c.body(new ExprVhdl(nul));

        ((CaseVhdl) this.syntaxStack.peek()).cases.add(c);

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(indent()).append(commentStart());

        // id : process( sensitive list )
        sb.append(indent()).append(this.id).append(": process(");
        if (this.sensitivityList != null && this.sensitivityList.size() > 0) {
            for (Object s : this.sensitivityList) {
                sb.append(s.toString());
                sb.append(", ");
            }
            int index = sb.lastIndexOf(",");
            sb.delete(index, index + 2);
        }
        sb.append(")\n");

        // dcl list
        sb.append(getLocalVariablesString(indent()));

        // begin
        sb.append(indent()).append("begin\n");

        for (ObjectVhdl e : this.body) {
            //sb.append(e.indent());
            if (e != null) {
                sb.append(e);
            } else {
                sb.append(";\n");
            }
        }

        sb.append(indent()).append("end process;");
        sb.append(commentEnd());

        return sb.toString();
    }

    private ObjectVhdl currentParent() {
        if (this.syntaxStack.empty()) {
            return this;
        } else {
            return this.syntaxStack.peek();
        }
    }

    private void appendBody(ObjectVhdl i) {
        if (this.syntaxStack.empty()) {
            this.body(i);
        } else {
            this.syntaxStack.peek().body(i);
        }

    }

    public boolean isActive() {
        return this.syntaxStack != null;
    }
}
