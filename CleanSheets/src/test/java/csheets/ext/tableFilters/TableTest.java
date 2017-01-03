/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.tableFilters;

import csheets.core.Cell;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 */
public class TableTest {

	private final SpreadsheetWithTables sheet;

	private Cell[][] cells;
	private Cell[] headers;
	private Cell[] row;
	private Cell[] row2;

	public TableTest() {
		this.sheet = (SpreadsheetWithTables) new Workbook(1).getSpreadsheet(0).
			getExtension(TableFiltersExtension.NAME);
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		try {
			// Headers
			sheet.getCell(0, 0).setContent("nome");
			sheet.getCell(1, 0).setContent("idade");
			sheet.getCell(2, 0).setContent("altura");
			// content
			sheet.getCell(0, 1).setContent("joao");
			sheet.getCell(1, 1).setContent("10");
			sheet.getCell(2, 1).setContent("130");

			sheet.getCell(0, 2).setContent("jose");
			sheet.getCell(1, 2).setContent("15");
			sheet.getCell(2, 2).setContent("160");

		} catch (FormulaCompilationException ex) {
		}

		headers = new Cell[3];
		headers[0] = sheet.getCell(0, 0);
		headers[1] = sheet.getCell(1, 0);
		headers[2] = sheet.getCell(2, 0);

		row = new Cell[3];
		row[0] = sheet.getCell(0, 1);
		row[1] = sheet.getCell(1, 1);
		row[2] = sheet.getCell(2, 1);

		row2 = new Cell[3];

		cells = new Cell[3][3];
		cells[0] = headers;
		cells[1] = row;
		cells[2] = row2;
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of addFilter method, of class Table.
	 */
	@Test
	public void testReplaceRelativeReferences() throws Exception {
		System.out.println("testReplaceRelativeReferences");
		String expression = "=or(_col[\"idade\"]>10;_col[3]<123)";

		Table instance = new Table(sheet, cells, expression);

		String expected = "=OR(B2>10;C2<123)";
		String result = instance.
			replaceRelativeReferences(expression, headers, row);

		Assert.assertTrue(result.equalsIgnoreCase(expected));
	}

	/**
	 * Ensure invalid creation of table fails.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInValidSizeTableCreation() throws Exception {
		System.out.println("testInValidSizeTableCreation");

		Cell[][] invalid = new Cell[2][3];
		invalid[0] = headers;
		invalid[1] = row;

		Table instance = new Table(sheet, invalid, "test");

	}

	/**
	 * Ensure invalid creation of table fails.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyHeadersTableCreation() throws Exception {
		System.out.println("testEmptyHeadersTableCreation");

		cells[0][0].setContent("");
		cells[0][1].setContent("");
		cells[0][2].setContent("");

		Table instance = new Table(sheet, cells, "test");

	}

}
