/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.inmemory;

import csheets.domain.Contact;
import csheets.domain.Note;
import csheets.framework.persistence.repositories.impl.immemory.InMemoryRepository;
import csheets.persistence.NoteRepository;
import csheets.support.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Martins
 */
class InMemoryNoteRepository extends InMemoryRepository<Note, Long>
	implements NoteRepository {

	long nextID = 1;

	@Override
	protected Long newPK(Note entity) {
		return ++nextID;
	}

	@Override
	public List<Note> notesByContact(Contact contact) {
		List<Note> list = new ArrayList();
		for (Note note : this.all()) {
			if (note.getContact().equals(contact)) {
				list.add(note);
			}
		}
		return list;
	}

	@Override
	public List<Note> principalNotes(Contact contact) {
		List<Note> list = new ArrayList();
		for (Note note : this.all()) {
			if (note.getContact().equals(contact) && note.noteState()) {
				list.add(note);
			}
		}
		return list;
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
		List<Note> list = new ArrayList();
		if (startDate != null && endDate != null) {
			for (Note note : this.all()) {
				if (DateTime.isBetweenDates(startDate, endDate, note.date()) && note.
					noteState()) {
					list.add(note);
				}
			}
		}
		if (startDate == null) {
			for (Note note : this.all()) {
				if (DateTime.isPreviousDate(note.date(), endDate) && note.
					noteState()) {
					list.add(note);
				}
			}
		}
		if (endDate == null) {
			for (Note note : this.all()) {
				if (DateTime.isFutureDate(note.date(), startDate) && note.
					noteState()) {
					list.add(note);
				}
			}
		}
		return list;
	}

	@Override
	public Iterable<Note> allPrincipal() {
		List<Note> list = new ArrayList();
		for (Note note : this.all()) {
			if (note.noteState()) {
				list.add(note);
			}
		}
		return list;
	}
}
