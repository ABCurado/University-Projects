package csheets.ext.email;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * An extension to support emails. An extension must extend the Extension
 * abstract class. The class that implements the Extension is the "bootstrap" of
 * the extension.
 *
 * @see Extension
 * @author Rui Bastos
 */
public class EmailExtension extends Extension {

	/**
	 * The name of extension
	 */
	public static final String NAME = "E-mail";

	/**
	 * Creates a new extension.
	 */
	public EmailExtension() {
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
		return new UIExtensionEmail(this, uiController);
	}
}
