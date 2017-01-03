package csheets.ext.macro_beanshell.ui;

import csheets.CleanSheets;
import csheets.ui.ctrl.UIController;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;

/**
 * Represents the UI extension menu of the share extension.
 *
 * @author José Barros
 */
class BeanShellMenu extends JMenu {

	/**
	 * Creates a new workbook menu. This constructor creates and adds the menu
	 * Beanshell.
	 *
	 *
	 * @param uiController The user interface controller
	 * @param distributedController The distributed workbook search controller.
	 */
	public BeanShellMenu(UIController uiController) {
		super("Macro/BeanShell");
		setMnemonic(KeyEvent.VK_W);
		this.setIcon(new ImageIcon(CleanSheets.class.
			getResource("ext/macro_beanshell/script_small.png")));

		// Adds font actions
		//add(new MacroBeanShellAction(uiController));
		add(new ScriptManagerAction(uiController));
	}

}
