/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.cafeteria.consoleapp.presentation;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.application.ChangeUserPasswordController;
import eapli.ecafeteria.domain.authz.Password;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;

/**
 *
 * @author nervousdev
 */
public class ChangeUserPasswordUI extends AbstractUI {

	private final ChangeUserPasswordController theController = new ChangeUserPasswordController();

	@Override
	protected boolean doShow() {
		String newPass;
		Password thePass;
		// Really needed?!?! I do this to get a managed object!?!?
		SystemUser theUser = theController.currentUser(AppSettings.
			getCurrentUser().username());

		if (theUser == null) {
			System.out.println("User data not found!");
			return false;
		}
		int op1, op2;

		do {
			op1 = -1;
			newPass = Console.readLine("Insert new password:");
			thePass = new Password(newPass);

			if (theUser.passwordMatches(thePass)) {
				System.out.
					println("The new password must be different from the old one.");
				return false;
			} else {
				do {
					op2 = -1;

					if (!newPass.equals(Console.
						readLine("Reinsert new password:"))) {

						System.out.println("Passwords are different");

						System.out.println("\t1. Continue");
						System.out.println("\t2. Retry");
						op2 = Console.readOption(1, 2, 1);

						if (op2 == 1) {
							return false;
						}
					}
				} while (op2 == 2);
			}

			if (thePass.strength().equals(Password.PasswordStrength.Weak)) {
				System.out.println("Password is " + thePass.strength());

				System.out.println("\t1. Continue");
				System.out.println("\t2. Retry");
				op1 = Console.readOption(1, 2, 1);
			}

		} while (op1 == 2);

		try {
			theUser.changePassword(newPass);
			theController.updateUser(theUser);
		} catch (DataConcurrencyException ex) {
			System.out.println("Data has changed. Please try later.");
			return false;
		}

		System.out.println("Password changed.");

		return false;
	}

	@Override
	public String headline() {
		return "Change user password";
	}

}
