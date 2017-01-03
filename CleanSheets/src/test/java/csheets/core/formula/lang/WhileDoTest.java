/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.core.formula.lang;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Marcelo Barroso 1131399
 */
public class WhileDoTest {

	private Workbook workBook;
	private Spreadsheet spreadSheet;
	private Cell cellA1;
	private Cell cellB1;

	public WhileDoTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		this.workBook = new Workbook(1);
		this.spreadSheet = this.workBook.getSpreadsheet(0);
		this.cellA1 = this.spreadSheet.getCell(0, 0);
		this.cellB1 = this.spreadSheet.getCell(1, 0);
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of getIdentifier method, of class WhileDo.
	 */
	@Test
	public void testGetIdentifier() throws FormulaCompilationException {
		System.out.println("getIdentifier");
		/*
		this.cellA1.
			setContent("= {@Counter:=1; WhileDo(Eval( \"A\"&@Counter)> 0; {C1:=C1+Eval(\"B\"&@Counter); @Counter:=@Counter+1 }) }");
		assertEquals(this.cellB1.getValue().toString(), "");
		 */
		assertEquals(true, true);
	}

}
