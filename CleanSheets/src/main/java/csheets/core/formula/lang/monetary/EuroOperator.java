package csheets.core.formula.lang.monetary;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.UnaryOperator;
import csheets.framework.Money;

/**
 * This UnaryOperator returns a Money type value with Euro currency basead on
 * the preceded value.
 *
 * In this case, this operator does no conversion because all the monetary
 * values are converted to euro (see Design explanation).
 *
 * @author Rui Freitas
 */
public class EuroOperator implements UnaryOperator {

	/**
	 * The unique version identifier used for serialization
	 */
	private static final long serialVersionUID = -8181316415514876313L;

	public EuroOperator() {
	}

	/**
	 * Returns a Money value type.
	 *
	 * @return money value type with no conversion.
	 * @throws IllegalValueTypeException if the value of the operand is not
	 * Money
	 */
	@Override
	public Value applyTo(Expression operand) throws IllegalValueTypeException {
		return new Value(Money.
			euros(operand.evaluate().toNumber().doubleValue()));
	}

	@Override
	public String getIdentifier() {
		return "€";
	}

	@Override
	public boolean isPrefix() {
		return false;
	}

	@Override
	public Value.Type getOperandValueType() {
		return Value.Type.NUMERIC;
	}

	@Override
	public String toString() {
		return getIdentifier();
	}
}
