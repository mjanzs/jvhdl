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
 * Abstract class for numbers.
 * 
 * @author Martin Janyš
 */
public abstract class NumberVhdl extends DataTypeVhdl {

    /**
     * Counter of numbers without id.
     */
    protected static int counter = 0;
    /**
     * First argument of numberic type.
     */
    protected final Float arg1;
    /**
     * Second argument of numberic type.
     */
    protected final Float arg2;
    /**
     * Direction of interval (to/downto)
     */
    protected final Direction d;

    /**
     *
     */
    public NumberVhdl() {
        
        this.setId("number"+NumberVhdl.counter++);
        this.arg1 = 0f;
        this.arg2 = 0f;
        this.d = Direction.TO;
    }

    /**
     *
     * First and second argument is use for create interval (arg1 to/downto
     * arg2)
     *
     * @param id Identificator
     * @param arg1 First argument
     * @param arg2 Second argument
     * @param d Direction
     */
    public NumberVhdl(String id, Float arg1, Float arg2, Direction d) {

        this.setId(id);
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.d = d;
    }
}
