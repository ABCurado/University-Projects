package csheets.ext.address;

import csheets.domain.Address;
import csheets.domain.Contact;
import csheets.ext.address.ui.AddressPanel;
import csheets.notification.Notification;
import csheets.persistence.PersistenceContext;
import csheets.support.PostalCodeValidator;
import csheets.ui.ctrl.UIController;
import java.io.FileNotFoundException;

/**
 * A controller for updating the user-specified comment of a cell.
 *
 * @author João Martins
 */
public class AddressController {

	/**
	 * The user interface controller
	 */
	private UIController uiController;

	/**
	 * User interface panel *
	 */
	private AddressPanel uiPanel;

	/**
	 * Creates a new comment controller.
	 *
	 * @param uiController the user interface controller
	 * @param uiPanel the user interface panel
	 */
	public AddressController(UIController uiController, AddressPanel uiPanel) {
		this.uiController = uiController;
		this.uiPanel = uiPanel;
	}

	public Iterable<Contact> allContacts() {
		return PersistenceContext.repositories().contacts().all();
	}

	public Iterable<Address> allAddress(Contact contact) {
		return contact.contactAddress();
	}

	public void createAddress(Contact contact, String country, String city,
							  String street, String postalCode, boolean main) {
		Address address = new Address(country, city, street, postalCode);
		if (main) {
			contact.setMainAddress(address);
		} else {
			contact.setSecundaryAddress(address);
		}
		PersistenceContext.repositories().contacts().save(contact);
		Notification.addressInformer().notifyChange(address);
	}

	public void editAddress(Contact contact, Address address, String country,
							String city, String street, String postalCode,
							boolean main) {
		address.defineAddress(country, city, street, postalCode);
		if (main && contact.getSecundaryAddress().equals(address)) {
			contact.setMainAddress(address);
			contact.setSecundaryAddress(null);
		} else if (!main && contact.getMainAddress().equals(address)) {
			contact.setMainAddress(null);
			contact.setSecundaryAddress(address);
		}
		PersistenceContext.repositories().addresses().save(address);
		Notification.addressInformer().notifyChange();
	}

	public void removeAddress(Contact contact, Address address, boolean main) {
		contact.removeAddress(address, main);
		PersistenceContext.repositories().contacts().save(contact);
		PersistenceContext.repositories().addresses().delete(address);
		Notification.addressInformer().notifyChange();
	}

	public void importPostalCodes() throws FileNotFoundException {
		PostalCodeValidator.importData();
	}
}
