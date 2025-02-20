package LowLevelClasses;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ContactListManager {

    /**
       * Il metodo consente di cercare un contatto attraverso una query formata da una stringa o sottostringa
       * del contatto da cercare. Questo viene effettuato attraverso l'utilizzo della StreamAPI, che partendo da una lista
       * di contatti già esistente (contacts) , produce uno stream e lo filtra controllando ogni elemento della lista per
       * nome e cognome. Se in quell'elemento è presente la query, viene inserito in una nuova lista, che viene ritornata
       * ordinata alfabeticamente.
     **/
    public static ArrayList<Contact> searchContact(ArrayList<Contact> contacts ,String query){
        if (query == null)
            return contacts;

        List<Contact> sc = contacts.stream()
                .filter(c -> (c.getName() + c.getSurname()).toLowerCase().contains(query.toLowerCase()))
                .toList();
        ArrayList<Contact> search = new ArrayList<>(sc);
        search.sort(null);
        return search;
    }
    //questo metodo sovrascrive il contatto vecchio (a) con il nuovo contatto (b), controllando prima se è presente.

    public static void updateContact (ObservableList<Contact> contacts, Contact a , Contact b){
        int index;
        if (contacts.contains(a) ) {
            index = contacts.indexOf(a);
            contacts.set(index , b);
        }
    }
}
