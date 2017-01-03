/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.FindWorkbook.ui;

import csheets.ext.Extension;
import csheets.ext.cellsSharing.ui.ShareMenu;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JMenu;

/**
 *
 * @author Carlos Mateus
 */
public class UIExtensionFindWorkbook extends UIExtension {

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
	private FindWorkbookMenu menu;

	/**
	 * Controller.
	 */
	private FindWorkbookController findWorkbookController;

	/**
	 * The menu of the extension
	 *
	 * @param extension extension
	 * @param uiController ui controller
	 */
	public UIExtensionFindWorkbook(Extension extension,
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
		return null;
	}

	/**
	 * Returns an instance of a class that implements JMenu.
	 *
	 * @see ShareMenu
	 * @return a JMenu component
	 */
	@Override
	public JMenu getMenu() {
		if (menu == null) {
			if (findWorkbookController == null) {
				findWorkbookController = new FindWorkbookController();
			}
			menu = new FindWorkbookMenu(uiController, findWorkbookController);
		}
		return menu;
	}

	/**
	 * Returns a side bar that gives access to extension-specific functionality.
	 *
	 * @return a component, or null if the extension does not provide one
	 */
	@Override
	public JComponent getSideBar() {
		return null;
	}
}
