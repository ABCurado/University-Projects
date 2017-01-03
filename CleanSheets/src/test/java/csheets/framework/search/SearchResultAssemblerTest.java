/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.framework.search;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.comments.Comment;
import csheets.ext.comments.CommentableCell;
import csheets.ext.comments.CommentsExtension;
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
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 */
public class SearchResultAssemblerTest {

	Spreadsheet spreadSheet;
	List<Comment> comments;
	CommentableCell commentableCell;

	public SearchResultAssemblerTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		Workbook workBook = new Workbook(1);
		spreadSheet = workBook.getSpreadsheet(0);
		Cell cell = spreadSheet.getCell(0, 0);
		try {
			cell.setContent("=2+2");
		} catch (FormulaCompilationException ex) {
		}
		commentableCell = (CommentableCell) cell.
			getExtension(CommentsExtension.NAME);
		commentableCell.addComment("Test", "This is a test.");
		Comment comment = new Comment("Test", "This is a test.");
		comments = new ArrayList<>();
		comments.add(comment);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void ensureGetResultInformationWorksAsIntended() {
		SearchResultDTO expResult = new SearchResultDTO(
			"", "Sheet  1", "A1", "=2+2", "4", comments);
		SearchResultDTO result = SearchResultAssembler.
			getResultInformation(spreadSheet, commentableCell);
		assertEquals(expResult, result);
	}

	@Test
	public void resultInformationIsInvalid() {
		SearchResultDTO expResult = new SearchResultDTO(
			"* Unsaved File 1 *", "Sheet 1", "A1", "=2+2", "4", comments);
		SearchResultDTO result = SearchResultAssembler.
			getResultInformation(spreadSheet, commentableCell);
		boolean validation = expResult.equals(result);
		assertEquals(false, validation);
	}
}
