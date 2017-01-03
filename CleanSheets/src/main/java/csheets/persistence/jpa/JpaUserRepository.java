/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.jpa;

import csheets.ext.chat.domain.User;
import csheets.framework.persistence.repositories.impl.jpa.JpaRepository;
import csheets.persistence.UserRepository;

/**
 *
 * @author Marcelo Barroso 1131399
 */
public class JpaUserRepository extends JpaRepository<User, Long> implements UserRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

	@Override
	public User findName(String name) {
		for (User user : this.all()) {
			if (user.name().equals(name)) {
				return user;
			}
		}
		return null;
	}

}
