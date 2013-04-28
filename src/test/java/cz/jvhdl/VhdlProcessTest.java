/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jvhdl;

import cz.jvhdl.datatypes.EnumVhdl;
import cz.jvhdl.datatypes.VariableVhdl;
import cz.jvhdl.datatypes.SignalVhdl;
import cz.jvhdl.VHDL;
import cz.jvhdl.ExprVhdl;
import cz.jvhdl.ConditionVhdl;
import cz.jvhdl.ProcessVhdl;
import cz.jvhdl.datatypes.std.StdLogicVhdl;
import cz.jvhdl.examples.VhdlMooreExample;
import cz.jvhdl.exception.InvalidVhdlTypeException;
import cz.jvhdl.exception.SyntaxErrorVhdl;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Martin
 */
public class VhdlProcessTest {

    public VhdlProcessTest() {
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
     * TestFunc of setId and getId methods, of class VhdlProcess.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "";
        ProcessVhdl instance = this.getVhdlProcess();
        instance.setId(id);

        assertEquals(id, instance.getId());
    }

    /**
     * TestFunc of add method, of class VhdlProcess.
     */
    @Test
    public void testAdd_VhdlElement_If1() {
        System.out.println("add(VhdlElement if 1)");
        ProcessVhdl instance = this.getVhdlProcess();
        instance.if_(new ConditionVhdl(new ExprVhdl("1 + 1")));
        instance.endif();
        String excepted =
                "-- process p\n"
                + "p: process(S1, S2)\n"
                + "begin\n"
                + "  if (1 + 1) then\n"
                + "\n"
                + "  end if;\n"
                + "end process;";

        TestFunc.diff(excepted, instance.toString());
    }

    /**
     * TestFunc of add method, of class VhdlProcess.
     */
    @Test
    public void testAdd_VhdlElement_If2() {
        System.out.println("add(VhdlElement if 2)");
        ProcessVhdl instance = this.getVhdlProcess();
        instance.if_(new ConditionVhdl(new ExprVhdl("1 + 1")));
        instance.if_(new ConditionVhdl(new ExprVhdl("1 + 1")));
        instance.if_(new ConditionVhdl(new ExprVhdl("1 + 1")));
        instance.endif();
        instance.endif();
        instance.endif();
        String excepted =
                "-- process p\n"
                + "p: process(S1, S2)\n"
                + "begin\n"
                + "  if (1 + 1) then\n"
                + "    if (1 + 1) then\n"
                + "      if (1 + 1) then\n"
                + "\n"
                + "      end if;\n"
                + "    end if;\n"
                + "  end if;\n"
                + "end process;";
        TestFunc.diff(excepted, instance.toString());
    }

    /**
     * TestFunc of add method, of class VhdlProcess.
     */
    @Test
    public void testAdd_VhdlElement_If3() {
        System.out.println("add(VhdlElement if 3)");
        ProcessVhdl instance = this.getVhdlProcess();

        SignalVhdl x1 = new SignalVhdl("x1", new StdLogicVhdl(StdLogicVhdl.Type.IN));
        SignalVhdl x2 = new SignalVhdl("x2", new StdLogicVhdl(StdLogicVhdl.Type.IN));
        SignalVhdl x3 = new SignalVhdl("x3", new StdLogicVhdl(StdLogicVhdl.Type.IN));
        SignalVhdl x4 = new SignalVhdl("x4", new StdLogicVhdl(StdLogicVhdl.Type.IN));
        SignalVhdl x5 = new SignalVhdl("x5", new StdLogicVhdl(StdLogicVhdl.Type.IN));
        SignalVhdl x6 = new SignalVhdl("x6", new StdLogicVhdl(StdLogicVhdl.Type.IN));

        instance.if_(new ConditionVhdl(new ExprVhdl("1 + 1")));
        instance.assignment(x1, "y1");
        instance.if_(new ConditionVhdl(new ExprVhdl("2 + 2")));
        instance.assignment(x2, "y2");
        instance.elseif(new ConditionVhdl(new ExprVhdl("3 + 3")));
        instance.assignment(x3, "y3");
        instance.else_();
        instance.assignment(x4, "y4");
        instance.endif();
        instance.if_(new ConditionVhdl(new ExprVhdl("4 + 4")));
        instance.assignment(x5, "y5");
        instance.endif();
        instance.assignment(x6, "y6");
        instance.endif();
        String excepted =
                "-- process p\n"
                + "p: process(S1, S2)\n"
                + "begin\n"
                + "  if (1 + 1) then\n"
                + "    x1 <= y1;\n"
                + "    if (2 + 2) then\n"
                + "      x2 <= y2;\n"
                + "    elsif (3 + 3) then\n"
                + "      x3 <= y3;\n"
                + "    else\n"
                + "      x4 <= y4;\n"
                + "    end if;\n"
                + "    if (4 + 4) then\n"
                + "      x5 <= y5;\n"
                + "    end if;\n"
                + "    x6 <= y6;\n"
                + "  end if;\n"
                + "end process;";

        TestFunc.diff(excepted, instance.toString());
    }

