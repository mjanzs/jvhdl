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

/**
 *
 * VHDL component class.
 * 
 * Syntax:
 * <pre>
 * <component_identifier>: <component_name>
 * port map( port_name => assigned_signal, ... )
 * </pre>
 *
 * @author Martin Janyš
 */
public class ComponentVhdl extends EntityVhdl {

    private final EntityVhdl entity;
    private final Map<String, String> values;
    private final boolean standalone;

    /**
     *
     * @param entity Entity whitch is mapped
     * @param standalone Boolean flag. If is true output will be with import of
     * entity from work directory.
     */
    public ComponentVhdl(EntityVhdl entity, boolean standalone) {
        super("");
        this.entity = entity;
        this.values = new TreeMap<>();
        this.standalone = standalone;
    }

    /**
     *
     * @param entity Entity whitch is mapped
     * @param values Values of mapping component
     * @param standalone Boolean flag. If is true output will be with import of
     * entity from work directory.
     * @deprecated
     */
    @Deprecated
    public ComponentVhdl(EntityVhdl entity, Map<String, String> values, boolean standalone) {
        super("", null);
        this.entity = entity;
        this.values = values;
        this.standalone = standalone;
    }

    /**
     * Returns VHDL output string.
     *
     * @return VHDL output string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(entity.getId().toUpperCase()).append("_U");
        sb.append(": ");

        if (standalone) {
            sb.append("entity ")
                    .append("work.")
                    .append(entity.getId())
                    .append("\n");
        }
        else {
            sb.append(indent()).append(entity.getId()).append("\n");
        }

        sb.append(indent()).append("port map (\n");
        // Map
        for (Map.Entry<String, String> entry : values.entrySet()) {
            String s = entry.getKey();
            if (!s.isEmpty()) {
                String logic = entry.getValue();

                sb.append(indent()).append(indent);
                sb.append(s).append(" => ").append(logic).append(",\n");
            }
        }

        if (!values.isEmpty()) {
            int index = sb.lastIndexOf(",");
            if (index > 0) {
                sb.deleteCharAt(index);
            }
        }

        sb.append(indent()).append(");\n");

        return sb.toString();
    }

    /**
     * Add values to map.
     *
     * @param map1 value1
     * @param map2 value2
     */
    public void put(String map1, String map2) {
        this.values.put(map1, map2);
    }
}
