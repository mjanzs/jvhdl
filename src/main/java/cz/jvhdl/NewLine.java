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
 * Type new line character to the output.
 * 
 * @author Martin Janyš
 */
public class NewLine extends BlockCodeVhdl {

    public NewLine() {
    }

    @Override
    public String toString() {
        return "\n";
    }
}
