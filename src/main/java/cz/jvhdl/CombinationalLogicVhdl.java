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
 * This class represent line block of code. It is assigment in combinational logic.
 * 
 * @author Martin Janyš
 */
class CombinationalLogicVhdl extends BlockCodeVhdl {

    private AssignmentVhdl body;

    /**
     * Construct combinational assigment
     * 
     * @param body Inner assigment.
     */
    CombinationalLogicVhdl(AssignmentVhdl body) {
        this.body = body;
        body.commentStart = "";
        body.commentEnd = "";
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(indent())
                .append(commentStart())
                .append(body.toString())
                .append(commentEnd())
                .append("\n").toString();
    }
}
