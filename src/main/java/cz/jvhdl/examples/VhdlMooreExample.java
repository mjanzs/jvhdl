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

import cz.jvhdl.exception.SyntaxErrorVhdl;
import cz.jvhdl.exception.InvalidVhdlTypeException;
import cz.jvhdl.datatypes.EnumVhdl;
import cz.jvhdl.datatypes.SignalVhdl;
import cz.jvhdl.VHDL;
import cz.jvhdl.ConditionVhdl;
import cz.jvhdl.ExprVhdl;
import cz.jvhdl.datatypes.std.StdLogicVhdl;

/**
 * Example of moore finite state machine. <br/> <img alt=""
 * src="data:image/jpeg;base64,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"
 * />
 *
 * @author Martin Janyš
 */
public class VhdlMooreExample extends VHDL {

    /**
     * Creates FSM code.
     *
     * @throws InvalidVhdlTypeException
     * @throws SyntaxErrorVhdl
     */
    public VhdlMooreExample() throws InvalidVhdlTypeException, SyntaxErrorVhdl, Exception {

        EnumVhdl fsmState = Enum(new EnumVhdl("FSMstate", "SXX, SX1, S10"));
//          fsmState = new VhdlEnum("FSMstate", "SXX;SX1;S10", ';');
//          String[] values = {"SXX", "SX1", "S10"};
//          fsmState = new VhdlEnum("FSMstate", values);

        SignalVhdl pstate = Signal("pstate", fsmState);
        SignalVhdl nstate = Signal("nstate", fsmState);

        SignalVhdl y = new SignalVhdl("Y", new StdLogicVhdl(StdLogicVhdl.Type.OUT));
////////////////////////////////////////////////////////////////////////////////
        // PRESENT STATE
        Process("pstatereg", new String[]{"RST", "CLK"});

        If(new ConditionVhdl(new ExprVhdl("RST").eq(1)));
        {
            Assignment(pstate, "SXX");
        }
        Elseif(new ConditionVhdl(new ExprVhdl("(CLK'event) and (CLK='1')")));
        {
            Assignment(pstate, "nstate");
        }
        EndIf();

        EndProcess();
////////////////////////////////////////////////////////////////////////////////
        // Output logic
        Process("pstatereg", new String[]{"pstate"});

        Assignment(y, 0);

        Cases(pstate);

        When("S10");
        Assignment(y, 1);
        
        WhenOthers();
        EndWhens();

        EndCases();

        EndProcess();
////////////////////////////////////////////////////////////////////////////////
        // Output logic
        Process("pstatereg", new String[]{"pstate, A"});

        Assignment(nstate, "SXX");

        Cases(pstate);

        When("SXX");
        If(new ConditionVhdl(new ExprVhdl("A = '1'")));
        Assignment(nstate, "SX1");
        EndIf();

        When("SX1");
        Assignment(nstate, "SX1");
        If(new ConditionVhdl(new ExprVhdl("A = '0'")));
        Assignment(nstate, "S10");
        EndIf();

        When("S10");
        Assignment(nstate, "S10");
        If(new ConditionVhdl(new ExprVhdl("A = '0'")));
        Assignment(nstate, "SXX");
        EndIf();

        WhenOthers();      
        EndWhens();

        EndCases();

        EndProcess();
    }

    /**
     * Runs example.
     *
     * @param args
     *
     * @throws InvalidVhdlTypeException
     * @throws SyntaxErrorVhdl
     */
    public static void main(String[] args) throws InvalidVhdlTypeException, SyntaxErrorVhdl, Exception {
        VhdlMooreExample myVhdlCode = new VhdlMooreExample();
        System.out.println(myVhdlCode.toString());
    }

    /**
     * Auxiliary method for testing.
     *
     * @return Example of vhdl code
     * 
     * @throws InvalidVhdlTypeException
     * @throws SyntaxErrorVhdl
     */
    public VHDL createVhdl() throws InvalidVhdlTypeException, SyntaxErrorVhdl, Exception {
        return new VhdlMooreExample();
    }
}
