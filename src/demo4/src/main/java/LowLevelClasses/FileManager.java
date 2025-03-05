package LowLevelClasses;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;

import java.io.*;
/**
 * gestisce il caricamento e il salvataggio di una lista di contatti
 * in formato JSON.
 * <p>
 * Utilizza la libreria {@link com.google.gson.Gson} per la serializzazione e la deserializzazione degli oggetti
 * {@link ContactList}.
 * </p>
 * @Author Foschillo Gennaro
 **/
public class FileManager {
private static final String FILE_PATH = "src/main/resources/contact.json";
private File f;
private final Gson gson;
/**
 * costruttore vuoto per inizializzare l'attributo {@link #gson}
 * */
public FileManager(){
    gson = new GsonBuilder().setPrettyPrinting().create();
}

    /**
     * Carica il file JSON dal percorso specificato e restituisce una {@link ContactList}.
     * <p>
     * Se il file non esiste o è vuoto, viene restituita una lista vuota. Durante il caricamento,
     * il contenuto del file viene letto, stampato a console e, successivamente, la lista viene
     * salvata nuovamente per aggiornare eventuali modifiche.
     * </p>
     *
     * @param filepath Il percorso del file da caricare.
     * @return Una {@link ContactList} contenente i contatti caricati, oppure una lista vuota
     *         se il file non esiste o è vuoto.
     */
    public ContactList loadFile(String filepath){
        f = new File(filepath);
        if (!f.exists() || f.length() == 0) {
            return new ContactList();
        }

        try {
             String fileContent = printFile();
            ContactList contacts = new ContactList(gson.fromJson(fileContent, ContactList.class).getContacts());
            if (contacts.getContacts() == null) {
                contacts = new ContactList();
            }

            return contacts;
        } catch (JsonSyntaxException | MalformedJsonException e) {
            System.out.println("file con formato json non valido");
            return new ContactList();
        }catch (IOException e){e.printStackTrace();}
        return new ContactList();
    }
    /**
     * Carica il file JSON presente tra le risorse del progetto e restituisce una {@link ContactList}.
     * <p>
     * Se il file {@code contact.json} non viene trovato tra le risorse, viene restituita una lista vuota.
     * Se il file presenta contatti con campi non conformi ai requisiti, viene restituita una lista con i soli contatti validi.
     * Dopo il caricamento, la lista viene salvata per aggiornare eventuali modifiche.
     * </p>
     *
     * @return Una {@link ContactList} contenente i contatti caricati, oppure una lista vuota in caso di errore.
     */
    public ContactList loadFileFromResource() {
        InputStream is = getClass().getResourceAsStream("/contact.json");
        if (is == null) {
            System.out.println("Il file contact.json non è stato trovato come risorsa.");
            return new ContactList();
        }
        try (Reader reader = new InputStreamReader(is)) {
            ContactList contacts = new ContactList(gson.fromJson(reader, ContactList.class).getContacts()) ;
            if (contacts.getContacts() == null) {
                return new ContactList();
            }

            return contacts;
        } catch (JsonSyntaxException | MalformedJsonException  e) {
            return new ContactList();
        }catch (IOException e ){e.printStackTrace();}
        return new ContactList();
    }

    /**
     * Salva la {@link ContactList} in un file JSON.
     * <p>
     * Il file viene salvato nel percorso specificato dalla costante {@code FILE_PATH}.
     * </p>
     *
     * @param contactList La lista di contatti da salvare.
     */
    public void saveFile(ContactList contactList) {
         f = new File(FILE_PATH);
        try (Writer writer = new FileWriter(f)) {
            gson.toJson(contactList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String printFile() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        String fileContent = sb.toString();
        System.out.println("Contenuto del file: " + fileContent);
        return fileContent;
    }
}

