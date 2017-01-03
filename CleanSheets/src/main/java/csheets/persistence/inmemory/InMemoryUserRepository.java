/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.inmemory;

import csheets.ext.chat.domain.User;
import csheets.framework.persistence.repositories.impl.immemory.InMemoryRepository;
import csheets.persistence.UserRepository;

/**
 *
 * @author Marcelo Barroso 1131399
 */
public class InMemoryUserRepository extends InMemoryRepository<User, Long> implements UserRepository {

	long nextID = 1;

	@Override
	protected Long newPK(User entity) {
		return ++nextID;
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
