package LowLevelClasses;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * rappresenta un contatto con nome, cognome, email e numeri telefonici.
 * <p>
 * Viene utilizzata per gestire le informazioni base di un contatto e implementa:
 * l'interfaccia {@link Comparable} per poter essere ordinata in base a cognome e nome.
 * l'interfaccia {@link Serializable} per la corretta serializzazione attraverso {@link com.google.gson.Gson}
 * </p>
 *
 * @author Foschillo Gennaro
 */
public class Contact implements Serializable , Comparable<Contact> {
    private String name;
    private String surname;
    private ArrayList<String> emails;
    private ArrayList<String> numbers;
    /**
     * Il costruttore vuoto è utilizzato dalla libreria {@link com.google.gson.Gson} per la serializzazione degli oggetti.
     */
    public Contact(){}
    /**
     * Il costruttore con parametri permette di inizializzare i campi relativi ad un contatto.
     * @param name nome del contatto.
     * @param surname cognome del contatto.
     * @param emails ArrayList di String contenente le email del contatto.
     * @param numbers ArrayList di String contenente i numeri del contatto.
     */
    public Contact (String name , String surname , ArrayList<String> emails , ArrayList<String> numbers ){
        this.name = name;
        this.surname = surname;
        this.emails = emails;
        this.numbers = numbers;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public ArrayList<String> getEmails() {
        return emails;
    }
    public ArrayList<String> getNumbers() {
        return numbers;
    }

    /**
     * Confronta questo contatto con un altro contatto per determinare l'ordine alfabetico.
     * <p>
     * La logica di confronto è la seguente:
     * <ul>
     *   <li>Se entrambi i cognomi sono non vuoti, vengono confrontati in ordine alfabetico.</li>
     *   <li>Se i cognomi sono uguali, vengono confrontati i nomi.</li>
     *   <li>Se uno dei campi (nome o cognome) è vuoto, il contatto con il campo non vuoto viene considerato "minore".</li>
     *   <li>Se il confronto tra cognomi e nomi non restituisce una differenza, i contatti sono considerati uguali.</li>
     * </ul>
     * </p>
     *
     * @param o L'altro contatto da confrontare.
     * @return Un valore negativo se questo contatto è "minore", zero se sono uguali, o un valore positivo se questo contatto è "maggiore".
     */
    @Override
    public int compareTo(Contact o) {
        boolean thisEmptyName = this.getName().trim().isEmpty();
        boolean thisEmptySurname = this.getSurname().trim().isEmpty();
        boolean oEmptyName = o.getName().trim().isEmpty();
        boolean oEmptySurname = o.getSurname().trim().isEmpty();

        boolean bothEmptySurname = oEmptySurname && thisEmptySurname;
        boolean bothNotEmptySurname = !oEmptySurname && !thisEmptySurname;
        boolean bothNotEmptyName = !oEmptyName && !thisEmptyName;
        boolean bothEmptyName = oEmptyName && thisEmptyName;

        boolean bothSurnameEquals = ( o.getSurname().trim().compareToIgnoreCase(this.getSurname().trim()) ) == 0;
        boolean bothNameEquals = ( o.getName().trim().compareToIgnoreCase(this.getName().trim()) ) == 0;

        int compareSurname = this.getSurname().trim().compareToIgnoreCase(o.getSurname().trim());
        int compareName = this.getName().trim().compareToIgnoreCase(o.getName().trim());


        if (bothNotEmptySurname){ // se i cognomi non sono vuoti ->
            if (!bothSurnameEquals){  //controlla se i cognomi non sono uguali .
                return compareSurname; //se non sono uguali , li compara
            }else if (bothNotEmptyName){ // se i cognomi sono uguali , ->
                if (!bothNameEquals){ //  controlla se i nomi non sono uguali
                    return compareName; // se non sono uguali, li compara
                } else return 0;   // sennò ritorna 0.
            }else if (bothEmptyName){
                return 0;
            }else if (oEmptyName) return -1;
            else return 1;

        }else if (bothEmptySurname){ // se sono entrambi vuoti
            if (bothNotEmptyName){ // se i nomi non sono vuoti
                if (!bothNameEquals){ // se i nomi non sono uguali
                    return compareName;
                } else return 0;
            }else if (thisEmptyName){
                return 1;
            }else return -1;
        }else if (thisEmptySurname){ // se cognome this è vuoto
            return 1;
        }else return -1;

    }
    /**
     * Override del metodo toString che restituisce una rappresentazione testuale del contatto.
     * @return una stringa con le informazioni del contatto.
     * */
    @Override
    public String toString(){
        StringBuffer s = new StringBuffer("emails:");
        StringBuffer n = new StringBuffer("numeri: ");
        for (String p : numbers)
            n.append(p + "\n");
        for (String e : emails)
            s.append(e + "\n");
        return name + " " + surname + "\n" + s + "\n" + n + "\n";
    }
}

