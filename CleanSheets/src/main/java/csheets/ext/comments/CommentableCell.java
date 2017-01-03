package csheets.ext.comments;

import csheets.core.Cell;
import csheets.ext.CellExtension;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.Border;

/**
 * An extension of a cell in a spreadsheet, with support for comments.
 *
 * @author Alexandre Braganca
 * @author Einar Pehrson
 */
public class CommentableCell extends CellExtension {

	/**
	 * The unique version identifier used for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The cell's user-specified comments list
	 */
	private List<Comment> commentsList = new ArrayList<>();

	/**
	 * The listeners registered to receive events from the comentable cell
	 */
	private transient List<CommentableCellListener> listeners
		= new ArrayList<CommentableCellListener>();

	/**
	 * Creates a comentable cell extension for the given cell.
	 *
	 * @param cell the cell to extend
	 */
	CommentableCell(Cell cell) {
		super(cell, CommentsExtension.NAME);
	}


	/*
	 * DATA UPDATES
	 */
//	public void contentChanged(Cell cell) {
//	}
	/*
	 * COMMENT ACCESSORS
	 */
	/**
	 *
	 * @return lstComments
	 */
	public List<Comment> getCommentsList() {
		return this.commentsList;
	}

	/**
	 * Returns whether the cell has comments.
	 *
	 * @return true if the cell has comments
	 */
	public boolean hasComments() {
		if (this.commentsList == null) {
			return false;
		}
		return !this.commentsList.isEmpty();
	}

	/**
	 * Sets the user-specified comment list for the cell.
	 *
	 * @param userName the username
	 * @param text the user-specified comment list
	 */
	public void addComment(String userName, String text)
		throws IllegalArgumentException {

		if (userName == null || userName.isEmpty()) {
			throw new IllegalArgumentException("Unable to get the username.");
		}
		if (text == null || text.isEmpty()) {
			throw new IllegalArgumentException("Comment string is empty or null.");
		}
		Comment newComment = new Comment(userName, text);
		commentsList.add(newComment);
		// Notifies listeners
		fireCommentsChanged();
	}

	public void addComment(String userName, String text, Font font,
						   Color bgColor, Border border)
		throws IllegalArgumentException {
		if (font == null || bgColor == null || border == null) {
			throw new IllegalArgumentException("Unable to get the date.");
		}
		if (userName == null || userName.isEmpty()) {
			throw new IllegalArgumentException("Unable to get the username.");
		}
		if (text == null || text.isEmpty()) {
			throw new IllegalArgumentException("Comment string is empty or null.");
		}
		Comment newComment = new Comment(userName, text, font, bgColor, border);
		commentsList.add(newComment);
		// Notifies listeners
		fireCommentsChanged();
	}


	/*
	 * EVENT LISTENING SUPPORT
	 */
	/**
	 * Registers the given listener on the cell.
	 *
	 * @param listener the listener to be added
	 */
	public void addCommentableCellListener(CommentableCellListener listener) {
		listeners.add(listener);
	}

	/**
	 * Removes the given listener from the cell.
	 *
	 * @param listener the listener to be removed
	 */
	public void removeCommentableCellListener(CommentableCellListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Notifies all registered listeners that the cell's comments changed.
	 */
	protected void fireCommentsChanged() {
		for (CommentableCellListener listener : listeners) {
			listener.commentChanged(this);
		}
	}

	public String getTooltip() {

		String tooltip = null;;
		if (this.hasComments()) {

			tooltip = "<html>";
			for (Comment cmt : commentsList) {
				tooltip += "<b>";
				tooltip += cmt.userName() + ":</b> ";
				tooltip += cmt.text();
				tooltip += "<br/>";
			}
			tooltip += "</html>";
		}

		return tooltip;
	}

	/**
	 * Customizes serialization, by recreating the listener list.
	 *
	 * @param stream the object input stream from which the object is to be read
	 * @throws IOException If any of the usual Input/Output related exceptions
	 * occur
	 * @throws ClassNotFoundException If the class of a serialized object cannot
	 * be found.
	 */
	private void readObject(java.io.ObjectInputStream stream)
		throws java.io.IOException, ClassNotFoundException {
		stream.defaultReadObject();
		listeners = new ArrayList<CommentableCellListener>();
	}
}
