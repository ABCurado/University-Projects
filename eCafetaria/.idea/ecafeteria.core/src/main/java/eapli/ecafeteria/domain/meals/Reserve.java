/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.authz.SystemUser;

/**
 *
 * @author Hicham
 */
public class Reserve {

    private SystemUser user;
    private Meal meal;

	public Reserve(SystemUser user, Meal meal) {

		if (user == null || meal == null) {
			throw new IllegalArgumentException();
		}

		this.user = user;
		this.meal = meal;
	}

	public SystemUser user() {
		return user;
	}

	public Meal meal() {
		return meal;
	}

    
}
