/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.CreateMenuController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class CreateMenuUI extends AbstractUI {
    
    private final CreateMenuController theController = new CreateMenuController();
    
    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String headline() {
        return "Create new Menu";
    }
}
