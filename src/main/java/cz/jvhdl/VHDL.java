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
package cz.jvhdl;

import cz.jvhdl.datatypes.NumberVhdl;
import cz.jvhdl.datatypes.CompositeVhdl;
import cz.jvhdl.datatypes.ArrayVhdl;
import cz.jvhdl.datatypes.VariableVhdl;
import cz.jvhdl.datatypes.DataTypeVhdl;
import cz.jvhdl.datatypes.EnumVhdl;
import cz.jvhdl.datatypes.SignalVhdl;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import cz.jvhdl.exception.InvalidVhdlTypeException;
import cz.jvhdl.exception.SyntaxErrorVhdl;
import cz.jvhdl.vdhdlgenerator.VhdlGenerator;

/**
 * <b>Main class of module.</b>
 *
 * This class is used for building vhdl code by extends it.
 *
 * From DataLayerVhdl inherits key methods and attributes for building code.
 *
 * @see DataLayerVhdl
 *
 * @author Martin Janyš
 */
public class VHDL extends BlockCodeRecursiveVhdl {

    public static final String extension = ".vhdl";
    public static String kwAppedix = "_1";

    /**
     *
     */
    public VHDL() {
        super(null);
    }

    /**
     *
     * @param id
     */
    public VHDL(String id) {
        super(id);
        selected = new TreeMap<>();
        for (UsesAndLibs v : UsesAndLibs.values()) {
            selected.put(v, Boolean.TRUE);
        }
    }
    /**
     * Contains blocks like processes, architectures etc.
     */
    protected final ArrayList<BlockCodeVhdl> code = new ArrayList<>();
    private final Stack<BlockCodeVhdl> syntaxStack = new Stack<>();
    private Map<UsesAndLibs, Boolean> selected;
    public static final String RST = "RESET";
    public static final String CLK = "CLK";
    public static final Set<String> keyWords = new HashSet<>(Arrays.asList(
            new String[]{
                "access", "after", "alias", "all", "attribute", "block",
                "body", "buffer", "bus", "constant", "exit", "file",
                "for", "function", "generic", "group", "in", "is",
                "label", "loop", "mod", "new", "next", "null",
                "of", "on", "open", "out", "range", "rem",
                "return", "signal", "shared", "then", "to", "type",
                "until", "use", "variable", "wait", "while", "with",
                "to", "downto"
            }));
    public static final Set<String> testOperators = new HashSet<>(Arrays.asList(
            new String[]{
                "=", "/=", "<", "<=", ">", ">="
            }));
    /* Patterns */
    public static Pattern identifierPattern = Pattern.compile("[A-Za-z](_?[A-Za-z0-9])*");
    public static Pattern testNumberPattern = Pattern.compile("^\\s*\\d+\\s*$");
    public static Pattern testOpratorsPattern;

    static {
        /* Operators */
        StringBuilder regex = new StringBuilder("^.*(");
        for (String string : testOperators) {
            regex.append(string).append("|");
        }
        regex.deleteCharAt(regex.lastIndexOf("|"));

        regex.append(").*$");
        testOpratorsPattern = Pattern.compile(regex.toString());
    }

    public enum UsesAndLibs {

        IEEE("library ieee;\n"),
        IEEE_STD_LOGIC("use ieee.std_logic_1164.all;\n"),
        IEEE_STD_LOGIC_ARITH("use ieee.std_logic_arith.all;\n"),
        IEEE_NUMERIC_STD("use IEEE.numeric_std.all;\n"),
        IEEE_NUMERIC_SIGNED("use IEEE.numeric_signed.all;\n"),
        IEEE_NUMERIC_UNSIGNED("use IEEE.numberic_unsigned.all;\n"),
        IEEE_NUMERIC_BIT("use IEEE.numeric_bit.all;\n"),
        IEEE_MATH_REAL("use IEEE.math_real.all;\n"),
        IEEE_MATH_COMPLEX("use IEEE.math_complex.all;\n");
        public String defLine;
        public String addsUsesAndLibs = "";

