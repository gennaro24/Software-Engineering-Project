package LowLevelClasses;

import java.util.ArrayList;
import java.util.List;


public class ContactList {
    private ArrayList<Contact> contacts;

    public ContactList(){
        contacts = new ArrayList<>();
        contacts.sort(null);
    }
    public ContactList(ArrayList<Contact> contacts ){
        this.contacts = contacts;
        contacts.sort(null);
    }
   /*
   Il metodo consente di cercare un contatto attraverso una query formata da una stringa o sottostringa
   del contatto da cercare. Questo viene effettuato attraverso l'utilizzo della StreamAPI, che partendo da una lista
   di contatti già esistente (contacts) , produce uno stream e lo filtra controllando ogni elemento della lista per
   nome e cognome. Se in quell'elemento è presente la query, viene inserito in una nuova lista, che viene ritornata
   ordinata alfabeticamente.
    */
    public ArrayList<Contact> searchContact(String query){
        List<Contact> sc = contacts.stream()
                .filter(c -> (c.getName() + c.getSurname()).toLowerCase().contains(query.toLowerCase()))
                .toList();
        ArrayList<Contact> search = new ArrayList<>(sc);
        search.sort(null);
        return search;
    }
    public void updateContact (Contact a , Contact b){
        
    }

}
