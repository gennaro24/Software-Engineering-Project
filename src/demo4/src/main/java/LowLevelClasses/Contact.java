package LowLevelClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class Contact implements Serializable , Comparable<Contact> {
    private String name;
    private String surname;
    private ArrayList<String> emails;
    private ArrayList<String> numbers;
    public Contact(){}
    public Contact (String name , String surname , ArrayList<String> email , ArrayList<String> number ){
        this.name = name;
        this.surname = surname;
        this.emails = email;
        this.numbers = number;
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

