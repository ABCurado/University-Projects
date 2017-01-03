/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.inmemory;

import csheets.domain.Reminder;
import csheets.framework.persistence.repositories.impl.immemory.InMemoryRepository;
import csheets.persistence.ReminderRepository;

/**
 *
 * @author Martins
 */
class InMemoryReminderRepository extends InMemoryRepository<Reminder, Long>
	implements ReminderRepository {

	long nextID = 1;

	@Override
	protected Long newPK(Reminder entity) {
		return ++nextID;
	}
}
