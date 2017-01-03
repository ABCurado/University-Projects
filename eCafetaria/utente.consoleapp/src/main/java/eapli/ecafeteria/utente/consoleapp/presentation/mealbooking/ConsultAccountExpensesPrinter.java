/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation.mealbooking;

import eapli.ecafeteria.domain.mealbooking.Transaction;
import eapli.framework.visitor.Visitor;
import eapli.util.DateTime;

/**
 *
 * @author João Martins
 */
class ConsultAccountExpensesPrinter implements Visitor<Transaction> {

        @Override
	public void visit(Transaction visitee) {
		System.out.printf("Date: " + DateTime.format(visitee.transactionDate()) + " Amount: " + visitee.getAmount() + " Type: " + visitee.getType()+ "\n");
	}

	@Override
	public void beforeVisiting(Transaction visitee) {
		// nothing to do
	}

	@Override
	public void afterVisiting(Transaction visitee) {
		// nothing to do
	}

}