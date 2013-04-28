/**
 * VYSOKÉ UČENÍ TECHNICKÉ V BRNĚ BRNO UNIVERSITY OF TECHNOLOGY
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
package cz.jvhdl;

import java.util.Map;
import java.util.TreeMap;
import cz.jvhdl.datatypes.VariableVhdl;
import cz.jvhdl.exception.SyntaxErrorVhdl;

/**
 * With select VHDL combinational structure.
 * 
 * Vhdl example:
 * <pre>
 * with PS select
 *  Y <= '0' when ST0,
 *       '1' when ST1,
 *       '0' when others;
 * </pre>
 *
 * @author Martin Janyš
 */
public class WithSelectVhdl extends AssignmentVhdl {

    public static abstract class Whens {

        public Whens() throws SyntaxErrorVhdl {
            this.define();

            if (values.isEmpty()) {
                throw new SyntaxErrorVhdl("Missing definition of conditions in WithSelect");
            }
        }
        /**
         * String - condition ExprVhdl - value to assigment
         */
        public Map<String, ExprVhdl> values = new TreeMap<>();

        public abstract void define();

        public void def(String s, ExprVhdl e) {
            this.values.put(s, e);
        }

        @Override
        public String toString() {

            StringBuilder sb = new StringBuilder();

            if (!values.isEmpty()) {
                sb.append("\n");
                for (Map.Entry<String, ExprVhdl> entry : values.entrySet()) {
                    String string = entry.getKey();
                    ExprVhdl exprVhdl = entry.getValue();
                    sb.append(exprVhdl.indent);
                    sb.append(exprVhdl.toString()).append(" when ").append(string);
                    sb.append(",\n");
                }

                int index = sb.lastIndexOf(",\n");

                sb.replace(index, index + 2, "");
            }

            return sb.toString();
        }
    }

    public WithSelectVhdl(VariableVhdl lvalue, Whens rvalue, ObjectVhdl parent) {
        super(lvalue, rvalue.toString(), parent);

    }
}
