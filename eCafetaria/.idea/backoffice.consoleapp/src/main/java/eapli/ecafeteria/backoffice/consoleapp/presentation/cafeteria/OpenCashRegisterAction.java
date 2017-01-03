/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria;

import eapli.framework.actions.Action;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class OpenCashRegisterAction implements Action{

    @Override
    public boolean execute() {
        return new OpenCashRegisterUI().doShow();
    }
    
}
