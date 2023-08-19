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
		RULE_exprCond = 13, RULE_termoCond = 14, RULE_exprRep = 15, RULE_termoRep = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
			"cmdattrib", "cmdselecao", "cmdrepeticao", "expr", "termo", "exprCond", 
			"termoCond", "exprRep", "termoRep"
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
	    private String _exprRepetition;
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
			setState(34);
			match(T__0);
			setState(35);
			decl();
			setState(36);
			bloco();
			setState(37);
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
			setState(41); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(40);
				declaravar();
				}
				}
				setState(43); 
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
			setState(45);
			tipo();
			setState(46);
			match(ID);
			defineId(symbolTable, _tipo, _input.LT(-1).getText());
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(48);
				match(VIR);
				setState(49);
				match(ID);
				defineId(symbolTable, _tipo, _input.LT(-1).getText());
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(56);
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
			setState(64);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				match(T__2);
				_tipo = BombasticVariable.NUMBER;
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				match(T__3);
				_tipo = BombasticVariable.TEXT;
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
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
			        
			setState(68); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(67);
				cmd();
				}
				}
				setState(70); 
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
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				cmdleitura();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				cmdescrita();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(74);
				cmdattrib();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(75);
				cmdselecao();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(76);
				cmdrepeticao();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(77);
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
			setState(80);
			match(T__5);
			setState(81);
			match(AP);
			setState(82);
			match(ID);
			verificaId(_input.LT(-1).getText());
			                         _readId = _input.LT(-1).getText();
			                         
			setState(84);
			match(FP);
			setState(85);
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
			setState(88);
			match(T__6);
			setState(89);
			match(AP);
			setState(90);
			match(ID);
			verificaId(_input.LT(-1).getText());
			                            verificaAtr(_input.LT(-1).getText());
			                            _writeId = _input.LT(-1).getText();
			                            
			setState(92);
			match(FP);
			setState(93);
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
			setState(96);
			match(ID);
			verificaId(_input.LT(-1).getText());
			                _exprId = _input.LT(-1).getText();
			              
			setState(98);
			match(ATTR);
			_exprContent = "";
			setState(100);
			expr();
			setState(101);
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
			setState(104);
			match(T__7);
			setState(105);
			match(AP);
			_exprDecision = "";
			setState(107);
			exprCond();
			setState(108);
			match(OPREL);
			_exprDecision += _input.LT(-1).getText();
			setState(110);
			exprCond();
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPLOG) {
				{
				{
				_exprDecision += " ";
				setState(112);
				match(OPLOG);
				_exprDecision += _input.LT(-1).getText();
				_exprDecision += " ";
				setState(115);
				exprCond();
				setState(116);
				match(OPREL);
				_exprDecision += _input.LT(-1).getText();
				setState(118);
				exprCond();
				}
				}
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(125);
			match(FP);
			setState(126);
			match(AC);
			 
			                        curThread = new ArrayList<AbstractCommand>();
			                        stack.push(curThread);
			                   
			setState(129); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(128);
				cmd();
				}
				}
				setState(131); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
			setState(133);
			match(FC);

			                        listaTrue = stack.pop();
			                   
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(135);
				match(T__8);
				setState(136);
				match(AC);
				 
				                        curThread = new ArrayList<AbstractCommand>();
				                        stack.push(curThread);
				                    
				{
				setState(139); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(138);
					cmd();
					}
					}
					setState(141); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				}
				setState(143);
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
		public List<ExprRepContext> exprRep() {
			return getRuleContexts(ExprRepContext.class);
		}
		public ExprRepContext exprRep(int i) {
			return getRuleContext(ExprRepContext.class,i);
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
			setState(214);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				match(T__9);
				setState(149);
				match(AP);
				_exprRepetition = "";
				setState(151);
				exprRep();
				setState(152);
				match(OPREL);
				_exprRepetition += _input.LT(-1).getText();
				setState(154);
				exprRep();
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPLOG) {
					{
					{
					_exprRepetition += " ";
					setState(156);
					match(OPLOG);
					_exprRepetition += _input.LT(-1).getText();
					_exprRepetition += " ";
					setState(159);
					exprRep();
					setState(160);
					match(OPREL);
					_exprRepetition += _input.LT(-1).getText();
					setState(162);
					exprRep();
					}
					}
					setState(168);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(169);
				match(FP);
				setState(170);
				match(AC);
				 
				                        curThread = new ArrayList<AbstractCommand>();
				                        stack.push(curThread);
				                   
				setState(173); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(172);
					cmd();
					}
					}
					setState(175); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				setState(177);
				match(FC);

				                        listaLoop = stack.pop();
				                        CommandRepeticao cmd = new CommandRepeticao(_exprRepetition, listaLoop, false);
				                        stack.peek().add(cmd);
				                   
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(180);
				match(T__10);
				setState(181);
				match(AC);
				 
				                        curThread = new ArrayList<AbstractCommand>();
				                        stack.push(curThread);
				                   
				setState(184); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(183);
					cmd();
					}
					}
					setState(186); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				setState(188);
				match(FC);
				setState(189);
				match(T__9);
				setState(190);
				match(AP);
				_exprRepetition = "";
				setState(192);
				exprRep();
				setState(193);
				match(OPREL);
				_exprRepetition += _input.LT(-1).getText();
				setState(195);
				exprRep();
				setState(207);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPLOG) {
					{
					{
					_exprRepetition += " ";
					setState(197);
					match(OPLOG);
					_exprRepetition += _input.LT(-1).getText();
					_exprRepetition += " ";
					setState(200);
					exprRep();
					setState(201);
					match(OPREL);
					_exprRepetition += _input.LT(-1).getText();
					setState(203);
					exprRep();
					}
					}
					setState(209);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(210);
				match(FP);
				setState(211);
				match(SC);

				                        listaLoop = stack.pop();
				                        CommandRepeticao cmd = new CommandRepeticao(_exprRepetition, listaLoop, true);
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
			setState(216);
			termo();
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(217);
				match(OP);
				_exprContent += _input.LT(-1).getText();
				setState(219);
				termo();
				}
				}
				setState(224);
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
			setState(233);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(225);
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
				setState(227);
				match(NUMBER);

				                _exprContent += _input.LT(-1).getText();
				              
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(229);
				match(TEXT);

				                _exprContent += _input.LT(-1).getText();
				              
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(231);
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
			setState(235);
			termoCond();
			setState(241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(236);
				match(OP);
				_exprDecision += _input.LT(-1).getText();
				setState(238);
				termoCond();
				}
				}
				setState(243);
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
			setState(252);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(244);
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
				setState(246);
				match(NUMBER);

				                _exprDecision += _input.LT(-1).getText();
				              
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(248);
				match(TEXT);

				                _exprDecision += _input.LT(-1).getText();
				              
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(250);
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

	public static class ExprRepContext extends ParserRuleContext {
		public List<TermoRepContext> termoRep() {
			return getRuleContexts(TermoRepContext.class);
		}
		public TermoRepContext termoRep(int i) {
			return getRuleContext(TermoRepContext.class,i);
		}
		public List<TerminalNode> OP() { return getTokens(BombasticParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(BombasticParser.OP, i);
		}
		public ExprRepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprRep; }
	}

	public final ExprRepContext exprRep() throws RecognitionException {
		ExprRepContext _localctx = new ExprRepContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_exprRep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			termoRep();
			setState(260);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(255);
				match(OP);
				_exprRepetition += _input.LT(-1).getText();
				setState(257);
				termoRep();
				}
				}
				setState(262);
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

	public static class TermoRepContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(BombasticParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(BombasticParser.NUMBER, 0); }
		public TerminalNode TEXT() { return getToken(BombasticParser.TEXT, 0); }
		public TerminalNode CHAR() { return getToken(BombasticParser.CHAR, 0); }
		public TermoRepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termoRep; }
	}

	public final TermoRepContext termoRep() throws RecognitionException {
		TermoRepContext _localctx = new TermoRepContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_termoRep);
		try {
			setState(271);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(263);
				match(ID);
				verificaId(_input.LT(-1).getText());
				                verificaAtr(_input.LT(-1).getText());
				                BombasticVariable varexpr= (BombasticVariable)symbolTable.get(_input.LT(-1).getText());
				                varexpr.setUsed();
				                _exprRepetition += _input.LT(-1).getText();
				              
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(265);
				match(NUMBER);

				                _exprRepetition += _input.LT(-1).getText();
				              
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(267);
				match(TEXT);

				                _exprRepetition += _input.LT(-1).getText();
				              
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(269);
				match(CHAR);

				                _exprRepetition += _input.LT(-1).getText();
				              
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\34\u0114\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6\3,\n\3\r\3\16\3-\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\7\4\66\n\4\f\4\16\49\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5C\n\5"+
		"\3\6\3\6\6\6G\n\6\r\6\16\6H\3\7\3\7\3\7\3\7\3\7\3\7\5\7Q\n\7\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\7\13{\n\13\f\13\16\13~\13\13\3\13\3\13\3\13"+
		"\3\13\6\13\u0084\n\13\r\13\16\13\u0085\3\13\3\13\3\13\3\13\3\13\3\13\6"+
		"\13\u008e\n\13\r\13\16\13\u008f\3\13\3\13\3\13\5\13\u0095\n\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00a7\n\f"+
		"\f\f\16\f\u00aa\13\f\3\f\3\f\3\f\3\f\6\f\u00b0\n\f\r\f\16\f\u00b1\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u00bb\n\f\r\f\16\f\u00bc\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00d0\n\f\f\f"+
		"\16\f\u00d3\13\f\3\f\3\f\3\f\3\f\5\f\u00d9\n\f\3\r\3\r\3\r\3\r\7\r\u00df"+
		"\n\r\f\r\16\r\u00e2\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16"+
		"\u00ec\n\16\3\17\3\17\3\17\3\17\7\17\u00f2\n\17\f\17\16\17\u00f5\13\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00ff\n\20\3\21\3\21\3\21"+
		"\3\21\7\21\u0105\n\21\f\21\16\21\u0108\13\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\5\22\u0112\n\22\3\22\2\2\23\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"\2\2\2\u0121\2$\3\2\2\2\4+\3\2\2\2\6/\3\2\2\2\bB\3\2\2"+
		"\2\nD\3\2\2\2\fP\3\2\2\2\16R\3\2\2\2\20Z\3\2\2\2\22b\3\2\2\2\24j\3\2\2"+
		"\2\26\u00d8\3\2\2\2\30\u00da\3\2\2\2\32\u00eb\3\2\2\2\34\u00ed\3\2\2\2"+
		"\36\u00fe\3\2\2\2 \u0100\3\2\2\2\"\u0111\3\2\2\2$%\7\3\2\2%&\5\4\3\2&"+
		"\'\5\n\6\2\'(\7\4\2\2()\b\2\1\2)\3\3\2\2\2*,\5\6\4\2+*\3\2\2\2,-\3\2\2"+
		"\2-+\3\2\2\2-.\3\2\2\2.\5\3\2\2\2/\60\5\b\5\2\60\61\7\30\2\2\61\67\b\4"+
		"\1\2\62\63\7\23\2\2\63\64\7\30\2\2\64\66\b\4\1\2\65\62\3\2\2\2\669\3\2"+
		"\2\2\67\65\3\2\2\2\678\3\2\2\28:\3\2\2\29\67\3\2\2\2:;\7\20\2\2;\7\3\2"+
		"\2\2<=\7\5\2\2=C\b\5\1\2>?\7\6\2\2?C\b\5\1\2@A\7\7\2\2AC\b\5\1\2B<\3\2"+
		"\2\2B>\3\2\2\2B@\3\2\2\2C\t\3\2\2\2DF\b\6\1\2EG\5\f\7\2FE\3\2\2\2GH\3"+
		"\2\2\2HF\3\2\2\2HI\3\2\2\2I\13\3\2\2\2JQ\5\16\b\2KQ\5\20\t\2LQ\5\22\n"+
		"\2MQ\5\24\13\2NQ\5\26\f\2OQ\5\26\f\2PJ\3\2\2\2PK\3\2\2\2PL\3\2\2\2PM\3"+
		"\2\2\2PN\3\2\2\2PO\3\2\2\2Q\r\3\2\2\2RS\7\b\2\2ST\7\16\2\2TU\7\30\2\2"+
		"UV\b\b\1\2VW\7\17\2\2WX\7\20\2\2XY\b\b\1\2Y\17\3\2\2\2Z[\7\t\2\2[\\\7"+
		"\16\2\2\\]\7\30\2\2]^\b\t\1\2^_\7\17\2\2_`\7\20\2\2`a\b\t\1\2a\21\3\2"+
		"\2\2bc\7\30\2\2cd\b\n\1\2de\7\22\2\2ef\b\n\1\2fg\5\30\r\2gh\7\20\2\2h"+
		"i\b\n\1\2i\23\3\2\2\2jk\7\n\2\2kl\7\16\2\2lm\b\13\1\2mn\5\34\17\2no\7"+
		"\26\2\2op\b\13\1\2p|\5\34\17\2qr\b\13\1\2rs\7\27\2\2st\b\13\1\2tu\b\13"+
		"\1\2uv\5\34\17\2vw\7\26\2\2wx\b\13\1\2xy\5\34\17\2y{\3\2\2\2zq\3\2\2\2"+
		"{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\177\3\2\2\2~|\3\2\2\2\177\u0080\7\17\2"+
		"\2\u0080\u0081\7\24\2\2\u0081\u0083\b\13\1\2\u0082\u0084\5\f\7\2\u0083"+
		"\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2"+
		"\2\2\u0086\u0087\3\2\2\2\u0087\u0088\7\25\2\2\u0088\u0094\b\13\1\2\u0089"+
		"\u008a\7\13\2\2\u008a\u008b\7\24\2\2\u008b\u008d\b\13\1\2\u008c\u008e"+
		"\5\f\7\2\u008d\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u008d\3\2\2\2\u008f"+
		"\u0090\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092\7\25\2\2\u0092\u0093\b"+
		"\13\1\2\u0093\u0095\3\2\2\2\u0094\u0089\3\2\2\2\u0094\u0095\3\2\2\2\u0095"+
		"\25\3\2\2\2\u0096\u0097\7\f\2\2\u0097\u0098\7\16\2\2\u0098\u0099\b\f\1"+
		"\2\u0099\u009a\5 \21\2\u009a\u009b\7\26\2\2\u009b\u009c\b\f\1\2\u009c"+
		"\u00a8\5 \21\2\u009d\u009e\b\f\1\2\u009e\u009f\7\27\2\2\u009f\u00a0\b"+
		"\f\1\2\u00a0\u00a1\b\f\1\2\u00a1\u00a2\5 \21\2\u00a2\u00a3\7\26\2\2\u00a3"+
		"\u00a4\b\f\1\2\u00a4\u00a5\5 \21\2\u00a5\u00a7\3\2\2\2\u00a6\u009d\3\2"+
		"\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\u00ab\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ac\7\17\2\2\u00ac\u00ad\7"+
		"\24\2\2\u00ad\u00af\b\f\1\2\u00ae\u00b0\5\f\7\2\u00af\u00ae\3\2\2\2\u00b0"+
		"\u00b1\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\3\2"+
		"\2\2\u00b3\u00b4\7\25\2\2\u00b4\u00b5\b\f\1\2\u00b5\u00d9\3\2\2\2\u00b6"+
		"\u00b7\7\r\2\2\u00b7\u00b8\7\24\2\2\u00b8\u00ba\b\f\1\2\u00b9\u00bb\5"+
		"\f\7\2\u00ba\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc"+
		"\u00bd\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\7\25\2\2\u00bf\u00c0\7"+
		"\f\2\2\u00c0\u00c1\7\16\2\2\u00c1\u00c2\b\f\1\2\u00c2\u00c3\5 \21\2\u00c3"+
		"\u00c4\7\26\2\2\u00c4\u00c5\b\f\1\2\u00c5\u00d1\5 \21\2\u00c6\u00c7\b"+
		"\f\1\2\u00c7\u00c8\7\27\2\2\u00c8\u00c9\b\f\1\2\u00c9\u00ca\b\f\1\2\u00ca"+
		"\u00cb\5 \21\2\u00cb\u00cc\7\26\2\2\u00cc\u00cd\b\f\1\2\u00cd\u00ce\5"+
		" \21\2\u00ce\u00d0\3\2\2\2\u00cf\u00c6\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1"+
		"\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d4\3\2\2\2\u00d3\u00d1\3\2"+
		"\2\2\u00d4\u00d5\7\17\2\2\u00d5\u00d6\7\20\2\2\u00d6\u00d7\b\f\1\2\u00d7"+
		"\u00d9\3\2\2\2\u00d8\u0096\3\2\2\2\u00d8\u00b6\3\2\2\2\u00d9\27\3\2\2"+
		"\2\u00da\u00e0\5\32\16\2\u00db\u00dc\7\21\2\2\u00dc\u00dd\b\r\1\2\u00dd"+
		"\u00df\5\32\16\2\u00de\u00db\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3"+
		"\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\31\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3"+
		"\u00e4\7\30\2\2\u00e4\u00ec\b\16\1\2\u00e5\u00e6\7\31\2\2\u00e6\u00ec"+
		"\b\16\1\2\u00e7\u00e8\7\32\2\2\u00e8\u00ec\b\16\1\2\u00e9\u00ea\7\33\2"+
		"\2\u00ea\u00ec\b\16\1\2\u00eb\u00e3\3\2\2\2\u00eb\u00e5\3\2\2\2\u00eb"+
		"\u00e7\3\2\2\2\u00eb\u00e9\3\2\2\2\u00ec\33\3\2\2\2\u00ed\u00f3\5\36\20"+
		"\2\u00ee\u00ef\7\21\2\2\u00ef\u00f0\b\17\1\2\u00f0\u00f2\5\36\20\2\u00f1"+
		"\u00ee\3\2\2\2\u00f2\u00f5\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2"+
		"\2\2\u00f4\35\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00f7\7\30\2\2\u00f7\u00ff"+
		"\b\20\1\2\u00f8\u00f9\7\31\2\2\u00f9\u00ff\b\20\1\2\u00fa\u00fb\7\32\2"+
		"\2\u00fb\u00ff\b\20\1\2\u00fc\u00fd\7\33\2\2\u00fd\u00ff\b\20\1\2\u00fe"+
		"\u00f6\3\2\2\2\u00fe\u00f8\3\2\2\2\u00fe\u00fa\3\2\2\2\u00fe\u00fc\3\2"+
		"\2\2\u00ff\37\3\2\2\2\u0100\u0106\5\"\22\2\u0101\u0102\7\21\2\2\u0102"+
		"\u0103\b\21\1\2\u0103\u0105\5\"\22\2\u0104\u0101\3\2\2\2\u0105\u0108\3"+
		"\2\2\2\u0106\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107!\3\2\2\2\u0108\u0106"+
		"\3\2\2\2\u0109\u010a\7\30\2\2\u010a\u0112\b\22\1\2\u010b\u010c\7\31\2"+
		"\2\u010c\u0112\b\22\1\2\u010d\u010e\7\32\2\2\u010e\u0112\b\22\1\2\u010f"+
		"\u0110\7\33\2\2\u0110\u0112\b\22\1\2\u0111\u0109\3\2\2\2\u0111\u010b\3"+
		"\2\2\2\u0111\u010d\3\2\2\2\u0111\u010f\3\2\2\2\u0112#\3\2\2\2\26-\67B"+
		"HP|\u0085\u008f\u0094\u00a8\u00b1\u00bc\u00d1\u00d8\u00e0\u00eb\u00f3"+
		"\u00fe\u0106\u0111";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}