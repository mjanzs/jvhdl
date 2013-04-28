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
package cz.jvhdl.datatypes;

/**
 * VHDL iteger.
 * 
 * @author Martin Janyš
 */
public class IntVhdl extends ScalarVhdl {

    /**
     * Creates vhdl integer scalar type.
     *
     * First and second argument is use for create interval (arg1 to/downto
     * arg2)
     *
     * @param id Identificator
     * @param arg1 First argument
     * @param arg2 Second argument
     * @param d Direction
     */
    public IntVhdl(String id, Integer arg1, Integer arg2, Direction d) {
        super(id, arg1.floatValue(), arg2.floatValue(), d);
    }
}