    /**
     * TestFunc of assignment method, of class VhdlProcess.
     */
    @Test
    public void testAssignment_String_String() {
        System.out.println("assignment");

        SignalVhdl lvalue = new SignalVhdl("a", new StdLogicVhdl(StdLogicVhdl.Type.IN));

        String rvalue = "b";
        ProcessVhdl instance = this.getVhdlProcess();
        instance.assignment(lvalue, rvalue);
        String excepted =
                "-- process p\n"
                + "p: process(S1, S2)\n"
                + "begin\n"
                + "  a <= b;\n"
                + "end process;";

        TestFunc.diff(excepted, instance.toString());
    }

    /**
     * TestFunc of assignment method, of class VhdlProcess.
     */
    @Test
    public void testAssignment_String_int() throws Exception {
        System.out.println("assignment");

        SignalVhdl lvalue = new SignalVhdl("a", new StdLogicVhdl(StdLogicVhdl.Type.IN));

        int rvalue = 1;
        ProcessVhdl instance = this.getVhdlProcess();
        instance.assignment(lvalue, rvalue);

        String excepted =
                "-- process p\n"
                + "p: process(S1, S2)\n"
                + "begin\n"
                + "  a <= '1';\n"
                + "end process;";

        TestFunc.diff(excepted, instance.toString());
    }

    /**
     * TestFunc of when method, of class VhdlProcess.
     */
    @Test
    public void testSwitch_() throws InvalidVhdlTypeException, SyntaxErrorVhdl {
        System.out.println("cases");
        SignalVhdl pstate = new SignalVhdl("pstate", new EnumVhdl("FSMstate", "SXX, SX1, S10"));
        ProcessVhdl output_logic = new ProcessVhdl("pstatereg", new String[]{"pstate"});

        SignalVhdl Y = new SignalVhdl("Y", new StdLogicVhdl(StdLogicVhdl.Type.IN));

        output_logic.assignment(Y, 0);

        output_logic.cases(pstate);

        output_logic.when("S10");
        output_logic.assignment(Y, 1);
        output_logic.endWhenWithOthers();
        output_logic.endCases();

        output_logic.end();

        String excepted = "-- process pstatereg\n"
                + "pstatereg: process(pstate)\n"
                + "begin\n"
                + "  Y <= '0';\n"
                + "  case pstate is\n"
                + "    when S10 =>\n"
                + "      Y <= '1';\n"
                + "    when others =>\n"
                + "      null;\n"
                + "  end case;\n"
                + "end process;";

        TestFunc.diff(excepted, output_logic.toString());
    }

