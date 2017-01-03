/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation.mealbooking;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.application.CancelReserveController;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

/**
 *
 * @author Diogo Leite<1140260@isep.ipp.pt>
 */
public class CancelReserveUI extends AbstractUI {

	private final CancelReserveController controller = new CancelReserveController();

	@Override
	protected boolean doShow() {
		try {
			SystemUser user = AppSettings.getCurrentUser();

			Iterable<Reserve> reserves = controller.showUserReservations(user);
			final SelectWidget<Reserve> selector2 = new SelectWidget<>(reserves, new CancelReservePrinter());
			selector2.show();

			final Reserve reserve = selector2.
				selectedElement();

			if (controller.cancelReserve(reserve, reserve.user())) {
				if (reserve.penalty()) {
					System.out.printf("Your penalty is  :", reserve.meal().
									  dish().dishValue().amount() / 2, " €");
				} else {
					System.out.println("You have no penalty");
				}
			} else {
				System.out.
					println("You can not cancel because you do not have enough balance");
			}

		} catch (Exception ex) {
			System.out.println("There are no reserves" + "\n");
		}

		return false;

	}

	@Override
	public String headline() {
		return "Cancel Reserve";
	}

}
