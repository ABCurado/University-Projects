/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.jpa;

import csheets.domain.Contact;
import csheets.domain.Note;
import csheets.framework.persistence.repositories.impl.jpa.JpaRepository;
import csheets.persistence.NoteRepository;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Diogo Azevedo
 */
public class JpaNoteRepository extends JpaRepository<Note, Long> implements NoteRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

	@Override
	public List<Note> notesByContact(Contact contact) {
		final Query query = entityManager().
			createQuery("select m from Note m where m.contact.id = :contact", Note.class);
		query.setParameter("contact", contact.id());
		List<Note> tmp = query.getResultList();
		return tmp;
	}

	@Override
	public List<Note> principalNotes(Contact contact) {
		final Query query = entityManager().
			createQuery("select m from Note m where m.contact.id = :contact and m.versionState = 1", Note.class);
		query.setParameter("contact", contact.id());
		List<Note> tmp = query.getResultList();
		return tmp;
	}

	@Override
	public Iterable<Note> search(Calendar startDate, Calendar endDate,
								 String text, boolean content) {
		Iterable<Note> list = searchDates(startDate, endDate);
		if (text != null && !text.isEmpty()) {
			list = this.search(list, text, content);
		}
		return list;
	}

	public Iterable<Note> search(Iterable<Note> lists, String expression,
								 Boolean content) {
		Set<Note> results = new HashSet();
		for (Note note : lists) {
			if (!content) {
				if (note.getTitle().matches(expression)) {
					results.add(note);
				}
			} else {
				for (String line : note.getInfo().split("\n")) {
					if (line.matches(expression)) {
						results.add(note);
					}
				}
			}
		}
		return results;
	}

	public Iterable<Note> searchDates(Calendar startDate, Calendar endDate) {
		if (startDate == null && endDate == null) {
			return this.allPrincipal();
		}
		String term = "SELECT n FROM Note n where n.time BETWEEN :startDate AND :endDate and n.versionState = true";
		if (startDate == null) {
			term = "SELECT n FROM Note n where n.time < :endDate and n.versionState = true";
		} else if (endDate == null) {
			term = "SELECT n FROM Note n where n.time > :startDate and n.versionState = true";
		}
		final Query query = entityManager().createQuery(term, Note.class);
		if (startDate != null) {
			query.setParameter("startDate", startDate, TemporalType.DATE);
		}
		if (endDate != null) {
			query.setParameter("endDate", endDate, TemporalType.DATE);
		}
		return (Iterable<Note>) query.getResultList();
	}

	@Override
	public Iterable<Note> allPrincipal() {
		final Query query = entityManager().
			createQuery("SELECT n FROM Note n where n.versionState = true", Note.class);
		return (Iterable<Note>) query.getResultList();
	}
}
