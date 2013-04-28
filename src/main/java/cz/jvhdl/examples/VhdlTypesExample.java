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
package cz.jvhdl.examples;

import cz.jvhdl.datatypes.NumberVhdl;
import cz.jvhdl.datatypes.FloatVhdl;
import cz.jvhdl.datatypes.IntVhdl;
import cz.jvhdl.datatypes.ArrayVhdl;
import cz.jvhdl.datatypes.VariableVhdl;
import cz.jvhdl.datatypes.EnumVhdl;
import cz.jvhdl.datatypes.SignalVhdl;
import cz.jvhdl.VHDL;
import static cz.jvhdl.datatypes.DataTypeVhdl.Direction.*;
import static cz.jvhdl.datatypes.DataTypeVhdl.DataUnit.*;
import cz.jvhdl.datatypes.std.StdLogicVectorVhdl;
import cz.jvhdl.exception.InvalidVhdlTypeException;

/**
 * @author Martin Janyš
 */
public final class VhdlTypesExample extends VHDL {

    /**
     *
     * @throws InvalidVhdlTypeException
     */
    public VhdlTypesExample() throws InvalidVhdlTypeException {
    }

    /**
     *
     * @throws InvalidVhdlTypeException
     */
    public void stdTypes() throws InvalidVhdlTypeException {
        Signal("var", new StdLogicVectorVhdl(0, 1, TO));
    }

    /**
     * Example of creating basic vhdl types.
     *
     * @throws InvalidVhdlTypeException
     */
    public void basicTypes() throws InvalidVhdlTypeException {
//      type FSMstate is (SXX, SX1, S10);
        EnumVhdl fsmState = Enum(new EnumVhdl("FSMstate", "SXX, SX1, S10"));
//      signal pstate : FSMstate;
        SignalVhdl pstate = Signal("pstate", fsmState);
//      variable pstate : FSMstate;
        VariableVhdl pstate_v = Variable("pstate", fsmState);
     
    }

    /**
     *
     * Example of creating scalar vhdl types.
     *
     * @throws InvalidVhdlTypeException
     */
    public void scalarTypes() throws InvalidVhdlTypeException {

//      type my_small is range -5 to 5 ;
        NumberVhdl my_small = Scalar(new IntVhdl("my_small", -5, 5, TO));
//      type my_bits  is range 31 downto 0 ;
        NumberVhdl my_bits = Scalar(new IntVhdl("my_bits", 31, 0, DOWNTO));
//      type my_float is range 1.0 to 1.0E6 ;
        NumberVhdl my_float = Scalar(new FloatVhdl("my_float", 1.0f, 1.0E6f, DOWNTO));
        my_float.commentEnd = "\n";
    }

    /**
     * Example of creating array vhdl types.
     *
     * @throws InvalidVhdlTypeException
     */
    public void arrayTypes() throws InvalidVhdlTypeException {
//      type word is array (0 to 31) of bit;
        ArrayVhdl word = Array(new ArrayVhdl("word", 0, 31, TO, BIT));
//      type data is array (7 downto 0) of word;
        ArrayVhdl data = Array(new ArrayVhdl("data", 7, 0, DOWNTO, WORD));
//      type mem is array (natural range <>) of real;
//        ArrayVhdl array = Array(new ArrayVhdl("mem", NATURAL_RANGE, REAL)); // TODO: rerefactor natural range
//      type matrix is array (integer range <>, 
//                            integer range <>) of real;

        // TODO: matrix
    }

    /**
     *
     * Runs examples.
     *
     * @param args
     * @throws InvalidVhdlTypeException
     */
    public static void main(String[] args) throws InvalidVhdlTypeException {
        VhdlTypesExample e = new VhdlTypesExample();

//        e.stdTypes();

        e.basicTypes();
//        e.scalarTypes();
//        e.arrayTypes();

        System.out.println(e);
    }
}
