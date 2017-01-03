/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.email.ui;

import csheets.ext.email.EmailController;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;

/**
 *
 * @author Rui Bastos
 */
class ConfigureAction extends BaseAction {

	private UIController uiController;
	private EmailController controller;

	public ConfigureAction(UIController uiController,
						   EmailController controller) {
		this.uiController = uiController;
		this.controller = controller;
	}

	@Override
	protected String getName() {
		return "Configure";
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		new Login(controller, uiController).setVisible(true);
	}

}
