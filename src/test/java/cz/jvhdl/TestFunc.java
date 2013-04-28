/**
 * VYSOKÉ UČENÍ TECHNICKÉ V BRNÉ BRNO UNIVERSITY OF TECHNOLOGY
 *
 * FAKULTA INFORMAČNÍCH TECHNOLOGIÍ
 *
 * Baklářská práce
 *
 * Generátor konečných automatů z grafického popisu pro jazyk VHDL
 */
package cz.jvhdl;

/**
 * @author Martin Janyš
 */
public class TestFunc {

    public static void diff(String s1, String s2) {

        String[] lines1 = s1.split("\n");
        String[] lines2 = s2.split("\n");

        int len = Math.min(lines1.length, lines2.length);

        int i;
        for (i = 0; i < len; i++) {

            if (!lines1[i].equals(lines2[i])) {
                throw new AssertionError("Diff line " + (i + 1) + " (" + "'" + lines1[i] + "'" + " ," + "'" + lines2[i] + "'" + ")");
            }
        }

        if (i != Math.max(lines1.length, lines2.length)) {
            throw new AssertionError("Diff line " + (i + 1) + "1 sample is longer");
        }
    }

    private ProcessVhdl getVhdlProcess() {
        return new ProcessVhdl("p", new String[]{"S1", "S2"});
    }
}
