package Contact;

import java.util.HashMap;
import java.util.Map;

public class ContactService {
    // In-memory data structure to hold contacts keyed by their unique contactId
    private final Map<String, Contact> contacts = new HashMap<>();

    // Adds a contact only if the ID is unique
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null.");
        }
        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException("Contact ID already exists.");
        }
        contacts.put(contact.getContactId(), contact);
    }

    // Deletes a contact matching the given ID
    public void deleteContact(String contactId) {
        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID not found.");
        }
        contacts.remove(contactId);
    }

    // Updates contact fields matching the given ID
    public void updateContact(String contactId, String firstName, String lastName, String phone, String address) {
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found.");
        }
        
        // Mutators automatically trigger the verification logic inside Contact.java
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPhone(phone);
        contact.setAddress(address);
    }

    // Helper getter for unit testing purposes
    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }
}