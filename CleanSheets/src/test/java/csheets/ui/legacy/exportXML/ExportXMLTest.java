/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ui.legacy.exportXML;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.comments.CommentableCell;
import csheets.ext.comments.CommentsExtension;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.ui.ctrl.UIController;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ruben
 */
public class ExportXMLTest {

	private Workbook workbook;
	private Spreadsheet spread1;
	private Spreadsheet spread2;
	private Spreadsheet spread3;
	private UIController uicontroller;

	public ExportXMLTest() throws FormulaCompilationException {

		this.workbook = new Workbook(3);
		this.spread1 = this.workbook.getSpreadsheet(0);
		this.spread2 = this.workbook.getSpreadsheet(1);
		this.spread3 = this.workbook.getSpreadsheet(2);
		this.spread1.setTitle("sheet");
		this.spread2.setTitle("sheet");
		this.spread3.setTitle("sheet");
		this.spread1.getCell(0, 0).setContent("={A1:=2}");
		this.spread2.getCell(1, 1).setContent("={B2:=2}");
		this.spread3.getCell(3, 3).setContent("={D4:=10}");

		StylableCell stylableCell = (StylableCell) spread1.
			getCell(0, 0).getExtension(
			StyleExtension.NAME);

		CommentableCell cell = (CommentableCell) spread1.
			getCell(0, 0).getExtension(
			CommentsExtension.NAME);

//		stylableCell.setFont(new Font(stylableCell.getFont()));
//		setFont("javax.swing.plaf.FontUIResource[family=Dialog,name=Dialog,style=plain,size=12]");
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
	 * Test of exportWorkbook method, of class ExportXML.
	 */
	@Test
	public void testExportWorkbook() {
		System.out.println("exportWorkbook");
		String tagWorkbook = "workbook1";
		String tagSpreadSheet = this.spread1.getTitle();
		String tagRow = "row";
		String tagColumn = "column";
		String tagValue = "value";
		String tagFont = "font";
		String tagBackground = "background";
		String tagBorder = "border";
		String tagForeground = "foreground";
		String tagHorizontal = "horizontal";
		String tagVertical = "vertical";
		String tagComment = "comment";
		String tagAuthor = "author";
		String tagScripts = "scripts";
		String tagScript = "script";
		String tagName = "name";
		String tagType = "type";
		String tagContent = "content";
		String tagSync = "sync";

		String expResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<workbook1>\n"
			+ "	<sheet name=\"sheet\">\n"
			+ "		<row index=\"0\">\n"
			+ "			<column index=\"0\">\n"
			+ "				<value>carlos</value>\n"
			+ "				<font>javax.swing.plaf.FontUIResource[family=Dialog,name=Dialog,style=plain,size=12]</font>\n"
			+ "				<background>-1</background>\n"
			+ "				<border>javax.swing.border.EmptyBorder@f40822</border>\n"
			+ "				<foreground>-13421773</foreground>\n"
			+ "	            <horizontal>2</horizontal>\n"
			+ "             <vertical>0</vertical>\n"
			+ "				<comment>\n"
			+ "					<author>Carlos Mateus</author>\n"
			+ "					<value>primeiro</value>\n"
			+ "					<font>javax.swing.plaf.FontUIResource[family=Dialog,name=Dialog,style=plain,size=12]</font>\n"
			+ "					<background>-1</background>\n"
			+ "					<border>javax.swing.border.EmptyBorder@5c900e</border>\n"
			+ "			    </comment>\n"
			+ "			</column>\n"
			+ "		</row>\n"
			+ "	</sheet>\n"
			+ "	<sheet name=\"sheet\">\n"
			+ "		<she<row index=\"1\">\n"
			+ "			<column index=\"1\">"
			+ "				<value>Mateus</value>\n"
			+ "				<font>javax.swing.plaf.FontUIResource[family=Dialog,name=Dialog,style=plain,size=12]</font>\n"
			+ "				<background>-1</background>\n"
			+ "				<border>javax.swing.border.EmptyBorder@f40822</border>\n"
			+ "				<foreground>-13421773</foreground>\n"
			+ "	            <horizontal>2</horizontal>\n"
			+ "             <vertical>0</vertical>\n"
			+ "			</column>\n"
			+ "		</row>\n"
			+ "	</sheet>\n"
			+ "	<sheet name=\"sheet\" >\n"
			+ "	</sheet>\n"
			+ " <scripts>\n"
			+ "  <script>\n"
			+ "		<name>novo</name>\n"
			+ "		<type>Macro</type>\n"
			+ "		<content>;Sets A1 cell content to 1 A1:= 1 ;Sets B1 cell content to 2(1+A1) B1:= {SUM(1;A1)} ;Sets A2 cell content to 3(1+B1) A2:= {SUM(1;B1)} ;Sets B2 cell content to 4(1+A2) B2:= {SUM(1;A2)}\n"
			+ "		</content>\n"
			+ "		<sync>true</sync>\n"
			+ "	 </script>\n"
			+ "	</scripts>\n"
			+ "</workbook1>\n";

//		String result = ExportXML.
//			exportWorkbook(tagWorkbook, tagSpreadSheet, tagRow, tagColumn, tagValue, tagFont, tagBackground, tagBorder, tagForeground, tagHorizontal, tagVertical, tagComment, tagAuthor, tagScripts, tagScript, tagName, tagType, tagContent, tagSync, this.workbook.
//						   getSpreadsheet(0).getWorkbook());
//
//		assertEquals(expResult, result);
	}

	/**
	 *
	 * Test of exportSpreadsheet method, of class ExportXML.
	 */
	@Test
	public void testExportSpreadsheet() {
		System.out.println("exportSpreadsheet");

		String tagSpreadSheet = this.spread1.getTitle();
		String tagWorkbook = "workbook1";
		String tagRow = "row";
		String tagColumn = "column";
		String tagValue = "value";
		String tagFont = "font";
		String tagBackground = "background";
		String tagBorder = "border";
		String tagForeground = "foreground";
		String tagHorizontal = "horizontal";
		String tagVertical = "vertical";
		String tagComment = "comment";
		String tagAuthor = "author";

		Spreadsheet spreadsheet = this.spread1;

		String expResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<sheet name=\"sheet\" >\n"
			+ "	<row index=\"0\">\n"
			+ "		<column index=\"0\">2</column>\n"
			+ "	</row>\n"
			+ "</sheet>\n"
			+ "";
//
//		String result = ExportXML.
//			exportSpreadsheet(tagWorkbook, tagSpreadSheet, tagRow, tagColumn, tagValue, tagFont, tagBackground, tagBorder, tagForeground, tagHorizontal, tagVertical, tagComment, tagAuthor, spreadsheet);
		//assertEquals(expResult, result);

	}

	/**
	 * Test of exportSpreadsheetSelected method, of class ExportXML.
	 *
	 * @Test
	 */
	public void testExportSpreadsheetSelected() {
		System.out.println("exportSpreadsheetSelected");
		String tagSpreadSheet = "sheet";
		String tagRow = "row";
		String tagColumn = "column";
		Cell[][] cells = this.uicontroller.focusOwner.getSelectedCells();

		String expResult = "";
		String result = ExportXML.
			exportSpreadsheetSelected(tagSpreadSheet, tagRow, tagColumn,
									  uicontroller);
		//assertEquals(expResult, result);

	}
}
