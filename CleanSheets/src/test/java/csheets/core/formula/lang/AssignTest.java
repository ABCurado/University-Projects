/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.core.formula.lang;

import csheets.core.Cell;
import csheets.core.CellImpl;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.core.formula.compiler.ExcelExpressionCompiler;
import csheets.core.formula.compiler.FormulaCompilationException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * This is the Class Test for the Assign Operations. It also test the
 * ExcelExpressionCompiler.
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 * @author João Martins 1131190@isep.ipp.pt
 */
public class AssignTest {

	private ExcelExpressionCompiler compiler;
	private Workbook workBook;
	private Spreadsheet spreadSheet;
	private Cell cellA1;
	private Cell cellB1;

	public AssignTest() {
		this.workBook = new Workbook(1);
		this.spreadSheet = this.workBook.getSpreadsheet(0);
		this.cellA1 = this.spreadSheet.getCell(0, 0);
		this.cellB1 = this.spreadSheet.getCell(1, 0);
	}

	/**
	 * Test of applyTo method, of class Assign.
	 *
	 * @throws Exception
	 */
	@Test
	public void testApplyTo() throws Exception {
		System.out.println("testApplyTo");
		this.cellA1.setContent("={B1:=2}");
		assertEquals(this.cellB1.getValue().toString(), "2");
	}

	@Test
	public void testGetCellTemporaryVariable() throws FormulaCompilationException {
		System.out.println("testGetCellTemporaryVariable");
		this.cellA1.setContent("={_temp:=11}");
		CellImpl cell = (CellImpl) this.cellA1;
		assertEquals(cell.getVariableValue("_temp", 1).toString(), "11");
	}

	@Test
	public void testAssignTemporaryVariable() throws FormulaCompilationException {
		System.out.println("testAssignTemporaryVariable");
		this.cellA1.setContent("={_temp:=10; B1:=_temp}");
		assertEquals(this.cellB1.getValue().toString(), "10");
	}

	@Test(expected = NullPointerException.class)
	public void testLastNameNotAcceptNull() throws FormulaCompilationException {
		System.out.println("testLastNameNotAcceptNull");
		cellA1.setContent("={B1:=_algo}");
	}

	@Test
	public void testGetCellGlobalVariable() throws FormulaCompilationException {
		System.out.println("testGetCellGlobalVariable");
		this.cellA1.setContent("={@temp:=11}");
		this.cellB1.setContent("={@temp}");
		assertEquals(this.cellB1.getValue().toString(), "11");
	}

	@Test
	public void testAssignGlobalVariable() throws FormulaCompilationException {
		System.out.println("testAssignGlobalVariable");
		this.cellA1.setContent("={@temp:=10}");
		this.cellB1.setContent("={@temp}");
		assertEquals(this.cellB1.getValue().toString(), "10");
	}

	/**
	 * Test for Array Variables.
	 *
	 * @throws FormulaCompilationException
	 */
	@Test
	public void testAssignGlobalVariablePosition() throws FormulaCompilationException {
		System.out.println("testAssignGlobalVariablePosition");
		this.cellA1.setContent("={@temp[2]:=10}");
		this.cellB1.setContent("={@temp[2]}");
		assertEquals(this.cellB1.getValue().toString(), "10");
	}

	@Test
	public void testAssignGlobalVariablePosition2() throws FormulaCompilationException {
		System.out.println("testAssignGlobalVariablePosition2");
		this.cellA1.setContent("={@temp[10]:=50}");
		this.cellB1.setContent("={@temp[10]}");
		assertEquals(this.cellB1.getValue().toString(), "50");
	}

	@Test
	public void testAssignTemporaryVariablePosition() throws FormulaCompilationException {
		System.out.println("testAssignTemporaryVariablePosition");
		this.cellA1.setContent("={_temp[4]:=10; B1:=_temp[4]}");
		assertEquals(this.cellB1.getValue().toString(), "10");
	}

	@Test
	public void testAssignTemporaryVariablePosition2() throws FormulaCompilationException {
		System.out.println("testAssignTemporaryVariablePosition2");
		this.cellA1.setContent("={_temp[30]:=100; B1:=_temp[30]}");
		assertEquals(this.cellB1.getValue().toString(), "100");
	}
}
