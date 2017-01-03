package csheets.ext.wizard.ui;

import csheets.CleanSheets;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

/**
 * An action of the simple extension that exemplifies how to interact with the
 * spreadsheet.
 *
 * @author AB-1140280
 */
public class WizardAction extends BaseAction {

	/**
	 * The user interface controller
	 */
	protected UIController uiController;

	/**
	 * Creates a new action.
	 *
	 * @param uiController the user interface controller
	 */
	public WizardAction(UIController uiController) {
		this.uiController = uiController;
	}

        @Override
	protected String getName() {
		return "Wizard to help the use of functions.";
	}

        @Override
	protected void defineProperties() {
		putValue(SMALL_ICON, new ImageIcon(CleanSheets.class.
				 getResource("ext/wizard/wizard-icon.png")));
	}

	/**
	 * A simple action that presents a wizard frame. Which allows the user to see all available functions
         * and try them
	 *
	 * @param event the event that was fired
	 */
        @Override
	public void actionPerformed(ActionEvent event) {
		new WizardFrame(uiController).setVisible(true);
	}
}
