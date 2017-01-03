package csheets.ext.chatApp;

import csheets.ext.Extension;
import csheets.ext.chatApp.ui.UIExtensionChat;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * An extension to support cell sharing. An extension must extend the Extension
 * abstract class. The class that implements the Extension is the "bootstrap" of
 * the extension.
 *
 * @see Extension
 * @author José Barros
 */
public class ChatAppExtension extends Extension {

	/**
	 * The name of extension
	 */
	public static final String NAME = "Chat Application";

	/**
	 * Creates a new Share extension.
	 */
	public ChatAppExtension() {
		super(NAME);
	}

	/**
	 * Returns the user interface extension of this extension
	 *
	 * @param uiController the user interface controller
	 * @return a user interface extension, or null if none is provided
	 */
	public UIExtension getUIExtension(UIController uiController) {
		return new UIExtensionChat(this, uiController);
	}
}
