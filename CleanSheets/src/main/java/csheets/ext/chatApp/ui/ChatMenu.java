package csheets.ext.chatApp.ui;

import csheets.ext.chatApp.application.ChatAppController;
import csheets.CleanSheets;
import csheets.ui.ctrl.UIController;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;

/**
 * Represents the UI extension menu of the share extension.
 *
 * @author Carlos Santos
 */
public class ChatMenu extends JMenu {

	/**
	 * Creates a new share menu. This constructor creates and adds the menu
	 * options. A menu option is an action (in this case
	 *
	 *
	 * @param uiController the user interface controller
	 * @param chatAppController The chat controller.
	 */
	public ChatMenu(UIController uiController,
					ChatAppController chatAppController) {
		super("Chat");
		setMnemonic(KeyEvent.VK_S);
		this.setIcon(new ImageIcon(CleanSheets.class.
			getResource("ext/chatApp/res/img/chat.gif")));

		// Adds font actions
		add(new ChatAction(uiController, chatAppController));
	}
}
