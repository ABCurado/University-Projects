/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapapp;

import eapli.ecafeteria.application.KitchenAlertController;
import eapli.framework.actions.Action;

/**
 *
 * @author João Martins
 */
public class KitchenAlertBootstrap implements Action {

	final KitchenAlertController controller = new KitchenAlertController();

	@Override
	public boolean execute() {
		register("AMARELO", 0.75);
		register("VERMELHO", 0.90);
		return true;
	}

	private void register(String description, Double ratio) {
		try {
			controller.addAlert(description, ratio);
		} catch (Exception ex) {
			// ignoring exception. assuming it is just a primary key violation
			// due to the tentative of inserting a duplicated user
		}
	}

}
