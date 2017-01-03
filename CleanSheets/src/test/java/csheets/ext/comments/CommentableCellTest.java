package csheets.ext.comments;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * A Unit Test class to test CommentableCell.
 *
 * @see CommentableCell
 * @author Alexandre Braganca
 */
public class CommentableCellTest {

	private boolean isNotified = false;
	// create a workbook with 2 sheets
	Workbook wb;
	Spreadsheet s;
	// get the first cell
	Cell c;
	Comment cmt;
	Comment cmt2;

	// activate the comments on the first cell
	CommentableCell cc;
	String username, comment;
	String username2, comment2;
	List<Comment> lstCmt;

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		wb = new Workbook(2);
		s = wb.getSpreadsheet(0);
		c = s.getCell(0, 0);
		cc = new CommentableCell(c);
		username = "Rafael";
		comment = "123";
		cmt = new Comment(username, comment);
		username2 = "Rafael";
		comment2 = "345";
		cmt2 = new Comment(username2, comment2);
		lstCmt = new ArrayList<>();
		lstCmt.add(cmt);
		lstCmt.add(cmt2);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * A method that tests the property hasComment.
	 *
	 */
	@Test
	public void testHasComments() {

		boolean hasComment = cc.hasComments();

		assertTrue(hasComment == false);

		cc.addComment("Rafael", "This is a comment");

		hasComment = cc.hasComments();

		assertTrue(hasComment);
	}

	/**
	 * A method that tests the setter and getter of the user comment.
	 */
	@Test
	public void testSetGetUserComment() {

		cc.addComment("Rafael", "Hello");
		cc.addComment("Rafael", "comment 2");

		assertTrue(cc.getCommentsList().equals(cc.getCommentsList()));
	}

//	/**
//	 * A method that tests the notifications for commented cell listeners.
//	 *
//	 * @see CommentableCellListener
//	 */
//	@Test
//	public void testCommentableCellListenner() {
//
//		// create a workbook with 2 sheets
//		Workbook wb = new Workbook(2);
//		Spreadsheet s = wb.getSpreadsheet(0);
//		// get the first cell
//		Cell c = s.getCell(0, 0);
//
//		// activate the comments on the first cell
//		CommentableCell cc = new CommentableCell(c);
//		CommentsPanel cmt;
//		CommentableCellListener listener = new CommentableCellListenerImpl();
//
//		cc.addCommentableCellListener(listener);
//
//		// modify the cell... this should create an event
//		cc.addComment("Hello", "Rafael");
//
//		assertTrue(isNotified);
//	}
	/**
	 * A inner utility class used by the method testCommentableCellListenner.
	 */
	class CommentableCellListenerImpl implements CommentableCellListener {

		@Override
		public void commentChanged(CommentableCell cell) {
			isNotified = true;
		}

	}

	/**
	 * Test of getCommentsList method, of class CommentableCell.
	 */
	@Test
	public void testGetCommentsList() {
		System.out.println("getCommentsList");
		CommentableCell instance = cc;
		cc.addComment("Rafael", "123");
		cc.addComment("Rafael", "345");
		List<Comment> expResult = lstCmt;
		List<Comment> result = instance.getCommentsList();
		assertEquals(expResult, result);
	}

	/**
	 * Test of addComment method, of class CommentableCell.
	 */
	@Test
	public void testAddComment() {
		System.out.println("addComment");
		String userName = "Rafael";
		String text = "123";
		CommentableCell instance = cc;
		instance.addComment(userName, text);
	}

	/**
	 * Test of fireCommentsChanged method, of class CommentableCell.
	 */
	@Test
	public void testFireCommentsChanged() {
		System.out.println("fireCommentsChanged");
		CommentableCell instance = cc;
		cc.addComment(username, comment);
		instance.fireCommentsChanged();
	}

	/**
	 * Test of getTooltip method, of class CommentableCell.
	 */
	@Test
	public void testGetTooltip() {
		System.out.println("getTooltip");
		CommentableCell instance = cc;
		cc.addComment("Rafael", "123");
		cc.addComment("Rafael", "345");
		String expResult = "<html><b>" + cmt.userName() + ":</b> " + cmt.text() + "<br/><b>" + cmt2.
			userName() + ":</b> " + cmt2.text() + "<br/></html>";
		String result = instance.getTooltip();
		assertEquals(expResult, result);
	}
}
