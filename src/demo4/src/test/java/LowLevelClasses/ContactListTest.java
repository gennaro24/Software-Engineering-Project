package LowLevelClasses;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @Author Foschillo Gennaro
 **/
public class ContactListTest {
    @DisplayName("test costruttore di default")
    @Test
    void testDefaultConstructor() {
        ContactList cl = new ContactList();
        assertNotNull(cl.getContacts());
        assertTrue(cl.getContacts().isEmpty());
    }
    @DisplayName("test di ordinamento corretto (Alfabetico)")
    @Test
    void testConstructorWithSorting() {
        ArrayList<Contact> unsorted = new ArrayList<>();
        unsorted.add(new Contact("Mario", "Rossi", new ArrayList<>(), new ArrayList<>()));
        unsorted.add(new Contact("Anna", "Bianchi", new ArrayList<>(), new ArrayList<>()));
        unsorted.add(new Contact("Zoe", "Verdi", new ArrayList<>(), new ArrayList<>()));

        ContactList cl = new ContactList(unsorted);
        List<Contact> result = cl.getContacts();

        // Verifica che siano stati ordinati da compareTo (Bianchi < Rossi < Verdi)
        assertEquals("Anna", result.get(0).getName());
        assertEquals("Bianchi", result.get(0).getSurname());
        assertEquals("Mario", result.get(1).getName());
        assertEquals("Rossi", result.get(1).getSurname());
        assertEquals("Zoe", result.get(2).getName());
        assertEquals("Verdi", result.get(2).getSurname());
    }
    @DisplayName("testing corretto reference dei contatti")
    @Test
    void testGetContactsReference() {
        ArrayList<Contact> original = new ArrayList<>();
        original.add(new Contact("Mario", "Rossi", new ArrayList<>(), new ArrayList<>()));
        ContactList cl = new ContactList(original);

        List<Contact> retrieved = cl.getContacts();
        retrieved.add(new Contact("Zoe", "Verdi", new ArrayList<>(), new ArrayList<>()));
        assertEquals(2, cl.getContacts().size());
    }
    @DisplayName("test fixList #1: numero di email > 3 ")
    @Test
    void testFixList_ManyEmails () {
        ArrayList<Contact> wrongList = new ArrayList<>();
        ArrayList<String> wrongMails = new ArrayList<>();
        for (int i = 0 ; i < 4 ; i++)
            wrongMails.add("example@gmail.it");
        Contact c = new Contact("Mario" , "Rossi"  , wrongMails , new ArrayList<>() );
        wrongList.add(c);
        ContactList cl = new ContactList(wrongList);
        assertTrue(cl.getContacts().isEmpty());
    }
    @DisplayName("test fixList #2: email sbagliata ")
    @Test
    void testFixList_WrongEmails () {
        ArrayList<Contact> wrongList = new ArrayList<>();
        ArrayList<String> wrongMails = new ArrayList<>();
        wrongMails.add("example@gmail.com");
        wrongMails.add("X"); // errata
        Contact c = new Contact("Mario" , "Rossi"  , wrongMails , new ArrayList<>() );
        wrongList.add(c);
        ContactList cl = new ContactList(wrongList);
        assertTrue(cl.getContacts().isEmpty());
    }
    @DisplayName("test fixList #3: numeri > 3 ")
    @Test
    void testFixList_ManyNumbers () {
        ArrayList<Contact> wrongList = new ArrayList<>();
        ArrayList<String> wrongNumbers = new ArrayList<>();
        for (int i = 0 ; i < 4 ; i++)
            wrongNumbers.add("1111");
        Contact c = new Contact("Mario" , "Rossi"  , new ArrayList<>() , wrongNumbers );
        wrongList.add(c);
        ContactList cl = new ContactList(wrongList);
        assertTrue(cl.getContacts().isEmpty());
    }
    @DisplayName("test fixList #4: numero sbagliato ")
    @Test
    void testFixList_WrongNumbers () {
        ArrayList<Contact> wrongList = new ArrayList<>();
        ArrayList<String> wrongNumbers = new ArrayList<>();
        wrongNumbers.add("119");
        wrongNumbers.add("test"); // sbagliato
        Contact c = new Contact("Mario" , "Rossi"  , new ArrayList<>() , wrongNumbers );
        wrongList.add(c);
        ContactList cl = new ContactList(wrongList);
        assertTrue(cl.getContacts().isEmpty());
    }
}
