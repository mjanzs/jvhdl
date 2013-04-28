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
 * Parent for all data types.
 * 
 * @author Martin Janyš
 */
public abstract class DataTypeVhdl {

    /**
     * 
     */
    public enum Type {

        /**
         * IN std_logic
         */
        IN,
        /**
         * OUT std_logic
         */
        OUT,
        /**
         * INOUT std_logic
         */
        INOUT,
        BUFFER
    }

    /**
     * Enum with basic types
     */
    public static enum DataUnit {

        /**
         * Bit type
         */
        BIT("bit"),
        /**
         * Byte type
         */
        BYTE("byte"),
        /**
         * Word type
         */
        WORD("word"),
        /**
         * Real type
         */
        REAL("real");

        private DataUnit(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return id;
        }
        String id;
    }
    
    /**
     * Std datatypes
     */
    public enum Std {
        STD_LOGIC,
        STD_LOGIC_VECTOR
    }

    /**
     * Direction of numerical data type range.
     */
    public static enum Direction {

        /**
         * Creates arg1 to arg2
         */
        TO,
        /**
         * Creates arg1 downto arg2
         */
        DOWNTO,
    }
    /**
     * Comment before type.
     */
    public String commentStart = "";
    /**
     * Comment after type.
     */
    public String commentEnd = "";
    
    private String id;

    /**
     *
     * @return Comment before
     */
    public String commentStart() {
        return this.commentStart;
    }

    /**
     *
     * @return Comment after
     */
    public String commentEnd() {
        return this.commentEnd;
    }

    /**
     * Returns identificator of datatype.
     *
     * @return Identificator of datatype
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets identificator of datatype.
     *
     * @param id Identificator
     */
    public void setId(String id) {
        this.id = id;
    }
    
    public DataTypeVhdl getUndirected() {
        return this;
    }
}
