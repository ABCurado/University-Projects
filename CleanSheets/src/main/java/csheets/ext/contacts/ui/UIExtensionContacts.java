package csheets.ext.contacts.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.JComponent;

/**
 *
 * @author Rui Freitas
 */
public class UIExtensionContacts extends UIExtension {

	/**
	 * A side bar that provides contact edition
	 */
	private JComponent sideBar;

	public UIExtensionContacts(Extension extension, UIController uiController) {
		super(extension, uiController);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns a side bar that gives access to extension-specific functionality.
	 *
	 * @return a component, or null if the extension does not provide one
	 */
	@Override
	public JComponent getSideBar() {
		if (sideBar == null) {
			sideBar = new ContactPanel(uiController);
		}
		return sideBar;
	}

}
