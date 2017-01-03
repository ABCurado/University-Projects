package csheets.ext.notes_and_lists;

import csheets.ext.notes_and_lists.ui.UIExtensionNotesAndLists;
import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * An extension to support note for contacts.
 *
 * @see Extension
 * @author Diogo Azevedo
 * @author Rui Bento
 */
public class NotesListsExtension extends Extension {

    /**
     * The name of the extension
     */
    public static final String NAME = "Notes and Lists";

    /**
     * Creates a new Example extension.
     */
    public NotesListsExtension() {
        super(NAME);
    }

    /**
     * Returns the user interface extension of this extension
     *
     * @param uiController the user interface controller
     * @return a user interface extension, or null if none is provided
     */
    @Override
    public UIExtension getUIExtension(UIController uiController) {
        return new UIExtensionNotesAndLists(this, uiController);
    }
}
