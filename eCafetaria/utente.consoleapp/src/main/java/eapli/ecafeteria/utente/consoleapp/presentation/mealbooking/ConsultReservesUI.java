/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation.mealbooking;

import eapli.ecafeteria.application.ConsultReservesController;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.Console;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Carlos Mateus
 */
public class ConsultReservesUI extends AbstractUI {

	private final ConsultReservesController controller = new ConsultReservesController();

	@Override
	protected boolean doShow() {

		try {

			Calendar dateInitial = Calendar.getInstance();
			Calendar dateFinal = Console.
				readCalendar(" Please insert final date (dd-MM-yyyy):", "dd-MM-yyyy");
			final Iterable<Reserve> reserves = controller.
				getReservesBetweenDates(dateInitial, dateFinal);
                        
                        
                        System.out.println("\nReserves: ");
                        for (final Reserve r : reserves) {
                        System.out.println("Date: " + new SimpleDateFormat("dd-MM-yyyy").
				format(r.meal().date().getTime())
				+ " Dish type: " + r.meal().dish().dishType() + "\n");
                        }

		} catch (NullPointerException ex) {
			System.out.println("There is no reserves" + "\n");
		} catch (IllegalArgumentException ex) {
			System.out.println("Invalid reserve data" + "\n");
                }
                
		return false;
	}

	@Override
	public String headline() {
		return ("Consult reserves in next n days");
	}

}
