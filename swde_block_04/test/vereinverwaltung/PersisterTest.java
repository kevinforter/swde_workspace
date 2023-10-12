package vereinverwaltung;

import ch.hslu.informatik.swde.vereinverwaltung.domain.Adresse;
import ch.hslu.informatik.swde.vereinverwaltung.domain.Kontakt;
import ch.hslu.informatik.swde.vereinverwaltung.domain.Person;

import ch.hslu.informatik.swde.vereinverwaltung.persister.api.Persister;
import ch.hslu.informatik.swde.vereinverwaltung.persister.txt.PersisterTxt;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersisterTest {

    private static Persister p;
    private static File f;
    private static final Kontakt kontakt = new Kontakt("079HetSieGseit", "globi@globimail.ch");
    private final static Adresse adresse = new Adresse("Globistrasse 69", 69420, "Globi Stadt");
    private final static Person person = new Person("Pingu", "Globi", LocalDate.of(9999, 12,1), kontakt, adresse);

    @BeforeEach
    void create_PersisterTxt() {
        try {
            f = File.createTempFile("test", ".txt");
            p = new PersisterTxt(f);
        } catch (Exception msg){
            System.out.println("Exception occured: " + msg);
        }
    }

    @AfterEach
    void loeschen_person() {
        try {
            p.loeschen(person.getPersonNummer());
            person.setVorname("Globi");
        } catch (Exception msg) {
            System.out.println("Exception occured: " + msg);
        }
    }

    @Test
    void speichern_shouldWork() {
        try {
            Person savedPerson = p.speichern(person);
            assertNotNull(savedPerson);
            assertNotEquals(0, savedPerson.getPersonNummer());
            assertEquals("Globi", savedPerson.getVorname());

            Person personFromFile = p.finde(savedPerson.getPersonNummer());
            assertNotNull(personFromFile);
            assertEquals("Globi", personFromFile.getVorname());
        } catch (Exception msg) {
            System.out.println("Exception occured: " + msg);
        }
    }

    @Test
    void aktualisieren_shouldWork() {

        try {
            Person savedPerson = p.speichern(person);
            assertNotNull(savedPerson);
            assertNotEquals(0, savedPerson.getPersonNummer());

            person.setVorname("Erica");
            Person updatedPerson = p.aktualisieren(person);
            assertNotNull(updatedPerson);
            assertEquals("Erica", updatedPerson.getVorname());
            assertNotEquals(0, updatedPerson.getPersonNummer());

            Person personFromFile = p.finde(updatedPerson.getPersonNummer());
            assertNotNull(personFromFile);
            assertEquals("Erica", personFromFile.getVorname());
        } catch (Exception msg) {
            System.out.println("Exception occured: " + msg);
        }
    }

    @Test
    void loeschenPerson_shouldWork() {
        try {
            Person savedPerson = p.speichern(person);
            assertNotNull(savedPerson);
            assertNotEquals(0, savedPerson.getPersonNummer());

            boolean deleted = p.loeschen(person);
            assertTrue(deleted);

        } catch (Exception msg) {
            System.out.println("Exception occured: " + msg);
        }

    }

    @Test
    void loeschenPersonenNummer_shouldWork() {
        try {
            Person savedPerson = p.speichern(person);
            assertNotNull(savedPerson);
            assertNotEquals(0, savedPerson.getPersonNummer());

            boolean deleted = p.loeschen(person.getPersonNummer());
            assertTrue(deleted);

        } catch (Exception msg) {
            System.out.println("Exception occured: " + msg);
        }
    }

    @Test
    void finde_shouldWork() {
        try {
            List<Person> liste;
            Person savedPerson = p.speichern(person);
            assertNotNull(savedPerson);
            assertNotEquals(0, savedPerson.getPersonNummer());

            liste = p.finde(savedPerson.getName(), savedPerson.getVorname());
            assertNotNull(liste);

            String name = liste.get(0).getName();
            String vorname = liste.get(0).getVorname();
            assertEquals("Globi", vorname);
            assertEquals("Pingu", name);
        } catch (Exception msg) {
            System.out.println("Exception occured: " + msg);
        }
    }

    @Test
    void findePersonenNummer_shouldWork() {
        try {
            Person savedPerson = p.speichern(person);
            assertNotNull(savedPerson);
            assertNotEquals(0, savedPerson.getPersonNummer());

            Person personFromFile = p.finde(savedPerson.getPersonNummer());
            assertEquals(1, personFromFile.getPersonNummer());
        } catch (Exception msg) {
            System.out.println("Exception occured: " + msg);
        }
    }

    @Test
    void alle_shouldWork() {
        try {
            List<Person> liste;
            Person savedPerson = p.speichern(person);
            assertNotNull(savedPerson);
            assertNotEquals(0, savedPerson.getPersonNummer());

            liste = p.alle();
            assertNotNull(liste);

            assertEquals(person, liste.get(0));
        } catch (Exception msg) {
            System.out.println("Exception occured: " + msg);
        }
    }
}