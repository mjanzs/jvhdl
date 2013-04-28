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

import cz.jvhdl.datatypes.DataTypeVhdl.Direction;
import cz.jvhdl.exception.InvalidVhdlTypeException;

/**
 * VHDL array.
 * 
 * "type word is array (0 to 31) of bit;". <br/> Syntax:
 * <b>type</b> ID <b>is array</b> (range) <b>of TYPEID</b>
 *
 * @author Martin Janyš
 */
public class ArrayVhdl extends CompositeVhdl {

    DataTypeVhdl.DataUnit of;

    /**
     * Creates vhdl array code.
     * 
     * First and second argument is used to create interval, arg1 to/downto arg2.
     * 
     * @param id Identificator if array
     * @param arg1 First argument
     * @param arg2 Second argument
     * @param d Direction between arguments.
     * @param of Data type that is use to build an array
     * 
     * @see DataTypeVhdl.DataUnit
     */
    public ArrayVhdl(String id, Integer arg1, Integer arg2, Direction d, DataTypeVhdl.DataUnit of) {
        super(id, arg1, arg2, d);
        this.of = of;
    }

    /**
     *
     * Creates vhdl array code.
     * 
     * Used for natural range array.
     * 
     * @param id Identificator
     * @param d NATURAL_RANGE only
     * @param of Data type that is use to build an array
     * 
     * @throws InvalidVhdlTypeException 
     */
    public ArrayVhdl(String id, Direction d, DataUnit of) throws InvalidVhdlTypeException {
        super(id, new Integer(0), new Integer(0), d);
        this.of = of;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(commentStart);
        
        sb.append("type ").append(this.getId());
        sb.append(" is array ");

        if (d == Direction.TO || d == Direction.DOWNTO) {
            sb.append("(").append(arg1.intValue()).append(" to ").append(arg2.intValue()).append(")");
        }
        sb.append(" of ").append(of).append(";\n");

        sb.append(commentEnd);
        
        return sb.toString();
    }
}
