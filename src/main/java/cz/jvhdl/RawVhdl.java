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

/**
 *
 * Raw input class.
 * 
 * @author Martin Janyš
 */
public class RawVhdl extends BlockCodeVhdl {

    private final String text;
    
    /**
     * 
     * @param s input string
     */
    public RawVhdl(String s) {
        text = s;
    }

    /**
     * 
     * @return Indented output VHDL code from user.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for (String string : text.split("\n|\r\n")) {
            sb.append(indent()).append(string).append("\n");
        }
        
        return sb.toString();
    }
}
