/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.tableFilters;

import csheets.core.Spreadsheet;
import csheets.ext.Extension;
import csheets.ext.tableFilters.ui.UIExtensionTableFilters;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 *
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 */
public class TableFiltersExtension extends Extension {

	/**
	 * Extension name - required.
	 */
	public static final String NAME = "Tables and Filters";

	/**
	 * Creates a new SearchExtension.
	 */
	public TableFiltersExtension() {
		super(NAME);
	}

	@Override
	public SpreadsheetWithTables extend(Spreadsheet sheet) {
		return new SpreadsheetWithTables(sheet);
	}

	/**
	 * Returns the User Interface Extension, the SearchExtension.
	 *
	 * @return UIExtension
	 */
	@Override
	public UIExtension getUIExtension(UIController uiController) {
		return new UIExtensionTableFilters(this, uiController);
	}

}
