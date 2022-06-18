package africa.semicolon.truecaller.services;

import africa.semicolon.truecaller.data.models.Contact;
import africa.semicolon.truecaller.data.repositories.ContactRepository;

import java.util.ArrayList;
import java.util.List;

public class ContactServiceImpl implements ContactService{
    private ContactRepository contactRepository;


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


    private List<Contact> findByFirstName(String firstName) {
        return contactRepository.findByFirstName(firstName);
    }

    @Override
    public List<Contact> findByName(String lastName) {
        List <Contact> allContactsWithMatchingName = new ArrayList<>();
//        allContactsWithMatchingName.
        return allContactsWithMatchingName;
    }

    @Override
    public Contact findByPhoneNumber(String phoneNumber) {
        return contactRepository.findByPhoneNumber(phoneNumber);
    }
}
