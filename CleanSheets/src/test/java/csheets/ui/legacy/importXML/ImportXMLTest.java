/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ui.legacy.importXML;

import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author scarl
 */
public class ImportXMLTest {

	public ImportXMLTest() {
	}

	@BeforeClass
	public static void setUpClass() {

	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of getFileData method, of class ImportXML.
	 */
	@Test
	public void testGetFileData() throws Exception {
		System.out.println("getFileData");
		String path = "EXPORT.xml";
		String expResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<WorkBook>"
			+ "<SpreadSheet name=\"Sheet  1\" >"
			+ "<Row index=\"0\">"
			+ "<Column index=\"0\">"
			+ "<Value>1</Value>"
			+ "<Font>java.awt.Font[family=Dialog,name=Dialog,style=bold,size=12]</Font>"
			+ "<Background>-1</Background>"
			+ "<Border>javax.swing.border.EmptyBorder@c95c11</Border>"
			+ "<Foreground>-13421773</Foreground>"
			+ "<Horizontal>4</Horizontal>"
			+ "<Vertical>0</Vertical>"
			+ "<Comment>"
			+ "<CommentAuthor>Carlos Mateus</CommentAuthor>"
			+ "<Value>primeiro</Value>"
			+ "<Font>javax.swing.plaf.FontUIResource[family=Dialog,name=Dialog,style=plain,size=12]</Font>"
			+ "<Background>-1</Background>"
			+ "<Border>javax.swing.border.EmptyBorder@b5464d</Border>"
			+ "</Comment>"
			+ "</Column>"
			+ "</Row>"
			+ "</SpreadSheet>"
			+ "<SpreadSheet name=\"Sheet  2\" >"
			+ "</SpreadSheet>"
			+ "<SpreadSheet name=\"Sheet  3\" >"
			+ "</SpreadSheet>"
			+ "<Scripts>"
			+ "<Script>"
			+ "<Name>novo</Name>"
			+ "<Type>Macro</Type>"
			+ "<Content>A1:= 1</Content>"
			+ "<Synchronous>true</Synchronous>"
			+ "</Script>"
			+ "</Scripts>"
			+ "</WorkBook>";

		String result = ImportXML.getFileData(path);
		assertEquals(expResult, result);

	}

	/**
	 * Test of importWorkbook method, of class ImportXML.
	 */
	@Test
	public void testImportWorkbook() throws Exception {
		System.out.println("importWorkbook");
		String path = "EXPORT.xml";
		String tagWorkbook = "WorkBook";
		String tagSpreadSheet = "SpreadSheet";
		String tagRow = "Row";
		String tagColumn = "Column";
		String tagValue = "Value";
		String tagFont = "Font";
		String tagBackground = "Background";
		String tagBorder = "Border";
		String tagForeground = "Foreground";
		String tagHorizontal = "Horizontal";
		String tagVertical = "Vertical";
		String tagComment = "Comment";
		String tagAuthor = "CommentAuthor";
		String tagScripts = "Scripts";
		String tagScript = "Script";
		String tagName = "Name";
		String tagType = "Type";
		String tagContent = "Content";
		String tagSync = "Synchronous";
		Workbook workbook = new Workbook(3);
		String expResult = "1";

		ImportXML.
			importWorkbook(path, tagWorkbook, tagSpreadSheet, tagRow, tagColumn, tagValue, tagFont, tagBackground, tagBorder, tagForeground, tagHorizontal, tagVertical, tagComment, tagAuthor, tagScripts, tagScript, tagName, tagType, tagContent, tagSync, workbook);
		String result = workbook.getSpreadsheet(0).getCell(0, 0).getValue().
			toString();
		assertEquals(expResult, result);
	}

	/**
	 * Test of importSpreadsheet method, of class ImportXML.
	 */
	@Test
	public void testImportSpreadsheet() throws Exception {
		System.out.println("importSpreadsheet");
		String path = "file_spreadsheet_test.xml";
		String tagSpreadSheet = "SpreadSheet";
		String tagRow = "Row";
		String tagColumn = "Column";
		Workbook workbook = new Workbook(1);
		Spreadsheet spreadsheet = workbook.getSpreadsheet(0);
		List<String> expResult = new ArrayList<>();
		expResult.add("a");
		expResult.add("b");
		expResult.add("c");
		expResult.add("d");
		expResult.add("e");
		expResult.add("f");
		expResult.add("coisa");
		expResult.add("g");
		expResult.add("vamos ver");

		//ImportXML.importSpreadsheet(path, tagSpreadSheet, tagRow, tagColumn, spreadsheet);
		List<String> result = new ArrayList<>();

		for (int i = 0; i <= spreadsheet.getRowCount(); i++) {
			for (int j = 0; j <= spreadsheet.getColumnCount(); j++) {
				if (!spreadsheet.getCell(j, i).getContent().isEmpty()) {
					result.add(spreadsheet.getCell(j, i).getContent());
				}
			}
		}
		//assertEquals(expResult, result);
	}

}
