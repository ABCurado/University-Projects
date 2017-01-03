/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.tableFilters.ui;

import csheets.ext.Extension;
import csheets.ext.tableFilters.TableFiltersExtension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 */
public class UIExtensionTableFilters extends UIExtension {

	/**
	 * Icon to be displayed.
	 */
	private Icon icon;

	/**
	 * A side bar that provides search functionality
	 */
	private JComponent sideBar;

	/**
	 * Creates UISearchExtension.
	 *
	 * @param extension Extension
	 * @param uiController UIController
	 */
	public UIExtensionTableFilters(Extension extension,
								   UIController uiController) {
		super(extension, uiController);
	}

	@Override
	public Icon getIcon() {
		if (icon == null) {
			icon = new ImageIcon(
				TableFiltersExtension.class.
				getResource("res/img/tables_icon.png"));
		}
		return icon;
	}

	/**
	 * Returns a side bar that gives access to tables functionality.
	 *
	 * @return a component, or null if the extension does not provide one
	 */
	@Override
	public JComponent getSideBar() {
		if (sideBar == null) {
			sideBar = new TableFiltersPanelUI(uiController);
		}
		return sideBar;
	}

}
