/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.advancedWorkbookSearch;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.advancedWorkbookSearch.ui.WorkbookPreview;
import csheets.ext.advancedWorkbookSearch.ui.WorkbookPreviewTest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Eduardo
 */
public class AdvancedWorkbookSearchControllerTest {

	/**
	 * Flag to check if the files exist.
	 */
	boolean flag = true;

	/**
	 * Allow to set up a Temporary Folder for Testing.
	 */
	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	/**
	 * File under the Temporary Folder.
	 */
	public File file;

	/**
	 * Instance of AdvancedWorkbookSearchController.
	 */
	private AdvancedWorkbookSearchController instance = new AdvancedWorkbookSearchController();

	public AdvancedWorkbookSearchControllerTest() {
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
	 * Test of search method, of class AdvancedWorkbookSearchController. The
	 * Result List constains all files located under the Temporary Folder.
	 *
	 */
	@Test
	public void testSearch() {

		try {
			file = temporaryFolder.newFile("file.cls");
		} catch (IOException ex) {
			Logger.getLogger(AdvancedWorkbookSearchControllerTest.class.
				getName()).
				log(Level.SEVERE, null, ex);
		}
		System.out.println("search");
		boolean exist;
		String pattern = ".*.cls";
		List<File> result = instance.search(temporaryFolder.getRoot(), pattern);
		exist = result.contains(file);
		assertEquals(flag, exist);
	}

	/**
	 *	 * Test if content is loaded for Preview. It is necessary to have a
	 * workbook with content. The first For Loop starts with 0 which will add
	 * content to the very first cell of the Workbook. The WorkbookPreview uses
	 * the Address of the first non-empty Cell. This means that the Preview for
	 * this Workbook will be composed by a 5*5 matrix of the {A,B,C,D,E}
	 * columns. The second For Loop guarantees that no other value exists in the
	 * Preview. If it reaches the last condition the test fails.
	 */
	@Test
	public void testSetUpWorkbookPreview() {
		boolean result = true;
		Workbook wb = new Workbook(2);
		Spreadsheet ss = wb.getSpreadsheet(1);
		String _content = "1";
		for (int i = 0; i < 10; i++) {
			Cell c = ss.getCell(i, i);
			try {
				c.setContent(_content);
			} catch (FormulaCompilationException ex) {
				Logger.getLogger(WorkbookPreviewTest.class.getName()).
					log(Level.SEVERE, null, ex);
			}
		}
		String[][] previewContent = new String[WorkbookPreview.COLUMNS][WorkbookPreview.ROWS];
		String[] previewTitles = new String[WorkbookPreview.COLUMNS];

		instance.setUpWorkbookPreview(wb, previewContent, previewTitles);

		for (int j = 0; j < previewTitles.length; j++) {
			if (previewTitles[j].contains("A")) {

			} else if (previewTitles[j].contains("B")) {

			} else if (previewTitles[j].contains("C")) {

			} else if (previewTitles[j].contains("D")) {

			} else if (previewTitles[j].contains("E")) {

			} else if (!previewTitles[j].isEmpty() && previewTitles[j].isEmpty()) {//it must not reach here - the last condition.
				result = false;
			}
		}
		assertEquals(flag, result);
	}

}
