/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.advancedWorkbookSearch.ui;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for Workbook Preview. This class allows to perform tests to all methods
 * belonging to WorkbookPreview Class that handle internal behavior, no UI. In
 * the setUp() the middle of the second spreadsheet of a workbook will be filled
 * with content equal to 1. The WorkbookPreview provides a preview of the
 * Workbook - a matrix (5*5) of cells - where the first position of this matrix
 * will be the first cell with content from giving workbook, even if the first
 * cell with content isn't in the first Spreadsheet. Therefore the first cell of
 * should have content equal to 1. After the spreadsheet setup, a second
 * spreadsheet from a different workbook will provide the expected result. The
 * Preview of this new spreadsheet will be made manually, by inserting its
 * values one by one. If the values from both Previews match the tests are
 * successful.
 *
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
public class WorkbookPreviewTest {

	/**
	 * Static variables to auxiliate tests.
	 */
	static final int FIRST_SPREADSHEET = 0;
	static final int SECOND_SPREADSHEET = 1;
	static final int SPREADSHEET_COUNT = 2;
	static final int COLUMN = WorkbookPreview.COLUMNS;
	static final int ROW = WorkbookPreview.ROWS;

	WorkbookPreview wbpreview;

	public WorkbookPreviewTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {

	}

	/**
	 * Set up elements required to performe tests.
	 *
	 */
	@Before
	public void setUp() {
		Workbook wb = new Workbook(SPREADSHEET_COUNT);
		Spreadsheet ss = wb.getSpreadsheet(SECOND_SPREADSHEET);
		String content = "1";
		for (int i = 4; i < 20; i++) {
			Cell c = ss.getCell(i, i);
			try {
				c.setContent(content);
			} catch (FormulaCompilationException ex) {
				Logger.getLogger(WorkbookPreviewTest.class.getName()).
					log(Level.SEVERE, null, ex);
			}
		}
		wbpreview = new WorkbookPreview(wb);
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test the size of the Preview. It must be a 5*5.
	 */
	@Test
	public void testPreviewSize() {
		Cell[][] matrix = wbpreview.getPreview();
		int column = 0;
		int row = 0;
		for (int i = 0; i < matrix.length; i++) {
			column++;
		}
		for (int i = 0; i < matrix[1].length; i++) {
			row++;
		}
		assertEquals(ROW * COLUMN, row * column);  //=25
	}

	/**
	 * Test of getPreview method, of class WorkbookPreview.
	 */
	@Test
	public void testGetPreview() {
		System.out.println("getPreview");

		/**
		 * Setting up the Result expected.
		 */
		Cell[][] expResult = new Cell[COLUMN][ROW];
		Workbook wb = new Workbook(SPREADSHEET_COUNT);
		Spreadsheet ss = wb.getSpreadsheet(FIRST_SPREADSHEET);
		String _content = "1";
		for (int i = 0; i < 5; i++) {
			Cell c = ss.getCell(i, i);
			try {
				c.setContent(_content);
				expResult[i][i] = c;
			} catch (FormulaCompilationException ex) {
				Logger.getLogger(WorkbookPreviewTest.class.getName()).
					log(Level.SEVERE, null, ex);
			}
		}
		Cell[][] result = wbpreview.getPreview();
		String[][] _result = new String[COLUMN][ROW];
		String[][] _expResult = new String[COLUMN][ROW];
		for (int i = 0; i < 5; i++) {
			_result[i][i] = result[i][i].getContent();
			_expResult[i][i] = expResult[i][i].getContent();
		}
		Assert.assertArrayEquals(_expResult, _result);
	}
}
