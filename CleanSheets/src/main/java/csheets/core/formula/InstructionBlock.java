package csheets.core.formula;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.util.ExpressionVisitor;

/**
 * A Set of Expressions.
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
public class InstructionBlock implements Expression {

	/**
	 * Array with expressions.
	 */
	private Expression[] expressions;

	/**
	 * Creates a new InstructionBlock.
	 *
	 * @param expressions Variable number of expression arguments.
	 */
	public InstructionBlock(Expression... expressions) {
		this.expressions = expressions;
	}

	/**
	 * Evaluate Expressions.
	 *
	 * @return Value of the final Expression within the InstructionBlock.
	 * @throws IllegalValueTypeException The value can be illegal.
	 */
	@Override
	public Value evaluate() throws IllegalValueTypeException {
		Value value = null;
		for (int i = 0; i < this.expressions.length; i++) {
			value = this.expressions[i].evaluate();
		}
		return value;
	}

	/**
	 *
	 * @return Expressions Array
	 */
	public Expression[] getExpressions() {
		return this.expressions;
	}

	/**
	 * Accept
	 *
	 * @return visited
	 * @param visitor The visitor.
	 */
	@Override
	public Object accept(ExpressionVisitor visitor) {
		return visitor.visitInstructionBlock(this);
	}

	@Override
	public String toString() {
		String string = "{";
		for (int i = 0; i < expressions.length; i++) {
			string += expressions[i];
			if (i + 1 < expressions.length) {
				string += "; ";
			}
		}
		string += "}";
		return string;
	}
}
