package csheets.core.formula.lang.monetary;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.BinaryOperator;
import csheets.core.formula.Expression;

/**
 * A subtracter of a monetary operand from another.
 *
 * @author Rui Freitas
 */
public class MonetarySubtracter implements BinaryOperator {

	private static final long serialVersionUID = -3880052781226099414L;

	@Override
	public Value applyTo(Expression leftOperand, Expression rightOperand) throws IllegalValueTypeException {
		return new Value(leftOperand.evaluate().toMoney().subtract(rightOperand.
			evaluate().toMoney()));
	}

	@Override
	public String getIdentifier() {
		return "-€";
	}

	@Override
	public Value.Type getOperandValueType() {
		return Value.Type.MONEY;
	}

}
