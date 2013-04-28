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
package cz.jvhdl.exception;

/**
 * Syntax error exception.
 * 
 * Is throwned if is used method end() of ProcessVhdl, where is control started.
 * 
 * @author Martin Janyš
 */
public class SyntaxErrorVhdl extends Exception {

    /**
     *
     */
    public SyntaxErrorVhdl() {
    }

    /**
     *
     * @param message
     */
    public SyntaxErrorVhdl(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public SyntaxErrorVhdl(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause
     */
    public SyntaxErrorVhdl(Throwable cause) {
        super(cause);
    }

    /**
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public SyntaxErrorVhdl(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
