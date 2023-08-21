// Generated from C:/Users/usuario/UFABC/2023.2/Compiladores/Projeto/bombastic-language/src/br/com/bombastic\Bombastic.g4 by ANTLR 4.12.0
package br.com.bombastic.antlr;

    import br.com.bombastic.datastructures.*;
    import br.com.bombastic.exceptions.*;
    import br.com.bombastic.ast.*;
    import java.util.ArrayList;
    import java.util.Stack;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BombasticParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BombasticVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BombasticParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(BombasticParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link BombasticParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(BombasticParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link BombasticParser#declaravar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaravar(BombasticParser.DeclaravarContext ctx);
	/**
	 * Visit a parse tree produced by {@link BombasticParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(BombasticParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link BombasticParser#bloco}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloco(BombasticParser.BlocoContext ctx);
	/**
	 * Visit a parse tree produced by {@link BombasticParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmd(BombasticParser.CmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link BombasticParser#cmdleitura}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdleitura(BombasticParser.CmdleituraContext ctx);
	/**
	 * Visit a parse tree produced by {@link BombasticParser#cmdescrita}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdescrita(BombasticParser.CmdescritaContext ctx);
	/**
	 * Visit a parse tree produced by {@link BombasticParser#cmdattrib}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdattrib(BombasticParser.CmdattribContext ctx);
	/**
	 * Visit a parse tree produced by {@link BombasticParser#cmdselecao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdselecao(BombasticParser.CmdselecaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link BombasticParser#cmdrepeticao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdrepeticao(BombasticParser.CmdrepeticaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link BombasticParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(BombasticParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link BombasticParser#termo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo(BombasticParser.TermoContext ctx);
}