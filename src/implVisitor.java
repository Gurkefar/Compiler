// Generated from impl.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link implParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface implVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link implParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(implParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SingleCommand}
	 * labeled alternative in {@link implParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleCommand(implParser.SingleCommandContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultipleCommands}
	 * labeled alternative in {@link implParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleCommands(implParser.MultipleCommandsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Assignment}
	 * labeled alternative in {@link implParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(implParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrAssignment}
	 * labeled alternative in {@link implParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrAssignment(implParser.ArrAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Output}
	 * labeled alternative in {@link implParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutput(implParser.OutputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhileLoop}
	 * labeled alternative in {@link implParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileLoop(implParser.WhileLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForLoop}
	 * labeled alternative in {@link implParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoop(implParser.ForLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code If}
	 * labeled alternative in {@link implParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(implParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfElse}
	 * labeled alternative in {@link implParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElse(implParser.IfElseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Array}
	 * labeled alternative in {@link implParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(implParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parenthesis}
	 * labeled alternative in {@link implParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesis(implParser.ParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Multiplication}
	 * labeled alternative in {@link implParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplication(implParser.MultiplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Addition}
	 * labeled alternative in {@link implParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddition(implParser.AdditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link implParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(implParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Constant}
	 * labeled alternative in {@link implParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(implParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Unequal}
	 * labeled alternative in {@link implParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnequal(implParser.UnequalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Not}
	 * labeled alternative in {@link implParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(implParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LessThan}
	 * labeled alternative in {@link implParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessThan(implParser.LessThanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link implParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(implParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Equal}
	 * labeled alternative in {@link implParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqual(implParser.EqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GreaterThan}
	 * labeled alternative in {@link implParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterThan(implParser.GreaterThanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code And}
	 * labeled alternative in {@link implParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(implParser.AndContext ctx);
}