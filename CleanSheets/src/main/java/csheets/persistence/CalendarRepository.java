/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence;

import csheets.domain.ContactCalendar;
import csheets.domain.Contact;
import csheets.framework.persistence.repositories.Repository;

/**
 *
 * @author Eduardo
 */
public interface CalendarRepository extends Repository<ContactCalendar, Long> {

	public Iterable<ContactCalendar> calendarsContact(Contact contact);

}