    /**
     * TestFunc of when method, of class VhdlProcess.
     */
    @Test
    public void testSwitch_1() throws InvalidVhdlTypeException, SyntaxErrorVhdl {
        System.out.println("cases1");
        VariableVhdl pstate = new VariableVhdl("pstate", new EnumVhdl("FSMstate", "SXX, SX1, S10"));
        ProcessVhdl instance = new ProcessVhdl("pstatereg", new String[]{"pstate"});

        SignalVhdl Y = new SignalVhdl("Y", new StdLogicVhdl(StdLogicVhdl.Type.IN));
        
        SignalVhdl x = new SignalVhdl("x", new StdLogicVhdl(StdLogicVhdl.Type.IN));
        SignalVhdl x1 = new SignalVhdl("x1", new StdLogicVhdl(StdLogicVhdl.Type.IN));
        SignalVhdl x2 = new SignalVhdl("x2", new StdLogicVhdl(StdLogicVhdl.Type.IN));
        SignalVhdl x3 = new SignalVhdl("x3", new StdLogicVhdl(StdLogicVhdl.Type.IN));
        SignalVhdl x4 = new SignalVhdl("x4", new StdLogicVhdl(StdLogicVhdl.Type.IN));
        SignalVhdl x5 = new SignalVhdl("x5", new StdLogicVhdl(StdLogicVhdl.Type.IN));
        SignalVhdl x6 = new SignalVhdl("x6", new StdLogicVhdl(StdLogicVhdl.Type.IN));
        
        instance.assignment(Y, 0);

        instance.cases(pstate);

        instance.when("S10");
        instance.assignment(Y, 1);
        instance.if_(new ConditionVhdl(new ExprVhdl("1 + 1")));
        instance.assignment(x1, "y1");
        instance.if_(new ConditionVhdl(new ExprVhdl("2 + 2")));
        instance.assignment(x2, "y2");
        instance.elseif(new ConditionVhdl(new ExprVhdl("3 + 3")));
        instance.assignment(x3, "y3");
        instance.else_();
        instance.assignment(x4, "y4");
        instance.endif();
        instance.if_(new ConditionVhdl(new ExprVhdl("4 + 4")));
        instance.assignment(x5, "y5");
        instance.endif();
        instance.assignment(x6, "y6");
        instance.endif();

        instance.when("S0");
        instance.assignment(x, "y");
        instance.if_(new ConditionVhdl(new ExprVhdl("1 + 1")));
        instance.if_(new ConditionVhdl(new ExprVhdl("1 + 1")));
        instance.endif();
        instance.endif();
        instance.assignment(x, "y");
        instance.endWhenWithOthers();
        instance.endCases();

        instance.end();

        String excepted = "-- process pstatereg\n"
                + "pstatereg: process(pstate)\n"
                + "begin\n"
                + "  Y <= '0';\n"
                + "  case pstate is\n"
                + "    when S10 =>\n"
                + "      Y <= '1';\n"
                + "      if (1 + 1) then\n"
                + "        x1 <= y1;\n"
                + "        if (2 + 2) then\n"
                + "          x2 <= y2;\n"
                + "        elsif (3 + 3) then\n"
                + "          x3 <= y3;\n"
                + "        else\n"
                + "          x4 <= y4;\n"
                + "        end if;\n"
                + "        if (4 + 4) then\n"
                + "          x5 <= y5;\n"
                + "        end if;\n"
                + "        x6 <= y6;\n"
                + "      end if;\n"
                + "    when S0 =>\n"
                + "      x <= y;\n"
                + "      if (1 + 1) then\n"
                + "        if (1 + 1) then\n"
                + "\n"
                + "        end if;\n"
                + "      end if;\n"
                + "      x <= y;\n"
                + "    when others =>\n"
                + "      null;\n"
                + "  end case;\n"
                + "end process;";
//        System.out.println(instance);
        TestFunc.diff(excepted, instance.toString());
    }

    @Test
    public void testMooreExample() throws InvalidVhdlTypeException, SyntaxErrorVhdl, Exception {
        System.out.println("MooreExample");
        VhdlMooreExample moore = new VhdlMooreExample();
        String excepted =
                "-- -- -- Declaration -- -- --\n"
                + "\n"
                + "type FSMstate is (SXX, SX1, S10);\n"
                + "signal pstate : FSMstate;\n"
                + "signal nstate : FSMstate;\n"
                + "\n"
                + "-- process pstatereg\n"
                + "pstatereg: process(RST, CLK)\n"
                + "begin\n"
                + "  if (RST = 1) then\n"
                + "    pstate <= SXX;\n"
                + "  elsif ((CLK'event) and (CLK='1')) then\n"
                + "    pstate <= nstate;\n"
                + "  end if;\n"
                + "end process;\n"
                + "\n"
                + "-- process pstatereg\n"
                + "pstatereg: process(pstate)\n"
                + "begin\n"
                + "  Y <= '0';\n"
                + "  case pstate is\n"
                + "    when S10 =>\n"
                + "      Y <= '1';\n"
                + "    when others =>\n"
                + "      null;\n"
                + "  end case;\n"
                + "end process;\n"
                + "\n"
                + "-- process pstatereg\n"
                + "pstatereg: process(pstate, A)\n"
                + "begin\n"
                + "  nstate <= SXX;\n"
                + "  case pstate is\n"
                + "    when SXX =>\n"
                + "      if (A = '1') then\n"
                + "        nstate <= SX1;\n"
                + "      end if;\n"
                + "    when SX1 =>\n"
                + "      nstate <= SX1;\n"
                + "      if (A = '0') then\n"
                + "        nstate <= S10;\n"
                + "      end if;\n"
                + "    when S10 =>\n"
                + "      nstate <= S10;\n"
                + "      if (A = '0') then\n"
                + "        nstate <= SXX;\n"
                + "      end if;\n"
                + "    when others =>\n"
                + "      null;\n"
                + "  end case;\n"
                + "end process;";

        VHDL vhdl = moore.createVhdl();

        TestFunc.diff(excepted, vhdl.toString());
    }

    private ProcessVhdl getVhdlProcess() {
        return new ProcessVhdl("p", new String[]{"S1", "S2"});
    }
}
