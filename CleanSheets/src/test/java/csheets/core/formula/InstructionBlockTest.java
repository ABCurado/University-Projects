/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.core.formula;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.core.Workbook;
import csheets.core.formula.compiler.ExcelExpressionCompiler;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
public class InstructionBlockTest {

	public InstructionBlockTest() {
	}

	/**
	 * Test of evaluate method, of class InstructionBlock.
	 *
	 * @throws Exception
	 */
	@Test
	public void testEvaluate() throws Exception {
		System.out.println("Evaluate");

		/**
		 * Gathering all Elements.
		 */
		Workbook testBook = new Workbook(2);
		Spreadsheet testSheet = testBook.getSpreadsheet(0);
		Cell cell = testSheet.getCell(0, 0);
		/**
		 * Source simulates an InstructionBlock where the final result should be
		 * equal to 1.
		 */
		cell.setContent("={2+2;4*4;6-1;6>3;2-1}");
		Value expectedResult = new Value(1);

		/**
		 * Generating Formula with recognizable tokens.
		 */
		ExcelExpressionCompiler compiler = new ExcelExpressionCompiler();
		Expression exp = compiler.compile(cell, cell.getContent());

		/**
		 * Mentioned method should recognize the Expression as a
		 * InstructionBlock and assign the result of the last Expression to
		 * result.
		 */
		Value result = exp.evaluate();

		assertEquals(result.toString(), expectedResult.toString());

	}
}
