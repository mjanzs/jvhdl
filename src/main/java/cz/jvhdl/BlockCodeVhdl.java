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
 * Parent class for all block code. (With block is mean not line code)
 * 
 * @author Martin Janyš
 */
abstract class BlockCodeVhdl extends ObjectVhdl {

    /**
     * Content of a block
     * 
     * @param elem  Added element
     */
    protected void body(ObjectVhdl elem) {
    }

    /**
     * Declares end of current block
     * 
     * @throws SyntaxErrorVhdl Syntax error
     */
    public void end() throws SyntaxErrorVhdl {   
    }
}
