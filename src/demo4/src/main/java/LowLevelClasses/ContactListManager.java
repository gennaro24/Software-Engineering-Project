package LowLevelClasses;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
/**
 * fornisce metodi statici per la gestione
 * e la manipolazione di liste di contatti.
 * Include metodi per effettuare ricerche sui contatti e per aggiornare un contatto
 * presente all'interno di una lista osservabile.
 * </p>
 *
 * @author Foschillo
 */
public class ContactListManager {

    /**
     * Cerca contatti che contengono la query specificata nel nome o nel cognome.
     * <p>
     * Il metodo filtra la lista dei contatti confrontando (in modo case-insensitive) la concatenazione
     * del nome e del cognome con la query fornita. La lista risultante viene poi ordinata in ordine naturale.
     * </p>
     *
     * @param contacts La lista di contatti in cui effettuare la ricerca.
     * @param query    La stringa (o sottostringa) da cercare. Se {@code null}, viene restituita la lista originale.
     * @return Una lista di contatti che corrispondono alla query, ordinata alfabeticamente.
     */
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
    /**
     * Aggiorna un contatto esistente in una lista osservabile.
     * Se il contatto {@code a} è presente nella lista, viene sostituito dal nuovo contatto {@code b}.
     * @param contacts La lista osservabile dei contatti.
     * @param a        Il contatto da sostituire.
     * @param b        Il nuovo contatto da inserire al posto di {@code a}.
     */

    public static void updateContact (ObservableList<Contact> contacts, Contact a , Contact b){
        int index;
        if (contacts.contains(a) ) {
            index = contacts.indexOf(a);
            contacts.set(index , b);
        }
    }
}
