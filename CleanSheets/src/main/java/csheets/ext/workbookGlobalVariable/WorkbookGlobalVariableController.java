/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.workbookGlobalVariable;

import csheets.core.Value;
import csheets.core.formula.VariableArray;
import csheets.ui.ctrl.UIController;
import java.util.List;

/**
 * This Controller concerns all operations of WorkbookGlobalVariable.
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
public class WorkbookGlobalVariableController {

	/**
	 * UIcontroller.
	 */
	private UIController uicontroller;

	/**
	 * Creates WorkbookGlobalVariableController.
	 *
	 * @param uicontroller uicontroller.
	 */
	public WorkbookGlobalVariableController(UIController uicontroller) {
		this.uicontroller = uicontroller;
	}

	/**
	 * Parses the String Value.
	 *
	 * @param newValue value to update.
	 * @return Value parsed.
	 */
	public Value parseValue(String newValue) {

		if (newValue.contains("\"")) { //String
			String temp = newValue.substring(1, newValue.length() - 1);
			return new Value(temp);
		} else if (newValue.equals("TRUE")) {
			return new Value(Boolean.TRUE);
		} else if (newValue.equals("FALSE")) {
			return new Value(Boolean.FALSE);
		} else { //number.
			double n = Double.parseDouble(newValue);
			return new Value(n);
		}
	}

	/**
	 * Get all Variables in the current Workbook.
	 *
	 * @return List of Variable Array.
	 */
	public List<VariableArray> getCurrentVariables() {
		return uicontroller.getActiveWorkbook().getAllVariables();
	}
}
