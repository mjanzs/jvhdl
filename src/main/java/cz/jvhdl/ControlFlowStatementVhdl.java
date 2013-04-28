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
 * Parent class of control flow statements like <b>if</b> of <b>case</b>.
 *
 * @author Martin Janyš
 */
public abstract class ControlFlowStatementVhdl extends ObjectVhdl {

    /**
     * Inserts element to body of ControlFlowStatementVhdl.
     *
     * @param elem Element if body of ControlFlowStatementVhdl
     */
    public abstract void body(ObjectVhdl elem);

}
