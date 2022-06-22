package africa.semicolon.truecaller.services;

import africa.semicolon.truecaller.data.models.Contact;
import africa.semicolon.truecaller.data.repositories.ContactRepository;

import java.util.ArrayList;
import java.util.List;

public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;


    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void addContact(String firstName, String lastName, String phoneNumber) {
        Contact contact = new Contact(firstName, lastName, phoneNumber);
        contactRepository.save(contact);
    }

    @Override
    public Contact findById(int i) {
        return contactRepository.findById(i);
    }

    @Override
    public List<Contact> findByName(String name) {
        List<Contact> allContactsWithMatchingName = new ArrayList<>();
        List<Contact> matchingFirstNameContactList = contactRepository.findByFirstName(name);
        List<Contact> matchingLastNameContactList = contactRepository.findByLastName(name);
        allContactsWithMatchingName.addAll(matchingFirstNameContactList);
        allContactsWithMatchingName.addAll(matchingLastNameContactList);

        return allContactsWithMatchingName;
    }

    @Override
    public Contact findByPhoneNumber(String phoneNumber) {
        return contactRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Contact deleteByFirstNameAndPhoneNumber(String firstName, String phoneNumber) {
        List<Contact> matchingContact = contactRepository.findByFirstName(firstName);
        Contact deletedContact = findContactWithMatchingContact(phoneNumber, matchingContact);
        return deletedContact;

    }

    private Contact findContactWithMatchingContact(String phoneNumber, List<Contact> matchingContact) {
        for (int i = 0; i < matchingContact.size(); i++) {
            Contact deletedContact = matchingContact.get(i);
            if(deletedContact.getPhoneNumber().equals(phoneNumber)){ matchingContact.remove(deletedContact); return deletedContact;}
        }
        return null;
    }

    @Override
    public int phoneBookSize() {
        return contactRepository.count();
    }

    @Override
    public Contact deleteByPhoneNumber(String phoneNumber) {
        Contact deletedContact = contactRepository.findByPhoneNumber(phoneNumber);
        if (deletedContact != null) { contactRepository.deleteContact(deletedContact); return deletedContact; }
        return null;
    }

}
