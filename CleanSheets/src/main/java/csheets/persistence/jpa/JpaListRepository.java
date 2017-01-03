/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.jpa;

import csheets.domain.Contact;
import csheets.domain.List;
import csheets.domain.List.ListLine;
import csheets.domain.Note;
import csheets.framework.persistence.repositories.impl.jpa.JpaRepository;
import csheets.persistence.ListRepository;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Rui Bento
 */
public class JpaListRepository extends JpaRepository<List, Long> implements ListRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

	@Override
	public Iterable<List> listsByContact(Contact contact) {
		final Query query = entityManager().
			createQuery("SELECT l FROM List l where l.contact.id = :contact "
				+ "and l.version.deleted = false "
				+ "and l.version.lastVersion = l.versionNum",
						List.class);
		query.setParameter("contact", contact.id());
		Iterable<List> tmp = query.getResultList();
		return tmp;
	}

	@Override
	public Iterable<List> listVersions(List list) {
		final Query query = entityManager().
			createQuery("SELECT l FROM List l where l.version.id = :version",
						List.class);
		query.setParameter("version", list.version().id());
		Iterable<List> tmp = query.getResultList();
		return tmp;
	}

	@Override
	public Iterable<List> search(Calendar startDate, Calendar endDate,
								 String text, boolean content) {
		Iterable<List> list = searchDates(startDate, endDate);
		if (text != null && !text.isEmpty()) {
			list = this.search(list, text, content);
		}
		return list;
	}

	public Iterable<List> search(Iterable<List> lists, String expression,
								 Boolean content) {
		Set<List> results = new HashSet();
		for (List list : lists) {
			if (!content) {
				if (list.getTitle().matches(expression)) {
					results.add(list);
				}
			} else {
				for (ListLine line : list.getLines()) {
					if (line.getText().matches(expression)) {
						results.add(list);
					}
				}
			}
		}
		return results;
	}

	public Iterable<List> searchDates(Calendar startDate, Calendar endDate) {
		if (startDate == null && endDate == null) {
			return this.allPrincipal();
		}
		String term = "SELECT l FROM List l where l.time BETWEEN :startDate AND :endDate and l.version.deleted = false and l.version.lastVersion = l.versionNum";
		if (startDate == null) {
			term = "SELECT l FROM List l where l.time < :endDate and l.version.deleted = false and l.version.lastVersion = l.versionNum";
		} else if (endDate == null) {
			term = "SELECT l FROM List l where l.time > :startDate and l.version.deleted = false and l.version.lastVersion = l.versionNum";
		}
		final Query query = entityManager().createQuery(term, Note.class);
		if (startDate != null) {
			query.setParameter("startDate", startDate, TemporalType.DATE);
		}
		if (endDate != null) {
			query.setParameter("endDate", endDate, TemporalType.DATE);
		}
		return (Iterable<List>) query.getResultList();
	}

	@Override
	public Iterable<List> allPrincipal() {
		final Query query = entityManager().
			createQuery("SELECT l FROM List l "
				+ "where l.version.deleted = false "
				+ "and l.version.lastVersion = l.versionNum",
						List.class);
		Iterable<List> tmp = query.getResultList();
		return tmp;
	}

}
