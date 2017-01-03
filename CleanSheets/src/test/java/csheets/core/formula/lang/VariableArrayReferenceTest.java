/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.core.formula.lang;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
public class VariableArrayReferenceTest {

	private VariableArrayReference reference;
	private Workbook workBook;
	private Spreadsheet spreadSheet;
	private Cell cellA1;
	private Cell cellB1;
	private Cell cellC1;
	private Cell cellD1;

	public VariableArrayReferenceTest() {
		this.workBook = new Workbook(1);
		this.spreadSheet = this.workBook.getSpreadsheet(0);
		this.cellA1 = this.spreadSheet.getCell(0, 0);
		this.cellB1 = this.spreadSheet.getCell(1, 0);
		this.cellC1 = this.spreadSheet.getCell(2, 0);
		this.cellD1 = this.spreadSheet.getCell(3, 0);
	}

	@Before
	public void setUp() {
		try {
			this.cellA1.setContent("=@global[2]:=2"); //Sets the Content for one specific cell.
			this.cellB1.setContent("=@ui[7]:=5"); //Sets the Content for one specific cell.
		} catch (FormulaCompilationException ex) {
			Logger.getLogger(VariableArrayReferenceTest.class.getName()).
				log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Test of parse method, of class VariableArrayReference. Using A1 cell.
	 */
	@Test
	public void testParse() {
		System.out.println("parse");
		VariableArrayReference instance = new VariableGlobalReference(cellA1, cellA1.
																	  getContent());
		String expResult = "=@global";
		String result = instance.getVariable();
		assertEquals(expResult, result);
	}

	/**
	 * Test of parse method, of class VariableArrayReference. Using B1 cell.
	 */
	@Test
	public void testParse2() {
		System.out.println("parse");
		VariableArrayReference instance = new VariableGlobalReference(cellB1, cellB1.
																	  getContent());
		String expResult = "=@ui";
		String result = instance.getVariable();
		assertEquals(expResult, result);
	}

	/**
	 * Test of evaluate method, of class VariableArrayReference. Using C1 Cell
	 */
	@Test
	public void testEvaluate() {
		System.out.println("evaluate");
		VariableArrayReference instance = new VariableGlobalReference(cellC1, "@global[2]");
		String expResult = "2";
		Value result = instance.evaluate();
		assertEquals(expResult, result.toString());

	}

	/**
	 * Test of evaluate method, of class VariableArrayReference. Using D1 Cell
	 */
	@Test
	public void testEvaluate2() {
		System.out.println("evaluate");
		VariableArrayReference instance = new VariableGlobalReference(cellD1, "@ui[7]");
		String expResult = "5";
		Value result = instance.evaluate();
		assertEquals(expResult, result.toString());
	}
}
