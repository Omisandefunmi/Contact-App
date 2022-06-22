package africa.semicolon.truecaller.services;

import africa.semicolon.truecaller.data.models.Contact;

import java.util.List;

public interface ContactService {

    void addContact(String firstName, String lastName, String phoneNumber);

    Contact findById(int id);

    List<Contact> findByName(String name);

    Contact findByPhoneNumber(String phoneNumber);

    Contact deleteByFirstNameAndPhoneNumber(String firstName, String phoneNumber);

    int phoneBookSize();

    Contact deleteByPhoneNumber(String phoneNumber);
}
