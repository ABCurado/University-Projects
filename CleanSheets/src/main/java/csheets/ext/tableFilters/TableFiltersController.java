/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.tableFilters;

import csheets.core.Cell;
import csheets.ext.tableFilters.Table.Filter;
import csheets.ui.ctrl.UIController;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 */
public class TableFiltersController {

	/**
	 * The UIController
	 */
	private final UIController uiController;

	/**
	 * The constructor
	 *
	 * @param uiController the UIController
	 */
	public TableFiltersController(UIController uiController) {
		this.uiController = uiController;
	}

	public void createNewTable(Observer observer, String name,
							   SpreadsheetWithTables spreadsheet) {
		Cell[][] cells = this.uiController.focusOwner.getSelectedCells();
		spreadsheet.createNewTable(observer, cells, name);
	}

	public List<Table> getTables(SpreadsheetWithTables activeSpreadsheet) {
		return activeSpreadsheet.tables();
	}

	public void removeTable(Table selected, SpreadsheetWithTables spreadsheet) {
		spreadsheet.removeTable(selected);
	}

	public void removeFilter(Table selectedTable, Filter selectedFilter) {
		selectedTable.removeFilter(selectedFilter);
	}

}
