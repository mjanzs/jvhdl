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
 * Vhdl expression class.
 *
 * @author Martin Janyš
 */
public class ExprVhdl extends ObjectVhdl {

    private String exprString;

    /**
     * Constructor with expression in string.
     *
     * @param operation String with expression
     */
    public ExprVhdl(String operation) {
        this.exprString = operation;
    }

    /**
     * Method add equal operator between current string and parameter.
     *
     * @param i Parameter to add
     *
     * @return Current instance
     */
    public ExprVhdl eq(int i) {
        this.exprString += (" = " + i);
        return this;
    }

    @Override
    public String toString() {
        return exprString;
    }

    public boolean isEmpty() {
        return exprString.isEmpty();
    }
}
