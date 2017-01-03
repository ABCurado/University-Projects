/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation.mealbooking;

import eapli.ecafeteria.domain.meals.Reserve;
import eapli.framework.visitor.Visitor;
import java.text.SimpleDateFormat;

/**
 *
 * @author Diogo Leite<1140260@isep.ipp.pt>
 */
public class CancelReservePrinter implements Visitor<Reserve> {

	@Override
	public void visit(Reserve visitee) {
		System.out.
			println("Reserve Date: " + new SimpleDateFormat("dd-MM-yyyy").
				format(visitee.meal().date().getTime())
				+ "\nReserve dish type: " + visitee.meal().dish().dishType());

	}

	@Override
	public void beforeVisiting(Reserve visitee) {
		// nothing to do
	}

	@Override
	public void afterVisiting(Reserve visitee) {
		// nothing to do
	}

}
