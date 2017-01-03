/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapapp;

import eapli.ecafeteria.domain.cafeteria.CashRegister.CashRegister;
import eapli.ecafeteria.persistence.CashRegisterRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;

public class CashRegisterBootstrap implements Action {

    @Override
    public boolean execute() {
        register("1");
        register("2");

        return false;
    }

    private void register(String number) {
        CashRegisterRepository repo = PersistenceContext.repositories().
                cashRegisters();
        CashRegister cashRegister = new CashRegister(number);
        try {
            repo.save(cashRegister);
        } catch (final Exception e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
        }

    }
}
