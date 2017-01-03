/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.conditionalFormatting.ui;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.comments.Comment;
import csheets.ext.comments.CommentableCell;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.ui.ctrl.UIController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Diogo Leite
 *
 */
public class ConditionalFormattingControllerTest {

	private Cell cell;
	private ConditionalFormattingController controller;
	private UIController m_controller;
	private boolean isNotified = false;
	// create a workbook with 2 sheets
	Workbook wb;
	Spreadsheet s;
	// get the first cell
	Cell c;
	Comment cmt;
	Comment cmt2;

	// activate the comments on the first cell
	CommentableCell cc;
	String username, comment;
	String username2, comment2;
	List<Comment> lstCmt;

	public ConditionalFormattingControllerTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {

		m_controller = UIController.getUIController();
		controller = new ConditionalFormattingController(m_controller);
		cell = controller.createConditionalCell();
	}

	@After
	public void tearDown() {
	}

	@Test
	public void isExpressionComparisonTrueTest() {
		try {
			cell.setContent("=2<5");
		} catch (FormulaCompilationException ex) {
			Logger.
				getLogger(ConditionalFormattingControllerTest.class.getName()).
				log(Level.SEVERE, null, ex);
		}

		assertEquals(true, controller.isExpressionComparison(cell));

	}

	@Test
	public void isExpressionComparisonFalseTest() {
		try {
			cell.setContent("=2+5");
		} catch (FormulaCompilationException ex) {
			Logger.
				getLogger(ConditionalFormattingControllerTest.class.getName()).
				log(Level.SEVERE, null, ex);
		}

		assertEquals(false, controller.isExpressionComparison(cell));

	}

	@Test
	public void evaluateExpressionTrueTest() {
		try {
			cell.setContent("=2<5");
		} catch (FormulaCompilationException ex) {
			Logger.
				getLogger(ConditionalFormattingControllerTest.class.getName()).
				log(Level.SEVERE, null, ex);
		}
		boolean result;
		try {
			result = controller.evaluateExpression(cell);
			assertEquals(true, result);
		} catch (IllegalValueTypeException ex) {
			Logger.
				getLogger(ConditionalFormattingControllerTest.class.getName()).
				log(Level.SEVERE, null, ex);
		}

	}

	@Test
	public void evaluateExpressionFalseTest() {
		try {
			cell.setContent("=2>5");
		} catch (FormulaCompilationException ex) {
			Logger.
				getLogger(ConditionalFormattingControllerTest.class.getName()).
				log(Level.SEVERE, null, ex);
		}
		boolean result;
		try {
			result = controller.evaluateExpression(cell);
			assertEquals(false, result);
		} catch (IllegalValueTypeException ex) {
			Logger.
				getLogger(ConditionalFormattingControllerTest.class.getName()).
				log(Level.SEVERE, null, ex);
		}
	}

	@Test
	public void testCreateConditionalCell() {
		System.out.println("createConditionalCell");
		Workbook workbook = new Workbook(1);
		Spreadsheet spreadsheet = workbook.getSpreadsheet(0);
		Cell expResult = spreadsheet.
			getCell(0, 0);
		Cell result = controller.createConditionalCell();
		assertEquals(expResult.getContent(), result.getContent());
	}

	/**
	 * Test of createStylableCells method, of class
	 * ConditionalFormattingController.
	 */
	@Test
	public void testCreateStylableCells() {
		System.out.println("createStylableCells");
		int nr = 0;
		Workbook workbook = new Workbook(1);
		Spreadsheet spreadsheet = workbook.getSpreadsheet(0);

		StylableCell cell = (StylableCell) spreadsheet.getCell(0, nr).
			getExtension(
				StyleExtension.NAME);
		StylableCell result = controller.createStylableCells(nr);
		assertEquals(cell.getAddress(), result.getAddress());
	}

}
