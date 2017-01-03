/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.advancedWorkbookSearch;

import csheets.ext.Extension;
import csheets.ext.advancedWorkbookSearch.ui.AdvancedWorkbookSearchUI;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * Advanced Workbook Search Extension. This Extension gives the user the ability
 * to search for workbooks inside the given directory while working in the
 * application. The found workbooks path are shown to the user on this
 * extension' sidebar and from that he is able to one-click once to see a
 * preview or click twice to open the workbook on the application.
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
public class AdvancedWorkbookSearchExtension extends Extension {

	/**
	 * Extension Name.
	 */
	public static final String NAME = "AdvancedWorkbookSearch";

	/**
	 * Extension Version.
	 */
	public static final String VERSION = "2.0";

	/**
	 * Creates Advanced Workbook Search Extension.
	 *
	 */
	public AdvancedWorkbookSearchExtension() {
		super(NAME);
	}

	/**
	 * Returns the User Interface for this Extension.
	 *
	 * @param uicontroller UIController
	 * @return UIExtension UIExtension
	 */
	@Override
	public UIExtension getUIExtension(UIController uicontroller) {
		return new AdvancedWorkbookSearchUI(this, uicontroller);
	}

}
