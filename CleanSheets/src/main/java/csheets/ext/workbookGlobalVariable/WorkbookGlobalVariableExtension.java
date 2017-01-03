/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.workbookGlobalVariable;

import csheets.ext.Extension;
import csheets.ext.workbookGlobalVariable.ui.WorkbookGlobalVariableUI;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * Workbook Global Variable Extension.
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
public class WorkbookGlobalVariableExtension extends Extension {

	/**
	 * Extension name - required.
	 */
	public static final String NAME = "workbookGlobalVariable";

	/**
	 * Creates a new WorkbookGlobalExtension.
	 */
	public WorkbookGlobalVariableExtension() {
		super(NAME);

	}

	/**
	 * Returns the User Interface Extension of the WorkbookGlobalExtension.
	 *
	 * @return UIExtension
	 */
	@Override
	public UIExtension getUIExtension(UIController uiController) {
		return new WorkbookGlobalVariableUI(this, uiController);
	}

}
