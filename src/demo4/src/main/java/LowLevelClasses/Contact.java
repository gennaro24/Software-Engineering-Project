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
        int result = this.name.compareToIgnoreCase(o.getName());
        if (result == 0) {
            result = this.surname.compareToIgnoreCase(o.getSurname());
        }
        return result;
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

