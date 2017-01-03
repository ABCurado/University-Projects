///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package csheets.ext.importExportData.ui;
//
//import csheets.CleanSheets;
//import csheets.core.Cell;
//import csheets.core.Spreadsheet;
//import csheets.core.Workbook;
//import csheets.core.formula.compiler.FormulaCompilationException;
//import csheets.ext.importExportData.parsers.FileHandler;
//import csheets.ui.ctrl.UIController;
//import csheets.ui.sheet.SpreadsheetTable;
//import java.io.File;
//import java.io.IOException;
//import org.junit.After;
//import org.junit.AfterClass;
//import static org.junit.Assert.assertEquals;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
///**
// *
// * @author Rui Bastos
// */
//public class ImportExportTextFileControllerTest {
//
//	ImportExportTextFileController instance;
//	String path, separator;
//	UIController uic = new UIController(new CleanSheets());
//
//	public ImportExportTextFileControllerTest() {
//
//		File f = new File("exportTest.txt");
//		f.delete();
//		instance = new ImportExportTextFileController();
//		path = "file_test.txt";
//		separator = ",";
//		Workbook wb = new Workbook(2);
//		String[][] content = new String[10][10];
//		for (int i = 0; i < 10; i++) {
//			for (int j = 0; j < 10; j++) {
//				content[i][j] = "";
//			}
//		}
//		wb.addSpreadsheet(content);
//		Spreadsheet s = wb.getSpreadsheet(wb.getSpreadsheetCount() - 1);
//		SpreadsheetTable sst = new SpreadsheetTable(s, uic);
//		uic.focusOwner = sst;
//
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
//
//	}
//
//	@After
//	public void tearDown() {
//	}
//
//	/**
//	 * Test of hasEnoughCells method, of class ImportExportTextFileController.
//	 */
//	@Test
//	public void testHasEnoughCells() {
//
//		Cell[][] cells = uic.focusOwner.getSelectedCells();
//		boolean expResult = false;
//		boolean result = instance.hasEnoughCells(path, separator, cells);
//		assertEquals(expResult, result);
//		expResult = true;
//		uic.focusOwner.selectAll();
//
//		cells = uic.focusOwner.getSelectedCells();
//		result = instance.hasEnoughCells(path, separator, cells);
//
//		assertEquals(expResult, result);
//
//	}
//
//	/**
//	 * Test of parse method, of class ImportExportTextFileController.
//	 */
//	@Test
//	public void testParse() throws Exception {
//
//		Cell[][] cells = new Cell[2][3];
//		cells[0][0] = uic.focusOwner.getSpreadsheet().getCell(0, 0);
//		cells[0][1] = uic.focusOwner.getSpreadsheet().getCell(0, 1);
//		cells[0][2] = uic.focusOwner.getSpreadsheet().getCell(0, 2);
//		cells[1][0] = uic.focusOwner.getSpreadsheet().getCell(1, 0);
//		cells[1][1] = uic.focusOwner.getSpreadsheet().getCell(1, 1);
//		cells[1][2] = uic.focusOwner.getSpreadsheet().getCell(1, 2);
//		Cell[][] result = instance.parse(path, separator, true, cells);
//		assertEquals(result[0].length, 4);
//		result = instance.parse(path, separator, false, cells);
//		assertEquals(result[0].length, 4);
//
//	}
//
//	/**
//	 * Test of exportFile method, of class ImportExportTextFileController.
//	 */
//	@Test
//	public void testExportFile() throws FormulaCompilationException, IOException {
//
//		boolean expResult = true;
//		uic.focusOwner.getSpreadsheet().getCell(0, 0).setContent("test1");
//		uic.focusOwner.getSpreadsheet().getCell(0, 1).setContent("test2");
//		uic.focusOwner.setRowSelectionInterval(0, 1);
//		uic.focusOwner.setColumnSelectionInterval(0, 1);
//		String path = "exportTest.txt";
//		boolean result = instance.exportFile(path, uic.focusOwner.
//											 getSelectedCells(), separator);
//		assertEquals(expResult, result);
//
//		String fileContents = new FileHandler().getFileContents(path);
//		assertEquals(true, fileContents.contains("test1") && fileContents.
//					 contains("test2"));
//		File f = new File(path);
//		f.delete();
//
//	}
//}
