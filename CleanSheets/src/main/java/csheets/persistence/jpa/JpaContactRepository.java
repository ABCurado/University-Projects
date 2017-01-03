/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.jpa;

import csheets.domain.CompanyContact;
import csheets.domain.Contact;
import csheets.domain.ContactCalendar;
import csheets.domain.Note;
import csheets.domain.PersonContact;
import csheets.framework.persistence.repositories.impl.jpa.JpaRepository;
import csheets.persistence.ContactRepository;
import csheets.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Rui Freitas
 */
public class JpaContactRepository extends JpaRepository<Contact, Long> implements ContactRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

	public Contact getByName(String name) {
		/*
		final Query query = entityManager().
			createQuery("select m from Contact m where m.name = :name", Contact.class);
		query.setParameter("name", name);
		return (Contact) query.getSingleResult();
		 */
		for (Contact contact : this.all()) {
			if (contact instanceof PersonContact) {
				PersonContact person = (PersonContact) contact;
				if (person.name().equalsIgnoreCase(name)) {
					return contact;
				}
			} else if (contact instanceof CompanyContact) {
				CompanyContact company = (CompanyContact) contact;
				if (company.name().equalsIgnoreCase(name)) {
					return contact;
				}
			}
		}
		return null;
	}

	@Override
	public Iterable<Contact> contactsCompany(Contact company) {
		List<Contact> list = new ArrayList();
		for (Contact contact : this.all()) {
			if (contact instanceof PersonContact) {
				PersonContact person = (PersonContact) contact;
				if (person.company() != null && person.company().equals(company)) {
					list.add(contact);
				}
			}
		}
		return list;
	}

	@Override
	public Iterable<Contact> allCompanies() {
		List<Contact> list = new ArrayList();
		for (Contact contact : this.all()) {
			if (contact instanceof CompanyContact) {
				list.add(contact);
			}
		}
		return list;
	}

	@Override
	public void delete(Contact entity) {
		for (ContactCalendar calendar : PersistenceContext.repositories().
			calendars().
			calendarsContact(entity)) {
			PersistenceContext.repositories().calendars().delete(calendar);
		}
		for (Note note : PersistenceContext.repositories().notes().
			principalNotes(entity)) {
			for (Note version : note.versionByNote()) {
				PersistenceContext.repositories().notes().delete(version);
			}
			PersistenceContext.repositories().notes().delete(note);
		}
		super.delete(entity);
	}

	@Override
	public Iterable<Contact> getContactByTag(String tag) {
		List<Contact> contacts = new ArrayList();
		if (tag != null && !tag.isEmpty()) {
			for (Contact contact : this.all()) {
				for (String tagName : contact.tags()) {
					if (tagName != null && !tagName.isEmpty() && tagName.
						matches(tag)) {
						contacts.add(contact);
					}
				}
			}
		}
		return contacts;
	}

	@Override
	public Map<String, Integer> tagFrequency() {
		Map<String, Integer> map = new LinkedHashMap();
		for (Contact contact : this.all()) {
			for (String contactTag : contact.tags()) {
				Integer quantidade = map.get(contactTag);
				if (quantidade != null) {
					map.put(contactTag, quantidade + 1);
				} else {
					map.put(contactTag, 1);
				}
			}
		}
		List<Entry<String, Integer>> sortedEntries = new ArrayList(map.
			entrySet());

		Collections.sort(sortedEntries,
						 new Comparator<Entry<String, Integer>>() {
						 @Override
						 public int compare(Entry<String, Integer> e1,
											Entry<String, Integer> e2) {
							 return e2.getValue().compareTo(e1.getValue());
						 }
					 }
		);
		map.clear();
		for (Entry<String, Integer> entry : sortedEntries) {
			map.put(entry.getKey(), entry.getValue());
		}

		return map;
	}

}
