package africa.semicolon.truecaller.services;

import africa.semicolon.truecaller.data.models.Contact;
import africa.semicolon.truecaller.data.repositories.ContactRepository;
import africa.semicolon.truecaller.data.repositories.ContactRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceImplTest {

    private ContactService contactService;
    private ContactRepository contactRepository;

    @BeforeEach
    public void setUp(){
        contactRepository = new ContactRepositoryImpl();
        contactService = new ContactServiceImpl(contactRepository);


    }

    @Test
    public void saveContact_findContactReturnContact(){
        contactService.addContact("ijebu", "ode", "0987");

        Contact contact = contactService.findById(1);
        assertEquals(1, contactRepository.count());
        assertEquals("ijebu", contact.getFirstName());
        assertEquals("ode", contact.getLastName());
        assertEquals("0987", contact.getPhoneNumber());
    }

    @Test
    public void saveContacts_findContact_returnAllMatchingNamesInContacts(){
        contactService.addContact("ijebu", "buga", "0987");
        contactService.addContact("buga", "ijesha", "1010");
        contactService.addContact("osun", "ola", "8883");

        List<Contact> contacts = contactService.findByName("Buga");
        String searchResult = "[Contact(id=2, firstName=buga, lastName=ijesha, phoneNumber=1010), Contact(id=1, " +
                "firstName=ijebu, lastName=buga, phoneNumber=0987)]";
        assertEquals(searchResult, contacts.toString());
        assertEquals(2, contacts.size());
    }

    @Test
    public void saveContacts_findContactByPhoneNumber_returnContact(){
        contactService.addContact("ijebu", "buga", "0987");
        contactService.addContact("ilesa", "buga", "1010");
        contactService.addContact("osun", "ola", "8883");


        Contact contacts = contactService.findByPhoneNumber("1010");
        String searchResult = "Contact(id=2, firstName=ilesa, lastName=buga, phoneNumber=1010)";
        assertEquals(searchResult, contacts.toString());
    }

    @Test
    public void saveContact_deleteSavedContactByFirstName(){
        contactService.addContact("ijebu", "buga", "0987");
        contactService.addContact("ilesa", "buga", "1010");
        contactService.addContact("osun", "ola", "8883");


        contactService.deleteByFirstNameAndPhoneNumber("osun", "8893");
        assertEquals(2, contactService.phoneBookSize());
    }

    @Test
    public void saveContact_deleteContactByPhoneNumber(){
        contactService.addContact("ijebu", "buga", "0987");
        contactService.addContact("ilesa", "buga", "1010");
        contactService.addContact("osun", "ola", "8883");


        contactService.deleteByPhoneNumber("8893");
        assertEquals(2, contactService.phoneBookSize());
    }
}



