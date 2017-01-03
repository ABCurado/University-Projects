/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.advancedWorkbookSearch.ui;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;

/**
 * A Preview of one specific Workbook. WorkbookPreview is not a Domain Concept
 * altought it has a connection to one Workbook. It contains a matrix of cells
 * where the position (0,0) will be the first content cell of the giving
 * workbook. The size of the preview will be 5*5 but can be changed.
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
public class WorkbookPreview {

	/**
	 * Preview Columns.
	 */
	public static final int COLUMNS = 5;

	/**
	 * Preview Rows.
	 */
	public static final int ROWS = 5;

	/**
	 * Workbook.
	 */
	private final Workbook workbook;

	/**
	 * First cell with content.
	 */
	private Cell cell;

	/**
	 * First Spreadsheet with content.
	 */
	private Spreadsheet spreadsheet;

	/**
	 * Preview Matrix.
	 */
	private Cell[][] matrix = new Cell[COLUMNS][ROWS];

	/**
	 * Creates an Workbook Preview.
	 *
	 * @param workbook workbook
	 */
	public WorkbookPreview(Workbook workbook) {
		this.workbook = workbook;
		preview();
		setUpPreview();
	}

	/**
	 * Search for Content.
	 */
	private void preview() throws NullPointerException {
		outerloop:
		for (int i = 0; i < this.workbook.getSpreadsheetCount(); i++) {
			Spreadsheet ss = this.workbook.getSpreadsheet(i);
			for (int j = 0; j < ss.getColumnCount() + 1; j++) {
				Cell[] column = ss.getColumn(j);
				if (checkColumn(column)) { //checks in the specific column if there is a non-empty cell.
					spreadsheet = ss;
					break outerloop; //breaks outerloop when first non-empty cell is found.
				}
			}
		}
	}

	/**
	 * Checks in the given Column is has a non-empty cell.
	 *
	 * @param column
	 * @return true if Column has the non-empty cell.
	 */
	private boolean checkColumn(Cell[] column) {
		for (Cell c : column) {
			if (!c.getContent().equalsIgnoreCase("") && c != null) {
				cell = c;
				return true;
			}
		}
		return false;
	}

	/**
	 * Builds the matrix.
	 */
	private void setUpPreview() {
		int[] column = new int[COLUMNS];
		int[] row = new int[ROWS];

		for (int i = 0; i < COLUMNS; i++) {
			column[i] = cell.getAddress().getColumn() + i;
		}
		for (int j = 0; j < ROWS; j++) {
			row[j] = cell.getAddress().getRow() + j;
		}
		for (int i = 0; i < COLUMNS; i++) {
			for (int j = 0; j < ROWS; j++) {
				matrix[i][j] = this.spreadsheet.getCell(column[i], row[j]);
			}
		}
	}

	/**
	 *
	 * @return Workbook Preview Matrix.
	 */
	public Cell[][] getPreview() {
		return this.matrix;
	}
}
