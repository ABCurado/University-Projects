/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.tags;

import csheets.domain.CompanyContact;
import csheets.domain.Contact;
import csheets.domain.ContactCalendar;
import csheets.domain.Event;
import csheets.domain.PersonContact;
import csheets.persistence.PersistenceContext;
import java.awt.Color;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ruben
 */
public class TagControllerTest {

	private Color color;
	byte[] photo = new byte[1];

	String tag = "text";
	TagController tagcontroller = new TagController();
	private static final String DEFAULT_USER_PHOTO = "res/img/default_user.png";

	public TagControllerTest() {

		Contact companyContact = new CompanyContact("Isep", photo);
		Contact personContact = new PersonContact("Ruben", "Santos", "estudante", companyContact, photo);

	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of allContacts method, of class TagController.
	 */
	@Test
	public void testAllContacts() {
		System.out.println("allContacts");
		TagController instance = new TagController();
		Iterable<Contact> expResult = PersistenceContext.repositories().
			contacts().all();
		Iterable<Contact> result = tagcontroller.allContacts();
		assertEquals(expResult, result);

	}

	/**
	 * Test of allContactsTag method, of class TagController.
	 */
	@Test
	public void testAllContactsTag() {
		System.out.println("allContactsTag");

		Iterable<Contact> expResult = PersistenceContext.repositories().
			contacts().getContactByTag(tag);
		Iterable<Contact> result = tagcontroller.getContactByTag(tag);
		assertEquals(expResult, result);

	}

	/**
	 * Test of allCompanies method, of class TagController.
	 */
	@Test
	public void testAllCompanies() {
		System.out.println("allCompanies");
		Iterable<Contact> expResult = PersistenceContext.repositories().
			contacts().allCompanies();
		Iterable<Contact> result = tagcontroller.allCompanies();
		assertEquals(expResult, result);

	}

	/**
	 * Test of contactsCompany method, of class TagController.
	 */
	@Test
	public void testContactsCompany() {
		System.out.println("contactsCompany");
		Contact companyContact = new CompanyContact("Isep", photo);
		Iterable<Contact> expResult = PersistenceContext.repositories().
			contacts().contactsCompany(companyContact);
		Iterable<Contact> result = tagcontroller.contactsCompany(companyContact);
		assertEquals(expResult, result);

	}

	/**
	 * Test of eventsContact method, of class TagController.
	 */
	@Test
	public void testEventsContact() {
		System.out.println("eventsContact");
		Contact companyContact = new CompanyContact("Isep", photo);

		Iterable<Event> expResult = PersistenceContext.repositories().events().
			eventsContact(companyContact);
		Iterable<Event> result = tagcontroller.eventsContact(companyContact);
		assertEquals(expResult, result);

	}

//	/**
//	 * Test of contactPhoto method, of class TagController.
//	 */
//	@Test
//	public void testContactPhoto() throws Exception {
//		System.out.println("contactPhoto");
//		Contact contact = new CompanyContact("Isep", photo);
//		Image expResult = Converter.getImage(contact.photo());
//		Image result = tagcontroller.contactPhoto(contact);
//		assertEquals(expResult, result);
//
//	}
	/**
	 * Test of addPerson method, of class TagController.
	 */
	/**
	 * Test of removeContact method, of class TagController.
	 */
	@Test
	public void testRemoveContact() {
		System.out.println("removeContact");
		Contact theContact = new CompanyContact("Isep", photo);
		//tagcontroller.removeContact(theContact);
	}

//	/**
//	 * Test of editContact method, of class TagController.
//	 */
//	@Test
//	public void testEditContact() throws Exception {
//		System.out.println("editContact");
//
//		Contact companyContact = new CompanyContact("Isep", photo);
//		Contact personContact = new PersonContact("Ruben", "Santos", "estudante", companyContact, photo);
//		Contact expResult = PersistenceContext.repositories().contacts().
//			save(personContact);
//		Contact result = tagcontroller.editContact(personContact);
//		assertEquals(expResult, result);
//
//	}
	/**
	 * Test of getContact method, of class TagController.
	 */
	@Test
	public void testGetContact() {
		System.out.println("getContact");
		Contact contact = new CompanyContact("Isep", photo);

		Contact expResult = PersistenceContext.repositories().contacts().
			getByName("Isep");
		Contact result = tagcontroller.getContact("Isep");
		assertEquals(expResult, result);

	}

	/**
	 * Test of calendarContact method, of class TagController.
	 */
	@Test
	public void testCalendarContact() {
		System.out.println("calendarContact");
		Contact contact = new CompanyContact("Isep", photo);

		Iterable<ContactCalendar> expResult = PersistenceContext.repositories().
			calendars().
			calendarsContact(contact);
		Iterable<ContactCalendar> result = tagcontroller.
			calendarContact(contact);
		assertEquals(expResult, result);

	}

	/**
	 * Test of setText method, of class TagController.
	 */
	@Test
	public void testSetText() {
		System.out.println("setText");
		String text = "Text1";

		tagcontroller.setText(text);

	}

}
