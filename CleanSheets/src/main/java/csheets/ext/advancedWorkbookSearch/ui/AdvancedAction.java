/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.advancedWorkbookSearch.ui;

import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;

/**
 *
 * @author Eduardo
 */
class AdvancedAction extends BaseAction {

	private UIController ui;

	public AdvancedAction(UIController uiController) {
		this.ui = uiController;
	}

	@Override
	protected String getName() {
		return "Advanced";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new JanelaFrame();
	}

}
