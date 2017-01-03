/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.workbookGlobalVariable.ui;

import csheets.ext.Extension;
import csheets.ext.workbookGlobalVariable.WorkbookGlobalVariableExtension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 * This is the UI for Workbook Global Variable Extension.
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
public class WorkbookGlobalVariableUI extends UIExtension {

	/**
	 * Side Bar Icon to be displayed.
	 */
	private Icon icon;

	/**
	 * SideBar's Extension.
	 */
	private JComponent sideBar;

	/**
	 * Creates WorkbookGlobalVariable Panel.
	 *
	 * @param extension Extension.
	 * @param uiController UIController.
	 */
	public WorkbookGlobalVariableUI(Extension extension,
									UIController uiController) {
		super(extension, uiController);
	}

	/**
	 *
	 * @return Extensions' Icon.
	 */
	@Override
	public Icon getIcon() {
		if (icon == null) {
			icon = new ImageIcon(
				WorkbookGlobalVariableExtension.class.
				getResource("res/img/icone_2.png"));
		}
		return icon;
	}

	/**
	 * Extension' side bar.
	 *
	 * @return JComponent JComponent.
	 */
	@Override
	public JComponent getSideBar() {
		if (sideBar == null) {
			sideBar = new WorkbookGlobalVariablePanel(uiController);
		}
		return sideBar;
	}
}
