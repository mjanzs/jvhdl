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
package cz.jvhdl.vdhdlgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import cz.jvhdl.VHDL;

/**
 * Writes vhdl code from VHDL class to file or to stdout.
 *
 * @author Martin Janyš
 */
public class VhdlGenerator implements VhdlWriter {

    /**
     * Output stream
     */
    public PrintStream out;
    private VHDL code;
    private String header = "";

    /**
     * Inits generator to stdout.
     *
     * @param code VHDL instance
     */
    public VhdlGenerator(VHDL code) {
        this.out = System.out;
        this.code = code;
    }

    /**
     * Inits generator to file.
     *
     * @param code VHDL instance
     * @param filename Filename
     *
     * @throws FileNotFoundException
     */
    public VhdlGenerator(VHDL code, String filename) throws FileNotFoundException {
        this.code = code;
        this.out = new PrintStream(new File(filename + VHDL.extension));
    }

    /**
     * Inits generator to stream.
     *
     * @param code VHDL instance
     * @param stream Otputstream
     */
    public VhdlGenerator(VHDL code, PrintStream stream) {
        this.out = stream;
    }

    /**
     * Set header witch is printed before VHDL.
     *
     * @param header Header string.
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Writes code.
     */
    @Override
    public void writeVhdl() {
        this.out.println(header);
        this.out.println(code);
        this.out.close();
    }
}