        private UsesAndLibs(String s) {
            defLine = s;
        }

        @Override
        public String toString() {
            return defLine;
        }
    }
    private List<UsesAndLibs> libs = new ArrayList<>();

    public void addLib(UsesAndLibs lib) {
        if (!libs.contains(lib)) {
            libs.add(lib);
        }
    }

    private BlockCodeVhdl lastBlockCode() {
        if (syntaxStack.isEmpty()) {
            return null;
        }
        return syntaxStack.peek();
    }

    private boolean testLastProcess() throws SyntaxErrorVhdl {
        if (lastBlockCode() instanceof ProcessVhdl) {
            return true;
        }
        else {
            throw new SyntaxErrorVhdl("There is not process context (" + lastBlockCode().getClass() + ")");
        }
    }

    public static String outputString(String s) {
        if (VHDL.keyWords.contains(s.toLowerCase())) {
            return s + "_1";
        }
        return s;
    }

    public void NewLine() {
        code.add(new NewLine());
    }

    public void Raw(String s) {
        if (syntaxStack.size() > 0) {
            RawVhdl raw = new RawVhdl(s);
            raw.parent = syntaxStack.peek();
            syntaxStack.peek().body(raw);
        }
        else {
            code.add(new RawVhdl(s));
        }
    }

    /**
     * Declare vhdl entity.
     *
     * @param id Identificator of entity
     *
     * @return Created entity
     */
    public EntityVhdl Entity(String id) {
        EntityVhdl e = new EntityVhdl(id);
        code.add(e);
        return e;
    }

    /**
     * Declare vhdl entity.
     *
     * @param id Identificator of entity
     * @param values Map with values
     *
     * @return Created entity
     */
    @Deprecated
    public EntityVhdl Entity(String id, Map<String, DataTypeVhdl> values) {
        EntityVhdl e = new EntityVhdl(id, values);
        code.add(0, e);
        return e;
    }

    /**
     * Declare vhdl port map.
     *
     * @param id Identificator of port map
     *
     * @return Created map
     */
    public ComponentVhdl Component(EntityVhdl entity, boolean standalone) {
        ComponentVhdl e = new ComponentVhdl(entity, standalone);
        e.setParent(abstractTreePeek());
        add(e);
        return e;
    }

    /**
     * Declare vhdl port map.
     *
     * @param id Identificator of port map
     * @param values Map with values
     *
     * @return Created map
     */
    @Deprecated
    public EntityVhdl Component(EntityVhdl entity,
            Map<String, String> values, boolean standalone) {

        ComponentVhdl e = new ComponentVhdl(entity, values, standalone);
        e.setParent(abstractTreePeek());
        add(e);
        return e;
    }

    /**
     * Declare a type that may be used to create scalar objects.<br/> <br/>
     * Example: <br/> <br/> <b>type</b> identifier is scalar_type_definition ;
     * <br/> <br/> <b>type</b> my_small <b>is range<b/> -5 to 5 ; <br/>
     * <b>type</b> my_bits <b>is range<b/> 31 downto 0 ; <br/> <b>type</b>
     * my_float <b>is range<b/> 1.0 to 1.0E6 ; <br/>
     *
     * @param type New declared scalar type
     *
     * @return New declared scalar type
     * @throws InvalidVhdlTypeException
     */
    public NumberVhdl Scalar(NumberVhdl type) throws InvalidVhdlTypeException {
        dclAdd(type);
        return type;
    }

    private CompositeVhdl composite(CompositeVhdl composite) throws InvalidVhdlTypeException {
        dclAdd(composite);
        return composite;
    }

