package csheets.ext.conditionalFormatting.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.Icon;
import javax.swing.JComponent;

/**
 *
 * @author Diogo Leite
 */
public class UIExtensionCondicionalFormatting extends UIExtension {

	/**
	 * A side bar that provides contact edition
	 */
	private JComponent sideBar;

	/**
	 * The icon to display with the extension's name
	 */
	private Icon icon;
	private ConditionalFormattingController conditionalFormatting;

	public UIExtensionCondicionalFormatting(Extension extension,
											UIController uiController) {
		super(extension, uiController);
	}

	/**
	 * Returns a side bar that gives access to extension-specific functionality.
	 *
	 * @return a component, or null if the extension does not provide one
	 */
	public JComponent getSideBar() {
		if (sideBar == null) {
			sideBar = new ConditionalFormattingUI(uiController);
		}
		return sideBar;
	}

}
