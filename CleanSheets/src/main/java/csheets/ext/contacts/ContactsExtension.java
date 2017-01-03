package csheets.ext.contacts;

import csheets.ext.Extension;
import csheets.ext.contacts.ui.UIExtensionContacts;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 *
 * @author Rui Freitas
 */
public class ContactsExtension extends Extension {

	/**
	 * The name of the extension
	 */
	public static final String NAME = "Contacts";

	/**
	 * Creates a new Example extension.
	 */
	public ContactsExtension() {
		super(NAME);
	}

	/**
	 * Returns the user interface extension of this extension (an instance of
	 * the class {@link  csheets.ext.simple.ui.UIExtensionExample}). In this
	 * extension example we are only extending the user interface.
	 *
	 * @param uiController the user interface controller
	 * @return a user interface extension, or null if none is provided
	 */
	@Override
	public UIExtension getUIExtension(UIController uiController) {
		return new UIExtensionContacts(this, uiController);
	}

}
