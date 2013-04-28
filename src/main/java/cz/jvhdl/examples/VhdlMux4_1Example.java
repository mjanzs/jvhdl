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
import cz.jvhdl.datatypes.std.StdLogicVhdl;
import cz.jvhdl.datatypes.std.StdLogicVectorVhdl;
import cz.jvhdl.datatypes.VariableVhdl;
import cz.jvhdl.datatypes.SignalVhdl;
import cz.jvhdl.VHDL;
import cz.jvhdl.ConditionVhdl;
import cz.jvhdl.EntityVhdl;
import cz.jvhdl.ExprVhdl;
import cz.jvhdl.datatypes.DataTypeVhdl.Direction;
import static cz.jvhdl.datatypes.DataTypeVhdl.Type.*;
/**
 * @author Martin Janyš
 */
public class VhdlMux4_1Example extends VHDL {

    /**
     *
     */
    public VhdlMux4_1Example() {
    }

    /**
     * <pre>
     * ENTITY mux4_1 IS 
     *  PORT (
     *      s0 : IN STD_LOGIC; 
     *      s1 : IN STD_LOGIC; 
     *      in0 : IN STD_LOGIC; 
     *      in1 : IN STD_LOGIC; 
     *      in2 : IN STD_LOGIC; 
     *      in3 : IN STD_LOGIC;
     *      output : OUT STD_LOGIC 
     *  ); 
     * END mux4_1;
     * </pre>
     */
    public void entityExample() {
        EntityVhdl e = Entity("mux4_1");
        
        e.put("s0", new StdLogicVhdl(IN));
        e.put("s1", new StdLogicVhdl(IN));
        e.put("in0", new StdLogicVhdl(IN));
        e.put("in1", new StdLogicVhdl(IN));
        e.put("in2", new StdLogicVhdl(IN));
        e.put("in3", new StdLogicVhdl(IN));
        e.put("output", new StdLogicVhdl(OUT));
        
    }

    /**
     * <pre>
     * architecture if_example of mux4_1 is
     *
     * begin
     *
     *  -- process mux
     *  mux: process(s0, s1, in0, in1, in2, in3)
     *  begin
     *
     *  if (s0='0' AND s1='0') then
     *      output <= in0;
     *  elsif (s0='1' AND s1='0') then
     *      output <= in1;
     *  elsif (s0='0' AND s1='1') then
     *      output <= in2;
     *  elsif (s0='1' AND s1='1') then
     *      output <= in3;
     *  else          -- (s0 or s1 are not 0 or 1)
     *      output <= 'X';
     *  end if;
     *
     *  end process mux;
     *
     * end if_example;
     *
     * </pre>
     * @throws SyntaxErrorVhdl 
     * @throws InvalidVhdlTypeException 
     */
    public void ifExample() throws SyntaxErrorVhdl, InvalidVhdlTypeException, Exception {
        
        Architecture("if_example", "mux4_1");
        {
            Process("mux", new String[]{"s0", "s1", "in0", "in1", "in2", "in3"});
            {
                SignalVhdl output = new SignalVhdl("output", new StdLogicVectorVhdl(0, 3, Direction.TO));
                
                If(new ConditionVhdl(new ExprVhdl("s0='0' AND s1='0'")));
                {
                    Assignment(output, "in0");
                }
                Elseif(new ConditionVhdl(new ExprVhdl("s0='1' AND s1='0'")));
                {
                    Assignment(output, "in1");
                }
                Elseif(new ConditionVhdl(new ExprVhdl("s0='0' AND s1='1'")));
                {
                    Assignment(output, "in2");
                }
                Elseif(new ConditionVhdl(new ExprVhdl("s0='1' AND s1='1'")));
                {
                    Assignment(output, "in3");
                }
                Else();
                {
                    Assignment(output, "'X'");
                }
                EndIf();
            }
            EndProcess();
        }
        EndArchitecture();
    }

    /**
     * <pre>
     * ARCHITECTURE case_example OF mux4_1 IS
     *
     *  BEGIN
     *  mux:PROCESS(s0, s1, in0, in1, in2, in3)
     *      VARIABLE sel : STD_LOGIC_VECTOR(1 DOWNTO 0);
     *      BEGIN sel := s1 & s0; -- concatenate s1 and s0
     *          CASE sel IS
     *              WHEN "00" =>
     *                  output <= in0;
     *              WHEN "01" =>
     *                  output <= in1;
     *              WHEN "10" =>
     *                  output <= in2;
     *              WHEN "11" =>
     *                  output <= in3;
     *              WHEN OTHERS =>
     *                  output <= 'X'; END CASE;
     *  END PROCESS mux;
     *
     * END case_example;
     * </pre>
     * @throws SyntaxErrorVhdl
     * @throws InvalidVhdlTypeException  
     */
    public void caseExample() throws SyntaxErrorVhdl, InvalidVhdlTypeException, Exception {
        Architecture("case_example", "mux4_1");
        {
            Process("mux", new String[]{"s0", "s1", "in0", "in1", "in2", "in3"});
            {
                SignalVhdl output = new SignalVhdl("output", new StdLogicVectorVhdl(0, 3, Direction.TO));
                
                VariableVhdl var =
                        Variable("sel", new StdLogicVectorVhdl(1, 0, Direction.DOWNTO));
                Cases(var);
                {
                    When("00");
                    {
                        Assignment(output, "in0");
                    }
                    When("01");
                    {
                        Assignment(output, "in1");
                    }
                    When("10");
                    {
                        Assignment(output, "in2");
                    }
                    When("00");
                    {
                        Assignment(output, "in3");
                    }
                    When("others");
                    {
                        Assignment(output, "'X'");
                    }
                    EndWhens();
                }
                EndCases();
            }
            EndProcess();
        }
        EndArchitecture();

    }

    /**
     *
     */
    public void withExample() {
        
    }
    
    /**
     *
     */
    public void whenExample() {
        
    }
    
    /**
     *
     * @param args
     * @throws SyntaxErrorVhdl
     * @throws InvalidVhdlTypeException
     */
    public static void main(String[] args) throws SyntaxErrorVhdl, InvalidVhdlTypeException {
        VhdlMux4_1Example e = new VhdlMux4_1Example();

        e.entityExample();

        System.out.println(e);
    }
}
