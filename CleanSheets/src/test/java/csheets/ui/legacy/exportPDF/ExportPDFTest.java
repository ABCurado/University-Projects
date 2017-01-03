//package csheets.ui.legacy.exportPDF;
//
//import csheets.CleanSheets;
//import csheets.core.Cell;
//import csheets.core.IllegalValueTypeException;
//import csheets.core.Spreadsheet;
//import csheets.core.Workbook;
//import csheets.core.formula.compiler.FormulaCompilationException;
//import csheets.ui.ctrl.UIController;
//import csheets.ui.sheet.SpreadsheetTable;
//import org.junit.After;
//import org.junit.AfterClass;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
///**
// *
// * @author Diogo Leite
// */
//public class ExportPDFTest {
//
//	ExportPDF instance;
//	Workbook workbook;
//	Spreadsheet spreadsheet;
//	UIController uic;
//	Cell[][] cells;
//	SpreadsheetTable sst;
//
//	public ExportPDFTest() {
//	}
//
//	@BeforeClass
//	public static void setUpClass() {
//	}
//
//	@AfterClass
//	public static void tearDownClass() {
//	}
//
//	@Before
//	public void setUp() {
//		instance = new ExportPDF();
//		uic = new UIController(new CleanSheets());
//		workbook = new Workbook(2);
//		String[][] content = new String[10][10];
//		for (int i = 0; i < 10; i++) {
//			for (int j = 0; j < 10; j++) {
//				content[i][j] = "";
//			}
//		}
//
//		workbook.addSpreadsheet(content);
//
//		spreadsheet = workbook.
//			getSpreadsheet(workbook.getSpreadsheetCount() - 1);
//		sst = new SpreadsheetTable(spreadsheet, uic);
//		uic.focusOwner = sst;
//	}
//
//	@After
//	public void tearDown() {
//	}
//
//	/**
//	 * Test to check if we can get the correct information that is in a specific
//	 * workbook in a specific Spreadsheet in a specific cell
//	 *
//	 * @throws csheets.core.formula.compiler.FormulaCompilationException
//	 * exception
//	 * @throws csheets.core.IllegalValueTypeException exception
//	 */
//	@Test
//	public void testInformationInColumm() throws FormulaCompilationException, IllegalValueTypeException {
//
//		sst.getSpreadsheet().getCell(0, 0).setContent("=2+5");
//		sst.getSpreadsheet().getCell(0, 1).setContent("=2-5");
//		sst.setRowSelectionInterval(0, 1);
//		sst.setColumnSelectionInterval(0, 1);
//		long result = 7;
//		assertEquals(sst.getSpreadsheet().getCell(0, 0).getValue().toNumber(), result);
//		//should fail because the cell value is not the same
//		assertNotEquals(sst.getSpreadsheet().getCell(0, 1).getValue().
//			toNumber(), result);
//
//		//test with string
//		sst.getSpreadsheet().getCell(0, 0).setContent("Diogo");
//		sst.getSpreadsheet().getCell(1, 0).setContent("Miguel");
//		sst.setRowSelectionInterval(0, 1);
//		sst.setColumnSelectionInterval(0, 1);
//		String stringResult = "Diogo";
//		assertEquals(sst.getSpreadsheet().getCell(0, 0).getValue().toText(), stringResult);
//		//should fail because the cell value is not the same
//		String stringResultFalse = "Ana";
//		assertNotEquals(sst.getSpreadsheet().getCell(1, 0).getValue().
//			toText(), stringResultFalse);
//
//	}
//
//	/**
//	 * Test to check if we can get the correct information that is in a specific
//	 * workbook in a specific Spreadsheet in a specific cell
//	 *
//	 * @throws csheets.core.formula.compiler.FormulaCompilationException
//	 * exception
//	 * @throws csheets.core.IllegalValueTypeException exception
//	 */
//	@Test
//	public void testInformationInRow() throws FormulaCompilationException, IllegalValueTypeException {
//
//		sst.getSpreadsheet().getCell(0, 0).setContent("=2+5");
//		sst.getSpreadsheet().getCell(1, 0).setContent("=2-5");
//		sst.setRowSelectionInterval(0, 1);
//		sst.setColumnSelectionInterval(0, 1);
//		long result = 7;
//		assertEquals(sst.getSpreadsheet().getCell(0, 0).getValue().toNumber(), result);
//		//should fail because the cell value is not the same
//		assertNotEquals(sst.getSpreadsheet().getCell(1, 0).getValue().
//			toNumber(), result);
//
//		//test with string
//		sst.getSpreadsheet().getCell(0, 0).setContent("Diogo");
//		sst.getSpreadsheet().getCell(1, 0).setContent("Miguel");
//		sst.setRowSelectionInterval(0, 1);
//		sst.setColumnSelectionInterval(0, 1);
//		String stringResult = "Diogo";
//		assertEquals(sst.getSpreadsheet().getCell(0, 0).getValue().toText(), stringResult);
//		//should fail because the cell value is not the same
//		String stringResultFalse = "Ana";
//		assertNotEquals(sst.getSpreadsheet().getCell(1, 0).getValue().
//			toText(), stringResultFalse);
//
//	}
//
//	/**
//	 * Test to check if we can get the correct information that is in a specific
//	 * workbook in a specific Spreadsheet in a specific cell
//	 *
//	 * @throws csheets.core.formula.compiler.FormulaCompilationException
//	 * exception
//	 * @throws csheets.core.IllegalValueTypeException exception
//	 */
//	@Test
//	public void testInformationInOnCell() throws FormulaCompilationException, IllegalValueTypeException {
//		//test with numbers
//		sst.getSpreadsheet().getCell(0, 0).setContent("=2+5");
//		sst.setRowSelectionInterval(0, 1);
//		sst.setColumnSelectionInterval(0, 1);
//		long result = 7;
//		assertEquals(sst.getSpreadsheet().getCell(0, 0).getValue().toNumber(), result);
//		//should fail because the cell value is not the same
//		long resultFalse = 8;
//		assertNotEquals(sst.getSpreadsheet().getCell(0, 0).getValue().
//			toNumber(), resultFalse);
//
//		//test with string
//		sst.getSpreadsheet().getCell(0, 0).setContent("Diogo");
//		sst.setRowSelectionInterval(0, 1);
//		sst.setColumnSelectionInterval(0, 1);
//		String stringResult = "Diogo";
//		assertEquals(sst.getSpreadsheet().getCell(0, 0).getValue().toText(), stringResult);
//		//should fail because the cell value is not the same
//		String stringResultFalse = "Ana";
//		assertNotEquals(sst.getSpreadsheet().getCell(0, 0).getValue().
//			toText(), stringResultFalse);
//
//	}
//
//}
