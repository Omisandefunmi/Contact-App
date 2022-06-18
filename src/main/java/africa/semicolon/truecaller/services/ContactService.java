package africa.semicolon.truecaller.services;

import africa.semicolon.truecaller.data.models.Contact;

import java.util.List;

public interface ContactService {

    void addContact(String firstName, String lastName, String phoneNumber);

    Contact findById(int i);

    List<Contact> findByName(String lastName);

    Contact findByPhoneNumber(String phoneNumber);
}
