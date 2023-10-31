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

class PersisterTestSer {

    private final Kontakt kontakt = new Kontakt("079HetSieGseit", "globi@globimail.ch");
    private final Adresse adresse = new Adresse("Globistrasse 69", 69420, "Globi Stadt");
    private Person person = new Person("Pingu", "Globi", LocalDate.of(9999, 12,1), kontakt, adresse);

    @Test
    void speichernSer_shouldWork() throws Exception {

        Persister s = PersisterTestUtils.create_PersisterSer();

        Person savedPerson = s.speichern(person);
        assertNotNull(savedPerson);
        assertNotEquals(0, savedPerson.getPersonNummer());
        assertEquals("Globi", savedPerson.getVorname());

        Person personFromFile = s.finde(savedPerson.getPersonNummer());
        assertNotNull(personFromFile);
        assertEquals("Globi", personFromFile.getVorname());
    }

    @Test
    void aktualisierenSer_shouldWork() throws Exception {

        Persister s = PersisterTestUtils.create_PersisterSer();

        Person savedPerson = s.speichern(person);
        assertNotNull(savedPerson);
        assertNotEquals(0, savedPerson.getPersonNummer());

        person.setVorname("Erica");
        Person updatedPerson = s.aktualisieren(person);
        assertNotNull(updatedPerson);
        assertEquals("Erica", updatedPerson.getVorname());
        assertNotEquals(0, updatedPerson.getPersonNummer());

        Person personFromFile = s.finde(updatedPerson.getPersonNummer());
        assertNotNull(personFromFile);
        assertEquals("Erica", personFromFile.getVorname());
    }

    @Test
    void loeschenPersonSer_shouldWork() throws Exception{

        Persister s = PersisterTestUtils.create_PersisterSer();

        Person savedPerson = s.speichern(person);
        assertNotNull(savedPerson);
        assertNotEquals(0, savedPerson.getPersonNummer());

        boolean deleted = s.loeschen(person);
        assertTrue(deleted);
    }

    @Test
    void loeschenPersonenNummerSer_shouldWork() throws Exception{

        Persister s = PersisterTestUtils.create_PersisterSer();

        Person savedPerson = s.speichern(person);
        assertNotNull(savedPerson);
        assertNotEquals(0, savedPerson.getPersonNummer());

        boolean deleted = s.loeschen(person.getPersonNummer());
        assertTrue(deleted);
    }

    @Test
    void findeSer_shouldWork() throws Exception{

        Persister s = PersisterTestUtils.create_PersisterSer();

        List<Person> liste;
        Person savedPerson = s.speichern(person);
        assertNotNull(savedPerson);
        assertNotEquals(0, savedPerson.getPersonNummer());

        liste = s.finde(savedPerson.getName(), savedPerson.getVorname());
        assertNotNull(liste);

        String name = liste.get(0).getName();
        String vorname = liste.get(0).getVorname();
        assertEquals("Globi", vorname);
        assertEquals("Pingu", name);
    }

    @Test
    void findePersonenNummerSer_shouldWork() throws Exception{

        Persister s = PersisterTestUtils.create_PersisterSer();

        Person savedPerson = s.speichern(person);
        assertNotNull(savedPerson);
        assertNotEquals(0, savedPerson.getPersonNummer());

        Person personFromFile = s.finde(savedPerson.getPersonNummer());
        assertEquals(1, personFromFile.getPersonNummer());
    }

    @Test
    void alleSer_shouldWork() throws Exception{

        Persister s = PersisterTestUtils.create_PersisterSer();

        List<Person> liste;
        Person savedPerson = s.speichern(person);
        assertNotNull(savedPerson);
        assertNotEquals(0, savedPerson.getPersonNummer());

        liste = s.alle();
        assertNotNull(liste);
        assertEquals(person, liste.get(0));
    }
}