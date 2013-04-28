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
package cz.jvhdl.datatypes.literals;

import cz.jvhdl.datatypes.DataTypeVhdl;
import cz.jvhdl.exception.InvalidVhdlTypeException;

/**
 * @author Martin Janyš
 */
public class BitVhdl extends DataTypeVhdl {

    protected int value;

    public BitVhdl(int value) throws InvalidVhdlTypeException {
        if (value != 1 && value != 0) {
            throw new InvalidVhdlTypeException("Bit type acepts only '0' or '1' " + value);
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return "'" + value + "'";
    }
}
