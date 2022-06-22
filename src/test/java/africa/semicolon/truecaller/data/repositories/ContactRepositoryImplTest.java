package africa.semicolon.truecaller.data.repositories;

import africa.semicolon.truecaller.data.models.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactRepositoryImplTest {
    private ContactRepository contactRepository;

    @BeforeEach
    public void setUp(){
        contactRepository = new ContactRepositoryImpl();
    }

    @Test
    public void saveContact_countIsOneTest(){
        Contact contact = new Contact();
        contact.setFirstName("Samson");

        Contact savedContact = contactRepository.save(contact);
        assertEquals(1, savedContact.getId());
        assertEquals(1, contactRepository.count());
    }

    @Test
    public void saveContactTwice_countIsTwoTest(){
        Contact contact = new Contact();
        contact.setFirstName("Samson");

        Contact contact1 = new Contact();
        contact1.setFirstName("SAM");

        Contact savedContact = contactRepository.save(contact);
        Contact savedContact2 = contactRepository.save(contact1);
        assertEquals(1, savedContact.getId());
        assertEquals(2, savedContact2.getId());
        assertEquals(2, contactRepository.count());
    }

    @Test
    public void saveContact_findByIdReturnsContact() {
        Contact contact = new Contact();
        contact.setFirstName("Samson");

        Contact contact2 = new Contact();
        contact2.setFirstName("Abiola");

        contactRepository.save(contact);
        contactRepository.save(contact2);

        Contact foundContact = contactRepository.findById(1);

        assertEquals(1, foundContact.getId());
        assertEquals("Samson", foundContact.getFirstName());
    }

    @Test
    public void saveContact_findSavedContactByFirstName(){
        Contact contact = new Contact();
        contact.setFirstName("Samson");

        Contact contact2 = new Contact();
        contact2.setFirstName("Abiola");

        contactRepository.save(contact);
        contactRepository.save(contact2);

        List<Contact> foundContact = contactRepository.findByFirstName("Samson");
        String boo = "[Contact(id=1, firstName=Samson, lastName=null, phoneNumber=null)]";

        assertEquals(boo, foundContact.toString());
    }

    @Test
    public void saveContact_findSavedContactByLastName(){
        Contact contact = new Contact();
        contact.setFirstName("Samson");
        contact.setLastName("Adeoye");

        Contact contact2 = new Contact();
        contact2.setFirstName("Abiola");
        contact2.setLastName("Aina");

        contactRepository.save(contact);
        contactRepository.save(contact2);

        List<Contact> foundContact = contactRepository.findByLastName("Aina");
        String boo = "[Contact(id=2, firstName=Abiola, lastName=Aina, phoneNumber=null)]";

        assertEquals(boo, foundContact.toString());
    }

    @Test
    public void saveContact_findSavedContactByPhoneNumber(){
        Contact contact = new Contact();
        contact.setFirstName("Samson");
        contact.setPhoneNumber("09900");


        contactRepository.save(contact);

        Contact foundContact = contactRepository.findByPhoneNumber("09900");
        String boo = "Contact(id=1, firstName=Samson, lastName=null, phoneNumber=09900)";

        assertEquals(boo, foundContact.toString());
    }

    @Test
    public void saveContact_deleteSavedContact(){
        Contact contact = new Contact();
        contact.setFirstName("Samson");
        contact.setPhoneNumber("09900");


        contactRepository.save(contact);
        assertEquals(1, contactRepository.count());
        contactRepository.deleteContact(contact);
        assertEquals(0, contactRepository.count());

    }
}