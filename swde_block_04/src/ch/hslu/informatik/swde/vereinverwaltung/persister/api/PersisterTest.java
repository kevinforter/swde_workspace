package ch.hslu.informatik.swde.vereinverwaltung.persister.api;

import ch.hslu.informatik.swde.vereinverwaltung.domain.Adresse;
import ch.hslu.informatik.swde.vereinverwaltung.domain.Kontakt;
import ch.hslu.informatik.swde.vereinverwaltung.domain.Person;

import ch.hslu.informatik.swde.vereinverwaltung.persister.ser.PersisterSer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersisterTest {
    private final LocalDate gebDatum = LocalDate.now();
    Kontakt kontakt = new Kontakt("0798652128", "kevin@forter.eu");
    Adresse adresse = new Adresse("Bahnhofstrasse 3", 7270, "Davos Platz");
    Person person = new Person ("Kevin", "Forter", gebDatum, kontakt, adresse);

    @Test
    void speichern_shouldWork() {

        assertNotNull(person);

        /*Person savedPerson = Persister.speichern(person);
        assertNotNull(savedPerson);
        assertNotEquals(0, savedPerson.getPersonNummer());*/
    }

    @Test
    void aktualisieren() {

    }

    @Test
    void loeschen() {

    }

    @Test
    void testLoeschen() {

    }

    @Test
    void finde() {

    }

    @Test
    void testFinde() {

    }

    @Test
    void alle() {

    }
}