/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence;

import csheets.ext.chat.domain.User;
import csheets.framework.persistence.repositories.Repository;

/**
 *
 * @author Marcelo Barroso 1131399
 */
public interface UserRepository extends Repository<User, Long> {

	public User findName(String name);
	//public Iterable<User> users(Room room);
}
