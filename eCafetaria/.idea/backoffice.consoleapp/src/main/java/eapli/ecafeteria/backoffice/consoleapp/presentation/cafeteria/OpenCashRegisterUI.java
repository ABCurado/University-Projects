/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria;

import eapli.ecafeteria.application.OpenCashRegisterController;
import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class OpenCashRegisterUI extends AbstractUI {

    private OpenCashRegisterController controller;

    @Override
    protected boolean doShow() {
        final String number = Console.readLine("Insert cash register number");
        controller = new OpenCashRegisterController(number);
        Iterable<MealType> mealTypes = controller.mealTypes();
        System.out.println("Types of meal: ");
        int i = 1;
        Iterator<MealType> iterator = mealTypes.iterator();

        List<MealType> mealT = new ArrayList<>();

        iterator.forEachRemaining(mealT::add);

        for (MealType mt : mealT) {
            String designation = mt.designation();
            System.out.println(i + " - " + designation);
            i++;
        }

        final int op = Console.readInteger("0 - Default");
        if (op == 0) {
            int now = Calendar.HOUR_OF_DAY;
            int minutes = Calendar.MINUTE;
            if (now < 15 || (now == 15 && minutes == 0)) {//Lunch
                Shift shift = null;
                for (MealType mt : mealT) {
                    if (mt.designation().equals("Lunch")) {
                        shift = this.controller.chooseMeal(Calendar.getInstance(), mt);
                        break;
                    }
                }
                if (shift == null) {
                    System.out.println("No lunch for today");
                    return false;
                }

            } else {//Dinner
                Shift shift = null;
                for (MealType mt : mealT) {
                    if (mt.designation().equals("Dinner")) {
                        shift = this.controller.chooseMeal(Calendar.getInstance(), mt);
                        break;
                    }
                }
                if (shift == null) {
                    System.out.println("No Dinner for today");
                    return false;
                }
            }
        } else {
            MealType meal = mealT.get(op - 1);
            Calendar date = Console.
                    readCalendar("Date (dd-mm-yyyy):");
            Date hours = Console.readDate("Hour (hh:mm):");

            date.setTime(hours);

            Shift shift = this.controller.chooseMeal(date, meal);

            if (shift == null) {
                System.out.println("There's no meal in the date selected");
                return false;
            }

            this.controller.open(number, shift);

            System.out.println("Cash Register opened sucessfully");

        }
        return true;
    }

    @Override
    public String headline() {
        return "Open CashRegister";
    }

}
