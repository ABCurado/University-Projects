package csheets.ext.macro_beanshell.ui;

import csheets.CleanSheets;
import csheets.ext.Extension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;

/**
 *
 * @author Rui Bento
 * @author José Barros
 */
public class UIExtensionMacroBeanShell extends UIExtension {

	/**
	 * A side bar that provides contact edition
	 */
	private JComponent sideBar;
	private BeanShellMenu menu;

	public UIExtensionMacroBeanShell(Extension extension,
									 UIController uiController) {
		super(extension, uiController);
	}

	/**
	 * Returns an icon to display with the extension's name.
	 *
	 * @return an icon with style
	 */
	@Override
	public Icon getIcon() {
		return new ImageIcon(CleanSheets.class.
			getResource("ext/macro_beanshell/script_small.png"));
	}

	/**
	 * Returns a side bar that gives access to extension-specific functionality.
	 *
	 * @return a component, or null if the extension does not provide one
	 */
	@Override
	public JComponent getSideBar() {
		if (sideBar == null) {
			sideBar = new MacroBeanShellPanel(uiController);
		}
		return sideBar;
	}

	/**
	 * Returns an instance of a class that implements JMenu. In this simple case
	 * this class only supplies one menu option.
	 *
	 * @see BeanShellMenu
	 * @return a JMenu component
	 */
	@Override
	public JMenu getMenu() {
		if (menu == null) {
			menu = new BeanShellMenu(uiController);
		}
		return menu;
	}
}
