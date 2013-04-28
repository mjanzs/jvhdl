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
 * Class is used for condition in case or if.
 * 
 * @author Martin Janyš
 */
public class ConditionVhdl extends ObjectVhdl {

    /**
     * Condition expresion.
     */
    protected ExprVhdl expr;

    /**
     * COnstructor of VHDL condition.
     *
     * @param expr Expression
     */
    public ConditionVhdl(ExprVhdl expr) {
        this.expr = expr;
    }

    @Override
    public String toString() {
        return expr.toString();
    }
    
    public boolean isEmpty() {
        return expr.isEmpty();
    }
    
    
}
