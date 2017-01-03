/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapapp;

import eapli.ecafeteria.application.AddOrganicUnitController;
import eapli.framework.actions.Action;

/**
 *
 * @author arocha
 */
public class OrganicUnitBootsrap implements Action {

    @Override
    public boolean execute() {
        register("ISEP", "Instituto Superior de Engenharia do PORTO", "Good school :)");
        register("HSJ", "Hospital São João", "An hospital...");

        return false;
    }

    /**
     *
     */
    private void register(String acronym, String name, String description) {
        final AddOrganicUnitController controller = new AddOrganicUnitController();
        try {
            controller.addOrganicUnit(acronym, name, description);
        } catch (final Exception e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated organic unit
        }
    }
}
