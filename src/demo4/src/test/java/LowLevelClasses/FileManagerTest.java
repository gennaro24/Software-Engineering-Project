package LowLevelClasses;



import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    @Test
    void testLoadFileNotExists() {
        FileManager fm = new FileManager();
        ContactList list = fm.loadFile("null.json");
        assertNotNull(list);
        assertTrue(list.getContacts().isEmpty());
    }

    @Test
    void testLoadFileEmpty() throws IOException {
        File temp = Files.createTempFile("empty", ".json").toFile();
        FileManager fm = new FileManager();
        ContactList list = fm.loadFile(temp.getAbsolutePath());
        assertNotNull(list);
        assertTrue(list.getContacts().isEmpty());
    }

    @Test
    void testLoadFileValid() throws IOException {
        File temp = Files.createTempFile("valid", ".json").toFile();
        // Scriviamo un JSON valido con 1 contatto
        String json = """
                {
                  "contacts": [
                    {
                      "name": "Giuseppe",
                      "surname": "Bianchi",
                      "emails": ["example@gmail.it"],
                      "numbers": []
                    }
                  ]
                }
                """;
        try (FileWriter fw = new FileWriter(temp)) {
            fw.write(json);
        }

        FileManager fm = new FileManager();
        ContactList list = fm.loadFile(temp.getAbsolutePath());
        assertNotNull(list);
        assertEquals(1, list.getContacts().size());
        assertEquals("Giuseppe", list.getContacts().get(0).getName());
    }

    @Test
    void testLoadFileFromResource() {
        FileManager fm = new FileManager();
        ContactList list = fm.loadFileFromResource();
        assertNotNull(list);

    }
}

