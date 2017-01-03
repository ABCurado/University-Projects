/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.cellsSharing.ui;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.ui.ctrl.UIController;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author João Martins
 */
public class ShareCellsControllerTest {

	private final Workbook workBook;
	private final Spreadsheet spreadSheet;
	private final Cell cell;
	private final StylableCell stylableCell;
	private final ShareCellsController controller;
	private final UIController uiController;
	private final Map<String, String> cells;

	public ShareCellsControllerTest() {
		this.controller = new ShareCellsController();
		this.workBook = new Workbook(1);
		this.spreadSheet = this.workBook.getSpreadsheet(0);
		this.cell = this.spreadSheet.getCell(0, 0);
		this.stylableCell = (StylableCell) cell.
			getExtension(StyleExtension.NAME);
		this.uiController = UIController.getUIController();
		this.cells = new HashMap();
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() throws FormulaCompilationException {
		cell.setContent("={A1:=2}");
		this.stylableCell.setBackgroundColor(new Color(24));
		this.stylableCell.setFont(new Font("Arial", 10, 10));
		this.stylableCell.setForegroundColor(new Color(250));

	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of buildMessage method, of class ShareCellsController.
	 *
	 * @throws java.lang.Exception exception
	 */
	@Test
	public void testBuildMessage() throws Exception {
		String expResult = ";0;0;ERROR;={A1:=2};Arial;0;10;0;0;-16776966;-16777192";
		assertEquals(expResult, this.controller.buildMessage(cell));
	}
}
