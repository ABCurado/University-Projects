package csheets.ext.contacts;

import csheets.CleanSheets;
import csheets.domain.CompanyContact;
import csheets.domain.Contact;
import csheets.domain.ContactCalendar;
import csheets.domain.Event;
import csheets.domain.PersonContact;
import csheets.framework.persistence.repositories.DataIntegrityViolationException;
import csheets.notification.Notification;
import csheets.persistence.PersistenceContext;
import csheets.support.Converter;
import csheets.ui.ctrl.UIController;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JPanel;

/**
 *
 * @author Rui Freitas
 */
public class ContactsController {

	private static final String DEFAULT_USER_PHOTO = "res/img/default_user.png";

	/**
	 * The user interface controller
	 */
	private UIController uiController;

	/**
	 * User interface panel *
	 */
	private JPanel uiPanel;

	public ContactsController() {
	}

	public ContactsController(UIController uiController, JPanel uiPanel) {
		this.uiController = uiController;
		this.uiPanel = uiPanel;
	}

	public Iterable<Contact> allContacts() {
		return PersistenceContext.repositories().contacts().all();
	}

	public Iterable<String> allProfissions() {
		List<String> list = new ArrayList();
		try {
			Scanner input = new Scanner(new File(CleanSheets.class.
				getResource("res/profissions.props").getFile()));
			while (input.hasNextLine()) {
				list.add(input.nextLine());
			}
		} catch (FileNotFoundException ex) {
		}
		return list;
	}

	public Iterable<Contact> allCompanies() {
		return PersistenceContext.repositories().contacts().allCompanies();
	}

	public Iterable<Contact> contactsCompany(Contact contact) {
		return PersistenceContext.repositories().contacts().
			contactsCompany(contact);
	}

	public Iterable<Event> eventsContact(Contact contact) {
		return PersistenceContext.repositories().events().
			eventsContact(contact);
	}

	public Image contactPhoto(Contact contact) throws IOException {
		return Converter.getImage(contact.photo());
	}

	public Contact addPerson(String firstName, String lastName,
							 String profession, Contact company, File photoPath) throws DataIntegrityViolationException, IOException {
		byte[] thePhoto;
		if (photoPath == null) {
			thePhoto = Converter.setImage(new File(CleanSheets.class.
				getResource(DEFAULT_USER_PHOTO).getFile()));
		} else {
			try {
				thePhoto = Converter.setImage(photoPath);
			} catch (IOException ex) {
				throw new IOException("Loading file error!");
			}
		}
		PersonContact contact = new PersonContact(firstName, lastName, profession, company, thePhoto);
		PersistenceContext.repositories().contacts().add(contact);
		Notification.contactInformer().notifyChange(contact);
		return PersistenceContext.repositories().contacts().getByName(contact.
			name());
	}

	public Contact addCompany(String name, File photoPath) throws DataIntegrityViolationException, IOException {
		byte[] thePhoto;
		if (photoPath == null) {
			thePhoto = Converter.setImage(new File(CleanSheets.class.
				getResource(DEFAULT_USER_PHOTO).getFile()));
		} else {
			try {
				thePhoto = Converter.setImage(photoPath);
			} catch (IOException ex) {
				throw new IOException("Loading file error!");
			}
		}
		CompanyContact contact = new CompanyContact(name, thePhoto);
		PersistenceContext.repositories().contacts().add(contact);
		Notification.contactInformer().notifyChange(contact);
		return PersistenceContext.repositories().contacts().getByName(contact.
			name());
	}

	public void removeContact(Contact theContact) {
		PersistenceContext.repositories().contacts().delete(theContact);
		Notification.contactInformer().notifyChange();
	}

	public Contact editContact(Contact theContact) throws DataIntegrityViolationException {
		PersistenceContext.repositories().contacts().save(theContact);
		Notification.contactInformer().notifyChange();
		return theContact;
	}

	public Contact systemUser() {
		try {
			String userName = System.getProperty("user.name");
			Contact contact = getContact(userName);
			if (contact == null) {
				contact = this.
					addPerson(userName, null, null, null, new File(CleanSheets.class.
							  getResource(DEFAULT_USER_PHOTO).getFile()));
			}
			return contact;
		} catch (Exception ex) {
			return null;
		}
	}

	public Contact getContact(String name) {
		return PersistenceContext.repositories().contacts().getByName(name);
	}

	public Iterable<ContactCalendar> calendarContact(Contact contact) {
		return PersistenceContext.repositories().calendars().
			calendarsContact(contact);
	}

	public Iterable<Event> eventsCalendar(ContactCalendar calendarPer) {
		return PersistenceContext.repositories().events().
			eventsCalendar(calendarPer);
	}

}
