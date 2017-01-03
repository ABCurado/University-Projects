/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation.mealbooking;

import eapli.ecafeteria.application.ConsultWeeklyController;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Martins
 */
public class ShowReservationsForWeekUI extends AbstractUI {

	private final ConsultWeeklyController controller = new ConsultWeeklyController();

	@Override
	public boolean doShow() {
		try {
			System.out.println("Weekly Reserves:");

			System.out.println("\nMonday:");
			for (Reserve reserve : (List<Reserve>) controller.
				reservesCurrentWeekDay(Calendar.MONDAY)) {
				System.out.println(reserve);
			}

			System.out.println("\nTuesday:");
			for (Reserve reserve : (List<Reserve>) controller.
				reservesCurrentWeekDay(Calendar.TUESDAY)) {
				System.out.println(reserve);
			}

			System.out.println("\nWednesday:");
			for (Reserve reserve : (List<Reserve>) controller.
				reservesCurrentWeekDay(Calendar.WEDNESDAY)) {
				System.out.println(reserve);
			}

			System.out.println("\nThursday:");
			for (Reserve reserve : (List<Reserve>) controller.
				reservesCurrentWeekDay(Calendar.THURSDAY)) {
				System.out.println(reserve);
			}

			System.out.println("\nFriday:");
			for (Reserve reserve : (List<Reserve>) controller.
				reservesCurrentWeekDay(Calendar.FRIDAY)) {
				System.out.println(reserve);
			}

		} catch (Exception ex) {
			Logger.getLogger(ShowReservationsForWeekUI.class.getName()).
				log(Level.SEVERE, null, ex);
		}
		return true;
	}

	@Override
	public String headline() {
		return "Consult Weekly Menu";
	}
}
