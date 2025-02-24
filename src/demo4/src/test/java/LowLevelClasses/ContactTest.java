package LowLevelClasses;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @Author Foschillo Gennaro
 **/
class ContactTest {

    private Contact testedContact;
    private Contact LeftContact;
    private Contact RightContact;

    void compareToIstanceL (String name , String surname){
        LeftContact = new Contact(name , surname , null , null);
    }
    void compareToIstanceR (String name , String surname){
        RightContact = new Contact(name , surname , null , null);
    }
    @BeforeEach
    void setup() {
        // Creiamo un contatto di base per i test
        ArrayList<String> emails = new ArrayList<>(List.of("mario.rossi@example.com"));
        ArrayList<String> numbers = new ArrayList<>(List.of("333111222"));
        testedContact = new Contact("B", "B", emails, numbers);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("B", testedContact.getName());
        assertEquals("B", testedContact.getSurname());
        assertEquals(1, testedContact.getEmails().size());
        assertEquals("mario.rossi@example.com", testedContact.getEmails().get(0));
        assertEquals(1, testedContact.getNumbers().size());
        assertEquals("333111222", testedContact.getNumbers().get(0));
    }
    @Test
    void testNullConstructorsAndGetters(){
       Contact nullContact = new Contact(null , null , null , null);
        assertNull(nullContact.getName());
        assertNull(nullContact.getSurname());
        assertNull(nullContact.getEmails());
        assertNull(nullContact.getNumbers());
    }

    //NOMI UGUALI - COGNOMI DIVERSI (-1)
    @Test
    void SameName_CompareTo1(){
        compareToIstanceL("B" , "B");
        compareToIstanceR("B" , "C");
        assertEquals(-1 , LeftContact.compareTo(RightContact) );
    }
    //NOMI UGUALI - COGNOMI DIVERSI (1)
    @Test
    void SameName_CompareTo2(){
        compareToIstanceL("B" , "B");
        compareToIstanceR("B" , "A");
        assertEquals(1 , LeftContact.compareTo(RightContact) );
    }
    //NOMI UGUALI - COGNOMI UGUALI (0)
    @Test
    void SameName_CompareTo3(){
        compareToIstanceL("B" , "B");
        compareToIstanceR("B" , "B");
        assertEquals(0 , LeftContact.compareTo(RightContact) );
    }
    //NOMI UGUALI - COGNOMI VUOTI (0)
    @Test
    void SameName_CompareTo4(){
        compareToIstanceL("B" , "");
        compareToIstanceR("B" , "");
        assertEquals(0 , LeftContact.compareTo(RightContact) );
    }
    //NOMI UGUALI - COGNOME VUOTO (-1)
    @Test
    void SameName_CompareTo5(){
        compareToIstanceL("B" , "B");
        compareToIstanceR("B" , "");
        assertEquals(-1 , LeftContact.compareTo(RightContact) );
    }
    //NOMI UGUALI - COGNOME VUOTO (1)
    @Test
    void SameName_CompareTo6(){
        compareToIstanceL("B" , "");
        compareToIstanceR("B" , "B");
        assertEquals(1 , LeftContact.compareTo(RightContact) );
    }
    //COGNOMI UGUALI - NOMI DIVERSI (1)
    @Test
    void SameSurname_CompareTo7(){
        compareToIstanceL("B" , "B");
        compareToIstanceR("A" , "B");
        assertEquals(1 , LeftContact.compareTo(RightContact) );
    }
    //COGNOMI UGUALI - NOMI DIVERSI (-1)
    @Test
    void SameSurname_CompareTo8(){
        compareToIstanceL("A" , "B");
        compareToIstanceR("B" , "B");
        assertEquals(-1 , LeftContact.compareTo(RightContact) );
    }
    //COGNOMI UGUALI - NOMI UGUALI (0)
    @Test
    void SameSurname_CompareTo9(){
        compareToIstanceL("B" , "B");
        compareToIstanceR("B" , "B");
        assertEquals(0 , LeftContact.compareTo(RightContact));
    }
    //COGNOMI UGUALI - NOMI VUOTI (0)
    @Test
    void SameSurname_CompareTo10(){
        compareToIstanceL("" , "B");
        compareToIstanceR("" , "B");
        assertEquals(0 , LeftContact.compareTo(RightContact));
    }
    //COGNOMI UGUALI - NOME VUOTO (1)
    @Test
    void SameSurname_CompareTo11(){
        compareToIstanceL("" , "B");
        compareToIstanceR("B" , "B");
        assertEquals(1 , LeftContact.compareTo(RightContact));
    }
    //COGNOMI UGUALI - NOME VUOTO (-1)
    @Test
    void SameSurname_CompareTo12(){
        compareToIstanceL("B" , "B");
        compareToIstanceR("" , "B");
        assertEquals(-1 , LeftContact.compareTo(RightContact));
    }

