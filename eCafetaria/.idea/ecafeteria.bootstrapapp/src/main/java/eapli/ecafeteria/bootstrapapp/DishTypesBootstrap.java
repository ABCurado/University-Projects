/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapapp;

import eapli.ecafeteria.application.RegisterDishTypeController;
import eapli.framework.actions.Action;

/**
 *
 * @author mcn
 */
public class DishTypesBootstrap implements Action {

    @Override
    public boolean execute() {
        register("vegie", "vegetarian dish");
        register("fish", "fish dish");
        register("meat", "meat dish");
        return false;
    }

    /**
     *
     */
    private void register(String acronym, String description) {
        final RegisterDishTypeController controller = new RegisterDishTypeController();
        try {
            controller.registerDishType(acronym, description);
        } catch (final Exception e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
        }
    }
}
