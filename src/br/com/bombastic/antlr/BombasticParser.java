package br.com.bombastic.antlr;

// Generated from c:\Users\astan\OneDrive\Documentos\bombastic-language\src\br\com\bombastic\Bombastic.g4 by ANTLR 4.9.2

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
		FC=19, OPREL=20, OPLOG=21, ID=22, NUMBER=23, TEXT=24, CHAR=25, WS=26;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_cmdselecao = 9, RULE_cmdrepeticao = 10, RULE_expr = 11, RULE_termo = 12, 
		RULE_exprCond = 13, RULE_termoCond = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
			"cmdattrib", "cmdselecao", "cmdrepeticao", "expr", "termo", "exprCond", 
			"termoCond"
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
			"AP", "FP", "SC", "OP", "ATTR", "VIR", "AC", "FC", "OPREL", "OPLOG", 
			"ID", "NUMBER", "TEXT", "CHAR", "WS"
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
			setState(30);
			match(T__0);
			setState(31);
			decl();
			setState(32);
			bloco();
			setState(33);
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
			setState(37); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(36);
				declaravar();
				}
				}
				setState(39); 
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
			setState(41);
			tipo();
			setState(42);
			match(ID);
			defineId(symbolTable, _tipo, _input.LT(-1).getText());
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(44);
				match(VIR);
				setState(45);
				match(ID);
				defineId(symbolTable, _tipo, _input.LT(-1).getText());
				}
				}
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52);
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
			setState(60);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				match(T__2);
				_tipo = BombasticVariable.NUMBER;
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
				match(T__3);
				_tipo = BombasticVariable.TEXT;
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(58);
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
			        
			setState(64); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(63);
				cmd();
				}
				}
				setState(66); 
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
			setState(74);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				cmdleitura();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				cmdescrita();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				cmdattrib();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(71);
				cmdselecao();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(72);
				cmdrepeticao();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(73);
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
			setState(76);
			match(T__5);
			setState(77);
			match(AP);
			setState(78);
			match(ID);
			verificaId(_input.LT(-1).getText());
			                         _readId = _input.LT(-1).getText();
			                         
			setState(80);
			match(FP);
			setState(81);
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
			setState(84);
			match(T__6);
			setState(85);
			match(AP);
			setState(86);
			match(ID);
			verificaId(_input.LT(-1).getText());
			                            verificaAtr(_input.LT(-1).getText());
			                            _writeId = _input.LT(-1).getText();
			                            
			setState(88);
			match(FP);
			setState(89);
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
			setState(92);
			match(ID);
			verificaId(_input.LT(-1).getText());
			                _exprId = _input.LT(-1).getText();
			              
			setState(94);
			match(ATTR);
			_exprContent = "";
			setState(96);
			expr();
			setState(97);
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
		public List<ExprCondContext> exprCond() {
			return getRuleContexts(ExprCondContext.class);
		}
		public ExprCondContext exprCond(int i) {
			return getRuleContext(ExprCondContext.class,i);
		}
		public List<TerminalNode> OPREL() { return getTokens(BombasticParser.OPREL); }
		public TerminalNode OPREL(int i) {
			return getToken(BombasticParser.OPREL, i);
		}
		public TerminalNode FP() { return getToken(BombasticParser.FP, 0); }
		public List<TerminalNode> AC() { return getTokens(BombasticParser.AC); }
		public TerminalNode AC(int i) {
			return getToken(BombasticParser.AC, i);
		}
		public List<TerminalNode> FC() { return getTokens(BombasticParser.FC); }
		public TerminalNode FC(int i) {
			return getToken(BombasticParser.FC, i);
		}
		public List<TerminalNode> OPLOG() { return getTokens(BombasticParser.OPLOG); }
		public TerminalNode OPLOG(int i) {
			return getToken(BombasticParser.OPLOG, i);
		}
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
			setState(100);
			match(T__7);
			setState(101);
			match(AP);
			_exprDecision = "";
			setState(103);
			exprCond();
			setState(104);
			match(OPREL);
			_exprDecision += _input.LT(-1).getText();
			setState(106);
			exprCond();
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPLOG) {
				{
				{
				_exprDecision += " ";
				setState(108);
				match(OPLOG);
				_exprDecision += _input.LT(-1).getText();
				_exprDecision += " ";
				setState(111);
				exprCond();
				setState(112);
				match(OPREL);
				_exprDecision += _input.LT(-1).getText();
				setState(114);
				exprCond();
				}
				}
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(121);
			match(FP);
			setState(122);
			match(AC);
			 
			                        curThread = new ArrayList<AbstractCommand>();
			                        stack.push(curThread);
			                   
			setState(125); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(124);
				cmd();
				}
				}
				setState(127); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
			setState(129);
			match(FC);

			                        listaTrue = stack.pop();
			                   
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(131);
				match(T__8);
				setState(132);
				match(AC);
				 
				                        curThread = new ArrayList<AbstractCommand>();
				                        stack.push(curThread);
				                    
				{
				setState(135); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(134);
					cmd();
					}
					}
					setState(137); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				}
				setState(139);
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
		public List<ExprCondContext> exprCond() {
			return getRuleContexts(ExprCondContext.class);
		}
		public ExprCondContext exprCond(int i) {
			return getRuleContext(ExprCondContext.class,i);
		}
		public List<TerminalNode> OPREL() { return getTokens(BombasticParser.OPREL); }
		public TerminalNode OPREL(int i) {
			return getToken(BombasticParser.OPREL, i);
		}
		public TerminalNode FP() { return getToken(BombasticParser.FP, 0); }
		public TerminalNode AC() { return getToken(BombasticParser.AC, 0); }
		public TerminalNode FC() { return getToken(BombasticParser.FC, 0); }
		public List<TerminalNode> OPLOG() { return getTokens(BombasticParser.OPLOG); }
		public TerminalNode OPLOG(int i) {
			return getToken(BombasticParser.OPLOG, i);
		}
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
			setState(210);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				match(T__9);
				setState(145);
				match(AP);
				_exprDecision = "";
				setState(147);
				exprCond();
				setState(148);
				match(OPREL);
				_exprDecision += _input.LT(-1).getText();
				setState(150);
				exprCond();
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPLOG) {
					{
					{
					_exprDecision += " ";
					setState(152);
					match(OPLOG);
					_exprDecision += _input.LT(-1).getText();
					_exprDecision += " ";
					setState(155);
					exprCond();
					setState(156);
					match(OPREL);
					_exprDecision += _input.LT(-1).getText();
					setState(158);
					exprCond();
					}
					}
					setState(164);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(165);
				match(FP);
				setState(166);
				match(AC);
				 
				                        curThread = new ArrayList<AbstractCommand>();
				                        stack.push(curThread);
				                   
				setState(169); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(168);
					cmd();
					}
					}
					setState(171); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				setState(173);
				match(FC);

				                        listaLoop = stack.pop();
				                        CommandRepeticao cmd = new CommandRepeticao(_exprDecision, listaLoop,false);
				                        stack.peek().add(cmd);
				                   
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(176);
				match(T__10);
				setState(177);
				match(AC);
				 
				                        curThread = new ArrayList<AbstractCommand>();
				                        stack.push(curThread);
				                   
				setState(180); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(179);
					cmd();
					}
					}
					setState(182); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				setState(184);
				match(FC);
				setState(185);
				match(T__9);
				setState(186);
				match(AP);
				_exprDecision = "";
				setState(188);
				exprCond();
				setState(189);
				match(OPREL);
				_exprDecision += _input.LT(-1).getText();
				setState(191);
				exprCond();
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPLOG) {
					{
					{
					_exprDecision += " ";
					setState(193);
					match(OPLOG);
					_exprDecision += _input.LT(-1).getText();
					_exprDecision += " ";
					setState(196);
					exprCond();
					setState(197);
					match(OPREL);
					_exprDecision += _input.LT(-1).getText();
					setState(199);
					exprCond();
					}
					}
					setState(205);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(206);
				match(FP);
				setState(207);
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
			setState(212);
			termo();
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(213);
				match(OP);
				_exprContent += _input.LT(-1).getText();
				setState(215);
				termo();
				}
				}
				setState(220);
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
			setState(229);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(221);
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
				setState(223);
				match(NUMBER);

				                _exprContent += _input.LT(-1).getText();
				              
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(225);
				match(TEXT);

				                _exprContent += _input.LT(-1).getText();
				              
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(227);
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

	public static class ExprCondContext extends ParserRuleContext {
		public List<TermoCondContext> termoCond() {
			return getRuleContexts(TermoCondContext.class);
		}
		public TermoCondContext termoCond(int i) {
			return getRuleContext(TermoCondContext.class,i);
		}
		public List<TerminalNode> OP() { return getTokens(BombasticParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(BombasticParser.OP, i);
		}
		public ExprCondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprCond; }
	}

	public final ExprCondContext exprCond() throws RecognitionException {
		ExprCondContext _localctx = new ExprCondContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_exprCond);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			termoCond();
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(232);
				match(OP);
				_exprDecision += _input.LT(-1).getText();
				setState(234);
				termoCond();
				}
				}
				setState(239);
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

	public static class TermoCondContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(BombasticParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(BombasticParser.NUMBER, 0); }
		public TerminalNode TEXT() { return getToken(BombasticParser.TEXT, 0); }
		public TerminalNode CHAR() { return getToken(BombasticParser.CHAR, 0); }
		public TermoCondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termoCond; }
	}

	public final TermoCondContext termoCond() throws RecognitionException {
		TermoCondContext _localctx = new TermoCondContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_termoCond);
		try {
			setState(248);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(240);
				match(ID);
				verificaId(_input.LT(-1).getText());
				                verificaAtr(_input.LT(-1).getText());
				                BombasticVariable varexpr= (BombasticVariable)symbolTable.get(_input.LT(-1).getText());
				                varexpr.setUsed();
				                _exprDecision += _input.LT(-1).getText();
				              
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(242);
				match(NUMBER);

				                _exprDecision += _input.LT(-1).getText();
				              
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(244);
				match(TEXT);

				                _exprDecision += _input.LT(-1).getText();
				              
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(246);
				match(CHAR);

				                _exprDecision += _input.LT(-1).getText();
				              
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\34\u00fd\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\6\3(\n\3\r\3\16\3)\3\4\3\4\3\4\3\4\3\4\3\4\7\4\62\n\4\f\4\16"+
		"\4\65\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5?\n\5\3\6\3\6\6\6C\n\6\r"+
		"\6\16\6D\3\7\3\7\3\7\3\7\3\7\3\7\5\7M\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\7\13w\n\13\f\13\16\13z\13\13\3\13\3\13\3\13\3\13\6\13\u0080\n"+
		"\13\r\13\16\13\u0081\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u008a\n\13\r\13"+
		"\16\13\u008b\3\13\3\13\3\13\5\13\u0091\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00a3\n\f\f\f\16\f\u00a6\13"+
		"\f\3\f\3\f\3\f\3\f\6\f\u00ac\n\f\r\f\16\f\u00ad\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\6\f\u00b7\n\f\r\f\16\f\u00b8\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00cc\n\f\f\f\16\f\u00cf\13\f\3"+
		"\f\3\f\3\f\3\f\5\f\u00d5\n\f\3\r\3\r\3\r\3\r\7\r\u00db\n\r\f\r\16\r\u00de"+
		"\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00e8\n\16\3\17\3\17"+
		"\3\17\3\17\7\17\u00ee\n\17\f\17\16\17\u00f1\13\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\5\20\u00fb\n\20\3\20\2\2\21\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36\2\2\2\u0108\2 \3\2\2\2\4\'\3\2\2\2\6+\3\2\2\2\b>\3"+
		"\2\2\2\n@\3\2\2\2\fL\3\2\2\2\16N\3\2\2\2\20V\3\2\2\2\22^\3\2\2\2\24f\3"+
		"\2\2\2\26\u00d4\3\2\2\2\30\u00d6\3\2\2\2\32\u00e7\3\2\2\2\34\u00e9\3\2"+
		"\2\2\36\u00fa\3\2\2\2 !\7\3\2\2!\"\5\4\3\2\"#\5\n\6\2#$\7\4\2\2$%\b\2"+
		"\1\2%\3\3\2\2\2&(\5\6\4\2\'&\3\2\2\2()\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*\5"+
		"\3\2\2\2+,\5\b\5\2,-\7\30\2\2-\63\b\4\1\2./\7\23\2\2/\60\7\30\2\2\60\62"+
		"\b\4\1\2\61.\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\66\3"+
		"\2\2\2\65\63\3\2\2\2\66\67\7\20\2\2\67\7\3\2\2\289\7\5\2\29?\b\5\1\2:"+
		";\7\6\2\2;?\b\5\1\2<=\7\7\2\2=?\b\5\1\2>8\3\2\2\2>:\3\2\2\2><\3\2\2\2"+
		"?\t\3\2\2\2@B\b\6\1\2AC\5\f\7\2BA\3\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3\2\2"+
		"\2E\13\3\2\2\2FM\5\16\b\2GM\5\20\t\2HM\5\22\n\2IM\5\24\13\2JM\5\26\f\2"+
		"KM\5\26\f\2LF\3\2\2\2LG\3\2\2\2LH\3\2\2\2LI\3\2\2\2LJ\3\2\2\2LK\3\2\2"+
		"\2M\r\3\2\2\2NO\7\b\2\2OP\7\16\2\2PQ\7\30\2\2QR\b\b\1\2RS\7\17\2\2ST\7"+
		"\20\2\2TU\b\b\1\2U\17\3\2\2\2VW\7\t\2\2WX\7\16\2\2XY\7\30\2\2YZ\b\t\1"+
		"\2Z[\7\17\2\2[\\\7\20\2\2\\]\b\t\1\2]\21\3\2\2\2^_\7\30\2\2_`\b\n\1\2"+
		"`a\7\22\2\2ab\b\n\1\2bc\5\30\r\2cd\7\20\2\2de\b\n\1\2e\23\3\2\2\2fg\7"+
		"\n\2\2gh\7\16\2\2hi\b\13\1\2ij\5\34\17\2jk\7\26\2\2kl\b\13\1\2lx\5\34"+
		"\17\2mn\b\13\1\2no\7\27\2\2op\b\13\1\2pq\b\13\1\2qr\5\34\17\2rs\7\26\2"+
		"\2st\b\13\1\2tu\5\34\17\2uw\3\2\2\2vm\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3"+
		"\2\2\2y{\3\2\2\2zx\3\2\2\2{|\7\17\2\2|}\7\24\2\2}\177\b\13\1\2~\u0080"+
		"\5\f\7\2\177~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082"+
		"\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084\7\25\2\2\u0084\u0090\b\13\1\2"+
		"\u0085\u0086\7\13\2\2\u0086\u0087\7\24\2\2\u0087\u0089\b\13\1\2\u0088"+
		"\u008a\5\f\7\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0089\3\2"+
		"\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\7\25\2\2\u008e"+
		"\u008f\b\13\1\2\u008f\u0091\3\2\2\2\u0090\u0085\3\2\2\2\u0090\u0091\3"+
		"\2\2\2\u0091\25\3\2\2\2\u0092\u0093\7\f\2\2\u0093\u0094\7\16\2\2\u0094"+
		"\u0095\b\f\1\2\u0095\u0096\5\34\17\2\u0096\u0097\7\26\2\2\u0097\u0098"+
		"\b\f\1\2\u0098\u00a4\5\34\17\2\u0099\u009a\b\f\1\2\u009a\u009b\7\27\2"+
		"\2\u009b\u009c\b\f\1\2\u009c\u009d\b\f\1\2\u009d\u009e\5\34\17\2\u009e"+
		"\u009f\7\26\2\2\u009f\u00a0\b\f\1\2\u00a0\u00a1\5\34\17\2\u00a1\u00a3"+
		"\3\2\2\2\u00a2\u0099\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00a8\7\17"+
		"\2\2\u00a8\u00a9\7\24\2\2\u00a9\u00ab\b\f\1\2\u00aa\u00ac\5\f\7\2\u00ab"+
		"\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2"+
		"\2\2\u00ae\u00af\3\2\2\2\u00af\u00b0\7\25\2\2\u00b0\u00b1\b\f\1\2\u00b1"+
		"\u00d5\3\2\2\2\u00b2\u00b3\7\r\2\2\u00b3\u00b4\7\24\2\2\u00b4\u00b6\b"+
		"\f\1\2\u00b5\u00b7\5\f\7\2\u00b6\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8"+
		"\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\7\25"+
		"\2\2\u00bb\u00bc\7\f\2\2\u00bc\u00bd\7\16\2\2\u00bd\u00be\b\f\1\2\u00be"+
		"\u00bf\5\34\17\2\u00bf\u00c0\7\26\2\2\u00c0\u00c1\b\f\1\2\u00c1\u00cd"+
		"\5\34\17\2\u00c2\u00c3\b\f\1\2\u00c3\u00c4\7\27\2\2\u00c4\u00c5\b\f\1"+
		"\2\u00c5\u00c6\b\f\1\2\u00c6\u00c7\5\34\17\2\u00c7\u00c8\7\26\2\2\u00c8"+
		"\u00c9\b\f\1\2\u00c9\u00ca\5\34\17\2\u00ca\u00cc\3\2\2\2\u00cb\u00c2\3"+
		"\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce"+
		"\u00d0\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00d1\7\17\2\2\u00d1\u00d2\7"+
		"\20\2\2\u00d2\u00d3\b\f\1\2\u00d3\u00d5\3\2\2\2\u00d4\u0092\3\2\2\2\u00d4"+
		"\u00b2\3\2\2\2\u00d5\27\3\2\2\2\u00d6\u00dc\5\32\16\2\u00d7\u00d8\7\21"+
		"\2\2\u00d8\u00d9\b\r\1\2\u00d9\u00db\5\32\16\2\u00da\u00d7\3\2\2\2\u00db"+
		"\u00de\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\31\3\2\2"+
		"\2\u00de\u00dc\3\2\2\2\u00df\u00e0\7\30\2\2\u00e0\u00e8\b\16\1\2\u00e1"+
		"\u00e2\7\31\2\2\u00e2\u00e8\b\16\1\2\u00e3\u00e4\7\32\2\2\u00e4\u00e8"+
		"\b\16\1\2\u00e5\u00e6\7\33\2\2\u00e6\u00e8\b\16\1\2\u00e7\u00df\3\2\2"+
		"\2\u00e7\u00e1\3\2\2\2\u00e7\u00e3\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e8\33"+
		"\3\2\2\2\u00e9\u00ef\5\36\20\2\u00ea\u00eb\7\21\2\2\u00eb\u00ec\b\17\1"+
		"\2\u00ec\u00ee\5\36\20\2\u00ed\u00ea\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef"+
		"\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\35\3\2\2\2\u00f1\u00ef\3\2\2"+
		"\2\u00f2\u00f3\7\30\2\2\u00f3\u00fb\b\20\1\2\u00f4\u00f5\7\31\2\2\u00f5"+
		"\u00fb\b\20\1\2\u00f6\u00f7\7\32\2\2\u00f7\u00fb\b\20\1\2\u00f8\u00f9"+
		"\7\33\2\2\u00f9\u00fb\b\20\1\2\u00fa\u00f2\3\2\2\2\u00fa\u00f4\3\2\2\2"+
		"\u00fa\u00f6\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb\37\3\2\2\2\24)\63>DLx\u0081"+
		"\u008b\u0090\u00a4\u00ad\u00b8\u00cd\u00d4\u00dc\u00e7\u00ef\u00fa";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}