    //COGNOMI DIVERSI (1) - NOMI UGUALI (0)
    @Test
    void diffSurname_CompareTo13(){
        compareToIstanceL("B" , "B");
        compareToIstanceR("B" , "A");
        assertEquals(1 , LeftContact.compareTo(RightContact));
    }
    //COGNOMI DIVERSI (1) - NOMI DIVERSI (1)
    @Test
    void diffSurname_CompareTo14(){
        compareToIstanceL("B" , "B");
        compareToIstanceR("A" , "A");
        assertEquals(1 , LeftContact.compareTo(RightContact));
    }
    //COGNOMI DIVERSI (1) - NOMI DIVERSI (-1)
    @Test
    void diffSurname_CompareTo15(){
        compareToIstanceL("A" , "B");
        compareToIstanceR("B" , "A");
        assertEquals(1 , LeftContact.compareTo(RightContact));
    }
    //COGNOMI DIVERSI (1) - NOMI VUOTI
    @Test
    void diffSurname_CompareTo16(){
        compareToIstanceL("" , "B");
        compareToIstanceR("" , "A");
        assertEquals(1 , LeftContact.compareTo(RightContact));
    }
    //COGNOMI DIVERSI (1) - NOME VUOTO (1)
    @Test
    void diffSurname_CompareTo17(){
        compareToIstanceL("" , "B");
        compareToIstanceR("B" , "A");
        assertEquals(1 , LeftContact.compareTo(RightContact));
    }
    //COGNOMI DIVERSI (1) - NOME VUOTO (-1)
    @Test
    void diffSurname_CompareTo18(){
        compareToIstanceL("A" , "B");
        compareToIstanceR("" , "A");
        assertEquals(1 , LeftContact.compareTo(RightContact));
    }



    //COGNOMI DIVERSI (-1) - NOMI UGUALI (0)
    @Test
    void diffSurname_CompareTo19(){
        compareToIstanceL("B" , "A");
        compareToIstanceR("B" , "B");
        assertEquals(-1 , LeftContact.compareTo(RightContact));
    }
    //COGNOMI DIVERSI (-1) - NOMI DIVERSI (1)
    @Test
    void diffSurname_CompareTo20(){
        compareToIstanceL("B" , "A");
        compareToIstanceR("A" , "B");
        assertEquals(-1 , LeftContact.compareTo(RightContact));
    }
    //COGNOMI DIVERSI (-1) - NOMI DIVERSI (-1)
    @Test
    void diffSurname_CompareTo21(){
        compareToIstanceL("A" , "A");
        compareToIstanceR("B" , "B");
        assertEquals(-1 , LeftContact.compareTo(RightContact));
    }
    //COGNOMI DIVERSI (-1) - NOMI VUOTI
    @Test
    void diffSurname_CompareTo22(){
        compareToIstanceL("" , "A");
        compareToIstanceR("" , "B");
        assertEquals(-1 , LeftContact.compareTo(RightContact));
    }
    //COGNOMI DIVERSI (-1) - NOME VUOTO (1)
    @Test
    void diffSurname_CompareTo23(){
        compareToIstanceL("" , "A");
        compareToIstanceR("B" , "B");
        assertEquals(-1 , LeftContact.compareTo(RightContact));
    }
    //COGNOMI DIVERSI (-1) - NOME VUOTO (-1)
    @Test
    void diffSurname_CompareTo24(){
        compareToIstanceL("B" , "A");
        compareToIstanceR("" , "B");
        assertEquals(-1 , LeftContact.compareTo(RightContact));
    }



