package LowLevelClasses;

import java.util.Iterator;

public class ContactChecker {
    //controllo delle e-mail se almeno un campo contiene una mail.
    public static boolean checkEmail(Contact c){
        String pattern =
                "^[A-Za-z0-9]" +    //prima della chiocciola ci deve essere almeno un carattere alfanumerico
                "+@" +              //deve essere presente una chiocciola
                "[A-Za-z0-9]+" +    //dopo la chiocciola deve essere presente il mail agent
                "\\.[A-Za-z]{2,}$"; // dopo il mail agent deve essere presente il dominio di almeno 2 caratteri (.it , .com , ..)
        Iterator<String> it = c.getEmails().iterator();
        while (it.hasNext()){
           String email = it.next();
           if (!email.matches(pattern))
               return false;
        }
        return true;
    }

    public static boolean checkNumber(Contact c){
        String pattern = "^[0-9]{3,15}$"; // pattern che matcha da 3 a 15 cifre.
        Iterator<String> it = c.getEmails().iterator();
        while (it.hasNext()){
            String number = it.next();
            if (!number.matches(pattern))
                return false;
        }
        return true;
    }

    public static boolean checkNameSurname(Contact c){
        String name = c.getName();
        String surname = c.getSurname();

        return (name.trim().isEmpty() && surname.trim().isEmpty());


    }
}
