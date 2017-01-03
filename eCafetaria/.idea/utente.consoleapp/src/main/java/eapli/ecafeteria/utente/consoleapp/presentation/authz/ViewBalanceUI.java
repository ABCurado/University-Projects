/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation.authz;

import eapli.ecafeteria.application.ViewBalanceController;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.framework.application.Controller;
import eapli.framework.domain.Money;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author JoséBarros(1140364)
 */
public class ViewBalanceUI extends AbstractUI {
    
    private final ViewBalanceController controller = new ViewBalanceController();
    
      protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
      
       CafeteriaUser user = controller.getCafeteriaUserAccount();
       Money money = user.account().getBalance();
       System.out.println(money.amount());
       
       return true;
    }

    @Override
    public String headline() {
        return "Balance";
    }
    
}
