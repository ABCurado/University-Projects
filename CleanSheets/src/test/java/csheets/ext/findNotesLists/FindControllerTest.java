/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.findNotesLists;

import csheets.AppSettings;
import csheets.domain.Contact;
import csheets.domain.List;
import csheets.domain.Note;
import csheets.domain.PersonContact;
import csheets.ext.notes_and_lists.NotesListsController;
import csheets.framework.persistence.repositories.DataIntegrityViolationException;
import csheets.persistence.PersistenceContext;
import csheets.support.DateTime;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Martins
 */
public class FindControllerTest {

	private FindController findController;
	private NotesListsController notesListController;
	private byte[] photo;
	private Contact contact;
	private List list1;
	private List list2;
	private List list3;
	private Note note1;
	private Note note2;
	private Note note3;

	private static String repositoryFactory;

	public FindControllerTest() throws DataIntegrityViolationException {
	}

	@BeforeClass
	public static void setUpClass() {
		repositoryFactory = AppSettings.instance().
			get("persistence.repositoryFactory");
		AppSettings.instance().
			set("persistence.repositoryFactory", "csheets.persistence.inmemory.InMemoryRepositoryFactory");
	}

	@AfterClass
	public static void tearDownClass() {
		AppSettings.instance().
			set("persistence.repositoryFactory", repositoryFactory);
	}

	@Before
	public void setUp() throws DataIntegrityViolationException, IOException {
		this.findController = new FindController(null, null);
		this.notesListController = new NotesListsController(null);
		this.photo = new byte[12];
		this.contact = new PersonContact("João", "Martins", null, null, this.photo);
		this.list1 = this.notesListController.
			createList(this.contact, "Lista 1\nA minha primeira lista!");
		this.list1.changeTime(DateTime.newCalendar(2016, 6, 20));
		this.list2 = this.notesListController.
			createList(this.contact, "Lista 2\nA minha segunda lista!");
		this.list3 = this.notesListController.
			createList(this.contact, "Lista 3\nA minha... \nterceira lista!");
		this.note1 = this.notesListController.
			createNote("Nota 1\nA minha primeira nota", this.contact);
		this.note1.changeTime(DateTime.newCalendar(2016, 6, 20));
		this.note2 = this.notesListController.
			createNote("Nota 2\nA minha segunda nota", this.contact);
		this.note3 = this.notesListController.
			createNote("Nota 3\nA minha... \nterceira nota", this.contact);
		PersistenceContext.repositories().lists().save(list1);
		PersistenceContext.repositories().lists().save(list2);
		PersistenceContext.repositories().lists().save(list3);
		PersistenceContext.repositories().notes().save(note1);
		PersistenceContext.repositories().notes().save(note2);
		PersistenceContext.repositories().notes().save(note3);
	}

	@After
	public void tearDown() {
		PersistenceContext.repositories().lists().delete(list1);
		PersistenceContext.repositories().lists().delete(list2);
		PersistenceContext.repositories().lists().delete(list3);
		PersistenceContext.repositories().notes().delete(note1);
		PersistenceContext.repositories().notes().delete(note2);
		PersistenceContext.repositories().notes().delete(note3);
	}

	/**
	 * Test of searchLists by a specific begin date method, of class
	 * FindController.
	 */
	@Test
	public void testSearchListsByStartDate() {
		System.out.println("testSearchListsByStartDate");
		Calendar startDate = DateTime.dateToCalendar(new Date(116, 05, 01));
		java.util.List<List> lists = new ArrayList<>();
		lists = (java.util.List<List>) this.findController.
			searchLists(startDate, null, null, false);
		assertEquals(lists.size(), 3); //All elements must be in the list
		startDate = DateTime.newCalendar(2016, 6, 21);
		lists = (java.util.List<List>) this.findController.
			searchLists(startDate, null, null, false);
		assertEquals(lists.size(), 2); //2 elements must be in the list
	}

	/**
	 * Test of searchLists by a specific start date method, of class
	 * FindController.
	 */
	@Test
	public void testSearchNotesByStartDate() {
		System.out.println("testSearchNotesByStartDate");
		Calendar startDate = DateTime.newCalendar(2016, 6, 1);
		java.util.List<Note> notes = new ArrayList<>();
		notes = (java.util.List<Note>) this.findController.
			searchNotes(startDate, null, null, false);
		assertEquals(notes.size(), 3); //All elements must be in the list
		startDate = DateTime.newCalendar(2016, 6, 21);
		notes = (java.util.List<Note>) this.findController.
			searchNotes(startDate, null, null, false);
		assertEquals(notes.size(), 2); //2 elements must be in the list
	}

	/**
	 * Test of searchLists by a specific end date method, of class
	 * FindController.
	 */
	@Test
	public void testSearchListsByEndDate() {
		System.out.println("testSearchListsByEndDate");
		Calendar endDate = DateTime.newCalendar(2016, 6, 30);
		java.util.List<List> lists = new ArrayList<>();
		lists = (java.util.List<List>) this.findController.
			searchLists(null, endDate, null, false);
		assertEquals(lists.size(), 3); //All elements must be in the list
		endDate = DateTime.newCalendar(2016, 6, 21);
		lists = (java.util.List<List>) this.findController.
			searchLists(null, endDate, null, false);
		assertEquals(lists.size(), 1); //1 elements must be in the list
	}

