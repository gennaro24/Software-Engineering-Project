package LowLevelClasses;

import java.util.ArrayList;

public class ContactChecker {
    //controllo delle e-mail se almeno un campo contiene una mail.
    public static boolean isValidEmail(String e){
        String pattern ="^[A-Za-z0-9._]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        //prima della chiocciola ci deve essere almeno un carattere alfanumerico o caratteri consentiti (. _ )
        //deve essere presente una chiocciola
        //dopo la chiocciola deve essere presente il mail agent
        // dopo il mail agent deve essere presente il dominio di almeno 2 caratteri (.it , .com , ..)

            if (!e.matches(pattern))
                return false;
        return true;
    }

    public static boolean isValidNumber(String n){
        String pattern = "^[0-9]{3,15}$"; // pattern che matcha da 3 a 15 cifre.
        return n.matches(pattern);
    }

    public static boolean checkNameSurname(String name , String surname){
        return (name.trim().isEmpty() && surname.trim().isEmpty());
    }
}
