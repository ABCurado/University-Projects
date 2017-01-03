package csheets.ext.agenda.ui;

import csheets.CleanSheets;
import csheets.ui.ctrl.UIController;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;

/**
 * Representes the UI extension menu of the simple extension.
 *
 * @author Alexandre Braganca
 */
public class AgendaMenu extends JMenu {

	/**
	 * Creates a new simple menu. This constructor creates and adds the menu
	 * options. In this simple example only one menu option is created. A menu
	 * option is an action (in this case
	 *
	 * @param uiController the user interface controller
	 */
	public AgendaMenu(UIController uiController) {
		super("Agenda");
		setMnemonic(KeyEvent.VK_E);
		this.setIcon(new ImageIcon(CleanSheets.class.
			getResource("ext/agenda/calendar_select_days.png")));
		// Adds font actions
		add(new AgendaAction(uiController));
	}
}
