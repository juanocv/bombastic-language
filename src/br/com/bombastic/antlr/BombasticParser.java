package br.com.bombastic.antlr;

// Generated from d:\Fam�lia\Ale\bombastic-language\src\br\com\bombastic\Bombastic.g4 by ANTLR 4.9.2

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
		T__9=10, T__10=11, AP=12, FP=13, SC=14, OP=15, ATTR=16, VIR=17, AC=18, 
		FC=19, OPREL=20, ID=21, NUMBER=22, TEXT=23, CHAR=24, WS=25;
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
	        program.generateTargetJava();
	        program.generateTargetJS();
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
			                verificaUsed(symbolTable);
			            
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
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
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
			setState(70);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				cmdleitura();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				cmdescrita();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(66);
				cmdattrib();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(67);
				cmdselecao();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(68);
				cmdrepeticao();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(69);
				cmdrepeticao();
				}
				break;
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
			setState(72);
			match(T__5);
			setState(73);
			match(AP);
			setState(74);
			match(ID);
			verificaId(_input.LT(-1).getText());
			                         _readId = _input.LT(-1).getText();
			                         
			setState(76);
			match(FP);
			setState(77);
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
			setState(80);
			match(T__6);
			setState(81);
			match(AP);
			setState(82);
			match(ID);
			verificaId(_input.LT(-1).getText());
			                            verificaAtr(_input.LT(-1).getText());
			                            _writeId = _input.LT(-1).getText();
			                            
			setState(84);
			match(FP);
			setState(85);
			match(SC);
			 
			                    CommandEscrita cmd = new CommandEscrita(_writeId);
			                    BombasticVariable varwrite = (BombasticVariable)symbolTable.get(_writeId);
							    varwrite.setUsed();
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
			setState(88);
			match(ID);
			verificaId(_input.LT(-1).getText());
			                _exprId = _input.LT(-1).getText();
			              
			setState(90);
			match(ATTR);
			_exprContent = "";
			setState(92);
			expr();
			setState(93);
			match(SC);

			                CommandAtribuicao cmd = new CommandAtribuicao(_exprId, _exprContent);
			                BombasticVariable varatr = (BombasticVariable)symbolTable.get(_exprId);
							varatr.setValue(_exprContent);
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
			setState(96);
			match(T__7);
			setState(97);
			match(AP);
			setState(98);
			match(ID);
			 verificaAtr(_input.LT(-1).getText());
			                        BombasticVariable varwhen1 = (BombasticVariable)symbolTable.get(_input.LT(-1).getText());
							        varwhen1.setUsed();
			                        _exprDecision = _input.LT(-1).getText();
			setState(100);
			match(OPREL);
			_exprDecision += _input.LT(-1).getText();
			setState(107);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(102);
				match(ID);
				 verificaAtr(_input.LT(-1).getText());
				                         BombasticVariable varwhen2 = (BombasticVariable)symbolTable.get(_input.LT(-1).getText());
								         varwhen2.setUsed();
				}
				break;
			case NUMBER:
				{
				setState(104);
				match(NUMBER);
				}
				break;
			case TEXT:
				{
				setState(105);
				match(TEXT);
				}
				break;
			case CHAR:
				{
				setState(106);
				match(CHAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

			                                                _exprDecision += _input.LT(-1).getText();
			setState(110);
			match(FP);
			setState(111);
			match(AC);
			 
			                        curThread = new ArrayList<AbstractCommand>();
			                        stack.push(curThread);
			                   
			setState(114); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(113);
				cmd();
				}
				}
				setState(116); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
			setState(118);
			match(FC);

			                        listaTrue = stack.pop();
			                   
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(120);
				match(T__8);
				setState(121);
				match(AC);
				 
				                        curThread = new ArrayList<AbstractCommand>();
				                        stack.push(curThread);
				                    
				{
				setState(124); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(123);
					cmd();
					}
					}
					setState(126); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				}
				setState(128);
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
		public TerminalNode TEXT() { return getToken(BombasticParser.TEXT, 0); }
		public TerminalNode CHAR() { return getToken(BombasticParser.CHAR, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode SC() { return getToken(BombasticParser.SC, 0); }
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
			setState(185);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				match(T__9);
				setState(134);
				match(AP);
				setState(135);
				match(ID);
				 verificaAtr(_input.LT(-1).getText());
				                        BombasticVariable varwhile1 = (BombasticVariable)symbolTable.get(_input.LT(-1).getText());
								        varwhile1.setUsed();
				                        _exprDecision = _input.LT(-1).getText();
				setState(137);
				match(OPREL);
				_exprDecision += _input.LT(-1).getText();
				setState(144);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(139);
					match(ID);
					 verificaAtr(_input.LT(-1).getText());
					                         BombasticVariable varwhile2 = (BombasticVariable)symbolTable.get(_input.LT(-1).getText());
									         varwhile2.setUsed();
					}
					break;
				case NUMBER:
					{
					setState(141);
					match(NUMBER);
					}
					break;
				case TEXT:
					{
					setState(142);
					match(TEXT);
					}
					break;
				case CHAR:
					{
					setState(143);
					match(CHAR);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				 verificaAtr(_input.LT(-1).getText());
				                                                
				                                                _exprDecision += _input.LT(-1).getText();
				setState(147);
				match(FP);
				setState(148);
				match(AC);
				 
				                        curThread = new ArrayList<AbstractCommand>();
				                        stack.push(curThread);
				                   
				setState(151); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(150);
					cmd();
					}
					}
					setState(153); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				setState(155);
				match(FC);

				                        listaLoop = stack.pop();
				                        CommandRepeticao cmd = new CommandRepeticao(_exprDecision, listaLoop,false);
				                        stack.peek().add(cmd);
				                   
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				match(T__10);
				setState(159);
				match(AC);
				 
				                        curThread = new ArrayList<AbstractCommand>();
				                        stack.push(curThread);
				                   
				setState(162); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(161);
					cmd();
					}
					}
					setState(164); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				setState(166);
				match(FC);
				setState(167);
				match(T__9);
				setState(168);
				match(AP);
				setState(169);
				match(ID);
				 verificaAtr(_input.LT(-1).getText());
				                        _exprDecision = _input.LT(-1).getText();
				                        BombasticVariable vardo1 = (BombasticVariable)symbolTable.get(_input.LT(-1).getText());
								        vardo1.setUsed();
				setState(171);
				match(OPREL);
				_exprDecision += _input.LT(-1).getText();
				setState(178);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(173);
					match(ID);
					 verificaAtr(_input.LT(-1).getText());
					                         BombasticVariable vardo2 = (BombasticVariable)symbolTable.get(_input.LT(-1).getText());
									         vardo2.setUsed();
					}
					break;
				case NUMBER:
					{
					setState(175);
					match(NUMBER);
					}
					break;
				case TEXT:
					{
					setState(176);
					match(TEXT);
					}
					break;
				case CHAR:
					{
					setState(177);
					match(CHAR);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}

				                                                _exprDecision += _input.LT(-1).getText();
				setState(181);
				match(FP);
				setState(182);
				match(SC);

				                        listaLoop = stack.pop();
				                        CommandRepeticao cmd = new CommandRepeticao(_exprDecision, listaLoop,true);
				                        stack.peek().add(cmd);
				                   
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
			setState(187);
			termo();
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(188);
				match(OP);
				_exprContent += _input.LT(-1).getText();
				setState(190);
				termo();
				}
				}
				setState(195);
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
			setState(204);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(196);
				match(ID);
				verificaId(_input.LT(-1).getText());
				                verificaAtr(_input.LT(-1).getText());
				                BombasticVariable varexpr= (BombasticVariable)symbolTable.get(_input.LT(-1).getText());
				                varexpr.setUsed();
				                _exprContent += _input.LT(-1).getText();
				              
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(198);
				match(NUMBER);

				                _exprContent += _input.LT(-1).getText();
				              
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(200);
				match(TEXT);

				                _exprContent += _input.LT(-1).getText();
				              
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(202);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\33\u00d1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6\3$\n\3\r"+
		"\3\16\3%\3\4\3\4\3\4\3\4\3\4\3\4\7\4.\n\4\f\4\16\4\61\13\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\5\5;\n\5\3\6\3\6\6\6?\n\6\r\6\16\6@\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\5\7I\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\5\13n\n\13\3\13\3\13\3\13\3\13\3\13\6\13"+
		"u\n\13\r\13\16\13v\3\13\3\13\3\13\3\13\3\13\3\13\6\13\177\n\13\r\13\16"+
		"\13\u0080\3\13\3\13\3\13\5\13\u0086\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\5\f\u0093\n\f\3\f\3\f\3\f\3\f\3\f\6\f\u009a\n\f\r\f\16"+
		"\f\u009b\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u00a5\n\f\r\f\16\f\u00a6\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00b5\n\f\3\f\3\f\3\f"+
		"\3\f\3\f\5\f\u00bc\n\f\3\r\3\r\3\r\3\r\7\r\u00c2\n\r\f\r\16\r\u00c5\13"+
		"\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00cf\n\16\3\16\2\2\17"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\2\2\2\u00e0\2\34\3\2\2\2\4#\3\2\2\2"+
		"\6\'\3\2\2\2\b:\3\2\2\2\n<\3\2\2\2\fH\3\2\2\2\16J\3\2\2\2\20R\3\2\2\2"+
		"\22Z\3\2\2\2\24b\3\2\2\2\26\u00bb\3\2\2\2\30\u00bd\3\2\2\2\32\u00ce\3"+
		"\2\2\2\34\35\7\3\2\2\35\36\5\4\3\2\36\37\5\n\6\2\37 \7\4\2\2 !\b\2\1\2"+
		"!\3\3\2\2\2\"$\5\6\4\2#\"\3\2\2\2$%\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\5\3\2"+
		"\2\2\'(\5\b\5\2()\7\27\2\2)/\b\4\1\2*+\7\23\2\2+,\7\27\2\2,.\b\4\1\2-"+
		"*\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\62\3\2\2\2\61/\3\2\2\2"+
		"\62\63\7\20\2\2\63\7\3\2\2\2\64\65\7\5\2\2\65;\b\5\1\2\66\67\7\6\2\2\67"+
		";\b\5\1\289\7\7\2\29;\b\5\1\2:\64\3\2\2\2:\66\3\2\2\2:8\3\2\2\2;\t\3\2"+
		"\2\2<>\b\6\1\2=?\5\f\7\2>=\3\2\2\2?@\3\2\2\2@>\3\2\2\2@A\3\2\2\2A\13\3"+
		"\2\2\2BI\5\16\b\2CI\5\20\t\2DI\5\22\n\2EI\5\24\13\2FI\5\26\f\2GI\5\26"+
		"\f\2HB\3\2\2\2HC\3\2\2\2HD\3\2\2\2HE\3\2\2\2HF\3\2\2\2HG\3\2\2\2I\r\3"+
		"\2\2\2JK\7\b\2\2KL\7\16\2\2LM\7\27\2\2MN\b\b\1\2NO\7\17\2\2OP\7\20\2\2"+
		"PQ\b\b\1\2Q\17\3\2\2\2RS\7\t\2\2ST\7\16\2\2TU\7\27\2\2UV\b\t\1\2VW\7\17"+
		"\2\2WX\7\20\2\2XY\b\t\1\2Y\21\3\2\2\2Z[\7\27\2\2[\\\b\n\1\2\\]\7\22\2"+
		"\2]^\b\n\1\2^_\5\30\r\2_`\7\20\2\2`a\b\n\1\2a\23\3\2\2\2bc\7\n\2\2cd\7"+
		"\16\2\2de\7\27\2\2ef\b\13\1\2fg\7\26\2\2gm\b\13\1\2hi\7\27\2\2in\b\13"+
		"\1\2jn\7\30\2\2kn\7\31\2\2ln\7\32\2\2mh\3\2\2\2mj\3\2\2\2mk\3\2\2\2ml"+
		"\3\2\2\2no\3\2\2\2op\b\13\1\2pq\7\17\2\2qr\7\24\2\2rt\b\13\1\2su\5\f\7"+
		"\2ts\3\2\2\2uv\3\2\2\2vt\3\2\2\2vw\3\2\2\2wx\3\2\2\2xy\7\25\2\2y\u0085"+
		"\b\13\1\2z{\7\13\2\2{|\7\24\2\2|~\b\13\1\2}\177\5\f\7\2~}\3\2\2\2\177"+
		"\u0080\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082\3\2\2\2"+
		"\u0082\u0083\7\25\2\2\u0083\u0084\b\13\1\2\u0084\u0086\3\2\2\2\u0085z"+
		"\3\2\2\2\u0085\u0086\3\2\2\2\u0086\25\3\2\2\2\u0087\u0088\7\f\2\2\u0088"+
		"\u0089\7\16\2\2\u0089\u008a\7\27\2\2\u008a\u008b\b\f\1\2\u008b\u008c\7"+
		"\26\2\2\u008c\u0092\b\f\1\2\u008d\u008e\7\27\2\2\u008e\u0093\b\f\1\2\u008f"+
		"\u0093\7\30\2\2\u0090\u0093\7\31\2\2\u0091\u0093\7\32\2\2\u0092\u008d"+
		"\3\2\2\2\u0092\u008f\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0091\3\2\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\u0095\b\f\1\2\u0095\u0096\7\17\2\2\u0096\u0097\7"+
		"\24\2\2\u0097\u0099\b\f\1\2\u0098\u009a\5\f\7\2\u0099\u0098\3\2\2\2\u009a"+
		"\u009b\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3\2"+
		"\2\2\u009d\u009e\7\25\2\2\u009e\u009f\b\f\1\2\u009f\u00bc\3\2\2\2\u00a0"+
		"\u00a1\7\r\2\2\u00a1\u00a2\7\24\2\2\u00a2\u00a4\b\f\1\2\u00a3\u00a5\5"+
		"\f\7\2\u00a4\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6"+
		"\u00a7\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\7\25\2\2\u00a9\u00aa\7"+
		"\f\2\2\u00aa\u00ab\7\16\2\2\u00ab\u00ac\7\27\2\2\u00ac\u00ad\b\f\1\2\u00ad"+
		"\u00ae\7\26\2\2\u00ae\u00b4\b\f\1\2\u00af\u00b0\7\27\2\2\u00b0\u00b5\b"+
		"\f\1\2\u00b1\u00b5\7\30\2\2\u00b2\u00b5\7\31\2\2\u00b3\u00b5\7\32\2\2"+
		"\u00b4\u00af\3\2\2\2\u00b4\u00b1\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b3"+
		"\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\b\f\1\2\u00b7\u00b8\7\17\2\2"+
		"\u00b8\u00b9\7\20\2\2\u00b9\u00ba\b\f\1\2\u00ba\u00bc\3\2\2\2\u00bb\u0087"+
		"\3\2\2\2\u00bb\u00a0\3\2\2\2\u00bc\27\3\2\2\2\u00bd\u00c3\5\32\16\2\u00be"+
		"\u00bf\7\21\2\2\u00bf\u00c0\b\r\1\2\u00c0\u00c2\5\32\16\2\u00c1\u00be"+
		"\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4"+
		"\31\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00c7\7\27\2\2\u00c7\u00cf\b\16"+
		"\1\2\u00c8\u00c9\7\30\2\2\u00c9\u00cf\b\16\1\2\u00ca\u00cb\7\31\2\2\u00cb"+
		"\u00cf\b\16\1\2\u00cc\u00cd\7\32\2\2\u00cd\u00cf\b\16\1\2\u00ce\u00c6"+
		"\3\2\2\2\u00ce\u00c8\3\2\2\2\u00ce\u00ca\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf"+
		"\33\3\2\2\2\22%/:@Hmv\u0080\u0085\u0092\u009b\u00a6\u00b4\u00bb\u00c3"+
		"\u00ce";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}