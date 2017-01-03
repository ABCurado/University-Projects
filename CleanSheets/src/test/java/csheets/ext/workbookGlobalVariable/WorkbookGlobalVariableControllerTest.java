/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.workbookGlobalVariable;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.ui.ctrl.UIController;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * This is the Test Class for WorkbookGlobalVariableController.
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
public class WorkbookGlobalVariableControllerTest {

	private UIController controller;

	public WorkbookGlobalVariableControllerTest() {
		controller = UIController.getUIController();
	}

	/**
	 * Test of parseValue method, of class WorkbookGlobalVariableController.
	 *
	 * @throws csheets.core.IllegalValueTypeException
	 */
	@Test
	public void testParseValue() throws IllegalValueTypeException {
		System.out.println("parseValue");
		String newValue = "10";
		WorkbookGlobalVariableController instance = new WorkbookGlobalVariableController(controller);
		Value expResult = new Value(10);
		Value result = instance.parseValue(newValue);
		assertEquals(expResult.toDouble(), result.toDouble(), 0.001);
	}
}
