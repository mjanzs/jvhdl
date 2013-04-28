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

/**
 * Top parent of all VHDL objects. Contains all varables for typing code.
 * 
 * @author Martin Janyš
 */
public abstract class ObjectVhdl {

    /**
     * Comment before object
     */
    public String commentStart = "";
    /**
     * Coment after object
     */
    public String commentEnd = "";
    /**
     * Parent object
     */
    protected ObjectVhdl parent = null;
    /**
     * Indentation string - one indent
     */
    public static String indent = "    ";
    private final int indentCount = 1;
    private String indentStr = "";

    public String indent() {
        indentStr = "";
        return this.indent(this);
    }

    private String indent(ObjectVhdl e) {

        if (e.parent != null) {
            for (int i = 0; i < this.indentCount; i++) {
                indentStr += this.indent;
            }
            return this.indent(e.parent);
        }
        else {
            return indentStr;
        }


    }

    /**
     * Returns comment before object.
     * 
     * @return comment before object
     */
    public String commentStart() {
        return commentStart.isEmpty() ? "" : commentStart + "\n";
    }

    /**
     * Returns comment before object
     * @return comment after object
     */
    public String commentEnd() {
        return this.commentEnd;
    }

    public ObjectVhdl getParent() {
        return parent;
    }

    public void setParent(ObjectVhdl parent) {
        this.parent = parent;
    }
}
