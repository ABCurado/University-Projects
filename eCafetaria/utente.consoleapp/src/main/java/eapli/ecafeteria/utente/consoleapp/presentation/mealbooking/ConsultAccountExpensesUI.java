/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation.mealbooking;

import eapli.ecafeteria.application.ConsultAccountExpensesController;
import eapli.ecafeteria.domain.mealbooking.Transaction;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author João Martins
 */
public class ConsultAccountExpensesUI extends AbstractListUI<Transaction> {

    private final ConsultAccountExpensesController consultAccountExpensesController = new ConsultAccountExpensesController();

    protected ConsultAccountExpensesController controller() {
        return this.consultAccountExpensesController;
    }

    @Override
    protected Iterable<Transaction> listOfElements() {
        return this.consultAccountExpensesController.allDebtTransactions();
    }

    @Override
    protected Visitor<Transaction> elementPrinter() {
        return new ConsultAccountExpensesPrinter();
    }

    @Override
    protected String elementName() {
        return "Consult Account Expenses";
    }
}
    
