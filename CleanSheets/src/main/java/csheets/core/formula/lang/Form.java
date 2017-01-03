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

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Expression;
import csheets.core.formula.Function;
import csheets.core.formula.FunctionParameter;
import csheets.ui.FormEditor.ui.FormE;
import csheets.ui.FormEditor.ui.FormEditor;
import csheets.ui.ctrl.UIController;
import java.io.Serializable;

/**
 * A function that returns the numeric sum of its arguments.
 *
 * @author Einar Pehrson
 */
public class Form implements Function, Serializable {

	/**
	 * The only (but repeatable) parameter: a numeric term
	 */
	public static final FunctionParameter[] parameters = new FunctionParameter[]{
		new FunctionParameter(Value.Type.TEXT, "Name", false,
							  "A number to be included in the sum"),
		new FunctionParameter(Value.Type.BOOLEAN, "Modal", false,
							  "A value to return if the condition was met")
	};

	/**
	 * Creates a new instance of the SUM function.
	 */
	public Form() {
	}

	public String getIdentifier() {
		return "FORM";
	}

	public Value applyTo(Expression[] arguments) throws IllegalValueTypeException {
		String name = arguments[0].evaluate().toText();
		boolean modal = arguments[1].evaluate().toBoolean();
		FormE form = UIController.getUIController().getActiveWorkbook().
			getForm(name);
		if (form == null) {
			form = new FormE(name);
			UIController.getUIController().getActiveWorkbook().
				addFormE(name, form);
			form.setEditable(true);
		}
		//form.setCell(UIController.getUIController().getActiveCell());
		FormEditor formEditor = new FormEditor(form, modal);
		return new Value();
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
		return "Form edite Executed.";
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
