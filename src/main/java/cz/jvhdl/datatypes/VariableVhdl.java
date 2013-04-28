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
package cz.jvhdl.datatypes;

/**
 * Signal VHDL. 
 * 
 * Synta: "variable nstate : FSMstate;". <br/> Syntax: variable <b>ID</b> :
 * <i>typeid</i>;
 *
 * VHDL variable syntax.
 * <pre>
 * variable <variable_name> is : type;
 * </pre>
 *
 * @author Martin Janyš
 */
public class VariableVhdl extends DataTypeVhdl {

    protected String typeString = "variable";
    private String init = null;
    /**
     * Data type.
     */
    protected DataTypeVhdl type;

    /**
     * Constructor of signal.
     *
     * @param id
     * @param e
     */
    public VariableVhdl(String id, DataTypeVhdl e) {
        this.setId(id);
        this.type = e;
    }
    
    public VariableVhdl(String id, DataTypeVhdl e, Object init) {
        this(id, e);
        this.init = init.toString();
    }

    @Override
    public String toString() {

        // this.commentStart = "-- " + typeString + " " + this.getId() + "\n";

        StringBuilder sb = new StringBuilder();

        sb.append(commentStart());
        
        sb.append(this.typeString).append(" ");
        sb.append(this.getId());
        sb.append(" : ");
        if (this.type instanceof EnumVhdl) {
            sb.append(this.type.getId());
        }
        else {
            sb.append(this.type);
        }
        
        if (init != null) {
            sb.append(" := ").append(init);
        }
        sb.append(";\n");

        sb.append(commentEnd());

        return sb.toString();
    }
}
