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
 * VHDL Float.
 * 
 * @author Martin Janyš
 */
public class FloatVhdl extends ScalarVhdl {

    /**
     * Creates vhdl float type.
     * 
     * First and second argument is use for create interval (arg1 to/downto arg2)
     * 
     * @param id Identificator
     * @param arg1 First argument
     * @param arg2 Second argument
     * @param d Direction
     */
    public FloatVhdl(String id, Float arg1, Float arg2, Direction d) {
        super(id, arg1, arg2, d);
    }
    
    /**
     * Creates vhdl float type.
     * 
     * First and second argument is use for create interval (arg1 to/downto arg2).
     * 
     * <br/>
     * <b>Warning:</b> Use float precision.
     * 
     * @param id Identificator
     * @param arg1 First argument
     * @param arg2 Second argument
     * @param d Direction
     */
    public FloatVhdl(String id, Double arg1, Double arg2, Direction d) {
        super(id, arg1.floatValue(), arg2.floatValue(), d);
    }
}
