package africa.semicolon.truecaller.data.repositories;

import africa.semicolon.truecaller.data.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactRepositoryImpl implements ContactRepository {

    List<Contact> contacts = new ArrayList<>();
    @Override
    public Contact save(Contact contact) {
        contact.setId(contacts.size() + 1);
        contacts.add(contact);
        return contact;
    }

    @Override
    public List<Contact> findByFirstName(String firstName) {
        List <Contact> contactsWithSameFirstName = new ArrayList<>();
//        Contact contact;
        for (Contact contact : contacts) {
//            contact = value;
            if (contact.getFirstName().equalsIgnoreCase(firstName)) {
                contactsWithSameFirstName.add(contact);
            }
        }
        return contactsWithSameFirstName;
    }

    @Override
    public int count() {
        return contacts.size();
    }

    @Override
    public Contact findById(int id) {

        return contacts.get(id - 1);
    }

    @Override
    public List<Contact> findByLastName(String lastName) {
        List <Contact> matchingContacts = new ArrayList<>();
        Contact contact;
        for (Contact value : contacts) {
            contact = value;
            if (contact.getLastName().equalsIgnoreCase(lastName)) {
                matchingContacts.add(contact);
            }
        }
        return matchingContacts;
    }

    @Override
    public Contact findByPhoneNumber(String phoneNumber) {
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                return contact;
            }
        }
        return null;
    }
}
