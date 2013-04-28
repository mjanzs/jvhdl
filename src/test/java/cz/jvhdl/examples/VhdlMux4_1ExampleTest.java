/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jvhdl.examples;

import cz.jvhdl.TestFunc;
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
public class VhdlMux4_1ExampleTest {

    public VhdlMux4_1ExampleTest() {
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
     * Test of entityExample method, of class VhdlMux4_1Example.
     */
    @Test
    public void testEntityExample() {
        System.out.println("entityExample");
        VhdlMux4_1Example instance = new VhdlMux4_1Example();
        instance.entityExample();

        String excepted = "entity mux4_1 is\n"
                + "  port (\n"
                + "    s0 : IN STD_LOGIC;\n"
                + "    s1 : IN STD_LOGIC;\n"
                + "    in0 : IN STD_LOGIC;\n"
                + "    in1 : IN STD_LOGIC;\n"
                + "    in2 : IN STD_LOGIC;\n"
                + "    in3 : IN STD_LOGIC;\n"
                + "    output : OUT STD_LOGIC\n"
                + "  );\n"
                + "end mux4_1;";

        TestFunc.diff(excepted, instance.toString());
    }

    @Test
    public void testComponentExample() {
        System.out.println("portMapExample");
        ComponentExample instance = new ComponentExample();

        
        String excepted = "  ENTITY_U:   entity\n"
                + "  port map (\n"
                + "    map1 => MAP1,\n"
                + "    map2 => MAP2,\n"
                + "    map3 => MAP3\n"
                + "  );";
        TestFunc.diff(excepted, instance.toString());
    }

    /**
     * Test of ifExample method, of class VhdlMux4_1Example.
     */
    @Test
    public void testIfExample() throws Exception {
        System.out.println("ifExample");
        VhdlMux4_1Example instance = new VhdlMux4_1Example();
        instance.ifExample();

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

        TestFunc.diff(excepted, instance.toString());
    }

    /**
     * Test of caseExample method, of class VhdlMux4_1Example.
     */
    @Test
    public void testCaseExample() throws Exception {
        System.out.println("caseExample");
        VhdlMux4_1Example instance = new VhdlMux4_1Example();
        instance.caseExample();

        String excepted = "architecture case_example of mux4_1 is\n"
                + "begin\n"
                + "  -- process mux\n"
                + "  mux: process(s0, s1, in0, in1, in2, in3)\n"
                + "\n"
                + "    variable sel : STD_LOGIC_VECTOR(1 DOWNTO 0);\n"
                + "\n"
                + "  begin\n"
                + "    case sel is\n"
                + "      when 00 =>\n"
                + "        output <= in0;\n"
                + "      when 01 =>\n"
                + "        output <= in1;\n"
                + "      when 10 =>\n"
                + "        output <= in2;\n"
                + "      when 00 =>\n"
                + "        output <= in3;\n"
                + "      when others =>\n"
                + "        output <= 'X';\n"
                + "    end case;\n"
                + "  end process;\n"
                + "end case_example;";

        TestFunc.diff(excepted, instance.toString());
    }

    /**
     * Test of withExample method, of class VhdlMux4_1Example.
     */
    @Test
    public void testWithExample() {
        System.out.println("withExample");
        VhdlMux4_1Example instance = new VhdlMux4_1Example();
        instance.withExample();

        String excepted = "";

        System.out.println(instance);

        TestFunc.diff(excepted, instance.toString());
    }

    /**
     * Test of whenExample method, of class VhdlMux4_1Example.
     */
    @Test
    public void testWhenExample() {
        System.out.println("whenExample");
        VhdlMux4_1Example instance = new VhdlMux4_1Example();
        instance.whenExample();

        String excepted = "";

        System.out.println(instance);

        TestFunc.diff(excepted, instance.toString());
    }
}
