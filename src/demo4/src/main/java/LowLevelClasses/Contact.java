package LowLevelClasses;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String name;
    private String surname;
    private List<String> emails;
    private List<String> numbers;
    public Contact (String name , String surname , ArrayList<String> email , ArrayList<String> number ){
        this.name = name;
        this.surname = surname;
        this.emails = email;
        this.numbers = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }
}
