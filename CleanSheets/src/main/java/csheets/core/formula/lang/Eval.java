/*
 * Copyright (c) 2005 Einar Pehrson <einar@pehrson.nu>.
 *
 * This file is part of
 * CleanSheets - a spreadsheet application for the Java platform.
 *
 * CleanSheets is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * CleanSheets is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CleanSheets; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package csheets.core.formula.lang;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.Function;
import csheets.core.formula.FunctionParameter;
import csheets.core.formula.compiler.FormulaCompiler;
import csheets.ui.ctrl.UIController;

/**
 * A function that returns the numeric sum of its arguments.
 *
 * @author Einar Pehrson
 */
public class Eval implements Function {

	/**
	 * The only (but repeatable) parameter: a numeric term
	 */
	public static final FunctionParameter[] parameters = new FunctionParameter[]{
		new FunctionParameter(Value.Type.TEXT, "Formula", false,
							  "Expression to be executed by the function as formula")
	};

	/**
	 * Creates a new instance of the SUM function.
	 */
	public Eval() {
	}

	public String getIdentifier() {
		return "Eval";
	}

	public Value applyTo(Expression[] arguments) throws IllegalValueTypeException {
		try {
			String content = arguments[0].toString();
			if (content.charAt(0) == '"' && content.charAt(content.length() - 1) == '"') {
				content = content.substring(1, content.length() - 1);
			}
			content = "=" + content;
			Cell cell = UIController.getUIController().getActiveWorkbook().
				getSpreadsheet(0).getCell(0, 0);
			return FormulaCompiler.getInstance().compile(cell, content).
				evaluate();
		} catch (Exception ex) {
			return new Value();
		}
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
		return "Eval function.";
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
