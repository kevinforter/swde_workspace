package vereinverwaltung;

import ch.hslu.informatik.swde.vereinverwaltung.domain.Adresse;
import ch.hslu.informatik.swde.vereinverwaltung.domain.Kontakt;
import ch.hslu.informatik.swde.vereinverwaltung.domain.Person;
import ch.hslu.informatik.swde.vereinverwaltung.persister.api.Persister;

import org.junit.jupiter.api.Test;
import utils.PersisterTestUtils;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersisterTestTxt {

    private final Kontakt kontakt = new Kontakt("079HetSieGseit", "globi@globimail.ch");
    private final Adresse adresse = new Adresse("Globistrasse 69", 69420, "Globi Stadt");
    private Person person = new Person("Pingu", "Globi", LocalDate.of(9999, 12,1), kontakt, adresse);

    @Test
    void speichernTxt_shouldWork() throws Exception {

        Persister p = PersisterTestUtils.create_PersisterTxt();

        Person savedPerson = p.speichern(person);
        assertNotNull(savedPerson);
        assertNotEquals(0, savedPerson.getPersonNummer());
        assertEquals("Globi", savedPerson.getVorname());

        Person personFromFile = p.finde(savedPerson.getPersonNummer());
        assertNotNull(personFromFile);
        assertEquals("Globi", personFromFile.getVorname());
    }

    @Test
    void aktualisierenTxt_shouldWork() throws Exception {

        Persister p = PersisterTestUtils.create_PersisterTxt();

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
    }

    @Test
    void loeschenPersonTxt_shouldWork() throws Exception{

        Persister p = PersisterTestUtils.create_PersisterTxt();

        Person savedPerson = p.speichern(person);
        assertNotNull(savedPerson);
        assertNotEquals(0, savedPerson.getPersonNummer());

        boolean deleted = p.loeschen(person);
        assertTrue(deleted);
    }

    @Test
    void loeschenPersonenNummerTxt_shouldWork() throws Exception{

        Persister p = PersisterTestUtils.create_PersisterTxt();

        Person savedPerson = p.speichern(person);
        assertNotNull(savedPerson);
        assertNotEquals(0, savedPerson.getPersonNummer());

        boolean deleted = p.loeschen(person.getPersonNummer());
        assertTrue(deleted);
    }

    @Test
    void findeTxt_shouldWork() throws Exception{

        Persister p = PersisterTestUtils.create_PersisterTxt();

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
    }

    @Test
    void findePersonenNummerTxt_shouldWork() throws Exception{

        Persister p = PersisterTestUtils.create_PersisterTxt();

        Person savedPerson = p.speichern(person);
        assertNotNull(savedPerson);
        assertNotEquals(0, savedPerson.getPersonNummer());

        Person personFromFile = p.finde(savedPerson.getPersonNummer());
        assertEquals(1, personFromFile.getPersonNummer());
    }

    @Test
    void alleTxt_shouldWork() throws Exception{

        Persister p = PersisterTestUtils.create_PersisterTxt();

        List<Person> liste;
        Person savedPerson = p.speichern(person);
        assertNotNull(savedPerson);
        assertNotEquals(0, savedPerson.getPersonNummer());

        liste = p.alle();
        assertNotNull(liste);
        assertEquals(person, liste.get(0));
    }
}