    /**
     * Declare a type for creating array, record or unit objects.<br/> <br/>
     * Example<br/> <b>type</b> identifier is composite_type_definition ;<br/>
     * <br/> <b>type</b> word <b>is array</b> (0 to 31) of bit;<br/> <b>type</b>
     * data <b>is array</b> (7 downto 0) of word;<br/> <b>type</b> mem <b>is
     * array</b> (natural range <>) of word;<br/> <b>type</b> matrix <b>is
     * array</b> (integer range<>, integer range <>) of real;
     *
     * @param array New declared array
     *
     * @return New declared array
     * @throws InvalidVhdlTypeException
     */
    public ArrayVhdl Array(ArrayVhdl array) throws InvalidVhdlTypeException {
        return (ArrayVhdl) composite(array);
    }

    /**
     * Creates vhdl enum.
     *
     * @see EnumVhdl
     * @see ProcessVhdl
     *
     * @param e Enum class.
     *
     * @return Enum witch is created.
     *
     * @throws InvalidVhdlTypeException
     */
    public EnumVhdl Enum(EnumVhdl e) throws InvalidVhdlTypeException {
        dclAdd(e);
        return e;
    }

    /**
     * Creates vhdl signal.
     *
     * @see SignalVhdl
     * @see ProcessVhdl
     *
     * @param id Identificator of data type.
     * @param e Reference to datatype
     *
     * @return Signal witch is created.
     *
     * @throws InvalidVhdlTypeException
     */
    public SignalVhdl Signal(String id, DataTypeVhdl e) throws InvalidVhdlTypeException {
        SignalVhdl s = new SignalVhdl(id, e);
        dclAdd(s);
        return s;
    }

    /**
     * Creates vhdl signal.
     *
     * @see SignalVhdl
     * @see ProcessVhdl
     *
     * @param id Identificator of data type.
     * @param e Reference to datatype
     * @param init Initial value
     *
     * @return Signal witch is created.
     *
     * @throws InvalidVhdlTypeException
     */
    public SignalVhdl Signal(String id, DataTypeVhdl e, Object init) throws InvalidVhdlTypeException {
        SignalVhdl s = new SignalVhdl(id, e, init);
        dclAdd(s);
        return s;
    }

    /**
     * Creates vhdl signal.
     *
     * @see VariableVhdl
     * @see ProcessVhdl
     *
     * @param id Identificator of data type
     * @param e Reference to datatype
     *
     * @return Varable witch is created
     *
     * @throws InvalidVhdlTypeException
     */
    public VariableVhdl Variable(String id, DataTypeVhdl e) throws InvalidVhdlTypeException {
        VariableVhdl v = new VariableVhdl(id, e);
        dclAdd(v);
        return v;
    }

    /**
     * Creates architecture vhdl code block.
     *
     * @param id Identificator of architecture, architecture name
     * @param of Entity name
     */
    public void Architecture(String id, String of) {
        ArchitectureVhdl a = new ArchitectureVhdl(id, of);
        syntaxStack.push(a);
        code.add(a);
    }

    public void Architecture(String id, EntityVhdl of) {
        Architecture(id, of.getId());
    }

    /**
     * Ends architecture block.
     *
     * @throws SyntaxErrorVhdl
     */
    public void EndArchitecture() throws SyntaxErrorVhdl {
        if (syntaxStack.peek() instanceof ArchitectureVhdl) {
            syntaxStack.pop();
        }
        else {
            throw new SyntaxErrorVhdl("Missing end of block");
        }

    }

    /**
     * Starts process code block.
     *
     * @see ProcessVhdl
     *
     * @param id Identificator of code.
     */
    public void Process(String id) {
        this.Process(id, null);
    }

    /**
     * Starts process code block.
     *
     * @see ProcessVhdl
     *
     * @param id Identificator of code.
     * @param sensitivityList Sensitivity list of process.
     */
    public void Process(String id, Object[] sensitivityList) {
        ProcessVhdl p = new ProcessVhdl(id, sensitivityList);
        if (!syntaxStack.isEmpty()) {
            if (syntaxStack.peek() instanceof BlockCodeRecursiveVhdl) {
                p.parent = syntaxStack.peek();

            }
            syntaxStack.peek().body(p);
        }
        else {
            code.add(p);
        }
        syntaxStack.push(p);
    }

