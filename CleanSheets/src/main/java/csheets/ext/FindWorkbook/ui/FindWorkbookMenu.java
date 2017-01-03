/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.FindWorkbook.ui;

import csheets.ui.ctrl.UIController;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;

/**
 *
 * @author Carlos Mateus
 */
public class FindWorkbookMenu extends JMenu {

	/**
	 * Creates a new workbook menu. This constructor creates and adds the menu
	 * options. A menu option is an action (in this case
	 *
	 *
	 * @param uiController the user interface controller
	 * @param findWorkbookController find workbook controller.
	 */
	public FindWorkbookMenu(UIController uiController,
							FindWorkbookController findWorkbookController) {
		super("Find Workbook");
		setMnemonic(KeyEvent.VK_W);

		// Adds font actions
		add(new FindWorkbookAction(uiController, findWorkbookController));
	}

}