    // NOMI DIVERSI (-1) - COGNOME UGUALE
    @Test
    void diffName_CompareTo25(){
        compareToIstanceL("A", "X");
        compareToIstanceR("B", "X");
        assertEquals(-1, LeftContact.compareTo(RightContact));
    }

    // NOMI DIVERSI (-1) - COGNOME VUOTI
    @Test
    void diffName_CompareTo26(){
        compareToIstanceL("A", "");
        compareToIstanceR("B", "");
        assertEquals(-1, LeftContact.compareTo(RightContact));
    }

    // NOMI DIVERSI (-1) - COGNOMI DIVERSI (-1)
    @Test
    void diffName_CompareTo27(){
        compareToIstanceL("A", "A");
        compareToIstanceR("B", "B");
        assertEquals(-1, LeftContact.compareTo(RightContact));
    }

    // NOMI DIVERSI (-1) - COGNOMI DIVERSI (1)
    @Test
    void diffName_CompareTo28(){
        compareToIstanceL("A", "B");
        compareToIstanceR("B", "C"); // "B" < "C" ⇒ -1
        assertEquals(-1, LeftContact.compareTo(RightContact));
    }

    // NOMI DIVERSI (-1) - COGNOME VUOTO (1)
    @Test
    void diffName_CompareTo29(){
        compareToIstanceL("A", "");
        compareToIstanceR("B", "X");
        assertEquals(1, LeftContact.compareTo(RightContact));
    }

    // NOMI DIVERSI (-1) - COGNOME VUOTO (-1)
    @Test
    void diffName_CompareTo30(){
        compareToIstanceL("A", "X");
        compareToIstanceR("B", "");
        assertEquals(-1, LeftContact.compareTo(RightContact));
    }



    // NOMI DIVERSI (1) - COGNOME UGUALE
    @Test
    void diffName_CompareTo31(){
        compareToIstanceL("B", "X");
        compareToIstanceR("A", "X");
        assertEquals(1, LeftContact.compareTo(RightContact));
    }

    // NOMI DIVERSI (1) - COGNOME VUOTI
    @Test
    void diffName_CompareTo32(){
        compareToIstanceL("B", "");
        compareToIstanceR("A", "");
        assertEquals(1, LeftContact.compareTo(RightContact));
    }

    // NOMI DIVERSI (1) - COGNOMI DIVERSI (-1)
    @Test
    void diffName_CompareTo33(){
        compareToIstanceL("B", "B");
        compareToIstanceR("A", "A");
        assertEquals(1, LeftContact.compareTo(RightContact));
    }

    // NOMI DIVERSI (1) - COGNOMI DIVERSI (1)
    @Test
    void diffName_CompareTo34(){
        compareToIstanceL("B", "C");
        compareToIstanceR("A", "B");
        assertEquals(1, LeftContact.compareTo(RightContact));
    }

    // NOMI DIVERSI (1) - COGNOME VUOTO (1)
    @Test
    void diffName_CompareTo35(){
        compareToIstanceL("B", "");
        compareToIstanceR("A", "X");
        assertEquals(1, LeftContact.compareTo(RightContact));
    }

    // NOMI DIVERSI (1) - COGNOME VUOTO (-1)
    @Test
    void diffName_CompareTo36(){
        compareToIstanceL("B", "Y");
        compareToIstanceR("A", "");
        assertEquals(-1, LeftContact.compareTo(RightContact));
    }


    @Test
    void testToString() {
        String result = testedContact.toString();
        assertTrue(result.contains("B"), "La stringa dovrebbe contenere il nome");
        assertTrue(result.contains("B"), "La stringa dovrebbe contenere il cognome");
        assertTrue(result.contains("mario.rossi@example.com"), "Dovrebbe contenere l'email");
        assertTrue(result.contains("333111222"), "Dovrebbe contenere il numero");
    }
}
