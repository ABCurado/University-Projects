/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.FindWorkbook;

import csheets.ext.Extension;
import csheets.ext.FindWorkbook.ui.UIExtensionFindWorkbook;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 *
 * @author Carlos Mateus
 */
public class FindWorkbookExtension extends Extension {

	/**
	 * The name of extension
	 */
	public static final String NAME = "Find Workbooks";

	/**
	 * Creates a new workbook extension.
	 */
	public FindWorkbookExtension() {
		super(NAME);
	}

	/**
	 * Returns the user interface extension of this extension
	 *
	 * @param uiController the user interface controller
	 * @return a user interface extension, or null if none is provided
	 */
	@Override
	public UIExtension getUIExtension(UIController uiController) {
		return new UIExtensionFindWorkbook(this, uiController);
	}
}
