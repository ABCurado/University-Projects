/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation.mealbooking;

import eapli.framework.actions.Action;

/**
 *
 * @author João Martins
 */
public class ConsultAccountExpensesAction implements Action{

    @Override
    public boolean execute() {
       return new ConsultAccountExpensesUI().show();
    }
 
}
