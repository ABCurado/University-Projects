package csheets.ext.distributedWorkbookSearch.ui;

import csheets.ext.Extension;
import csheets.ext.distributedWorkbookSearch.NetworkWorkbookSearchExtension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;

/**
 * This class implements the UI interface extension for the comments extension.
 * A UI interface extension must extend the UIExtension abstract class.
 *
 * @see UIExtension
 * @author José Barros
 */
public class UIExtensionNetworkWorkbookSearch extends UIExtension {

	/**
	 * The icon to display with the extension's name
	 */
	private Icon icon;

	/**
	 * A side bar that provides editing of comments
	 */
	private JComponent sideBar;

	/**
	 * The menu of the extension
	 */
	private JComponent menu;

	/**
	 * Controller.
	 */
	private NetworkWorkbookSearchController networkSearchController;

	/**
	 * The menu of the extension
	 *
	 * @param extension extension
	 * @param uiController ui controller
	 */
	public UIExtensionNetworkWorkbookSearch(Extension extension,
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
		if (icon == null) {
			icon = new ImageIcon(
				NetworkWorkbookSearchExtension.class.
				getResource("res/img/find.png"));
		}
		return icon;
	}

	/**
	 * Returns an instance of a class that implements JMenu.
	 *
	 * @return a JMenu component
	 */
	@Override
	public JMenu getMenu() {
		return null;
	}

	/**
	 * Returns a side bar that gives access to extension-specific functionality.
	 *
	 * @return a component, or null if the extension does not provide one
	 */
	@Override
	public JComponent getSideBar() {
		if (sideBar == null) {
			if (networkSearchController == null) {
				networkSearchController = new NetworkWorkbookSearchController();
			}
			sideBar = new NetworkWorkbookSearchPanel(uiController, networkSearchController);
		}
		return sideBar;
	}

}
