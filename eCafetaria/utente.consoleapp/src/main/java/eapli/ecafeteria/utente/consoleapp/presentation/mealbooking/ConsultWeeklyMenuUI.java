/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation.mealbooking;

import eapli.ecafeteria.application.ConsultWeeklyController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno
 */
public class ConsultWeeklyMenuUI extends AbstractUI {

	private final ConsultWeeklyController controller = new ConsultWeeklyController();

	@Override
	public boolean doShow() {
		try {
			System.out.println("Weekly Menu:");

			System.out.println("\nMonday:");
			for (Meal meal : (List<Meal>) controller.
				mealsCurrentWeekDay(Calendar.MONDAY)) {
				System.out.println(meal);
			}

			System.out.println("\nTuesday:");
			for (Meal meal : (List<Meal>) controller.
				mealsCurrentWeekDay(Calendar.TUESDAY)) {
				System.out.println(meal);
			}

			System.out.println("\nWednesday:");
			for (Meal meal : (List<Meal>) controller.
				mealsCurrentWeekDay(Calendar.WEDNESDAY)) {
				System.out.println(meal);
			}

			System.out.println("\nThursday:");
			for (Meal meal : (List<Meal>) controller.
				mealsCurrentWeekDay(Calendar.THURSDAY)) {
				System.out.println(meal);
			}

			System.out.println("\nFriday:");
			for (Meal meal : (List<Meal>) controller.
				mealsCurrentWeekDay(Calendar.FRIDAY)) {
				System.out.println(meal);
			}

		} catch (Exception ex) {
			Logger.getLogger(ConsultWeeklyMenuUI.class.getName()).
				log(Level.SEVERE, null, ex);
		}
		return true;
	}

	@Override
	public String headline() {
		return "Consult Weekly Menu";
	}
}
