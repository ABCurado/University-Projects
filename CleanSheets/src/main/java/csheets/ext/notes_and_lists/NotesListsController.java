/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.notes_and_lists;

import csheets.domain.Contact;
import csheets.domain.List;
import csheets.domain.Note;
import csheets.factory.NoteFactory;
import csheets.framework.persistence.repositories.DataIntegrityViolationException;
import csheets.notification.Notification;
import csheets.persistence.PersistenceContext;
import csheets.ui.ctrl.UIController;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diogo Azevedo
 * @author Rui Bento
 * @author João Martins
 */
public class NotesListsController {

	/**
	 * The user interface controller
	 */
	private final UIController uiController;

	private Map<Integer, List> listVersions;

	/**
	 * Creates a new comment controller.
	 *
	 * @param uiCtrl the user interface controller
	 */
	public NotesListsController(UIController uiCtrl) {
		this.uiController = uiCtrl;
	}

	public Iterable<Contact> showContacts() {
		return PersistenceContext.repositories().contacts().all();
	}

	public Note createNote(String noteText, Contact contact) {
		Note note = NoteFactory.createNote(noteText, contact, true);
		try {
			PersistenceContext.repositories().notes().add(note);
			Notification.noteInformer().notifyChange(note);

		} catch (DataIntegrityViolationException ex) {
			Logger.getLogger(NotesListsController.class.getName()).
				log(Level.SEVERE, null, ex);
		}
		return note;
	}

	public Note editNote(String noteText, Note note) {
		Note version = new Note(note.getInfo(), note.getContact(), false);
		note.addVersion(version);
		note.edit(noteText);
		PersistenceContext.repositories().notes().save(note);
		Notification.noteInformer().notifyChange(note);
		return note;
	}

	public void deleteNote(Note note) {
		PersistenceContext.repositories().notes().delete(note);
		Notification.noteInformer().notifyChange(note);
	}

	public Iterable<Note> notesByContact(Contact contact) {
		return PersistenceContext.repositories().notes().principalNotes(contact);
	}

	public Iterable<Note> versionByNote(Note note) {
		return note.versionByNote();
	}

	public Iterable<Contact> getContacts() {
		return PersistenceContext.repositories().contacts().all();
	}

	public Iterable<List> getContactLists(Object contactItem) {
		if (contactItem != null && contactItem instanceof Contact) {
			Contact contact = (Contact) contactItem;
			return PersistenceContext.repositories().lists().
				listsByContact(contact);
		}
		return null;
	}

	public Map<Integer, List> getListVersions(List list) {
		if (list != null) {
			listVersions = new HashMap<>();
			for (List version : PersistenceContext.repositories().lists().
				listVersions(list)) {
				listVersions.put(version.getVersionNumber(), version);
			}
			return listVersions;
		}
		return null;
	}

	public List getVersion(List list, int version) {
		return version != -1 ? listVersions.get(version) : listVersions.
			get(listVersions.size());
	}

	public List createList(Object contactObj, String text) throws DataIntegrityViolationException, ClassCastException {
		if (!(contactObj instanceof Contact)) {
			throw new ClassCastException("Selected contact invalid.");
		}
		Contact contact = (Contact) contactObj;
		String data[] = text.split("\n", 2);
		if (data.length != 2) {
			throw new DataIntegrityViolationException("Text invalid !\nFirst line should be the title.\nEach other line should be the lines of the list.");
		}
		if (data[0].equals("")
			|| data[1].equals("")) {
			throw new DataIntegrityViolationException("Text invalid !\nText should contain info.");
		}
		List newList = new List(data[0], data[1], contact);
		return PersistenceContext.repositories().lists().save(newList);
	}

	public List editList(List list, String text) throws DataIntegrityViolationException {
		String data[] = text.split("\n", 2);
		if (data.length != 2) {
			throw new DataIntegrityViolationException("Text invalid !\nFirst line should be the title.\nEach other line should be the lines of the list.");
		}
		if (data[0].equals("")
			|| data[1].equals("")) {
			throw new DataIntegrityViolationException("Text invalid !\nText should contain info.");
		}
		List newList = list.newVersion(data[0], data[1]);
		return PersistenceContext.repositories().lists().save(newList);
	}

	public List applyList(List list, Object[][] listData) {
		for (Object[] data : listData) {
			if (data[0] instanceof Boolean
				&& data[1] instanceof String) {
				String text = (String) data[1];
				boolean check = (boolean) data[0];
				list.changeState(text, check);
			}
		}
		return PersistenceContext.repositories().lists().save(list);
	}

	public List deleteList(List list) {
		list.delete();
		return PersistenceContext.repositories().lists().save(list);
	}

}
