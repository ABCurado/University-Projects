package csheets.ext.wizard.ui;

import csheets.CleanSheets;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Representes the UI extension menu of the simple extension.
 *
 * @author Alexandre Braganca
 */
public class WizardButton extends JButton {

	/**
	 * Creates a new simple menu. This constructor creates and adds the menu
	 * options. In this simple example only one menu option is created. A menu
	 * option is an action (in this case
	 *
	 * @param uiController the user interface controller
	 */
	public WizardButton(UIController uiController) {
		super(new ImageIcon(CleanSheets.class.
                        getResource("ext/wizard/wizard-icon.png")));
		// Adds font actions
                this.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        WizardFrame f = new WizardFrame(uiController);
                        
                    }
                });
	}
}
