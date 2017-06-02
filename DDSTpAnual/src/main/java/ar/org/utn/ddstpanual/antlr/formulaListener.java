// Generated from formula.g4 by ANTLR 4.4
package ar.org.utn.ddstpanual.antlr;

	import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link formulaParser}.
 */
public interface formulaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link formulaParser#suma}.
	 * @param ctx the parse tree
	 */
	void enterSuma(@NotNull formulaParser.SumaContext ctx);
	/**
	 * Exit a parse tree produced by {@link formulaParser#suma}.
	 * @param ctx the parse tree
	 */
	void exitSuma(@NotNull formulaParser.SumaContext ctx);
	/**
	 * Enter a parse tree produced by {@link formulaParser#div}.
	 * @param ctx the parse tree
	 */
	void enterDiv(@NotNull formulaParser.DivContext ctx);
	/**
	 * Exit a parse tree produced by {@link formulaParser#div}.
	 * @param ctx the parse tree
	 */
	void exitDiv(@NotNull formulaParser.DivContext ctx);
	/**
	 * Enter a parse tree produced by {@link formulaParser#indicador}.
	 * @param ctx the parse tree
	 */
	void enterIndicador(@NotNull formulaParser.IndicadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link formulaParser#indicador}.
	 * @param ctx the parse tree
	 */
	void exitIndicador(@NotNull formulaParser.IndicadorContext ctx);
	/**
	 * Enter a parse tree produced by {@link formulaParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresion(@NotNull formulaParser.ExpresionContext ctx);
	/**
	 * Exit a parse tree produced by {@link formulaParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresion(@NotNull formulaParser.ExpresionContext ctx);
	/**
	 * Enter a parse tree produced by {@link formulaParser#mult}.
	 * @param ctx the parse tree
	 */
	void enterMult(@NotNull formulaParser.MultContext ctx);
	/**
	 * Exit a parse tree produced by {@link formulaParser#mult}.
	 * @param ctx the parse tree
	 */
	void exitMult(@NotNull formulaParser.MultContext ctx);
	/**
	 * Enter a parse tree produced by {@link formulaParser#operador_aditivo}.
	 * @param ctx the parse tree
	 */
	void enterOperador_aditivo(@NotNull formulaParser.Operador_aditivoContext ctx);
	/**
	 * Exit a parse tree produced by {@link formulaParser#operador_aditivo}.
	 * @param ctx the parse tree
	 */
	void exitOperador_aditivo(@NotNull formulaParser.Operador_aditivoContext ctx);
	/**
	 * Enter a parse tree produced by {@link formulaParser#num}.
	 * @param ctx the parse tree
	 */
	void enterNum(@NotNull formulaParser.NumContext ctx);
	/**
	 * Exit a parse tree produced by {@link formulaParser#num}.
	 * @param ctx the parse tree
	 */
	void exitNum(@NotNull formulaParser.NumContext ctx);
	/**
	 * Enter a parse tree produced by {@link formulaParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(@NotNull formulaParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link formulaParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(@NotNull formulaParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link formulaParser#cuenta}.
	 * @param ctx the parse tree
	 */
	void enterCuenta(@NotNull formulaParser.CuentaContext ctx);
	/**
	 * Exit a parse tree produced by {@link formulaParser#cuenta}.
	 * @param ctx the parse tree
	 */
	void exitCuenta(@NotNull formulaParser.CuentaContext ctx);
	/**
	 * Enter a parse tree produced by {@link formulaParser#resta}.
	 * @param ctx the parse tree
	 */
	void enterResta(@NotNull formulaParser.RestaContext ctx);
	/**
	 * Exit a parse tree produced by {@link formulaParser#resta}.
	 * @param ctx the parse tree
	 */
	void exitResta(@NotNull formulaParser.RestaContext ctx);
	/**
	 * Enter a parse tree produced by {@link formulaParser#operando}.
	 * @param ctx the parse tree
	 */
	void enterOperando(@NotNull formulaParser.OperandoContext ctx);
	/**
	 * Exit a parse tree produced by {@link formulaParser#operando}.
	 * @param ctx the parse tree
	 */
	void exitOperando(@NotNull formulaParser.OperandoContext ctx);
	/**
	 * Enter a parse tree produced by {@link formulaParser#operador_mult}.
	 * @param ctx the parse tree
	 */
	void enterOperador_mult(@NotNull formulaParser.Operador_multContext ctx);
	/**
	 * Exit a parse tree produced by {@link formulaParser#operador_mult}.
	 * @param ctx the parse tree
	 */
	void exitOperador_mult(@NotNull formulaParser.Operador_multContext ctx);
}