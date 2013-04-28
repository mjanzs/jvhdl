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
import java.util.Stack;
import cz.jvhdl.datatypes.DataTypeVhdl;

/**
 * 
 * Basic class for structures witch can be contain other structures. 
 * 
 * @author Martin Janyš
 */
public abstract class BlockCodeRecursiveVhdl extends BlockCodeVhdl {

    /**
     * Cointains declarations.
     */
    protected DeclarationVhdl dcl = new DeclarationVhdl();
    /**
     * Identificator.
     *
     * Example: For "pstatereg: process(RST, CLK)" indentificator is
     * "pstatereg".
     */
    protected String id;
    /**
     * Body.
     *
     * Example: Code between begin ... end process;
     */
    protected List<ObjectVhdl> body = null;
    /**
     * Stack of abstraction syntax tree.
     */
    protected Stack<ControlFlowStatementVhdl> syntaxStack = null;

    /**
     * Constructor.
     * 
     * @param id Identidicator of structure
     */
    public BlockCodeRecursiveVhdl(String id) {
        this.id = id;

        this.body = new ArrayList<>();
        this.syntaxStack = new Stack<>();
    }

    /**
     * Peeks object from top of syntax stack.
     * 
     * @return Object from top of stack
     */
    protected ObjectVhdl abstractTreePeek() {
        return (ObjectVhdl) (this.syntaxStack.empty() ? this : this.syntaxStack.peek());
    }

    /**
     * Adds new control flow statement to process.
     *
     * @param elem Element to add.
     */
    public void add(ControlFlowStatementVhdl elem) {
        this.syntaxStack.peek().body(elem);
    }

    /**
     * Adds vhdl object to body.
     *
     * @param elem Added element.
     */
    @Override
    protected void body(ObjectVhdl elem) {
        this.body.add(elem);
    }

    /**
     * Sets indentificator of process.
     *
     * @param id identificator
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns identificator of process.
     *
     * @return Identificator
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * 
     * Returns string with local variables of current structure.
     * 
     * @param indent Current indentation
     * 
     * @return Output VHDL string
     */
    protected String getLocalVariablesString (String indent) {
        
        indent = indent + this.indent;
        
        StringBuilder sb = new StringBuilder();
        
        if (!dcl.isEmpty()) {
            sb.append("\n");
            for (DataTypeVhdl t : dcl) {
                sb.append(indent).append(t);
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
}
