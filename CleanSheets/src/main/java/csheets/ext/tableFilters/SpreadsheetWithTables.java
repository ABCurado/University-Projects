/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.tableFilters;

import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.SpreadsheetExtension;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.SortedSet;

/**
 *
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 */
public class SpreadsheetWithTables extends SpreadsheetExtension implements Serializable {

	/**
	 * List containing the Tables of this Spreadsheet
	 */
	private final List<Table> tables;

	/**
	 * The constructor.
	 *
	 * @param delegate the delegated Spreadsheet
	 */
	public SpreadsheetWithTables(Spreadsheet delegate) {
		super(delegate, TableFiltersExtension.NAME);
		this.tables = new ArrayList<>();
	}

	/**
	 * Creates a new Table.
	 *
	 * @param observer the observer
	 * @param tableCells matrix of cells that will form the new Table
	 * @param name the name of the table
	 */
	public void createNewTable(Observer observer, Cell[][] tableCells,
							   String name) throws IllegalArgumentException {
		if (name == null || name.isEmpty()) {
			name = "Table" + this.tables.size() + 1;
		}
		Table newTable = new Table(this, tableCells, name);
		newTable.addObserver(observer);
		this.tables.add(newTable);
	}

	public void applyFilter(Table table, String expression, Cell tempCell)
		throws IllegalValueTypeException, FormulaCompilationException {
		table.addFilter(expression, tempCell);
	}

	/**
	 * Returns a SortedSet of the cells of this Spreadsheet
	 *
	 * @return SortedSet of the cells of this Spreadsheet
	 */
	@Override
	public SortedSet<Cell> getCells() {
		return this.getCells(new Address(0, 0), new Address(this.
							 getColumnCount(), this.getRowCount()));
	}

	/**
	 * Returns the current list of tables in this spreadsheet
	 *
	 * @param observer the observer
	 * @return the current list of tables
	 */
	public List<Table> tables(Observer observer) {
		this.tables.stream().
			forEach((table) -> {
				table.addObserver(observer);
			});
		return this.tables;
	}

	/**
	 * Returns the current list of tables in this spreadsheet
	 *
	 * @return the current list of tables
	 */
	public List<Table> tables() {
		return this.tables;
	}

	/**
	 * Retrieves Table with given name
	 *
	 * @param name the name of the table to fetch
	 * @return a Table
	 */
	public Table getTable(String name) {
		for (Table table : this.tables) {
			if (table.name().equals(name)) {
				return table;
			}
		}
		return null;
	}

	/**
	 * Removes Table from the spreadsheet
	 *
	 * @param selected table to be removed
	 */
	public void removeTable(Table selected) {
		this.tables.remove(selected);
	}

}
