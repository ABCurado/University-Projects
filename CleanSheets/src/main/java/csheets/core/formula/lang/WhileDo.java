package csheets.core.formula.lang;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.Function;
import csheets.core.formula.FunctionParameter;

/**
 * Execution of For Loop.
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
public class WhileDo implements Function {

	public static final FunctionParameter[] parameters = new FunctionParameter[]{
		new FunctionParameter(Value.Type.BOOLEAN, "Condition", false,
							  "A condition to evaluate before proceeding"),
		new FunctionParameter(Value.Type.UNDEFINED, "Expression", false,
							  "A number to be included in the sum")
	};

	public WhileDo() {
	}

	/**
	 *
	 * @return Identifier
	 */
	@Override
	public String getIdentifier() {
		return "WhileDo";
	}

	/**
	 *
	 * @param args Array of expressions.
	 * @return Returns the value of a cell.
	 * @throws IllegalValueTypeException The value can be illegal.
	 */
	@Override
	public Value applyTo(Expression[] args) throws IllegalValueTypeException {
		Value value = new Value();
		while (args[0].evaluate().toBoolean()) {
			value = args[1].evaluate();
		}
		return value;
	}

	public FunctionParameter[] getParameters() {
		return parameters;
	}

	public boolean isVarArg() {
		return false;
	}

	/**
	 * Gets the description of the function
	 *
	 * @return function description
	 */
	@Override
	public String getDescription() {
		return " Execution of While Do Loop.";
	}

	/**
	 * Return template of the function
	 *
	 * @return function template
	 */
	@Override
	public String getTemplate() {
		String result = "={" + getIdentifier() + "(";
		FunctionParameter[] param = getParameters();
		for (int i = 0; i < param.length; i++) {
			if (i != 0) {
				result += ";";
			}
			result += param[i].getValueType().toString();
		}
		result += ")}";
		return result;
	}
}
