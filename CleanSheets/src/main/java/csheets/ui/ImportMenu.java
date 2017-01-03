/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ui;

import csheets.CleanSheets;
import csheets.ui.ctrl.UIController;
import csheets.ui.legacy.importXML.ImportXMLAction;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;

/**
 *
 * @author scarl
 */
public class ImportMenu extends JMenu {

	/**
	 * The CleanSheets application
	 */
	private CleanSheets app;

	/**
	 * The maximum number of items in the menu
	 */
	private int maximumItems = 1;

	/**
	 * The user interface controller
	 */
	private UIController uiController;

	public ImportMenu(CleanSheets app, UIController uiController) {
		super("Import");
		this.app = app;
		this.uiController = uiController;
		setMnemonic(KeyEvent.VK_F);
		add(new ImportXMLAction(this.uiController));
	}
}
