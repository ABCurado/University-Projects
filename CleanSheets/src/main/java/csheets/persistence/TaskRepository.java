/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence;

import csheets.domain.Contact;
import csheets.domain.Task;
import csheets.framework.persistence.repositories.Repository;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface TaskRepository extends Repository<Task, Long> {

	/**
	 *
	 * @param contact Contact
	 * @return List of Tasks
	 */
	public List<Task> task(Contact contact);
}
