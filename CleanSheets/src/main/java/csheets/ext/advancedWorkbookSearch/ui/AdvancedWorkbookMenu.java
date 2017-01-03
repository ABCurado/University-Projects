/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.advancedWorkbookSearch.ui;

import csheets.ui.ctrl.UIController;
import javax.swing.JMenu;

/**
 *
 * @author Eduardo
 */
public class AdvancedWorkbookMenu extends JMenu {

	public AdvancedWorkbookMenu(UIController uiController) {
		super("Advanced WoorkBook Menu");
		add(new AdvancedAction(uiController));

	}
}
