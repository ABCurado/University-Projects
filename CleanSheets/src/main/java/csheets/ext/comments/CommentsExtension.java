package csheets.ext.comments;

import csheets.core.Cell;
import csheets.ext.Extension;
import csheets.ext.comments.ui.UIExtensionComments;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * An extension to support comments on cells.
 * An extension must extend the Extension abstract class.
 * The class that implements the Extension is the "bootstrap" of the extension.
 * @see Extension
 * @author Alexandre Braganca
 * @author Einar Pehrson
 */
public class CommentsExtension extends Extension {

	/** The name of the extension */
	public static final String NAME = "Comments";

	/**
	 * Creates a new Example extension.
	 */
	public CommentsExtension() {
		super(NAME);
	}
	
	/**
	 * Makes the given cell commentable.
	 * @param cell the cell to comment
	 * @return a commentable cell
	 */
        @Override
	public CommentableCell extend(Cell cell) {
		return new CommentableCell(cell);
	}
	
	/**
	 * Returns the user interface extension of this extension 
	 * @param uiController the user interface controller
	 * @return a user interface extension, or null if none is provided
	 */
	public UIExtension getUIExtension(UIController uiController) {
		return new UIExtensionComments(this, uiController);
	}
}

