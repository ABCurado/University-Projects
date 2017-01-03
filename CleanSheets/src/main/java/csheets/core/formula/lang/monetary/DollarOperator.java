package csheets.core.formula.lang.monetary;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.UnaryOperator;
import csheets.framework.Money;
import csheets.support.ExchangeRateConverter;

/**
 * This UnaryOperator returns a Money type value with Euro currency basead on
 * the preceded value.
 *
 * The value is converted to Euro currency (see design explanation).
 *
 * @author Rui Freitas
 */
public class DollarOperator implements UnaryOperator {

	/**
	 * The unique version identifier used for serialization
	 */
	private static final long serialVersionUID = -2522070229611665735L;

	public DollarOperator() {
	}

	/**
	 * Returns a Money value type with the currency changed to Euro applying a
	 * currency conversion on the value.
	 *
	 * @return money value type.
	 * @throws IllegalValueTypeException if the value of the operand is not
	 * Money
	 */
	public Value applyTo(Expression operand) throws IllegalValueTypeException {
		return new Value(Money.
			euros(operand.evaluate().toNumber().doubleValue()).
			multiply(ExchangeRateConverter.getDollarToEuroExchangeRate().
				amount()));
	}

	@Override
	public String getIdentifier() {
		return "$";
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
