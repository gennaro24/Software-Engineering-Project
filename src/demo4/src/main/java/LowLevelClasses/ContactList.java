package LowLevelClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ContactList implements Serializable {
    private ArrayList<Contact> contacts;

    public ContactList(){
        contacts = new ArrayList<>();
    }
    public ContactList(ArrayList<Contact> contacts ){
        this.contacts = contacts;
        contacts.sort(null);
    }
    public ArrayList<Contact> getContacts() {
        return contacts;
    }

}
