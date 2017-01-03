/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation.authz;

import eapli.ecafeteria.application.ViewBalanceController;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.framework.actions.ShowMessageAction;
import eapli.framework.application.Controller;
import eapli.framework.domain.Money;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author JoséBarros(1140364)
 */
public class ViewBalanceUI extends AbstractUI {

	private final ViewBalanceController controller = new ViewBalanceController();

	protected Controller controller() {
		return this.controller;
	}

	@Override
	protected boolean doShow() {

		Iterable<CafeteriaUser> user = controller.getCafeteriaUserAccount();

		if (!user.iterator().hasNext()) {
			new ShowMessageAction("No user found or user is not a cafeteria user!").
				execute();
			return false;
		}

		CafeteriaUser theUser = user.iterator().next();
		Money money = theUser.accountBalance();
		System.out.
			println("Balance: " + money.amount() + " " + money.currency() + "\n");

		return false;
	}

	@Override
	public String headline() {
		return "Balance";
	}

}
