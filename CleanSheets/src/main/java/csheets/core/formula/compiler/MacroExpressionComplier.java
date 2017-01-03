package csheets.core.formula.compiler;

import csheets.core.Value;
import csheets.core.formula.BinaryOperation;
import csheets.core.formula.BinaryOperator;
import csheets.core.formula.Expression;
import csheets.core.formula.Function;
import csheets.core.formula.FunctionCall;
import csheets.core.formula.InstructionBlock;
import csheets.core.formula.Literal;
import csheets.core.formula.Reference;
import csheets.core.formula.UnaryOperation;
import csheets.core.formula.lang.CellReference;
import csheets.core.formula.lang.Language;
import csheets.core.formula.lang.RangeReference;
import csheets.core.formula.lang.ReferenceOperation;
import csheets.core.formula.lang.UnknownElementException;
import csheets.ui.ctrl.UIController;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

/**
 *
 * @author Rui Bastos
 */
public class MacroExpressionComplier {

	/**
	 * uiController to be used
	 */
	UIController uiController;

	/**
	 * Creates a Macro expression compiler.
	 *
	 * @param uiController uiController
	 */
	public MacroExpressionComplier(UIController uiController) {
		this.uiController = uiController;
	}

	/**
	 * Compiles a formula of the macro
	 *
	 * @param source the formula to compile
	 * @return the expression result
	 * @throws FormulaCompilationException when the sintax is not correct
	 */
	public Expression compile(String source) throws FormulaCompilationException {

		source = "= " + source;//In macros the formulas don't hava "=", so i added them here, so i can use the gramar already created

		// Creates the lexer and parser
		ANTLRStringStream input = new ANTLRStringStream(source);

		// create the buffer of tokens between the lexer and parser
		FormulaLexer lexer = new FormulaLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		FormulaParser parser = new FormulaParser(tokens);

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
		return convert(tree);
	}

	/**
	 * Converts the given ANTLR AST to an expression.
	 *
	 * @param node the abstract syntax tree node to convert
	 * @return the result of the conversion
	 * @throws csheets.core.formula.compiler.FormulaCompilationException
	 * exception
	 */
	protected Expression convert(Tree node) throws FormulaCompilationException {
		System.out.
			println("Converting node '" + node.getText() + "' of tree '" + node.
				toStringTree() + "' with " + node.getChildCount() + " children.");
		if (node.getChildCount() == 0) {
			try {
				switch (node.getType()) {
					case FormulaLexer.CELL_REF:
						System.out.println("CELL_REF " + node.getText());
						return new CellReference(this.uiController.
							getActiveSpreadsheet(), node.
												 getText());
					case FormulaLexer.VART_REF:
						System.out.println("VART_REF " + node.getText());
					//return new VariableLocalReference(cell, node.getText());
					case FormulaLexer.VARG_REF:
						System.out.println("VARG_REF " + node.getText());
					//return new VariableGlobalReference(cell, node.getText());
					case FormulaLexer.NUMBER:
						System.out.println("NUMBER " + node.getText());
						return new Literal(Value.parseNumericValue(node.
							getText()));
					case FormulaLexer.STRING:
						System.out.println("STRING " + node.getText());
						return new Literal(Value.
							parseValue(node.getText(), Value.Type.BOOLEAN, Value.Type.DATE));
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
					args.add(convert(child));
				}
			}
			Expression[] argArray = args.toArray(new Expression[args.size()]);
			return new FunctionCall(function, argArray);
		}

		if (node.getText().equals("{")) {
			List<Expression> args = new ArrayList<Expression>();
			for (int numChild = 0; numChild < node.getChildCount(); numChild++) {
				Tree child = node.getChild(numChild);
				if (child != null) {
					args.add(convert(child));
				}
			}
			Expression[] argArray = args.toArray(new Expression[args.size()]);
			return new InstructionBlock(argArray);
		}

		if (node.getChildCount() == 1) {
			return new UnaryOperation(
				Language.getInstance().getUnaryOperator(node.getText()),
				convert(node.getChild(0))
			);
		} else if (node.getChildCount() == 2) {
			//Convert binary operation
			BinaryOperator operator = Language.getInstance().
				getBinaryOperator(node.getText());
			if (operator instanceof RangeReference) {
				return new ReferenceOperation(
					(Reference) convert(node.getChild(0)),
					(RangeReference) operator,
					(Reference) convert(node.getChild(1))
				);
			} else {
				return new BinaryOperation(
					convert(node.getChild(0)),
					operator,
					convert(node.getChild(1))
				);
			}
		}
		// Shouldn't happen
		throw new FormulaCompilationException();
	}

}
