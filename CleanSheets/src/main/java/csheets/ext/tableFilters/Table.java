/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.tableFilters;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import csheets.core.formula.Expression;
import csheets.core.formula.compiler.ExcelExpressionCompiler;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.notification.Notifier;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 *
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 */
public class Table extends Notifier implements Serializable {

	/**
	 * The spreadsheet this table belongs to
	 */
	private final SpreadsheetWithTables spreadsheet;

	/**
	 * Name of this table
	 */
	private String name;

	/**
	 * List of filters associated with this table
	 */
	private final List<Filter> filters;

	/**
	 * Pattern that will be applied to find column references
	 */
	private static final Pattern COLUMN_REFERENCE_PATTERN = Pattern.
		compile("(_col\\[\"([A-Za-z]+)\"\\])|(_col\\[([1-9]+)\\])");

	/**
	 * INDEX type columnReference format
	 */
	private static final String COLUMN_REFERENCE_FORMAT_INDEX = "_col[%s]";

	/**
	 * STRING type columnReference format
	 */
	private static final String COLUMN_REFERENCE_FORMAT_STRING = "_col[\"%s\"]";

	/**
	 * Matrix of cells in this table
	 */
	private final Cell[][] cells;

	/**
	 * The constructor
	 *
	 * @param spreadsheet correspondant spreadsheet
	 * @param cells Matrix of cells that will form the table
	 * @param name the name for this table
	 * @throws IllegalArgumentException
	 */
	public Table(SpreadsheetWithTables spreadsheet, Cell[][] cells,
				 String name) throws IllegalArgumentException {
		validate(cells);
		this.spreadsheet = spreadsheet;
		this.cells = cells;
		this.name = name;
		this.filters = new ArrayList<>();
	}

	/**
	 * Validates the structure of the cells matrix
	 *
	 * @param cells1
	 * @throws IllegalArgumentException
	 */
	private void validate(Cell[][] cells) throws IllegalArgumentException {
		if ((cells.length < 3 || cells[0].length < 2)) {
			throw new IllegalArgumentException("Table must be at least 3x2");
		}
		for (Cell header : cells[0]) {
			if (header.getContent().isEmpty()) {
				throw new IllegalArgumentException("Table Headers must have content");
			}
		}
	}

	/**
	 * Returns currently associated filters
	 *
	 * @return list of associated filters
	 */
	public List<Filter> filters() {
		return this.filters;
	}

	/**
	 * Gets the name of the table
	 *
	 * @return the name of the table
	 */
	public String name() {
		return this.name;
	}

	/**
	 * Changes name of the table
	 *
	 * @param newName new name
	 */
	public void changeName(String newName) {
		this.name = newName;
	}

	/**
	 * Removes filter
	 *
	 * @param filter the filter to remove
	 */
	public void removeFilter(Filter filter) {
		this.filters.remove(filter);
		notifyChange(filter);
	}

	public Cell[][] cells() {
		return this.cells;
	}

	public Spreadsheet spreadsheet() {
		return this.spreadsheet;
	}

	/**
	 * Adds the given filter to this Table
	 *
	 * @param expression String containing the expression that will be evaluated
	 * @param tempCell a cell to compile the expression
	 * @throws IllegalValueTypeException
	 * @throws csheets.core.formula.compiler.FormulaCompilationException
	 */
	public void addFilter(String expression, Cell tempCell) throws IllegalValueTypeException, FormulaCompilationException {

		if (expression.charAt(0) != '=') {
			expression = "=" + expression;
		}

		Filter newFilter = new Filter(expression);

		for (int i = 1; i < cells.length; i++) { // for each row (non-header)

			Cell[] row = cells[i];
			String filter = replaceRelativeReferences(expression, cells[0], row);
			ExcelExpressionCompiler compiler = new ExcelExpressionCompiler();
			Expression expr;

			expr = compiler.compile(tempCell, filter);

			if (!expr.evaluate().toBoolean()) {
				// add this row as one of the afected by the newFilter
				newFilter.addRow(cells[i][0].getAddress().getRow());
			}

		}
		// if everything goes well lets associate the filter to this table
		// for persistence and later use
		this.filters.add(newFilter);
		notifyChange(newFilter);
		System.out.println("Added '" + expression + "' filter to " + this);
	}

	/**
	 * This method is used to replace '_col[]' type references with true (A1,
	 * B6, ...) cell references so that Expression compiler is abstracted from
	 * evaluating relative references.
	 *
	 * @see
	 * <a href="http://www.tutorialspoint.com/java/java_regular_expressions.htm">Java
	 * Regular Expressions</a>
	 * @param expression the expression containing references
	 * @param row the row to take the true cell columnReference from
	 */
	public String replaceRelativeReferences(String expression, Cell[] headers,
											Cell[] row)
		throws PatternSyntaxException {
		boolean isString;
		if (!expression.matches(".*_col.*")) {
			return expression;
		}
		Matcher colMatcher = COLUMN_REFERENCE_PATTERN.matcher(expression);

		while (colMatcher.find()) {

			String columnReference = null, substring, trueRef;
			Cell header, cell;
			isString = false;

			if (colMatcher.group(3) == null) {// String columnReference (_col["age"])
				columnReference = colMatcher.group(2);
				isString = true;

			} else if (colMatcher.group(2) == null) { // Index columnReference (_col[2])
				columnReference = colMatcher.group(4);
			}

			substring = colMatcher.group(0);
			if (columnReference == null) {
				throw new PatternSyntaxException("Error evaluating regex pattern", expression, -1);
			}

			for (int i = 0; i < headers.length; i++) {

				header = headers[i]; // the header we will try to match
				cell = row[i];	// the actual cell that will correspond to the
				// matching column in this row

				if (isString) {
					trueRef = testMatch(columnReference, header, cell);
				} else {
					trueRef = testMatch(Integer.parseInt(columnReference), header, cell, i);
				}
				if (trueRef != null) {
					expression = expression.replace(substring, trueRef);
				}
			}
		}
		System.out.println("Compiled EXPRESSION: " + expression);
		return expression;
	}

	private String testMatch(String columnReference, Cell header, Cell cell) {
		if (header.getContent().equalsIgnoreCase(columnReference)
			|| header.getValue().toString().
			equalsIgnoreCase(columnReference)) {
			return cell.getAddress().toString();
		}
		return null;
	}

	private String testMatch(int columnReference, Cell header, Cell cell,
							 int index) {
		if (columnReference == (index + 1)) {
			return cell.getAddress().toString();
		}
		return null;
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 * Inner class represents a Filter which has a List of affected rows
	 */
	public class Filter implements Serializable {

		public List<Integer> rows;
		public String filter;

		protected Filter(String filter) {
			this.rows = new ArrayList<>();
			this.filter = filter;
		}

		public void addRow(Integer row) {
			System.out.println("Row " + row + " matches -> '" + filter);
			this.rows.add(row);
		}

		@Override
		public int hashCode() {
			int hash = 7;
			return hash;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			final Filter other = (Filter) obj;
			return Objects.equals(this.filter, other.filter);
		}

		@Override
		public String toString() {
			return filter;
		}
	}

}
