/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jvhdl;

import cz.jvhdl.datatypes.ArrayVhdl;
import cz.jvhdl.datatypes.DataTypeVhdl;
import cz.jvhdl.exception.InvalidVhdlTypeException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static cz.jvhdl.datatypes.DataTypeVhdl.DataUnit.*;
import static cz.jvhdl.datatypes.DataTypeVhdl.Direction.*;
import cz.jvhdl.datatypes.SignalVhdl;
import cz.jvhdl.datatypes.VariableVhdl;
import cz.jvhdl.datatypes.std.StdLogicVectorVhdl;
import cz.jvhdl.exception.SyntaxErrorVhdl;

/**
 *
 * @author Martin
 */
public class LocalVariableTest {

    public LocalVariableTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        ObjectVhdl.indent = "  ";
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of abstractTreePeek method, of class BlockCodeRecursiveVhdl.
     */
    @Test
    public void testLocalVariables() throws InvalidVhdlTypeException, SyntaxErrorVhdl, Exception {
        System.out.println("LocalVar");

        String excepted = "-- -- -- Declaration -- -- --\n"
                + "\n"
                + "type data is array (7 to 0) of word;\n"
                + "variable sel : STD_LOGIC_VECTOR(1 DOWNTO 0);\n"
                + "\n"
                + "architecture a of x is\n"
                + "\n"
                + "  type data is array (7 to 0) of word;\n"
                + "  variable sel : STD_LOGIC_VECTOR(1 DOWNTO 0);\n"
                + "\n"
                + "begin\n"
                + "  -- process p\n"
                + "  p: process()\n"
                + "\n"
                + "    type data is array (7 to 0) of word;\n"
                + "    variable sel : STD_LOGIC_VECTOR(1 DOWNTO 0);\n"
                + "    signal s : STD_LOGIC_VECTOR(1 DOWNTO 0);\n"
                + "\n"
                + "  begin\n"
                + "    sel := \"00\";\n"
                + "    s <= \"00\";\n"
                + "    s <= \"00\";\n"
                + "    sel := \"00\";\n"
                + "  end process;\n"
                + "end a;";


        TestFunc.diff(excepted, new LocalVariable().toString());
    }
}

class LocalVariable extends VHDL {

    public LocalVariable() throws InvalidVhdlTypeException, SyntaxErrorVhdl, Exception {

        Array(new ArrayVhdl("data", 7, 0, DOWNTO, WORD));
        Variable("sel", new StdLogicVectorVhdl(1, 0, DataTypeVhdl.Direction.DOWNTO));

        Architecture("a", "x");

        Array(new ArrayVhdl("data", 7, 0, DOWNTO, WORD));
        Variable("sel", new StdLogicVectorVhdl(1, 0, DataTypeVhdl.Direction.DOWNTO));

        Process("p");

        Array(new ArrayVhdl("data", 7, 0, DOWNTO, WORD));
        VariableVhdl sel = Variable("sel", new StdLogicVectorVhdl(1, 0, DataTypeVhdl.Direction.DOWNTO));
        SignalVhdl s = Signal("s", new StdLogicVectorVhdl(1, 0, DataTypeVhdl.Direction.DOWNTO));

        Assignment(sel, "\"00\"");
        Assignment(s, "\"00\"");
        Assignment(s, "\"00\"");
        Assignment(sel, "\"00\"");

        EndProcess();

        EndArchitecture();
    }
}
