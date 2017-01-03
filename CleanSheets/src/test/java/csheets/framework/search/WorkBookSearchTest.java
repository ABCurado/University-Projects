package csheets.framework.search;

import csheets.core.Value;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.comments.CommentableCell;
import csheets.ext.comments.CommentsExtension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 * @author José Barros
 */
public class WorkBookSearchTest {

	Workbook workBook;
	WorkBookSearch instance;
	Map<String, Value.Type> types;
	String pattern;

	public WorkBookSearchTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		pattern = "(.*4.*)|([0-9]\\+[0-9])";
		Stack<Workbook> workBooks = new Stack<>();
		workBook = new Workbook(1);
		workBooks.push(workBook);
		instance = new WorkBookSearch(workBooks);
		types = new HashMap<>();
		types.put("numeric", Value.Type.NUMERIC);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void ensureGetMatchesWorksAsIntended() {
		// Set a few cells' content and added a comment: only 3 of them should match (646; 3+3; adcwd4cecec9)
		try {
			workBook.getSpreadsheet(0).getCell(0, 0).setContent("646");
			workBook.getSpreadsheet(0).getCell(0, 1).setContent("=3+3");
			workBook.getSpreadsheet(0).getCell(0, 2).setContent("5");
			((CommentableCell) workBook.getSpreadsheet(0).getCell(0, 3).
				getExtension(CommentsExtension.NAME)).
				addComment("Test", "adcwd4cecec9");
		} catch (FormulaCompilationException ex) {
		}

		int expResult = 3;
		int result = instance.getMatches(pattern, types, true, true).size();
		assertEquals(expResult, result);
	}

	@Test
	public void searchForErrorAndBoolean() {
		String pattern2 = "FALSE|(.*4.*)";
		try {
			workBook.getSpreadsheet(0).getCell(0, 0).setContent("FALSE");
			workBook.getSpreadsheet(0).getCell(0, 1).setContent("=Z12+4");
		} catch (FormulaCompilationException ex) {
		}

		Map<String, Value.Type> errorMoneyTypes = new HashMap<>();
		errorMoneyTypes.put("error", Value.Type.ERROR);
		errorMoneyTypes.put("boolean", Value.Type.BOOLEAN);

		int expResult = 2;
		int result = instance.
			getMatches(pattern2, errorMoneyTypes, false, false).size();
		assertEquals(expResult, result);
	}

	@Test
	public void emptySearch() {
		String pattern3 = "[0-9]*";

		try {
			workBook.getSpreadsheet(0).getCell(0, 0).setContent("Empty");
			workBook.getSpreadsheet(0).getCell(0, 1).setContent("Search");

		} catch (FormulaCompilationException ex) {
		}

		types.put("numeric", Value.Type.NUMERIC);

		List<SearchResultDTO> results = instance.
			getMatches(pattern3, types, true, true);

		int expResult = 0;
		int result = results.size();
		assertEquals(expResult, result);
	}

	@Test(expected = NullPointerException.class)
	public void replaceOnNullSpread() {
		String pattern3 = "[0-9]*";

		try {
			workBook.getSpreadsheet(0).getCell(0, 0).setContent("10");
			workBook.getSpreadsheet(0).getCell(0, 1).setContent("=A1*2");

		} catch (FormulaCompilationException ex) {
		}

		types.put("numeric", Value.Type.NUMERIC);

		List<SearchResultDTO> results = instance.
			getMatches(pattern3, types, true, true);

		instance.replaceMatchContent(results.get(0), "travolta", null);
	}

	@Test
	public void replaceMatchContent() {
		String pattern3 = ".*.";

		try {
			workBook.getSpreadsheet(0).getCell(0, 0).setContent("john");
			workBook.getSpreadsheet(0).getCell(0, 1).setContent("hi");
			workBook.getSpreadsheet(0).getCell(0, 2).setContent("john");

		} catch (FormulaCompilationException ex) {
		}

		types.put("text", Value.Type.TEXT);

		List<SearchResultDTO> results = instance.
			getMatches(pattern3, types, true, true);

		System.out.println(results.get(0));

		instance.replaceMatchContent(results.get(0), "travolta", workBook.
									 getSpreadsheet(0));

		String expResult = "travolta";
		String result = workBook.getSpreadsheet(0).getCell(0, 0).getContent();
		assertEquals(expResult, result);
	}

}
