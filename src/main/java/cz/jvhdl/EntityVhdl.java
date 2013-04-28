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
package cz.jvhdl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import cz.jvhdl.datatypes.DataTypeVhdl;
import cz.jvhdl.datatypes.std.StdLogicVhdl;

/**
 * Vhdl entity.
 * 
 * Syntax:
 * <pre>
 * entity is <entity_name>
 * [
 * <signal_name>: in|out|inout type; ] end entity
 * </pre>
 *
 * @author Martin Janyš
 */
public class EntityVhdl extends BlockCodeVhdl
        implements Iterable<String> {

    protected Map<String, DataTypeVhdl> values = null;
    private String id = null;
    private boolean clk = false;
    private boolean rst = false;

    /**
     * Creates entity block.
     *
     * @param id Identificator of etity
     */
    public EntityVhdl(String id) {
        this.id = VHDL.outputString(id);
        this.values = new LinkedHashMap<>();
    }

    /**
     * Creates entity block.
     *
     * @param id Identificator of etity
     * @param values Values of entity ports
     */
    @Deprecated
    public EntityVhdl(String id, Map<String, DataTypeVhdl> values) {
        this.id = id;
        this.values = values;
    }

    /**
     * Adds entity port definition.
     *
     * @param key Port name
     * @param value Port datatype
     */
    public void put(String key, DataTypeVhdl value) {
        if (VHDL.keyWords.contains(key)) {
            key = key + VHDL.kwAppedix;
        }
        values.put(key, value);
    }

    /**
     * Returns datatype of port.
     *
     * @param key Datatype name
     *
     * @return Data type of port
     */
    public DataTypeVhdl get(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("entity ").append(id).append(" is\n");
        sb.append(indent).append("port (\n");

        // Ports
        for (Map.Entry<String, DataTypeVhdl> entry : values.entrySet()) {
            String s = entry.getKey();
            if (!s.isEmpty()) {
                DataTypeVhdl logic = entry.getValue();

                sb.append(indent).append(indent);
                sb.append(s).append(" : ").append(logic).append(";\n");
            }
        }

        if (!values.isEmpty()) {
            int index = sb.lastIndexOf(";");
            if (index > 0) {
                sb.deleteCharAt(index);
            }
        }

        sb.append(indent).append(");\n");
        sb.append("end ").append(id).append(";\n");
        return sb.toString();

    }

    public String getId() {
        return id;
    }

    @Override
    public Iterator<String> iterator() {
        return values.keySet().iterator();
    }

    public void setClk(boolean clk) {
        this.clk = clk;
        if (clk) {
            values.put(VHDL.CLK, new StdLogicVhdl(DataTypeVhdl.Type.IN));
        }
        else {
            values.remove(VHDL.CLK);
        }
    }

    public void setRst(boolean rst) {
        this.rst = rst;
        if (rst) {
            values.put(VHDL.RST, new StdLogicVhdl(DataTypeVhdl.Type.IN));
        }
        else {
            values.remove(VHDL.RST);
        }
    }
}
