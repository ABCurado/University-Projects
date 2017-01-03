/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation.mealbooking;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.visitor.Visitor;
import eapli.util.DateTime;

/**
 *
 * @author Martins
 */
public class MealPrinter implements Visitor<Meal> {

    @Override
    public void visit(Meal visitee) {
        System.out.
                printf("%11s%-12s%-20s-%6s\n", DateTime.format(visitee.date()), visitee.
                        dish().dishType(), visitee.
                        dish().
                        name(), visitee.
                        mealType().designation());
    }

    public void beforeVisiting(Meal visitee) {
        // nothing to do
    }

    @Override
    public void afterVisiting(Meal visitee) {
        // nothing to do
    }
}