	/**
	 * Test of search Notes by a specific end date method, of class
	 * FindController.
	 */
	@Test
	public void testSearchNotesByEndDate() {
		System.out.println("testSearchNotesByEndDate");
		Calendar endDate = DateTime.newCalendar(2016, 6, 30);
		java.util.List<Note> lists = new ArrayList<>();
		lists = (java.util.List<Note>) this.findController.
			searchNotes(null, endDate, null, false);
		assertEquals(lists.size(), 3); //All elements must be in the list
		endDate = DateTime.newCalendar(2016, 6, 21);
		lists = (java.util.List<Note>) this.findController.
			searchNotes(null, endDate, null, false);
		assertEquals(lists.size(), 1); //1 element must be in the list
	}

	/**
	 * Test of search all by a specific title method, of class FindController.
	 */
	@Test
	public void testSearchAllByTitle() {
		System.out.println("testSearchAllByTitle");
		String expression = ".*";
		Set<List> listLists = new HashSet();
		listLists = (Set<List>) this.findController.
			searchLists(null, null, expression, false);
		assertEquals(listLists.size(), 3); //All elements must be in the list
		Set<Note> listNotes = new HashSet();
		listNotes = (Set<Note>) this.findController.
			searchNotes(null, null, expression, false);
		assertEquals(listNotes.size(), 3); //All elements must be in the list
	}

	/**
	 * Test of search Lists by a specific title method, of class FindController.
	 */
	@Test
	public void testSearchListsByTitle() {
		System.out.println("testSearchListsByTitle");
		String expression = "Lista 1";
		Set<List> listLists = new HashSet();
		listLists = (Set<List>) this.findController.
			searchLists(null, null, expression, false);
		assertEquals(listLists.size(), 1); //1 element must be in the list
		expression = "Lista 2";
		listLists = (Set<List>) this.findController.
			searchLists(null, null, expression, false);
		assertEquals(listLists.size(), 1); //1 element must be in the list
		expression = "Lista 3";
		listLists = (Set<List>) this.findController.
			searchLists(null, null, expression, false);
		assertEquals(listLists.size(), 1); //1 element must be in the list
	}

	/**
	 * Test of search Notes by a specific title method, of class FindController.
	 */
	@Test
	public void testSearchNotesByTitle() {
		System.out.println("testSearchNotesByTitle");
		String expression = "Nota 1";
		Set<Note> listLists = new HashSet();
		listLists = (Set<Note>) this.findController.
			searchNotes(null, null, expression, false);
		assertEquals(listLists.size(), 1); //1 element must be in the list
		expression = "Nota 2";
		listLists = (Set<Note>) this.findController.
			searchNotes(null, null, expression, false);
		assertEquals(listLists.size(), 1); //1 element must be in the list
		expression = "Nota 3";
		listLists = (Set<Note>) this.findController.
			searchNotes(null, null, expression, false);
		assertEquals(listLists.size(), 1); //1 element must be in the list
	}

	/**
	 * Test of search Lists by a specific content method, of class
	 * FindController.
	 */
	@Test
	public void testSearchListsByContent() {
		System.out.println("testSearchListsByContent");
		String expression = ".*primeira lista.*";
		Set<List> listLists = new HashSet();
		listLists = (Set<List>) this.findController.
			searchLists(null, null, expression, true);
		assertEquals(listLists.size(), 1); //1 element must be in the list
		expression = ".*segunda lista.*";
		listLists = (Set<List>) this.findController.
			searchLists(null, null, expression, true);
		assertEquals(listLists.size(), 1); //1 element must be in the list
	}

	/**
	 * Test of search Notes by a specific content method, of class
	 * FindController.
	 */
	@Test
	public void testSearchNotesByContent() {
		System.out.println("testSearchNotesByContent");
		String expression = ".*primeira nota.*";
		Set<Note> listLists = new HashSet();
		listLists = (Set<Note>) this.findController.
			searchNotes(null, null, expression, true);
		assertEquals(listLists.size(), 1); //1 element must be in the list
		expression = ".*segunda nota.*";
		listLists = (Set<Note>) this.findController.
			searchNotes(null, null, expression, true);
		assertEquals(listLists.size(), 1); //1 element must be in the list
	}

	/**
	 * Test of search Lists by a specific content with new line(\n) method, of
	 * class FindController.
	 */
	@Test
	public void testSearchListsByContentWithNewLine() {
		System.out.println("testSearchListsByContentWithNewLine");
		String expression = ".*terceira lista.*";
		Set<List> listLists = new HashSet();
		listLists = (Set<List>) this.findController.
			searchLists(null, null, expression, true);
		assertEquals(listLists.size(), 1); //1 element must be in the list
	}

	/**
	 * Test of search Notes by a specific content with new line(\n) method, of
	 * class FindController.
	 */
	@Test
	public void testSearchNotesByContentWithNewLine() {
		System.out.println("testSearchNotesByContentWithNewLine");
		String expression = ".*terceira nota.*";
		Set<Note> listLists = new HashSet();
		listLists = (Set<Note>) this.findController.
			searchNotes(null, null, expression, true);
		assertEquals(listLists.size(), 1); //1 element must be in the list
	}
}
