/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.agenda.ui;

import csheets.domain.Contact;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author AB-1140280
 */
public class ContactListModel extends DefaultListModel<String> implements ComboBoxModel<String> {

    private transient List<Contact> listSubjects;
    private transient Contact selectedContact;

    public ContactListModel(List<Contact> listSubjects) {
        if(listSubjects == null){
            throw new IllegalArgumentException();
        }
        this.listSubjects = listSubjects;
    }

    @Override
    public int getSize() {
        return listSubjects.size();
    }

    @Override
    public String getElementAt(int index) {
        return listSubjects.get(index).name();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ContactListModel other = (ContactListModel) obj;
        if (this.getSize() != other.getSize()) {
            return false;
        }
        int i = 0;
        for (Contact cont : listSubjects) {
            if (!cont.equals(other.getContactAt(i))) {
                return false;
            }
            i++;
        }
        return true;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        String selected = (String) anItem;
        for (Contact contact : listSubjects) {
            if (contact.name().equals(selected)) {
                selectedContact = contact;
                break;
            }
        }
    }

    @Override
    public Object getSelectedItem() {
        return selectedContact;
    }

    public Contact getContactAt(int i) {
        return listSubjects.get(i);
    }

    public Contact getSelectedContact() {
        return selectedContact;
    }
}
