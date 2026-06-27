package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Contact.Contact;
import Contact.ContactService;

public class ContactServiceTest {
    private ContactService service;
    private Contact validContact;

    @BeforeEach
    public void setUp() {
        service = new ContactService();
        validContact = new Contact("123", "Jane", "Smith", "0987654321", "456 Elm St");
    }

    @Test
    public void testAddContactSuccess() {
        service.addContact(validContact);
        assertEquals(validContact, service.getContact("123"));
    }

    @Test
    public void testAddDuplicateContactIdFails() {
        service.addContact(validContact);
        Contact duplicateContact = new Contact("123", "Bob", "Jones", "5555555555", "789 Pine St");
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(duplicateContact);
        });
    }

    @Test
    public void testDeleteContactSuccess() {
        service.addContact(validContact);
        service.deleteContact("123");
        assertNull(service.getContact("123"));
    }

    @Test
    public void testDeleteContactNotFoundFails() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact("999");
        });
    }

    @Test
    public void testUpdateContactSuccess() {
        service.addContact(validContact);
        service.updateContact("123", "Alice", "Brown", "1112223333", "789 Oak Rd");
        
        Contact updated = service.getContact("123");
        assertEquals("Alice", updated.getFirstName());
        assertEquals("Brown", updated.getLastName());
        assertEquals("1112223333", updated.getPhone());
        assertEquals("789 Oak Rd", updated.getAddress());
    }

    @Test
    public void testUpdateContactWithInvalidDataFails() {
        service.addContact(validContact);
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("123", "NameTooLongString", "Brown", "1112223333", "789 Oak Rd");
        });
    }

    @Test
    public void testUpdateContactNotFoundFails() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("999", "Alice", "Brown", "1112223333", "789 Oak Rd");
        });
    }
}