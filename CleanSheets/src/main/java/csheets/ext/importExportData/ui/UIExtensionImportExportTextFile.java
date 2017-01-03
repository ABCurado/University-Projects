/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.importExportData.ui;

import csheets.ext.Extension;
import csheets.ext.importExportData.ImportExportDataExtension;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;

/**
 * This class implements the UI interface extension for the comments extension.
 * A UI interface extension must extend the UIExtension abstract class.
 *
 * @see UIExtension
 * @author Rui Bastos
 */
public class UIExtensionImportExportTextFile extends UIExtension {

	/**
	 * The icon to display with the extension's name
	 */
	private Icon icon;

	/**
	 * The menu of the extension
	 */
	private ImportExportDataMenu menu;

	/**
	 * Controller.
	 */
	private ImportExportTextFileController shareController;

	/**
	 * The menu of the extension
	 *
	 * @param extension extension
	 * @param uiController ui controller
	 */
	public UIExtensionImportExportTextFile(Extension extension,
										   UIController uiController) {
		super(extension, uiController);
	}

	/**
	 * Returns an icon to display with the extension's name.
	 *
	 * @return an icon with style
	 */
	public Icon getIcon() {
		if (icon == null) {
			icon = new ImageIcon(ImportExportDataExtension.class.
				getResource("res/img/import_export.png"));
		}
		return icon;
	}

	/**
	 * Returns an instance of a class that implements JMenu.
	 *
	 * @see ImportExportDataMenu
	 * @return a JMenu component
	 */
	public JMenu getMenu() {
		if (menu == null) {
			if (shareController == null) {
				shareController = new ImportExportTextFileController();
			}
			menu = new ImportExportDataMenu(uiController, shareController);
		}
		return menu;
	}

}
