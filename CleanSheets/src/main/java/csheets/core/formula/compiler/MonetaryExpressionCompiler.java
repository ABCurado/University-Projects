/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.core.formula.compiler;

import csheets.core.Cell;
import csheets.core.Value;
import csheets.core.formula.BinaryOperation;
import csheets.core.formula.BinaryOperator;
import csheets.core.formula.Expression;
import csheets.core.formula.Function;
import csheets.core.formula.FunctionCall;
import csheets.core.formula.Literal;
import csheets.core.formula.UnaryOperation;
import csheets.core.formula.lang.Language;
import csheets.core.formula.lang.UnknownElementException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

/**
 * A compiler that generates Monetary-style formulas from strings.
 *
 * @author Rui Freitas
 */
public class MonetaryExpressionCompiler implements ExpressionCompiler {

	/**
	 * The character that signals that a cell's content is a monetary expression
	 * ('#')
	 */
	public static final char FORMULA_STARTER = '#';

	/**
	 * Creates the Monetary expression compiler.
	 */
	public MonetaryExpressionCompiler() {
	}

	public char getStarter() {
		return FORMULA_STARTER;
	}

	@Override
	public Expression compile(Cell cell, String source) throws FormulaCompilationException {
		// Creates the lexer and parser
		ANTLRStringStream input = new ANTLRStringStream(source);

		// create the buffer of tokens between the lexer and parser
		MonetaryExpressionLexer lexer = new MonetaryExpressionLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		MonetaryExpressionParser parser = new MonetaryExpressionParser(tokens);

		CommonTree tree = null;

		try {
			// Attempts to match an expression
			tree = (CommonTree) parser.expression().getTree();

		} catch (RecognitionException e) {
			String message = parser.getErrorMessage(e, parser.tokenNames);
			throw new FormulaCompilationException("At (" + e.line + ";" + e.charPositionInLine + "): " + message);
		} catch (Exception e) {
			String message = "Other exception : " + e.getMessage();
			throw new FormulaCompilationException(message);
		}

		// Converts the expression and returns it
		return convert(cell, tree);
	}
        
        public CommonTree compileTree(String source) throws FormulaCompilationException {
		ANTLRStringStream input = new ANTLRStringStream(source);
		MonetaryExpressionLexer lexer = new MonetaryExpressionLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MonetaryExpressionParser parser = new MonetaryExpressionParser(tokens);
		CommonTree tree = null;
		try {
			tree = (CommonTree) parser.expression().getTree();
		} catch (RecognitionException e) {
			String message = parser.getErrorMessage(e, parser.tokenNames);
			throw new FormulaCompilationException("At (" + e.line + ";" + e.charPositionInLine + "): " + message);
		} catch (Exception e) {
			String message = "Other exception : " + e.getMessage();
			throw new FormulaCompilationException(message);
		}
		return tree;
	}

	/**
	 * Converts the given ANTLR AST to an expression.
	 *
	 * @param cell cell
	 * @param node the abstract syntax tree node to convert
	 * @return the result of the conversion
	 * @throws csheets.core.formula.compiler.FormulaCompilationException
	 * exception
	 */
	protected Expression convert(Cell cell, Tree node) throws FormulaCompilationException {

//		System.out.
//			println("Converting node '" + node.getText() + "' of tree '" + node.
//				toStringTree() + "' with " + node.getChildCount() + " children.");
		if (node.getChildCount() == 0) {
			try {
				switch (node.getType()) {
					case MonetaryExpressionLexer.NUMBER:
						return new Literal(Value.parseNumericValue(node.
							getText()));
				}
			} catch (ParseException e) {
				throw new FormulaCompilationException(e);
			}
		}

		// Convert function call
		Function function = null;
		try {
			if (node.getText() != null) {
				function = Language.getInstance().getFunction(node.getText());
			}
		} catch (UnknownElementException e) {
		}

		if (function != null) {
			List<Expression> args = new ArrayList<Expression>();
			Tree child = node.getChild(0);
			if (child != null) {
				for (int nChild = 0; nChild < node.getChildCount(); ++nChild) {
					child = node.getChild(nChild);
					args.add(convert(cell, child));
				}
			}
			Expression[] argArray = args.toArray(new Expression[args.size()]);
			return new FunctionCall(function, argArray);
		}

		if (node.getChildCount() == 1) // Convert unary operation
		{
			return new UnaryOperation(
				Language.getInstance().getUnaryOperator(node.getText()),
				convert(cell, node.getChild(0))
			);
		} else if (node.getChildCount() == 2 && node.getText() != null) {
			// Convert binary operation
			BinaryOperator operator = Language.getInstance().
				getBinaryOperator(node.getText().concat("€"));
			return new BinaryOperation(
				convert(cell, node.getChild(0)),
				operator,
				convert(cell, node.getChild(1))
			);

		}
		// Shouldn't happen
		throw new FormulaCompilationException();
	}

}
