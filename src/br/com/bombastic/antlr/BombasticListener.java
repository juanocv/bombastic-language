package br.com.bombastic.antlr;
// Generated from Bombastic.g4 by ANTLR 4.9.2

    import br.com.bombastic.datastructures.*;
    import br.com.bombastic.exceptions.*;
    import br.com.bombastic.ast.*;
    import java.util.ArrayList;
    import java.util.Stack;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BombasticParser}.
 */
public interface BombasticListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BombasticParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(BombasticParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link BombasticParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(BombasticParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link BombasticParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(BombasticParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link BombasticParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(BombasticParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link BombasticParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void enterDeclaravar(BombasticParser.DeclaravarContext ctx);
	/**
	 * Exit a parse tree produced by {@link BombasticParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void exitDeclaravar(BombasticParser.DeclaravarContext ctx);
	/**
	 * Enter a parse tree produced by {@link BombasticParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(BombasticParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link BombasticParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(BombasticParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link BombasticParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(BombasticParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link BombasticParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(BombasticParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link BombasticParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(BombasticParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link BombasticParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(BombasticParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link BombasticParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdleitura(BombasticParser.CmdleituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link BombasticParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdleitura(BombasticParser.CmdleituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link BombasticParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdescrita(BombasticParser.CmdescritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link BombasticParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdescrita(BombasticParser.CmdescritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link BombasticParser#cmdattrib}.
	 * @param ctx the parse tree
	 */
	void enterCmdattrib(BombasticParser.CmdattribContext ctx);
	/**
	 * Exit a parse tree produced by {@link BombasticParser#cmdattrib}.
	 * @param ctx the parse tree
	 */
	void exitCmdattrib(BombasticParser.CmdattribContext ctx);
	/**
	 * Enter a parse tree produced by {@link BombasticParser#cmdselecao}.
	 * @param ctx the parse tree
	 */
	void enterCmdselecao(BombasticParser.CmdselecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link BombasticParser#cmdselecao}.
	 * @param ctx the parse tree
	 */
	void exitCmdselecao(BombasticParser.CmdselecaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link BombasticParser#cmdrepeticao}.
	 * @param ctx the parse tree
	 */
	void enterCmdrepeticao(BombasticParser.CmdrepeticaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link BombasticParser#cmdrepeticao}.
	 * @param ctx the parse tree
	 */
	void exitCmdrepeticao(BombasticParser.CmdrepeticaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link BombasticParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(BombasticParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link BombasticParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(BombasticParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link BombasticParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(BombasticParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link BombasticParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(BombasticParser.TermoContext ctx);
}