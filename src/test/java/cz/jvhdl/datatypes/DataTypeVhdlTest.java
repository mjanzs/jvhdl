/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jvhdl.datatypes;

import cz.jvhdl.TestFunc;
import cz.jvhdl.examples.VhdlTypesExample;
import cz.jvhdl.exception.InvalidVhdlTypeException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Martin
 */
public class DataTypeVhdlTest {

    public DataTypeVhdlTest() {
    }

    @BeforeClass
    public static void setUpClass() {
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

    @Test
    public void testBasicTypes() throws InvalidVhdlTypeException {
        System.out.println("basicTypes");

        String excepted = "-- -- -- Declaration -- -- --\n"
                + "\n"
                + "type FSMstate is (SXX, SX1, S10);\n"
                + "signal pstate : FSMstate;\n"
                + "variable pstate : FSMstate;";

        VhdlTypesExample types = new VhdlTypesExample();
        types.basicTypes();
        
        TestFunc.diff(excepted, types.toString());
    }

    @Test
    public void testScalarTypes() throws InvalidVhdlTypeException {
        System.out.println("scalarTypes");

        String excepted = "-- -- -- Declaration -- -- --\n"
                + "\n"
                + "type my_small is range -5 to 5;\n"
                + "type my_bits is range 31 downto 0;\n"
                + "type my_float is range 1 downto 1000000;";

        VhdlTypesExample types = new VhdlTypesExample();
        types.scalarTypes();
        
        TestFunc.diff(excepted, types.toString());
    }

    @Test
    public void testArrayTypes() throws InvalidVhdlTypeException {
        System.out.println("arrayTypes");
        
        String excepted = "-- -- -- Declaration -- -- --\n"
                + "\n"
                + "type word is array (0 to 31) of bit;\n"
                + "type data is array (7 to 0) of word;\n";
//                + "type mem is array (natural range <>) of real;";

        VhdlTypesExample types = new VhdlTypesExample();
        types.arrayTypes();

        TestFunc.diff(excepted, types.toString());
    }
}
