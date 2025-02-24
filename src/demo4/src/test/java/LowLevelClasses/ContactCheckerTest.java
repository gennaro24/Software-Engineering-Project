package LowLevelClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
/**
 * @Author Foschillo Gennaro
 **/
class ContactCheckerTest {


    @Nested
    @DisplayName("Test isValidEmail")
    class IsValidEmailTests {

        @Test
        @DisplayName("Email standard valida")
        void testValidEmail_Standard() {
            assertTrue(ContactChecker.isValidEmail("mario.rossi@example.com"),
                    "mario.rossi@example.com dovrebbe essere valida");
        }

        @Test
        @DisplayName("Email con underscore valida")
        void testValidEmail_Underscore() {
            assertTrue(ContactChecker.isValidEmail("mario_rossi@example.com"),
                    "mario_rossi@example.com dovrebbe essere valida");
        }

        @Test
        @DisplayName("Email con punto nel local-part")
        void testValidEmail_DotLocalPart() {
            assertTrue(ContactChecker.isValidEmail("mar.io@example.co.uk"),
                    "mar.io@example.co.uk dovrebbe essere valida (dominio .co.uk)");
        }

        @Test
        @DisplayName("Email senza TLD minima (es. .c)")
        void testInvalidEmail_TooShortTLD() {
            // TLD deve essere almeno 2 caratteri => .c non valido
            assertFalse(ContactChecker.isValidEmail("mario.rossi@example.c"),
                    "Dominio di 1 carattere (.c) non è valido => false");
        }

        @Test
        @DisplayName("Email con caratteri speciali vietati nel local-part")
        void testInvalidEmail_SpecialChars() {
            // Il pattern ammette alfanumerici, punto, underscore => # non è ammesso
            assertFalse(ContactChecker.isValidEmail("mario#rossi@example.com"),
                    "mario#rossi@example.com non dovrebbe essere valida => false");
        }

        @Test
        @DisplayName("Email senza chiocciola")
        void testInvalidEmail_NoAt() {
            assertFalse(ContactChecker.isValidEmail("mario.rossiexample.com"),
                    "Manca la chiocciola => false");
        }

        @Test
        @DisplayName("Email vuota")
        void testInvalidEmail_Empty() {
            assertFalse(ContactChecker.isValidEmail(""),
                    "Stringa vuota => false");
        }

        @Test
        @DisplayName("Email solo spazi")
        void testInvalidEmail_OnlySpaces() {
            assertFalse(ContactChecker.isValidEmail("   "),
                    "Solo spazi => false");
        }

        @Test
        @DisplayName("Email con sottodomini multipli validi")
        void testValidEmail_MultiSubdomain() {
            assertTrue(ContactChecker.isValidEmail("nome.cognome@sub.dom.example.org"),
                    "nome.cognome@sub.dom.example.org dovrebbe essere valida");
        }
    }

    /* ------------------------------------------------------------------
       TEST per isValidNumber(String n)
       ------------------------------------------------------------------ */
    @Nested
    @DisplayName("Test isValidNumber")
    class IsValidNumberTests {

        @Test
        @DisplayName("Numero minimo 3 cifre")
        void testValidNumber_Min3() {
            assertTrue(ContactChecker.isValidNumber("123"),
                    "3 cifre => valido");
        }

        @Test
        @DisplayName("Numero massimo 15 cifre")
        void testValidNumber_Max15() {
            assertTrue(ContactChecker.isValidNumber("123456789012345"),
                    "15 cifre => valido");
        }

        @Test
        @DisplayName("Numero con 16 cifre => non valido")
        void testInvalidNumber_TooLong() {
            assertFalse(ContactChecker.isValidNumber("1234567890123456"),
                    "16 cifre => non valido");
        }

        @Test
        @DisplayName("Numero con 2 cifre => non valido (minimo 3)")
        void testInvalidNumber_TooShort() {
            assertFalse(ContactChecker.isValidNumber("12"),
                    "2 cifre => non valido");
        }

        @Test
        @DisplayName("Numero con caratteri non numerici")
        void testInvalidNumber_NonDigits() {
            assertFalse(ContactChecker.isValidNumber("123abc"),
                    "Contiene lettere => non valido");
        }

        @Test
        @DisplayName("Stringa vuota => non valida")
        void testInvalidNumber_Empty() {
            assertFalse(ContactChecker.isValidNumber(""),
                    "Stringa vuota => non valida");
        }

        @Test
        @DisplayName("Stringa solo spazi => non valida")
        void testInvalidNumber_OnlySpaces() {
            assertFalse(ContactChecker.isValidNumber("   "),
                    "Solo spazi => non valida");
        }
    }

    /* ------------------------------------------------------------------
       TEST per checkNameSurname(String name, String surname)
       Ritorna true se entrambi i campi, ripuliti da virgolette e spazi,
       risultano vuoti.
       ------------------------------------------------------------------ */
    @Nested
    @DisplayName("Test checkNameSurname")
    class CheckNameSurnameTests {

        @Test
        @DisplayName("Entrambi vuoti => true")
        void testBothEmpty() {
            assertTrue(ContactChecker.checkNameSurname("", ""),
                    "Entrambi vuoti => true");
        }

        @Test
        @DisplayName("Entrambi con solo spazi => true")
        void testBothOnlySpaces() {
            assertTrue(ContactChecker.checkNameSurname("   ", "   "),
                    "Solo spazi => true");
        }

        @Test
        @DisplayName("Name con virgolette => pulito => true")
        void testNameWithQuotes() {
            // name => "''" => dopo replaceAll => "" => true
            assertTrue(ContactChecker.checkNameSurname("''", ""),
                    "Name con virgolette => pulito => vuoto => true");
        }

        @Test
        @DisplayName("Entrambi con virgolette => puliti => true")
        void testBothWithQuotes() {
            assertTrue(ContactChecker.checkNameSurname("''", "\"\""),
                    "Dopo replaceAll => entrambi vuoti => true");
        }

        @Test
        @DisplayName("Uno pieno, l'altro vuoto => false")
        void testOneEmptyOneNonEmpty() {
            assertFalse(ContactChecker.checkNameSurname("Mario", ""),
                    "Mario / vuoto => false");
        }

        @Test
        @DisplayName("Entrambi pieni => false")
        void testBothNonEmpty() {
            assertFalse(ContactChecker.checkNameSurname("Mario", "Rossi"),
                    "Mario / Rossi => false");
        }

        @Test
        @DisplayName("Name pulito => spazi e virgolette => false se cognome è pieno")
        void testMixed() {
            // name => "''   " => diventa "" dopo pulizia, surname => "Bianchi"
            // => name vuoto, cognome pieno => false
            assertFalse(ContactChecker.checkNameSurname("''   ", "Bianchi"),
                    "Name vuoto, cognome pieno => false");
        }
    }
}
