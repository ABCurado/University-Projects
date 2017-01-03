/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence;

import csheets.domain.Contact;
import csheets.domain.Note;
import csheets.framework.persistence.repositories.Repository;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Diogo Azevedo
 */
public interface NoteRepository extends Repository<Note, Long> {

	public List<Note> notesByContact(Contact contact);

	public List<Note> principalNotes(Contact contact);

	public Iterable<Note> allPrincipal();

	public Iterable<Note> search(Calendar startdate, Calendar endDate,
								 String text, boolean content);
}
