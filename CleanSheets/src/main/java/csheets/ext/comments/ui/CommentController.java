package csheets.ext.comments.ui;

import csheets.core.Cell;
import csheets.ext.comments.Comment;
import csheets.ext.comments.CommentableCell;
import csheets.ext.style.ui.BorderChooser;
import csheets.ext.style.ui.FontChooser;
import csheets.notification.Notification;
import csheets.ui.ctrl.UIController;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JColorChooser;
import javax.swing.border.Border;

/**
 * A controller for updating the user-specified comment of a cell.
 *
 * @author Alexandre Braganca
 * @author Einar Pehrson
 */
public class CommentController {

	/**
	 * The user interface controller
	 */
	private final UIController uiController;

	private Cell cell;

	/**
	 * Creates a new comment controller.
	 *
	 * @param uiController the user interface controller
	 */
	public CommentController(UIController uiController) {
		this.uiController = uiController;
	}

	public CommentController(UIController uiController, Cell cell) {
		this.uiController = uiController;
		this.cell = cell;
	}

	/**
	 * Attempts to create a new comment from the given string. If successful,
	 * adds the comment to the given cell. If the input string is empty or null,
	 * the comment is set to null.
	 *
	 * @param cell the cell for which the comment should be set
	 * @param commentString the comment, as entered by the user
         * @param font Letter font
         * @param bgColor Background color
         * @param border Border
	 * @return true if the cell's comment was changed
	 */
	public boolean addComment(CommentableCell cell, String commentString,
							  Font font,
							  Color bgColor, Border border)
		throws IllegalArgumentException {

		String userName = System.getProperty("user.name");
		// Stores the comment
		cell.addComment(userName, commentString, font, bgColor, border);
		if (uiController != null) {
			uiController.
				setWorkbookModified(cell.getSpreadsheet().getWorkbook());
		}
		Notification.commentInformer().notifyChange();
		return true;
	}

	public boolean addComment(CommentableCell cell, String commentString)
		throws IllegalArgumentException {

		String userName = System.getProperty("user.name");
		// Stores the comment
		cell.addComment(userName, commentString);
		if (uiController != null) {
			uiController.
				setWorkbookModified(cell.getSpreadsheet().getWorkbook());
		}
		Notification.commentInformer().notifyChange(cell);
		return true;
	}

//	/**
//	 * A cell is selected.
//	 *
//	 * @param cell the cell whose comments changed
//	 */
//	public boolean cellSelected(CommentableCell cell) {
//		// Updates the text field and validates the comment, if any
//		if (cell.hasComment()) {
//			return true;
//		} else {
//			return false;
//		}
//	}
	public List<Comment> getCommentList(CommentableCell cell) {
		if (cell == null) {
			return new ArrayList();
		}
		return cell.getCommentsList();
	}

	/**
	 * This method change the Font of the Comment
	 *
	 * @param comment Comment
	 */
	public void changeFont(Comment comment) {

		Font font = FontChooser.showDialog(
			null,
			"Choose Font",
			Comment.FONT);

		if (font != null) {
			comment.setFont(font);
			Notification.commentInformer().notifyChange(comment);
		}
	}

	/**
	 * This method change the Background of the Comment
	 *
	 * @param comment Comment
	 */
	public void changeBackground(Comment comment) {

		Color color = JColorChooser.showDialog(
			null,
			"Choose Background Color",
			Comment.BACKGROUND);

		if (color != null) {
			comment.setBackgroundColor(color);
			Notification.commentInformer().notifyChange(comment);
		}
	}

	/**
	 * This method change the Border of the Comment
	 *
	 * @param comment comment
	 */
	public void changeBorder(Comment comment) {

		Border border = BorderChooser.showDialog(
			null,
			"Choose Border",
			Comment.BORDER);

		if (border != null) {
			comment.setBorder(border);
			Notification.commentInformer().notifyChange(comment);
		}
	}

	public void changeText(Comment comment) {
		if (cell == null) {
			new CommentEditUI(uiController, comment).setVisible(true);
		} else {
			new CommentEditUI(uiController, comment, false, cell).
				setVisible(true);
		}
	}

	public void apply(Comment origin, Comment newComment) {
		origin.setComment(newComment);
		Notification.commentInformer().notifyChange(origin);
	}

}
