package br.com.bombastic.antlr;
// Generated from Bombastic.g4 by ANTLR 4.9.2

    import br.com.bombastic.datastructures.*;
    import br.com.bombastic.exceptions.*;
    import br.com.bombastic.ast.*;
    import java.util.ArrayList;
    import java.util.Stack;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BombasticLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, AP=12, FP=13, SC=14, OP=15, ATTR=16, VIR=17, AC=18, 
		FC=19, OPREL=20, ID=21, NUMBER=22, TEXT=23, CHAR=24, WS=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "AP", "FP", "SC", "OP", "ATTR", "VIR", "AC", "FC", "OPREL", 
			"ID", "NUMBER", "TEXT", "CHAR", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'begin'", "'end'", "'num'", "'txt'", "'char'", "'read'", "'write'", 
			"'when'", "'otherwise'", "'enquanto'", "'faca'", "'('", "')'", "';'", 
			null, "'='", "','", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"AP", "FP", "SC", "OP", "ATTR", "VIR", "AC", "FC", "OPREL", "ID", "NUMBER", 
			"TEXT", "CHAR", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	    private int _tipo;
	    private String _varName;
	    private String _varValue;
	    private BombasticSymbolTable symbolTable = new BombasticSymbolTable();
	    private BombasticSymbol symbol;
	    private BombasticProgram program = new BombasticProgram();
	    private ArrayList<AbstractCommand> curThread;
	    private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();

	    private String _readId;
	    private String _writeId;
	    private String _exprId;
	    private String _exprContent;
	    private String _exprDecision;
	    private ArrayList<AbstractCommand> listaTrue;
	    private ArrayList<AbstractCommand> listaFalse;
		private ArrayList<AbstractCommand> listaLoop;
		
	    public void defineId(BombasticSymbolTable symbolTable, int _tipo, String _varName){
	        _varValue = null;
	        symbol = new BombasticVariable(_varName, _tipo, _varValue);
	        if (!symbolTable.exists(_varName)){
	            symbolTable.add(symbol);
	            System.out.println("Simbolo adicionado " +symbol);
	        } 
	        else {
	            throw new BombasticSemanticException("Symbol "+_varName+" already declared");
	        }
	    }

	    public void verificaId(String id){
	        if (symbolTable.get(id) == null){
	            throw new BombasticSemanticException("Symbol "+id+" not declared");
	        }
	    }

	    public void verificaAtr(String id){
			BombasticVariable var = (BombasticVariable)symbolTable.get(id);
			if (var == null){
				return;
			}
			if (var.getValue() == null){
			    throw new BombasticSemanticException("Symbol "+id+" declared but not attributed");
			}
		}

	    public void verificaUsed(BombasticSymbolTable symbolTable){
				ArrayList<BombasticSymbol> varlList = symbolTable.getAll();
				for (BombasticSymbol var : varlList){
					verificaVarUsed(var.getName());
				}
			}

		public void verificaVarUsed(String id){
			BombasticVariable var = (BombasticVariable)symbolTable.get(id);
			if (var.getUsed() == false){
				throw new BombasticSemanticException("Symbol "+id+" declared but not used");
			}
		}

	    public void exibeComandos(){
	        for(AbstractCommand c: program.getComandos()){
	            System.out.println(c);
	        }
	    }

	    public void generateCode(){
	        program.generateTarget();
	    }         


	public BombasticLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Bombastic.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\33\u00b6\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3"+
		"\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u008e\n\25\3\26"+
		"\3\26\7\26\u0092\n\26\f\26\16\26\u0095\13\26\3\27\6\27\u0098\n\27\r\27"+
		"\16\27\u0099\3\27\3\27\6\27\u009e\n\27\r\27\16\27\u009f\5\27\u00a2\n\27"+
		"\3\30\3\30\7\30\u00a6\n\30\f\30\16\30\u00a9\13\30\3\30\3\30\3\31\3\31"+
		"\5\31\u00af\n\31\3\31\3\31\3\32\3\32\3\32\3\32\2\2\33\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\63\33\3\2\b\5\2,-//\61\61\4\2>>@@\3\2c|\5"+
		"\2\62;C\\c|\3\2\62;\5\2\13\f\17\17\"\"\2\u00be\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2"+
		"\2\2\3\65\3\2\2\2\5;\3\2\2\2\7?\3\2\2\2\tC\3\2\2\2\13G\3\2\2\2\rL\3\2"+
		"\2\2\17Q\3\2\2\2\21W\3\2\2\2\23\\\3\2\2\2\25f\3\2\2\2\27o\3\2\2\2\31t"+
		"\3\2\2\2\33v\3\2\2\2\35x\3\2\2\2\37z\3\2\2\2!|\3\2\2\2#~\3\2\2\2%\u0080"+
		"\3\2\2\2\'\u0082\3\2\2\2)\u008d\3\2\2\2+\u008f\3\2\2\2-\u0097\3\2\2\2"+
		"/\u00a3\3\2\2\2\61\u00ac\3\2\2\2\63\u00b2\3\2\2\2\65\66\7d\2\2\66\67\7"+
		"g\2\2\678\7i\2\289\7k\2\29:\7p\2\2:\4\3\2\2\2;<\7g\2\2<=\7p\2\2=>\7f\2"+
		"\2>\6\3\2\2\2?@\7p\2\2@A\7w\2\2AB\7o\2\2B\b\3\2\2\2CD\7v\2\2DE\7z\2\2"+
		"EF\7v\2\2F\n\3\2\2\2GH\7e\2\2HI\7j\2\2IJ\7c\2\2JK\7t\2\2K\f\3\2\2\2LM"+
		"\7t\2\2MN\7g\2\2NO\7c\2\2OP\7f\2\2P\16\3\2\2\2QR\7y\2\2RS\7t\2\2ST\7k"+
		"\2\2TU\7v\2\2UV\7g\2\2V\20\3\2\2\2WX\7y\2\2XY\7j\2\2YZ\7g\2\2Z[\7p\2\2"+
		"[\22\3\2\2\2\\]\7q\2\2]^\7v\2\2^_\7j\2\2_`\7g\2\2`a\7t\2\2ab\7y\2\2bc"+
		"\7k\2\2cd\7u\2\2de\7g\2\2e\24\3\2\2\2fg\7g\2\2gh\7p\2\2hi\7s\2\2ij\7w"+
		"\2\2jk\7c\2\2kl\7p\2\2lm\7v\2\2mn\7q\2\2n\26\3\2\2\2op\7h\2\2pq\7c\2\2"+
		"qr\7e\2\2rs\7c\2\2s\30\3\2\2\2tu\7*\2\2u\32\3\2\2\2vw\7+\2\2w\34\3\2\2"+
		"\2xy\7=\2\2y\36\3\2\2\2z{\t\2\2\2{ \3\2\2\2|}\7?\2\2}\"\3\2\2\2~\177\7"+
		".\2\2\177$\3\2\2\2\u0080\u0081\7}\2\2\u0081&\3\2\2\2\u0082\u0083\7\177"+
		"\2\2\u0083(\3\2\2\2\u0084\u008e\t\3\2\2\u0085\u0086\7@\2\2\u0086\u008e"+
		"\7?\2\2\u0087\u0088\7>\2\2\u0088\u008e\7?\2\2\u0089\u008a\7?\2\2\u008a"+
		"\u008e\7?\2\2\u008b\u008c\7#\2\2\u008c\u008e\7?\2\2\u008d\u0084\3\2\2"+
		"\2\u008d\u0085\3\2\2\2\u008d\u0087\3\2\2\2\u008d\u0089\3\2\2\2\u008d\u008b"+
		"\3\2\2\2\u008e*\3\2\2\2\u008f\u0093\t\4\2\2\u0090\u0092\t\5\2\2\u0091"+
		"\u0090\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2"+
		"\2\2\u0094,\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0098\t\6\2\2\u0097\u0096"+
		"\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a"+
		"\u00a1\3\2\2\2\u009b\u009d\7\60\2\2\u009c\u009e\t\6\2\2\u009d\u009c\3"+
		"\2\2\2\u009e\u009f\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0"+
		"\u00a2\3\2\2\2\u00a1\u009b\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2.\3\2\2\2"+
		"\u00a3\u00a7\7$\2\2\u00a4\u00a6\t\5\2\2\u00a5\u00a4\3\2\2\2\u00a6\u00a9"+
		"\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00aa\3\2\2\2\u00a9"+
		"\u00a7\3\2\2\2\u00aa\u00ab\7$\2\2\u00ab\60\3\2\2\2\u00ac\u00ae\7)\2\2"+
		"\u00ad\u00af\t\5\2\2\u00ae\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1"+
		"\7)\2\2\u00b1\62\3\2\2\2\u00b2\u00b3\t\7\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00b5\b\32\2\2\u00b5\64\3\2\2\2\f\2\u008d\u0091\u0093\u0099\u009f\u00a1"+
		"\u00a5\u00a7\u00ae\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}