    /**
     * Ends process block.
     *
     * @throws SyntaxErrorVhdl
     */
    public void EndProcess() throws SyntaxErrorVhdl {
        if (syntaxStack.peek() instanceof ProcessVhdl) {
            lastBlockCode().end();
            syntaxStack.pop();
        }
        else {
            throw new SyntaxErrorVhdl("Missing end of block");
        }
    }

    /**
     * Starts if code block.
     *
     * @see IfVhdl
     * @see ProcessVhdl
     *
     * @param c Condition of flow statement.
     *
     * @throws SyntaxErrorVhdl
     */
    public void If(ConditionVhdl c) throws SyntaxErrorVhdl {
        testLastProcess();
        ((ProcessVhdl) lastBlockCode()).if_(c);
    }

    /**
     * Starts elseif code block.
     *
     * @see IfVhdl
     * @see ProcessVhdl
     *
     * @param c Condition of flow statement.
     *
     * @throws SyntaxErrorVhdl
     */
    public void Elseif(ConditionVhdl c) throws SyntaxErrorVhdl {
        testLastProcess();
        ((ProcessVhdl) lastBlockCode()).elseif(c);
    }

    /**
     * Else branch.
     *
     * @throws SyntaxErrorVhdl
     */
    public void Else() throws SyntaxErrorVhdl {
        testLastProcess();
        ((ProcessVhdl) lastBlockCode()).else_();
    }

    /**
     * Ends if code block.
     *
     * @throws SyntaxErrorVhdl
     */
    public void EndIf() throws SyntaxErrorVhdl {
        testLastProcess();
        ((ProcessVhdl) lastBlockCode()).endif();
    }

    /**
     * Assigment.
     *
     * @see AssignmentVhdl
     * @see ProcessVhdl
     *
     * @param lvalue Left value
     * @param rvalue Rigt value
     *
     * @throws SyntaxErrorVhdl
     */
    public void Assignment(VariableVhdl lvalue, String rvalue) throws SyntaxErrorVhdl {

        rvalue = rvalueEscape(rvalue);

        if (lastBlockCode() instanceof ProcessVhdl
                && ((ProcessVhdl) lastBlockCode()).isActive()) {
            // sequential logic
            ((ProcessVhdl) lastBlockCode()).assignment(lvalue, rvalue);
        }
        else if (lastBlockCode() instanceof ArchitectureVhdl) {
            CombinationalLogicVhdl assig = new CombinationalLogicVhdl(new AssignmentVhdl(lvalue, rvalue, null));
            assig.setParent(lastBlockCode());
            lastBlockCode().body(assig);
        }
        else {
            // combinational logic
            code.add(new CombinationalLogicVhdl(new AssignmentVhdl(lvalue, rvalue, null)));
        }
    }

    public void AssignmentToSignal(String lvalue, String rvalue) throws SyntaxErrorVhdl {
        Assignment(new SignalVhdl(lvalue, null), rvalue);
    }

    /**
     * Assigment.
     *
     * @see AssignmentVhdl
     * @see ProcessVhdl
     *
     * @param lvalue Left value.
     * @param rvalue Right value.
     *
     * @throws InvalidVhdlTypeException
     * @throws SyntaxErrorVhdl
     */
    public void Assignment(VariableVhdl lvalue, int rvalue) throws InvalidVhdlTypeException, SyntaxErrorVhdl {
        ((ProcessVhdl) lastBlockCode()).assignment(lvalue, rvalue);
    }

    public static String rvalueEscape(String rvalue) {
        Matcher matcher = testNumberPattern.matcher(rvalue);
        if (matcher.matches()) {
            String trim = rvalue.trim();
            if (trim.length() == 1) {
                if (trim.equals("1") || trim.equals("0")) {
                    return "'" + rvalue + "'";
                }
            }
            else {
                return "\"" + rvalue + "\"";
            }
        }
        return rvalue;
    }

