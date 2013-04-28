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

/**
 * Class represents else branch.
 * 
 * @author Martin Janyš
 */
class ElseVhdl extends IfVhdl {

    /**
     * Constructor of if with condition.
     *
     */
    public ElseVhdl(ObjectVhdl parent) {
        super(null, parent);
    }

    /**
     * Returns VHDL output string
     * 
     * @return VHDL output string
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        String thisIndent = this.indent();
        sb.append(thisIndent);

        sb.append("else\n");

        if (this.body != null) {
            for (ObjectVhdl e : this.body) {
                sb.append(e.toString());
            }
        }
        else {
            sb.append(thisIndent).append(this.indent);
            sb.append(";\n");
        }

        sb.append(thisIndent);
        sb.append("end if;\n");

        return sb.toString();
    }
}
