/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jvhdl;

import cz.jvhdl.examples.VhdlMux4_1Example;
import cz.jvhdl.exception.InvalidVhdlTypeException;
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
public class ArchitectureVhdlTest {

    public ArchitectureVhdlTest() {
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

    /**
     * Test of toString method, of class ArchitectureVhdl.
     */
    @Test
    public void testArchitectureMux() throws SyntaxErrorVhdl, InvalidVhdlTypeException, Exception {
        System.out.println("Mux");

        VhdlMux4_1Example e = new VhdlMux4_1Example();

        e.ifExample();

        String excepted = "architecture if_example of mux4_1 is\n"
                + "begin\n"
                + "  -- process mux\n"
                + "  mux: process(s0, s1, in0, in1, in2, in3)\n"
                + "  begin\n"
                + "    if (s0='0' AND s1='0') then\n"
                + "      output <= in0;\n"
                + "    elsif (s0='1' AND s1='0') then\n"
                + "      output <= in1;\n"
                + "    elsif (s0='0' AND s1='1') then\n"
                + "      output <= in2;\n"
                + "    elsif (s0='1' AND s1='1') then\n"
                + "      output <= in3;\n"
                + "    else\n"
                + "      output <= 'X';\n"
                + "    end if;\n"
                + "  end process;\n"
                + "end if_example;";

        TestFunc.diff(excepted, e.toString());

    }
}