    /**
     * Starts case code block.
     *
     * @see SwitchVhdl
     * @see ProcessVhdl
     *
     * @param s Signal of condition
     *
     * @throws SyntaxErrorVhdl
     */
    public void Cases(VariableVhdl s) throws SyntaxErrorVhdl {
        testLastProcess();
        ((ProcessVhdl) lastBlockCode()).cases(s);
    }

    /**
     * Ends cases code block.
     *
     * @see SwitchVhdl
     * @see ProcessVhdl
     *
     * @throws SyntaxErrorVhdl
     */
    public void EndCases() throws SyntaxErrorVhdl {
        testLastProcess();
        ((ProcessVhdl) lastBlockCode()).endCases();
    }

    /**
     * When branch.
     *
     * @param c Condition of when
     *
     * @throws SyntaxErrorVhdl
     * @throws InvalidVhdlTypeException
     */
    public void When(String c) throws SyntaxErrorVhdl, InvalidVhdlTypeException {
        testLastProcess();
        ((ProcessVhdl) lastBlockCode()).when(c);
    }

    /**
     * Ends when branch.
     *
     * @param o
     * @throws SyntaxErrorVhdl
     */
    public void WhenOthers(ObjectVhdl o) throws SyntaxErrorVhdl {
        testLastProcess();
        ((ProcessVhdl) lastBlockCode()).endWhenWithOthers("others", o.toString());
    }

    /**
     * Ends when branch.
     *
     * @throws SyntaxErrorVhdl
     */
    public void WhenOthers() throws SyntaxErrorVhdl {
        testLastProcess();
        ((ProcessVhdl) lastBlockCode()).endWhenWithOthers();
    }

    /**
     * Ends when branch.
     *
     * Creates "when others => null"
     *
     * @throws SyntaxErrorVhdl
     */
    public void EndWhens() throws SyntaxErrorVhdl {
        testLastProcess();
        ((ProcessVhdl) lastBlockCode()).endWhen();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        sb.append("");//selected);

        for (UsesAndLibs usesAndLibs : libs) {
            sb.append(usesAndLibs);
        }
        if (!libs.isEmpty()) {
            sb.append("\n");
        }

        if (dcl != null) {
            sb.append(this.dcl);
        }

        for (BlockCodeVhdl b : code) {
            sb.append(b).append("\n");
        }
        return sb.toString();
    }

    /**
     * Writes code to file.
     *
     * @param s Name of new file
     *
     * @throws FileNotFoundException
     */
    public void write(String s) throws FileNotFoundException {
        new VhdlGenerator(this, s).writeVhdl();
    }

    /**
     * Writes code to standard output.
     */
    public void write() {
        new VhdlGenerator(this).writeVhdl();
    }

    /**
     * Adds vhdl process code to vhdl code.
     *
     * @param c Process code
     */
    private void add(ProcessVhdl c) {
        this.code.add(c);
    }

    /**
     * Adds vhdl declarations to vhdl code.
     *
     * @param dcl Declarations
     */
    private void add(DeclarationVhdl dcl) {
        this.dcl = dcl;
    }

    private void dclAdd(DataTypeVhdl t) throws InvalidVhdlTypeException {
        if (!syntaxStack.isEmpty()
                && syntaxStack.peek() instanceof BlockCodeRecursiveVhdl) {
            ((BlockCodeRecursiveVhdl) syntaxStack.peek()).dcl.add(t);
        }
        else {
            dcl.add(t);
        }
    }

    private void add(BlockCodeVhdl block) {
        if (!syntaxStack.isEmpty()
                && syntaxStack.peek() instanceof BlockCodeRecursiveVhdl) {
            ((BlockCodeRecursiveVhdl) syntaxStack.peek()).body(block);
        }
        else {
            code.add(block);
        }
    }
}
