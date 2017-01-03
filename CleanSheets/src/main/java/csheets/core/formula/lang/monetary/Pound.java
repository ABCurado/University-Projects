package csheets.core.formula.lang.monetary;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.Function;
import csheets.core.formula.FunctionParameter;
import csheets.support.ExchangeRateConverter;

/**
 * This Function returns a Numeric type value with Pound currency basead on
 * value passed as argument.
 *
 * In this case, this function applies a convertion Euro to Pound.
 *
 * @author Rui Freitas
 */
public class Pound implements Function {

	/**
	 * The only parameter: a Money term
	 */
	public static final FunctionParameter[] parameters = new FunctionParameter[]{
		new FunctionParameter(Value.Type.MONEY, "Term", false,
							  "A number to be converted to pound")
	};

	/**
	 * Creates a new instance of the POUND function.
	 */
	public Pound() {
	}

	@Override
	public String getIdentifier() {
		return "pound";
	}

	/**
	 * Returns a Numeric type Value. In this case, a conversion from Euro to
	 * Pound is applied.
	 *
	 * @param arguments arguments
	 * @return value
	 * @throws IllegalValueTypeException exception
	 */
	@Override
	public Value applyTo(Expression[] arguments) throws IllegalValueTypeException {
		return new Value(arguments[0].evaluate().toMoney().
			multiply(ExchangeRateConverter.getEuroToPoundExchangeRate().amount()).
			amount());
	}

	@Override
	public FunctionParameter[] getParameters() {
		return parameters;
	}

	@Override
	public boolean isVarArg() {
		return false;
	}

	@Override
	public String getDescription() {
		return "Convert a monetary type value (parameter) to pound currency.";
	}

	/**
	 * Return template of the function
	 *
	 * @return function template
	 */
	@Override
	public String getTemplate() {
		String result = "#" + getIdentifier() + "{";
		FunctionParameter[] param = getParameters();
		for (int i = 0; i < param.length; i++) {
			if (i != 0) {
				result += ";";
			}
			result += param[i].getValueType().toString();
		}
		result += "}";
		return result;
	}
}
