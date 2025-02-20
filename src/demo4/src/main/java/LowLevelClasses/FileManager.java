package LowLevelClasses;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
public class FileManager {
private static final String FILE_PATH = "src/main/resources/contact.json";
private File f;
private Gson gson;

/**@Author Foschillo Gennaro**/
public FileManager(){
    gson = new GsonBuilder().setPrettyPrinting().create();
}
    public ContactList loadFile(String filepath) {
        f = new File(filepath);
        System.out.println("Sto cercando il file: " + f.getAbsolutePath());
        System.out.println("Esiste? " + f.exists());
        System.out.println("Dimensione del file: " + f.length());

        if (!f.exists() || f.length() == 0) {
            System.out.println("File non trovato o vuoto. Restituisco una lista vuota.");
            return new ContactList();
        }

        try {
            // Legge l'intero contenuto in una stringa per controllarlo
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            String fileContent = sb.toString();
            System.out.println("Contenuto del file: " + fileContent);

            ContactList contacts = gson.fromJson(fileContent, ContactList.class);
            if (contacts.getContacts() == null) {
                System.out.println("La deserializzazione ha restituito null per i contatti.");
                // Forza la creazione di una lista vuota
                contacts = new ContactList();
            }
            return contacts;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return new ContactList();
    }

    public ContactList loadFileFromResource(){
        InputStream is = getClass().getResourceAsStream("/contact.json");
        if (is == null) {
            System.out.println("Il file contact.json non è stato trovato come risorsa.");
            return new ContactList();
        }
        try (Reader reader = new InputStreamReader(is)) {
            ContactList contacts = gson.fromJson(reader, ContactList.class);
            if (contacts.getContacts() == null) {
                contacts = new ContactList();
            }
            return contacts;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return new ContactList();
    }


    public void saveFile(ContactList contactList) {
         f = new File(FILE_PATH);
        try (Writer writer = new FileWriter(f)) {
            gson.toJson(contactList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

