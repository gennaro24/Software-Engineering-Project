package LowLevelClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Astrazione di una lista di contatti. classe Wrapper di {@link Contact}
 * <p>
 * Questa classe gestisce un insieme di Contatti e fornisce
 * metodi per filtrare e ordinare i contatti in base a determinati criteri.
 * </p>
 *
 * @author Foschillo Gennaro
 */
public class ContactList implements Serializable {
    private final ArrayList<Contact> contacts;
    /**
     * Costruttore che inizializza l'attributo {@link #contacts }
     */
    public ContactList(){
        contacts = new ArrayList<>();
    }

    /**
     * Costruttore che inizializza la lista con i contatti passati come parametro.
     * <p>
     * Viene invocato il metodo {@link #fixList(ArrayList)} per verificare e filtrare
     * i contatti in base ai requisiti, dopodiché la lista viene ordinata in ordine naturale.
     * </p>
     *
     * @param ct La lista di contatti da validare e utilizzare per l'inizializzazione.
     */
    public ContactList(ArrayList<Contact> ct ){
        contacts = fixList(ct);
        contacts.sort(null);
    }
    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    /**
     * Verifica e corregge la lista di contatti passata come parametro.
     * <p>
     * Per ogni contatto viene effettuato il controllo:
     * <ul>
     *   <li>Il nome e il cognome vengono validati tramite {@link ContactChecker#checkNameSurname(String, String)}.
     *       Se il controllo fallisce, il contatto viene rimosso.</li>
     *   <li>La validità degli indirizzi email viene controllata: se il numero di email è maggiore di 3 o
     *       almeno una email non rispetta il formato corretto (verificato tramite {@link ContactChecker#isValidEmail(String)}),
     *       il contatto viene rimosso.</li>
     *   <li>Viene applicata una logica analoga per la lista dei numeri telefonici.</li>
     * </ul>
     * </p>
     *
     * @param ct La lista di contatti da verificare.
     * @return La lista di contatti validi.
     */
    public ArrayList<Contact> fixList(ArrayList<Contact> ct){
        Iterator<Contact> it = ct.iterator();
        while (it.hasNext()){
            int error = 0;
            Contact c = it.next();
            String name = c.getName();
            String surname = c.getSurname();
            ArrayList<String> emails = c.getEmails();
            ArrayList<String> numbers = c.getNumbers();
            if (ContactChecker.checkNameSurname(name , surname)){
                it.remove();
                continue;
            }
            if (!(emails.size() > 3)){
                for (String s : emails){
                    if (!ContactChecker.isValidEmail(s)){
                        error++;
                    }
                }
                if (error > 0){it.remove(); continue;}
            }else {it.remove(); continue;}

            if (!(numbers.size() > 3)){
                for (String s : numbers){
                    if (!ContactChecker.isValidNumber(s)){
                        error++;
                    }
                }
                if (error > 0){it.remove();}
            }else {it.remove();}

        }
        return ct;
    }
}
