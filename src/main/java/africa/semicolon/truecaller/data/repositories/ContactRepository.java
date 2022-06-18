package africa.semicolon.truecaller.data.repositories;

import africa.semicolon.truecaller.data.models.Contact;

import java.util.List;

public interface ContactRepository {
Contact save(Contact contact);
    List<Contact> findByFirstName(String firstName);
    int count();
    Contact findById(int id);

    List<Contact> findByLastName(String lastName);

    Contact findByPhoneNumber(String phoneNumber);
}
