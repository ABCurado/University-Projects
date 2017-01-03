/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.comments.ui;

import csheets.CleanSheets;
import csheets.core.Cell;
import csheets.core.Workbook;
import csheets.ext.comments.Comment;
import csheets.ext.comments.CommentableCell;
import csheets.ext.comments.CommentsExtension;
import csheets.ui.ctrl.UIController;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.border.Border;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Carlos Mateus
 */
public class CommentControllerTest {

	Comment newComment;
	Comment origin;
	String userName;
	String text;
	Font font;
	Color bgColor;
	Border border;
	UIController uiController;
	CleanSheets ap;
	CommentController control;

	public CommentControllerTest() {
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
	 * Test of addComment method, of class CommentController.
	 */
	@Test
	public void testFunctional() {
		System.out.println("testFunctional");
		Workbook workbook = new Workbook(1);
		Cell cell = workbook.getSpreadsheet(0).getCell(0, 0);
		CommentableCell cellCom = (CommentableCell) cell.
			getExtension(CommentsExtension.NAME);
		CommentController controller = new CommentController(null, cell);
		List<Comment> list = controller.getCommentList(cellCom);
		assertEquals(list.size(), 0);
		controller.addComment(cellCom, "Comment");
		list = controller.getCommentList(cellCom);
		assertEquals(list.size(), 1);
		Comment comment = list.get(0);
		assertEquals(comment.text(), "Comment");
		Comment newComment = new Comment(comment.userName(), "Comment 2");
		controller.apply(comment, newComment);
		assertEquals(comment.commentHistory().size(), 2);
		assertEquals(comment.text(), "Comment 2");
	}

}
