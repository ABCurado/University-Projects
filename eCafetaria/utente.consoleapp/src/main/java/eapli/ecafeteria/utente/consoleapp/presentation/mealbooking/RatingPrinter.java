/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation.mealbooking;

import eapli.ecafeteria.domain.meals.RatingType;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author JoséBarros(1140364)
 */
public class RatingPrinter implements Visitor<RatingType> {

    @Override
    public void visit(RatingType visitee) {
        System.out.printf("%2s\n", visitee.toString());
    }

    @Override
    public void beforeVisiting(RatingType visitee) {
        //nothing to do
    }

    @Override
    public void afterVisiting(RatingType visitee) {
        //nothing to do
    }

}
