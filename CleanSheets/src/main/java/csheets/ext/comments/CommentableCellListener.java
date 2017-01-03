package csheets.ext.comments;

import java.util.EventListener;


/**
 * A listener interface for receiving notification on events occurring in an
 * commentable cell.
 * @author Alexandre Braganca
 * @author Einar Pehrson
 */
public interface CommentableCellListener extends EventListener {

	/**
	 * Invoked when a comment is added to or removed from a cell.
	 * @param cell the cell that was modified
	 */
	public void commentChanged(CommentableCell cell);
}
