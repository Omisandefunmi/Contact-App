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

//    @Test
//    public void saveContact_findContact_returnAllMatchingContacts(){
//        contactService.addContact("ijebu", "ode", "0987");
//        contactService.addContact("ilesa", "buga", "1010");
//        contactService.addContact("ijebu", "ola", "8883");
//
//
//        assertEquals("[Contact(id=1, firstName=ijebu, lastName=ode, phoneNumber=0987), Contact(id=3, " +
//                "firstName=ijebu, lastName=ola, phoneNumber=8883)]", contact.toString());
//    }

    @Test
    public void saveContacts_findContact_returnAllMatchingNamesInContacts(){
        contactService.addContact("ijebu", "buga", "0987");
        contactService.addContact("buga", "ijesha", "1010");
        contactService.addContact("osun", "ola", "8883");

        List<Contact> contacts = contactService.findByName("Buga");
        String searchResult = "[Contact(id=1, firstName=ijebu, lastName=buga, phoneNumber=0987), Contact(id=2, " +
                "firstName=ilesa, lastName=buga, phoneNumber=1010)]";
        assertEquals(searchResult, contacts.toString());
    }

    @Test
    public void saveContacts_findContact_returnPhoneNumber(){
        contactService.addContact("ijebu", "buga", "0987");
        contactService.addContact("ilesa", "buga", "1010");
        contactService.addContact("osun", "ola", "8883");


        Contact contacts = contactService.findByPhoneNumber("1010");
        String searchResult = "Contact(id=2, firstName=ilesa, lastName=buga, phoneNumber=1010)";
        assertEquals(searchResult, contacts.toString());
    }



}