package csheets.core.formula.lang.monetary;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.Function;
import csheets.core.formula.FunctionParameter;

/**
 * This Function returns a Numeric type value with Euro currency basead on value
 * passed as argument.
 *
 * In this case, this function does no conversion because the value passed as
 * argument is already in euro currency.
 *
 * @author Rui Freitas
 */
public class Euro implements Function {

	/**
	 * The only (but repeatable) parameter: a numeric term
	 */
	public static final FunctionParameter[] parameters = new FunctionParameter[]{
		new FunctionParameter(Value.Type.MONEY, "Term", false,
							  "A number to be converted to euro")
	};

	/**
	 * Creates a new instance of the EURO function.
	 */
	public Euro() {
	}

	@Override
	public String getIdentifier() {
		return "euro";
	}

	/**
	 * Returns a Numeric type Value. In this case, no conversion is need.
	 *
	 * @param arguments arguments
	 * @return return
	 * @throws IllegalValueTypeException exception
	 */
	@Override
	public Value applyTo(Expression[] arguments) throws IllegalValueTypeException {

		return new Value(arguments[0].evaluate().toMoney().amount());
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
		return "Convert a monetary type value (parameter) to euro currency.";
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
