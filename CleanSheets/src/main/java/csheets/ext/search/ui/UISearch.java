/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.search.ui;

import csheets.ext.Extension;
import csheets.ext.search.SearchExtension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;

/**
 *
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 */
public class UISearch extends UIExtension {

	/**
	 * Icon to be displayed.
	 */
	private Icon icon;

	/**
	 * A side bar that provides search functionality
	 */
	private JComponent sideBar;

	/**
	 * A search and replace menu
	 */
	private SearchReplaceMenu menu;

	/**
	 * Creates UISearchExtension.
	 *
	 * @param extension Extension
	 * @param uiController UIController
	 */
	public UISearch(Extension extension, UIController uiController) {
		super(extension, uiController);
	}

	@Override
	public Icon getIcon() {
		if (icon == null) {
			icon = new ImageIcon(
				SearchExtension.class.getResource("res/img/search_icon.png"));
		}
		return icon;
	}

	/**
	 * Returns a side bar that gives access to search functionality.
	 *
	 * @return a component, or null if the extension does not provide one
	 */
	@Override
	public JComponent getSideBar() {
		if (sideBar == null) {
			sideBar = new SearchPanel(uiController);
		}
		return sideBar;
	}

	/**
	 * Returns an instance of a class that implements JMenu. In this simple case
	 * this class only supplies one menu option.
	 *
	 * @see SearchReplaceMenu
	 * @return a JMenu component
	 */
	@Override
	public JMenu getMenu() {
		if (menu == null) {
			menu = new SearchReplaceMenu(uiController);
		}
		return menu;
	}
}
