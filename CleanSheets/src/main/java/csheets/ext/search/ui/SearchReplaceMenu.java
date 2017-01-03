package csheets.ext.search.ui;

import csheets.ext.search.SearchExtension;
import csheets.ui.ctrl.UIController;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;

/**
 *
 * @author José Barros
 */
public class SearchReplaceMenu extends JMenu {

	/**
	 * Creates a new workbook menu. This constructor creates and adds the menu
	 * Beanshell.
	 *
	 * @param uiController The user interface controller
	 */
	public SearchReplaceMenu(UIController uiController) {
		super("Search");
		setMnemonic(KeyEvent.VK_S);
		this.setIcon(new ImageIcon(SearchExtension.class.
			getResource("res/img/search_icon.png")));

		// Adds font actions
		add(new SearchReplaceAction(uiController));
	}

}
