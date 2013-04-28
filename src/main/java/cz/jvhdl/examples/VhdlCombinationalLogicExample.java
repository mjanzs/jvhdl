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

import cz.jvhdl.VHDL;
import cz.jvhdl.datatypes.DataTypeVhdl;
import cz.jvhdl.datatypes.SignalVhdl;
import cz.jvhdl.datatypes.std.StdLogicVectorVhdl;
import cz.jvhdl.datatypes.std.StdLogicVhdl;
import cz.jvhdl.exception.InvalidVhdlTypeException;
import cz.jvhdl.exception.SyntaxErrorVhdl;

 /**
 * @author Martin Janyš
 */
public class VhdlCombinationalLogicExample extends VHDL {

    /**
     *
     * @throws SyntaxErrorVhdl
     * @throws InvalidVhdlTypeException
     */
    public VhdlCombinationalLogicExample() throws SyntaxErrorVhdl, InvalidVhdlTypeException, Exception {
        Signal("sel", new StdLogicVectorVhdl(0, 1, DataTypeVhdl.Direction.TO));
        
        SignalVhdl x = Signal("x", new StdLogicVhdl(StdLogicVhdl.Type.IN));
        
        Process("p", null);
        Assignment(x, "y");
        EndProcess();
        
        Assignment(x, "y");
        
    }
    
    
    
    /**
     *
     * @param args
     * @throws SyntaxErrorVhdl
     * @throws InvalidVhdlTypeException
     */
    public static void main (String[] args) throws SyntaxErrorVhdl, InvalidVhdlTypeException, Exception {
        System.out.println(new VhdlCombinationalLogicExample());
    }
}
