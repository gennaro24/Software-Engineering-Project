package LowLevelClasses;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.util.ArrayList;

/**
 * @Author Foschillo Gennaro
 **/

public class ContactListManagerTest {

    @Test
    @DisplayName("test con query nulla")
    void testSearchContactNullQuery() {
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Mario", "Rossi", new ArrayList<>(), new ArrayList<>()));
        contacts.add(new Contact("Anna", "Bianchi", new ArrayList<>(), new ArrayList<>()));

        ArrayList<Contact> result = ContactListManager.searchContact(contacts, null);
        assertEquals(2, result.size());
        assertTrue(result.contains(contacts.get(0)));
        assertTrue(result.contains(contacts.get(1)));
    }

    @Test
    @DisplayName("test con query vuota")
    void testSearchContactEmptyQuery() {
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Mario", "Rossi", new ArrayList<>(), new ArrayList<>()));
        contacts.add(new Contact("Anna", "Bianchi", new ArrayList<>(), new ArrayList<>()));

        ArrayList<Contact> result = ContactListManager.searchContact(contacts, "");
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("test con query corrispondenti")
    void testSearchContactMatches() {
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Mario", "Rossi", new ArrayList<>(), new ArrayList<>()));
        contacts.add(new Contact("Anna", "Bianchi", new ArrayList<>(), new ArrayList<>()));
        contacts.add(new Contact("Marina", "Verdi", new ArrayList<>(), new ArrayList<>()));

        ArrayList<Contact> result = ContactListManager.searchContact(contacts, "Mari");
        assertEquals(2, result.size());
        assertTrue(result.get(0).getName().contains("Mari") || result.get(0).getSurname().contains("Mari"));
        assertTrue(result.get(1).getName().contains("Mari") || result.get(1).getSurname().contains("Mari"));
    }

    @Test
    @DisplayName("test con query non corrispondente")
    void testSearchContactNoMatches() {
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Mario", "Rossi", new ArrayList<>(), new ArrayList<>()));
        contacts.add(new Contact("Anna", "Bianchi", new ArrayList<>(), new ArrayList<>()));

        ArrayList<Contact> result = ContactListManager.searchContact(contacts, "xyz");
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("test update con contatto contenuto")
    void testUpdateContactContains() {
        ObservableList<Contact> obs = FXCollections.observableArrayList();
        obs.add(new Contact("Mario", "Rossi", new ArrayList<>(), new ArrayList<>()));
        obs.add(new Contact("Anna", "Bianchi", new ArrayList<>(), new ArrayList<>()));

        Contact oldC = obs.get(0);
        Contact newC = new Contact("Marco", "Rossi", new ArrayList<>(), new ArrayList<>());
        ContactListManager.updateContact(obs, oldC, newC);

        assertEquals("Marco", obs.get(0).getName());
        assertEquals(2, obs.size());
    }

    @Test
    @DisplayName("test update con contatto non contenuto")
    void testUpdateContactNotContains() {
        ObservableList<Contact> obs = FXCollections.observableArrayList();
        Contact c1 = new Contact("Mario", "Rossi", new ArrayList<>(), new ArrayList<>());
        Contact c2 = new Contact("Anna", "Bianchi", new ArrayList<>(), new ArrayList<>());
        obs.add(c1);

        ContactListManager.updateContact(obs, c2, new Contact("X", "Y", new ArrayList<>(), new ArrayList<>()));
        assertEquals(1, obs.size());
        assertEquals("Mario", obs.get(0).getName());
    }
}
