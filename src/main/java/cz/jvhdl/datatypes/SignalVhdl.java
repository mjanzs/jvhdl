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
 * VHDL signal.
 * 
 * Syntax:
 * <pre>
 * signal <signal_name> : type;
 * </pre>
 *
 * @author Martin Janyš
 */
public class SignalVhdl extends VariableVhdl {

    public SignalVhdl(String id, DataTypeVhdl e) {
        super(id, e);
        this.typeString = "signal";
    }

    public SignalVhdl(String id, DataTypeVhdl e, Object init) {
        super(id, e, init);
        this.typeString = "signal";
    }
}
