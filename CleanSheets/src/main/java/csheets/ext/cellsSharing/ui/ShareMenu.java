package csheets.ext.cellsSharing.ui;

import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import csheets.ui.ctrl.UIController;

/**
 * Represents the UI extension menu of the share extension.
 *
 * @author Rui 1110506
 */
public class ShareMenu extends JMenu {

	/**
	 * Creates a new share menu. This constructor creates and adds the menu
	 * options. A menu option is an action (in this case
	 *
	 *
	 * @param uiController the user interface controller
	 * @param shareController The share cells controller.
	 */
	public ShareMenu(UIController uiController, ShareCellsController shareController) {
		super("Share");
		setMnemonic(KeyEvent.VK_S);

		// Adds font actions
		add(new OptionsAction(uiController, shareController));
	}
}
