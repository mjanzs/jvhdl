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
package cz.jvhdl.datatypes;

/**
 * Composite types.
 *
 * @author Martin Janyš
 */
public class CompositeVhdl extends NumberVhdl {

    /**
     *
     */
    public CompositeVhdl() {
    }

    /**
     * Parrent of composite types (array/record).
     *
     * First and second argument is used for crate inteval (arg1 to/downto arg2)
     *
     * @param id Identificator
     * @param arg1 First argument
     * @param arg2 Second argument
     * @param d Direction (to/downto)
     */
    public CompositeVhdl(String id, Integer arg1, Integer arg2, Direction d) {
        super(id, arg1.floatValue(), arg2.floatValue(), d);
    }

}
