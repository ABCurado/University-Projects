package csheets.ext.email.ui;

import csheets.ext.email.Email;
import csheets.ext.email.EmailController;
import csheets.ext.email.EmailExtension;
import csheets.ui.ctrl.UIController;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;

/**
 * Represents the UI extension menu of the mail extension.
 *
 * @author Rui Bastos
 */
public class EmailMenu extends JMenu {

	/**
	 * Creates a new share menu. This constructor creates and adds the menu
	 * options. A menu option is an action (in this case
	 *
	 *
	 * @param uiController the user interface controller
	 * @param controller The controller.
	 * @param email the email
	 */
	public EmailMenu(UIController uiController, EmailController controller,
					 Email email) {
		super("E-Mail");
		setMnemonic(KeyEvent.VK_E);

		// Adds font actions
		add(new ConfigureAction(uiController, controller)).
			setIcon(new ImageIcon(EmailExtension.class.
				getResource("res/img/configure.png")));
		add(new SendAction(uiController, controller, email)).
			setIcon(new ImageIcon(EmailExtension.class.
				getResource("res/img/send.png")));
	}
}
