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

import cz.jvhdl.exception.SyntaxErrorVhdl;

/**
 * 
 * Class provides generating architecture VHDL code.
 * 
 * @author Martin Janyš
 */
public class ArchitectureVhdl extends BlockCodeRecursiveVhdl {

    private String of;

    /**
     * Constructor 
     * 
     * @param id Identificator of architecture
     * @param of Name of entity
     */
    public ArchitectureVhdl(String id, String of) {
        super(id);
        this.of = of;
    }

    /**
     * 
     * Defines end of architecture
     * 
     * @throws SyntaxErrorVhdl 
     */
    @Override
    public void end() throws SyntaxErrorVhdl {
    }
    
    /**
     * String with VHDL output
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(indent()).append("architecture");
        sb.append(" ").append(getId()).append(" ");
        sb.append("of");
        sb.append(" ").append(of).append(" ");
        sb.append("is\n");
         //dcl list
        sb.append(getLocalVariablesString(indent()));
        
        sb.append(indent()).append("begin\n");
        
        for (ObjectVhdl b : body) {
            sb.append(b);
        }
        
        sb.append("end ").append(getId()).append(";\n");
        
        return sb.toString();
    }
}
