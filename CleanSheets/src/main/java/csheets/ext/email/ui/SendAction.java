/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.email.ui;

import csheets.ext.email.Email;
import csheets.ext.email.EmailController;
import csheets.ui.ctrl.BaseAction;
import csheets.ui.ctrl.UIController;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author Rui Bastos
 */
class SendAction extends BaseAction {

	private UIController uiController;
	private EmailController controller;
	private Email email;

	public SendAction(UIController uiController,
					  EmailController controller, Email email) {
		this.uiController = uiController;
		this.controller = controller;
		this.email = email;
	}

	@Override
	protected String getName() {
		return "New Email";
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			EmailDialog emailDialog = new EmailDialog(controller, uiController, email);
		} catch (IOException | MessagingException ex) {
			Logger.getLogger(SendAction.class.getName()).
				log(Level.SEVERE, null, ex);
		}
	}

}
