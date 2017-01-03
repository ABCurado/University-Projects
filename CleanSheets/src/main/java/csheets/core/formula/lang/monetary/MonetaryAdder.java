package csheets.core.formula.lang.monetary;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.BinaryOperator;
import csheets.core.formula.Expression;

/**
 * A adder of a monetary operand from another.
 *
 * @author Rui Freitas
 */
public class MonetaryAdder implements BinaryOperator {

	private static final long serialVersionUID = -8947645096015927922L;

	@Override
	public Value applyTo(Expression leftOperand, Expression rightOperand) throws IllegalValueTypeException {
		return new Value(leftOperand.evaluate().toMoney().add(rightOperand.
			evaluate().toMoney()));
	}

	@Override
	public String getIdentifier() {
		return "+€";
	}

	@Override
	public Value.Type getOperandValueType() {
		return Value.Type.MONEY;
	}

}
