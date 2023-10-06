/*
 * Copyright 2022 Jordan Sucur, HSLU Informatik, Switzerland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.informatik.swde.vereinverwaltung.persister.ser;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ch.hslu.informatik.swde.vereinverwaltung.domain.Person;
import ch.hslu.informatik.swde.vereinverwaltung.persister.api.Persister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Diese Klasse stellt eine konkrete Implementierung der Schnittstelle
 * 'Persister' dar. Die Persisteirung wird dabe mit Hilfe der Serialisierung
 * realisiert.
 */
public final class PersisterSer implements Persister {

    private static final Logger LOGGER = LogManager.getLogger(PersisterSer.class);

    /* Hilfsvariable, um ID-Werte (personNummer) zu generieren */
    private static int nextId;

    /**
     * File, in dem Daten verwaltet werden.
     */
    private final File file;

    /**
     * Konstruktor.
     * @param file File.
     * @throws IOException IOException.
     */
    public PersisterSer(final File file) throws Exception {
        this.file = file;
        init();
    }

    private void init() throws Exception {
        if (file.exists()) {
            initNextId();
        } else {
            nextId = 1;
        }
        if (nextId == 0) {
            String msg = "Der Wert für 'nextId' konnte nicht ausgelesen werden!";
            throw new Exception(msg);
        }
    }

    /*
     * @see prg.vereinverwaltung.persister.api.Persister#speichern(prg.
     * vereinverwaltung.domain.Person)
     */
    @Override
    public Person speichern(final Person person) throws Exception {

        /* Alle Objekte auslesen */
        List<Person> liste = null;

        if (person.getPersonNummer() == 0) {
            /* Neues Objekt: Person-Nummer setzen */
            person.setPersonNummer(nextId++);
        }

        if (file.exists()) {

            try {
                liste = allePersonenAuslesen();
            } catch (ClassNotFoundException | IOException e) {
                LOGGER.error("Fehler beim Auslesen von Personen: ", e);
                throw e;
            }
        } else {
            liste = new ArrayList<Person>();
        }

        /* Neues Objekt in die Liste einfügen */
        liste.add(person);

        /* Alle Objekte speichern */
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {

            for (Person p : liste) {
                oos.writeObject(p);
            }
        } catch (IOException e) {
            LOGGER.error("Fehler beim Schreiben von Personen: ", e);
            throw e;
        }

        return person;
    }

    /*
     * @see prg.vereinverwaltung.persister.api.Persister#aktualisieren(prg.
     * vereinverwaltung.domain.Person)
     */
    @Override
    public Person aktualisieren(final Person person) throws Exception {

        if (person.getPersonNummer() != 0) {
            /* Objekt bereits gespeichert: Update durchführen */
            loeschen(person.getPersonNummer());
            return speichern(person);
        } else {
            /* Objekt nocht nicht gespeichert: Speichern */
            return speichern(person);
        }
    }

    /*
     * @see prg.vereinverwaltung.persister.api.Persister#loeschen(prg.
     * vereinverwaltung.domain.Person)
     */
    @Override
    public boolean loeschen(final Person person) throws Exception {
        return loeschen(person.getPersonNummer());
    }

    /*
     * @see prg.vereinverwaltung.persister.api.Persister#loeschen(int)
     */
    @Override
    public boolean loeschen(final int personNummer) throws Exception {

        /* Alle Objekte auslesen */
        List<Person> liste = null;

        try {

            liste = allePersonenAuslesen();

            // Person mit der übergebenen personNummer aus der Liste entferenen
            Iterator<Person> it = liste.iterator();

            while (it.hasNext()) {
                Person p = it.next();

                if (p.getPersonNummer() == personNummer) {
                    it.remove();
                    break;
                }
            }

        } catch (ClassNotFoundException | IOException e) {
            LOGGER.error("Fehler beim Entfernen der Person: ", e);
            throw e;
        }

        /* Alle Objekte speichern */
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {

            for (Person p : liste) {
                oos.writeObject(p);
            }
        } catch (IOException e) {
            LOGGER.error("Fehler beim Entfernen der Person: ", e);
            throw e;
        }

        return true;
    }

