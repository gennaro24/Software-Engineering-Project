package LowLevelClasses;

/**
 * fornisce i metodi utili per validare i campi relativi ad un oggetto {@link Contact}
 * @Author Foschillo Gennaro
 */
public class ContactChecker {
    /**
     * Il metodo controlla se una Stringa relativa alle email di un {@link Contact} è valida attraverso il matching di una regex.
     * La logica di validazione è la seguente:
     *<ul>
     *  <li>la prima parte della mail deve contenere almeno un carattere alfanumerico. sono consentiti i caratteri: (. _)</li>
     *  <li>Deve contenere una sola chiocciola tra le due parti della mail.</li>
     *  <li>la seconda parte della mail deve essere composta da una parte di mail agent separata da . dalla parte del dominio</li>
     *  <li>la parte di domino deve contenere almeno due caratteri.</li>
     *</ul>
     * </p>
     * @param e una Stringa che rappresenta la mail da validare.
     * @return un valore booleano che è true se la mail è valida, false altrimenti.
     */
    public static boolean isValidEmail(String e){
        String pattern ="^[A-Za-z0-9._]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
            return e.matches(pattern);
    }
    /**
     * Il metodo controlla se una Stringa relativa ai numeri di un {@link Contact} è valida attraverso il matching di una regex.
     * La logica di validazione è la seguente:
     *<ul>
     *  <li>la stringa deve contenere solo numeri</li>
     *  <li>deve avere una lunghezza compresa tra 3 e 15 caratteri.</li>
     *</ul>
     * </p>
     * @param n una Stringa che rappresenta il numero da validare.
     * @return un valore booleano che è true se la mail è valida, false altrimenti.
     */
    public static boolean isValidNumber(String n){
        String pattern = "^[0-9]{3,15}$";
        return n.matches(pattern);
    }
    /**
     * Il metodo controlla se le stringhe relative al nome e cognome {@link  Contact} sono valide per l'inserimento nella {@link  ContactList}.
     * La logica di validazione è la seguente:
     *<ul>
     *  <li>Deve essere presente almeno un campo tra nome e cognome</li>
     *  <li>Se un campo contiene spazi , " o ' viene considerato vuoto</li>
     *</ul>
     * </p>
     * @param name una String che rappresenta il nome.
     * @param surname una String che rappresenta il cognome.
     * @return un valore booleano che è true se le due stringhe sono vuote.
     */
    public static boolean checkNameSurname(String name , String surname){
        String cleanedName = name.replaceAll("[\"']" , "").trim();
        String cleanedSurname = surname.replaceAll("[\"']" , "").trim();
        return (cleanedName.isEmpty() && cleanedSurname.isEmpty());
    }

}
