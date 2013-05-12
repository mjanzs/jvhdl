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

import cz.jvhdl.datatypes.DataTypeVhdl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import cz.jvhdl.exception.InvalidVhdlTypeException;

/**
 * Vhdl declaration code block. Has methods for adding declarations of signals
 * and datatypes.
 *
 * @author Martin Janyš
 */
public class DeclarationVhdl extends BlockCodeVhdl implements Iterable<DataTypeVhdl> {

    private List<DataTypeVhdl> types = new ArrayList<>();

    /**
     * Constructor only sets start comment.
     */
    public DeclarationVhdl() {
        this.commentStart = "-- -- -- Declaration -- -- --\n";
    }

    /**
     * Adds data type to begin of code.
     *
     * @param type DataType to add.
     *
     * @throws InvalidVhdlTypeException
     */
    public void add(DataTypeVhdl type) throws InvalidVhdlTypeException {

        if (type instanceof DataTypeVhdl) {
            this.types.add((DataTypeVhdl) type);
        }
        else {
            throw new InvalidVhdlTypeException("Invalid argument of VhdlDeclaration.add()");
        }
    }

    /**
     * 
     * @return True if there is no declaration
     */
    public boolean isEmpty() {
        return this.types.isEmpty();
    }

    @Override
    public String toString() {
        if (!this.types.isEmpty()) {
            StringBuilder sb = new StringBuilder();

            sb.append(commentStart());

            for (DataTypeVhdl t : this.types) {
                sb.append(t.toString());
            }

            sb.append(commentEnd());
            sb.append("\n");
            return sb.toString();
        }
        else {
            return new String();
        }
    }

    @Override
    public Iterator<DataTypeVhdl> iterator() {
        return types.iterator();
    }
}
