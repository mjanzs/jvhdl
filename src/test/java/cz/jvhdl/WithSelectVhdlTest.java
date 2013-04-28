/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jvhdl;

import cz.jvhdl.ExprVhdl;
import cz.jvhdl.WithSelectVhdl;
import cz.jvhdl.datatypes.DataTypeVhdl;
import cz.jvhdl.datatypes.SignalVhdl;
import cz.jvhdl.datatypes.std.StdLogicVhdl;
import cz.jvhdl.exception.SyntaxErrorVhdl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin
 */
public class WithSelectVhdlTest {

    public WithSelectVhdlTest() {
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
    public void test() throws SyntaxErrorVhdl {

        SignalVhdl Y = new SignalVhdl("Y", new StdLogicVhdl(DataTypeVhdl.Type.IN));

        WithSelectVhdl s = new WithSelectVhdl(Y, new WithSelectVhdl.Whens() {
            @Override
            public void define() {
                def("ST0", new ExprVhdl("'0'"));
                def("ST1", new ExprVhdl("'1'"));
                def("others", new ExprVhdl("'0'"));
            }
        }, null);

        WithSelectVhdl s2 = new WithSelectVhdl(Y, new WithSelectVhdl.Whens() {
            @Override
            public void define() {
                def("ST2", new ExprVhdl("'1'"));
                def("ST3", new ExprVhdl("'0'"));
                def("others", new ExprVhdl("'1'"));
            }
        }, null);

        String expected_s = "Y <= \n"
                + "  '0' when ST0,\n"
                + "  '1' when ST1,\n"
                + "  '0' when others;";
        String expected_s2 = "Y <= \n"
                + "  '1' when ST2,\n"
                + "  '0' when ST3,\n"
                + "  '1' when others;";

        TestFunc.diff(s.toString(), expected_s);
        TestFunc.diff(s2.toString(), expected_s2);

    }
}