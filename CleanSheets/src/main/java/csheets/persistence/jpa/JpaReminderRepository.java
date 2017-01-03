/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.jpa;
import csheets.domain.Reminder;
import csheets.framework.persistence.repositories.impl.jpa.JpaRepository;
import csheets.persistence.ReminderRepository;

/**
 *
 * @author Gabriel
 */
public class JpaReminderRepository  extends JpaRepository<Reminder, Long> implements ReminderRepository{

    @Override
    protected String persistenceUnitName() {
        return PersistenceSettings.PERSISTENCE_UNIT_NAME;
    }
    
}
