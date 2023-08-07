package br.com.bombastic.antlr;// Generated from c:\Compiladores\teste\bombastic-language\src\br\com\bombastic\Bombastic.g4 by ANTLR 4.9.2
// Generated from c:\Compiladores\teste\bombastic-language\src\br\com\bombastic\Bombastic.g4 by ANTLR 4.9.2

    import br.com.bombastic.datastructures.*;
    import br.com.bombastic.exceptions.*;
    import br.com.bombastic.ast.*;
    import java.util.ArrayList;
    import java.util.Stack;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BombasticParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, AP=11, FP=12, SC=13, OP=14, ATTR=15, VIR=16, AC=17, FC=18, OPREL=19, 
		ID=20, NUMBER=21, TEXT=22, CHAR=23, WS=24;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_cmdselecao = 9, RULE_cmdrepeticao = 10, RULE_expr = 11, RULE_termo = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
			"cmdattrib", "cmdselecao", "cmdrepeticao", "expr", "termo"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'begin'", "'end'", "'num'", "'txt'", "'char'", "'read'", "'write'", 
			"'when'", "'otherwise'", "'enquanto'", "'('", "')'", "';'", null, "'='", 
			"','", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "AP", 
			"FP", "SC", "OP", "ATTR", "VIR", "AC", "FC", "OPREL", "ID", "NUMBER", 
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

	@Override
	public String getGrammarFileName() { return "Bombastic.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	    public void exibeComandos(){
	        for(AbstractCommand c: program.getComandos()){
	            System.out.println(c);
	        }
	    }

	    public void generateCode(){
	        program.generateTarget();
	    }         

	public BombasticParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(T__0);
			setState(27);
			decl();
			setState(28);
			bloco();
			setState(29);
			match(T__1);
			 
			                program.setVarTable(symbolTable);
			                program.setComandos(stack.pop());
			            
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32);
				declaravar();
				}
				}
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaravarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(BombasticParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(BombasticParser.ID, i);
		}
		public TerminalNode SC() { return getToken(BombasticParser.SC, 0); }
		public List<TerminalNode> VIR() { return getTokens(BombasticParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(BombasticParser.VIR, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			tipo();
			setState(38);
			match(ID);
			defineId(symbolTable, _tipo, _input.LT(-1).getText());
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(40);
				match(VIR);
				setState(41);
				match(ID);
				defineId(symbolTable, _tipo, _input.LT(-1).getText());
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(48);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(56);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				match(T__2);
				_tipo = BombasticVariable.NUMBER;
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				match(T__3);
				_tipo = BombasticVariable.TEXT;
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(54);
				match(T__4);
				_tipo = BombasticVariable.CHAR;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 curThread = new ArrayList<AbstractCommand>();
			            stack.push(curThread);
			        
			setState(60); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(59);
				cmd();
				}
				}
				setState(62); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdattribContext cmdattrib() {
			return getRuleContext(CmdattribContext.class,0);
		}
		public CmdselecaoContext cmdselecao() {
			return getRuleContext(CmdselecaoContext.class,0);
		}
		public CmdrepeticaoContext cmdrepeticao() {
			return getRuleContext(CmdrepeticaoContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(69);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				cmdleitura();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(66);
				cmdattrib();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 4);
				{
				setState(67);
				cmdselecao();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 5);
				{
				setState(68);
				cmdrepeticao();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(BombasticParser.AP, 0); }
		public TerminalNode ID() { return getToken(BombasticParser.ID, 0); }
		public TerminalNode FP() { return getToken(BombasticParser.FP, 0); }
		public TerminalNode SC() { return getToken(BombasticParser.SC, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(T__5);
			setState(72);
			match(AP);
			setState(73);
			match(ID);
			verificaId(_input.LT(-1).getText());
			                         _readId = _input.LT(-1).getText();
			                         
			setState(75);
			match(FP);
			setState(76);
			match(SC);
			 
			                    BombasticVariable var = (BombasticVariable)symbolTable.get(_readId);
			                    CommandLeitura cmd = new CommandLeitura(_readId, var);
			                    stack.peek().add(cmd);
			                
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(BombasticParser.AP, 0); }
		public TerminalNode ID() { return getToken(BombasticParser.ID, 0); }
		public TerminalNode FP() { return getToken(BombasticParser.FP, 0); }
		public TerminalNode SC() { return getToken(BombasticParser.SC, 0); }
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(T__6);
			setState(80);
			match(AP);
			setState(81);
			match(ID);
			verificaId(_input.LT(-1).getText());
			                            verificaAtr(_input.LT(-1).getText());
			                            _writeId = _input.LT(-1).getText();
			                            
			setState(83);
			match(FP);
			setState(84);
			match(SC);
			 
			                    CommandEscrita cmd = new CommandEscrita(_writeId);
			                    stack.peek().add(cmd);
			                
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdattribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(BombasticParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(BombasticParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(BombasticParser.SC, 0); }
		public CmdattribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdattrib; }
	}

	public final CmdattribContext cmdattrib() throws RecognitionException {
		CmdattribContext _localctx = new CmdattribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdattrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(ID);
			verificaId(_input.LT(-1).getText());
			                _exprId = _input.LT(-1).getText();
			              
			setState(89);
			match(ATTR);
			_exprContent = "";
			setState(91);
			expr();
			setState(92);
			match(SC);

			                CommandAtribuicao cmd = new CommandAtribuicao(_exprId, _exprContent);
			                BombasticVariable var = (BombasticVariable)symbolTable.get(_exprId);
							var.setValue(_exprContent);
			                stack.peek().add(cmd);
			              
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdselecaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(BombasticParser.AP, 0); }
		public List<TerminalNode> ID() { return getTokens(BombasticParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(BombasticParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(BombasticParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(BombasticParser.FP, 0); }
		public List<TerminalNode> AC() { return getTokens(BombasticParser.AC); }
		public TerminalNode AC(int i) {
			return getToken(BombasticParser.AC, i);
		}
		public List<TerminalNode> FC() { return getTokens(BombasticParser.FC); }
		public TerminalNode FC(int i) {
			return getToken(BombasticParser.FC, i);
		}
		public TerminalNode NUMBER() { return getToken(BombasticParser.NUMBER, 0); }
		public TerminalNode TEXT() { return getToken(BombasticParser.TEXT, 0); }
		public TerminalNode CHAR() { return getToken(BombasticParser.CHAR, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdselecaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdselecao; }
	}

	public final CmdselecaoContext cmdselecao() throws RecognitionException {
		CmdselecaoContext _localctx = new CmdselecaoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdselecao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(T__7);
			setState(96);
			match(AP);
			setState(97);
			match(ID);
			 verificaAtr(_input.LT(-1).getText());
			                        _exprDecision = _input.LT(-1).getText();
			setState(99);
			match(OPREL);
			_exprDecision += _input.LT(-1).getText();
			setState(101);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << NUMBER) | (1L << TEXT) | (1L << CHAR))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			 verificaAtr(_input.LT(-1).getText());
			                                                _exprDecision += _input.LT(-1).getText();
			setState(103);
			match(FP);
			setState(104);
			match(AC);
			 
			                        curThread = new ArrayList<AbstractCommand>();
			                        stack.push(curThread);
			                   
			setState(107); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(106);
				cmd();
				}
				}
				setState(109); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << ID))) != 0) );
			setState(111);
			match(FC);

			                        listaTrue = stack.pop();
			                   
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(113);
				match(T__8);
				setState(114);
				match(AC);
				 
				                        curThread = new ArrayList<AbstractCommand>();
				                        stack.push(curThread);
				                    
				{
				setState(117); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(116);
					cmd();
					}
					}
					setState(119); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << ID))) != 0) );
				}
				setState(121);
				match(FC);

				                        listaFalse = stack.pop();
				                        CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
				                        stack.peek().add(cmd);
				                    
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdrepeticaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(BombasticParser.AP, 0); }
		public List<TerminalNode> ID() { return getTokens(BombasticParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(BombasticParser.ID, i);
		}
		public TerminalNode OPREL() { return getToken(BombasticParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(BombasticParser.FP, 0); }
		public TerminalNode AC() { return getToken(BombasticParser.AC, 0); }
		public TerminalNode FC() { return getToken(BombasticParser.FC, 0); }
		public TerminalNode NUMBER() { return getToken(BombasticParser.NUMBER, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdrepeticaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdrepeticao; }
	}

	public final CmdrepeticaoContext cmdrepeticao() throws RecognitionException {
		CmdrepeticaoContext _localctx = new CmdrepeticaoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdrepeticao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(T__9);
			setState(127);
			match(AP);
			setState(128);
			match(ID);
			_exprDecision = _input.LT(-1).getText();
			setState(130);
			match(OPREL);
			_exprDecision += _input.LT(-1).getText();
			setState(132);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NUMBER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			_exprDecision += _input.LT(-1).getText();
			setState(134);
			match(FP);
			setState(135);
			match(AC);
			 
			                        curThread = new ArrayList<AbstractCommand>();
			                        stack.push(curThread);
			                   
			setState(138); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(137);
				cmd();
				}
				}
				setState(140); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << ID))) != 0) );
			setState(142);
			match(FC);

			                        
			                   
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<TerminalNode> OP() { return getTokens(BombasticParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(BombasticParser.OP, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			termo();
			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(146);
				match(OP);
				_exprContent += _input.LT(-1).getText();
				setState(148);
				termo();
				}
				}
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(BombasticParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(BombasticParser.NUMBER, 0); }
		public TerminalNode TEXT() { return getToken(BombasticParser.TEXT, 0); }
		public TerminalNode CHAR() { return getToken(BombasticParser.CHAR, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_termo);
		try {
			setState(162);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(154);
				match(ID);
				verificaId(_input.LT(-1).getText());
				                _exprContent += _input.LT(-1).getText();
				              
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(156);
				match(NUMBER);

				                _exprContent += _input.LT(-1).getText();
				              
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(158);
				match(TEXT);

				                _exprContent += _input.LT(-1).getText();
				              
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(160);
				match(CHAR);

				                _exprContent += _input.LT(-1).getText();
				              
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32\u00a7\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6\3$\n\3\r"+
		"\3\16\3%\3\4\3\4\3\4\3\4\3\4\3\4\7\4.\n\4\f\4\16\4\61\13\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\5\5;\n\5\3\6\3\6\6\6?\n\6\r\6\16\6@\3\7\3\7\3\7\3"+
		"\7\3\7\5\7H\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13n\n\13\r\13\16\13o\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\6\13x\n\13\r\13\16\13y\3\13\3\13\3\13\5\13\177\n\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u008d\n\f\r\f\16\f"+
		"\u008e\3\f\3\f\3\f\3\r\3\r\3\r\3\r\7\r\u0098\n\r\f\r\16\r\u009b\13\r\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00a5\n\16\3\16\2\2\17\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\2\4\3\2\26\31\3\2\26\27\2\u00aa\2\34\3\2"+
		"\2\2\4#\3\2\2\2\6\'\3\2\2\2\b:\3\2\2\2\n<\3\2\2\2\fG\3\2\2\2\16I\3\2\2"+
		"\2\20Q\3\2\2\2\22Y\3\2\2\2\24a\3\2\2\2\26\u0080\3\2\2\2\30\u0093\3\2\2"+
		"\2\32\u00a4\3\2\2\2\34\35\7\3\2\2\35\36\5\4\3\2\36\37\5\n\6\2\37 \7\4"+
		"\2\2 !\b\2\1\2!\3\3\2\2\2\"$\5\6\4\2#\"\3\2\2\2$%\3\2\2\2%#\3\2\2\2%&"+
		"\3\2\2\2&\5\3\2\2\2\'(\5\b\5\2()\7\26\2\2)/\b\4\1\2*+\7\22\2\2+,\7\26"+
		"\2\2,.\b\4\1\2-*\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\62\3\2\2"+
		"\2\61/\3\2\2\2\62\63\7\17\2\2\63\7\3\2\2\2\64\65\7\5\2\2\65;\b\5\1\2\66"+
		"\67\7\6\2\2\67;\b\5\1\289\7\7\2\29;\b\5\1\2:\64\3\2\2\2:\66\3\2\2\2:8"+
		"\3\2\2\2;\t\3\2\2\2<>\b\6\1\2=?\5\f\7\2>=\3\2\2\2?@\3\2\2\2@>\3\2\2\2"+
		"@A\3\2\2\2A\13\3\2\2\2BH\5\16\b\2CH\5\20\t\2DH\5\22\n\2EH\5\24\13\2FH"+
		"\5\26\f\2GB\3\2\2\2GC\3\2\2\2GD\3\2\2\2GE\3\2\2\2GF\3\2\2\2H\r\3\2\2\2"+
		"IJ\7\b\2\2JK\7\r\2\2KL\7\26\2\2LM\b\b\1\2MN\7\16\2\2NO\7\17\2\2OP\b\b"+
		"\1\2P\17\3\2\2\2QR\7\t\2\2RS\7\r\2\2ST\7\26\2\2TU\b\t\1\2UV\7\16\2\2V"+
		"W\7\17\2\2WX\b\t\1\2X\21\3\2\2\2YZ\7\26\2\2Z[\b\n\1\2[\\\7\21\2\2\\]\b"+
		"\n\1\2]^\5\30\r\2^_\7\17\2\2_`\b\n\1\2`\23\3\2\2\2ab\7\n\2\2bc\7\r\2\2"+
		"cd\7\26\2\2de\b\13\1\2ef\7\25\2\2fg\b\13\1\2gh\t\2\2\2hi\b\13\1\2ij\7"+
		"\16\2\2jk\7\23\2\2km\b\13\1\2ln\5\f\7\2ml\3\2\2\2no\3\2\2\2om\3\2\2\2"+
		"op\3\2\2\2pq\3\2\2\2qr\7\24\2\2r~\b\13\1\2st\7\13\2\2tu\7\23\2\2uw\b\13"+
		"\1\2vx\5\f\7\2wv\3\2\2\2xy\3\2\2\2yw\3\2\2\2yz\3\2\2\2z{\3\2\2\2{|\7\24"+
		"\2\2|}\b\13\1\2}\177\3\2\2\2~s\3\2\2\2~\177\3\2\2\2\177\25\3\2\2\2\u0080"+
		"\u0081\7\f\2\2\u0081\u0082\7\r\2\2\u0082\u0083\7\26\2\2\u0083\u0084\b"+
		"\f\1\2\u0084\u0085\7\25\2\2\u0085\u0086\b\f\1\2\u0086\u0087\t\3\2\2\u0087"+
		"\u0088\b\f\1\2\u0088\u0089\7\16\2\2\u0089\u008a\7\23\2\2\u008a\u008c\b"+
		"\f\1\2\u008b\u008d\5\f\7\2\u008c\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e"+
		"\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091\7\24"+
		"\2\2\u0091\u0092\b\f\1\2\u0092\27\3\2\2\2\u0093\u0099\5\32\16\2\u0094"+
		"\u0095\7\20\2\2\u0095\u0096\b\r\1\2\u0096\u0098\5\32\16\2\u0097\u0094"+
		"\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a"+
		"\31\3\2\2\2\u009b\u0099\3\2\2\2\u009c\u009d\7\26\2\2\u009d\u00a5\b\16"+
		"\1\2\u009e\u009f\7\27\2\2\u009f\u00a5\b\16\1\2\u00a0\u00a1\7\30\2\2\u00a1"+
		"\u00a5\b\16\1\2\u00a2\u00a3\7\31\2\2\u00a3\u00a5\b\16\1\2\u00a4\u009c"+
		"\3\2\2\2\u00a4\u009e\3\2\2\2\u00a4\u00a0\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5"+
		"\33\3\2\2\2\r%/:@Goy~\u008e\u0099\u00a4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}