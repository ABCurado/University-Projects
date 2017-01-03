package csheets.ext.address;

import csheets.ext.Extension;
import csheets.ext.address.ui.AddressPanel;
import csheets.ext.simple.ui.ExampleMenu;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.CellDecorator;
import csheets.ui.ext.TableDecorator;
import csheets.ui.ext.UIExtension;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JToolBar;

/**
 * This class implements the UI interface extension for the comments extension.
 * A UI interface extension must extend the UIExtension abstract class.
 *
 * @see UIExtension
 * @author Alexandre Braganca
 * @author Einar Pehrson
 */
public class UIExtensionAddress extends UIExtension {

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
	 *
	 * @param extension extension
	 * @param uiController ui controller
	 */
	public UIExtensionAddress(Extension extension, UIController uiController) {
		super(extension, uiController);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns an icon to display with the extension's name.
	 *
	 * @return an icon with style
	 */
	@Override
	public Icon getIcon() {
		return null;
	}

	/**
	 * Returns an instance of a class that implements JMenu. In this simple case
	 * this class only supplies one menu option.
	 *
	 * @see ExampleMenu
	 * @return a JMenu component
	 */
	@Override
	public JMenu getMenu() {
		return null;
	}

	/**
	 * Returns a cell decorator that visualizes comments on cells.
	 *
	 * @return decorator for cells with comments
	 */
	@Override
	public CellDecorator getCellDecorator() {
		return null;
	}

	/**
	 * Returns a table decorator that visualizes the data added by the
	 * extension.
	 *
	 * @return a table decorator, or null if the extension does not provide one
	 */
	@Override
	public TableDecorator getTableDecorator() {
		return null;
	}

	/**
	 * Returns a toolbar that gives access to extension-specific functionality.
	 *
	 * @return a JToolBar component, or null if the extension does not provide
	 * one
	 */
	@Override
	public JToolBar getToolBar() {
		return null;
	}

	/**
	 * Returns a side bar that provides editing of comments.
	 *
	 * @return a side bar
	 */
	@Override
	public JComponent getSideBar() {
		if (sideBar == null) {
			sideBar = new AddressPanel(uiController);
		}
		return sideBar;
	}
}
