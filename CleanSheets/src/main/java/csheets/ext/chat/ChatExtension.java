package csheets.ext.chat;

import csheets.ext.Extension;
import csheets.ext.chat.ui.UIExtensionChat;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * An extension to support cell sharing. An extension must extend the Extension
 * abstract class. The class that implements the Extension is the "bootstrap" of
 * the extension.
 *
 * @see Extension
 * @author Marcelo Barroso 1131399
 */
public class ChatExtension extends Extension {

	/**
	 * The name of extension
	 */
	public static final String NAME = "Chat";

	/**
	 * Creates a new Share extension.
	 */
	public ChatExtension() {
		super(NAME);
		this.version = "1 .0";
		this.description = "Chat extension with the creation of rooms and invite users.";
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
