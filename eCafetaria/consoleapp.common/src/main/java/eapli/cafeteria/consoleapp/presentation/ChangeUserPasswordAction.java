/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.cafeteria.consoleapp.presentation;

import eapli.framework.actions.Action;

/**
 *
 * @author nervousdev
 */
public class ChangeUserPasswordAction implements Action {

	@Override
	public boolean execute() {
		return new ChangeUserPasswordUI().doShow();
	}
}