    /*
     * @see prg.vereinverwaltung.persister.api.Persister#finde(java.lang.String,
     * java.lang.String)
     */
    @Override
    public List<Person> finde(final String name, final String vorname) throws Exception {

        /* Alle Objekte auslesen */
        List<Person> tempListe = null;
        List<Person> personenListe = null;

        try {

            /* Alle Personen auslesen */
            tempListe = allePersonenAuslesen();

            /* Liste für Personen mit übergebenen Namen und Vornamen */
            personenListe = new ArrayList<>();

            /*
             * Personen mitnehmen, deren Name und Vorname identisch mit übergebenen Namen
             * und Vornamen sind
             */
            for (Person p : tempListe) {
                if (p.getName().equals(name) && p.getVorname().equals(vorname)) {
                    personenListe.add(p);
                }
            }

        } catch (ClassNotFoundException | IOException e) {
            LOGGER.error("Fehler beim Auslesen der Personen nach Person-Nummer: ", e);
            throw e;
        }

        return personenListe;
    }

    /*
     * @see prg.vereinverwaltung.persister.api.Persister#finde(int)
     */
    @Override
    public Person finde(final int personNummer) throws Exception {

        /* Alle Objekte auslesen */
        Person person = null;

        try {
            List<Person> liste = allePersonenAuslesen();

            for (Person p : liste) {
                if (p.getPersonNummer() == personNummer) {
                    person = p;
                    break;
                }
            }

        } catch (ClassNotFoundException | IOException e) {
            LOGGER.error("Fehler beim Auslesen der Personen nach Person-Nummer: ", e);
            throw e;
        }

        return person;
    }

    /*
     * @see prg.vereinverwaltung.persister.api.Persister#alle()
     */
    @Override
    public List<Person> alle() throws Exception {
        try {
            return allePersonenAuslesen();
        } catch (ClassNotFoundException | IOException e) {
            LOGGER.error("Fehler beim Auslesen von Personen: ", e);
            throw e;
        }
    }

    /**
     * HELPER - Liest alle Personen aus der Datei.
     *
     * @return Liste von Personen.
     * @throws IOException IOException.
     * @throws ClassNotFoundException ClassNotFoundException.
     */
    private List<Person> allePersonenAuslesen() throws IOException, ClassNotFoundException {

        /* Alle Objekte auslesen */
        List<Person> liste = new ArrayList<>();

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

                Person person = null;

                boolean check = true;

                while (check) {
                    try {
                        person = (Person) ois.readObject();

                        liste.add(person);
                    } catch (EOFException ex) {
                        check = false;
                    }
                }
            } catch (FileNotFoundException e) {
                LOGGER.error("File nicht gefunden: ", e);
                throw e;
            } catch (IOException e) {
                LOGGER.error("Fehler beim Auslesen des 'nextId' Werts: ", e);
                throw e;
            } catch (ClassNotFoundException e) {
                LOGGER.error("Klasse nicht gefunden: ", e);
                throw e;
            }
        }

        return liste;

    }

    /**
     * HELPER - Initialisiert den nextId Wert.
     *
     * @throws IOException IOException.
     * @throws ClassNotFoundException ClassNotFoundException.
     */
    private void initNextId() throws IOException, ClassNotFoundException {
        int maxValue = 0;

        /*
         * Es werden alle Person-Objekte ausgelesen. Der Wert für 'nextId' wird der Wert
         * sein, der um Eins grösser als 'maxValue' ist.
         */
        try {

            List<Person> liste = allePersonenAuslesen();

            for (Person p : liste) {

                if (p.getPersonNummer() > maxValue) {
                    maxValue = p.getPersonNummer();
                }
            }

            /* nextId setzen */
            nextId = maxValue + 1;

        } catch (FileNotFoundException e) {
            LOGGER.error("File nicht gefunden: ", e);
            throw e;
        } catch (IOException e) {
            LOGGER.error("Fehler beim Auslesen des 'nextId' Werts: ", e);
            throw e;
        } catch (ClassNotFoundException e) {
            LOGGER.error("Klasse nicht gefunden: ", e);
            throw e;
        }
    }
}
