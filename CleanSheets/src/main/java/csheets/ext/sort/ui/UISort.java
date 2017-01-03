package csheets.ext.sort.ui;

import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.Icon;
import javax.swing.JMenu;

/**
 * User Interface of Sort Extension
 */
public class UISort extends UIExtension {

	/**
	 * Icon to be displayed.
	 */
	private Icon icon;

	/**
	 * Sort extension menu.
	 */
	private SortMenu menu;

	/**
	 * Creats UISortExtension.
	 *
	 * @param extension extension
	 * @param uiController uiController
	 */
	public UISort(Extension extension, UIController uiController) {
		super(extension, uiController);
	}

	public Icon getIcon() {
		return null;

	}

	public JMenu getMenu() {
		if (menu == null) {
			menu = new SortMenu(uiController);
		}
		return menu;
	}
}
