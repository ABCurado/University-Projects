/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.distributedWorkbook;

import csheets.ext.distributedWorkbookSearch.WorkBookDTO;
import csheets.ext.distributedWorkbookSearch.WorkBookDTOAssembler;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 */
public class WorkBookDTOAssemblerTest {

	Workbook workBook;
	Spreadsheet spreadSheet;

	public WorkBookDTOAssemblerTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		workBook = new Workbook(3);
		spreadSheet = workBook.getSpreadsheet(0);
		spreadSheet.setTitle("S1");
		workBook.getSpreadsheet(1).setTitle("S2");
		workBook.getSpreadsheet(2).setTitle("S3");

		Cell cell00 = spreadSheet.getCell(0, 0);
		Cell cell01 = spreadSheet.getCell(1, 0);
		Cell cell10 = spreadSheet.getCell(0, 1);
		Cell cell11 = spreadSheet.getCell(1, 1);
		try {
			cell00.setContent("=2+2");
			cell01.setContent("5");
			cell10.setContent("=5+1");
			cell11.setContent("=7");

		} catch (FormulaCompilationException ex) {
		}
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of getWorkbookDTO method, of class WorkBookDTOAssembler.
	 */
	@Test
	public void ensureAssemblerWorksAsIntended() {
		System.out.println("ensureAssemblerWorksAsIntended");
		WorkBookDTO result = WorkBookDTOAssembler.
			getWorkbookDTO(workBook);
		assertEquals("S2", result.spreadsheets.get(1));
		assertEquals("6", result.cells.get(0)[1][0]);
		assertEquals("7", result.cells.get(0)[1][1]);
		assertTrue(result.spreadsheets.size() == 3);
	}

}
