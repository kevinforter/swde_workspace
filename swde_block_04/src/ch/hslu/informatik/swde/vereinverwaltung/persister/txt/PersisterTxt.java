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
package ch.hslu.informatik.swde.vereinverwaltung.persister.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ch.hslu.informatik.swde.vereinverwaltung.domain.Adresse;
import ch.hslu.informatik.swde.vereinverwaltung.domain.Kontakt;
import ch.hslu.informatik.swde.vereinverwaltung.domain.Person;
import ch.hslu.informatik.swde.vereinverwaltung.persister.api.Persister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Diese Klasse stellt eine konkrete Implementierung der Schnittstelle
 * 'Persister' dar. Dabei werden Objektinhalte als Plain-Text in der Datei
 * abgelegt.
 *
 * @author jsucur
 * @version 1.0
 */
public final class PersisterTxt implements Persister {

    private static final String DELIMITER = "#";

    private static final Logger LOGGER = LogManager.getLogger(PersisterTxt.class);

    /**
     * File, in dem Daten verwaltet werden.
     */
    private final File file;

    /* Hilfsvariable, um ID-Werte (personNummer) zu generieren */
    private static int nextId;

    /**
     * Konstruktor.
     * @param file File.
     * @throws IOException IOException.
     */
    public PersisterTxt(final File file) throws Exception {
        this.file = file;
        nextId = 0;
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

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true), true)) {

            if (person.getPersonNummer() == 0) {
                /* Neues Objekt: Person-Nummer setzen */
                person.setPersonNummer(nextId++);
            }

            String str = personAsString(person);
            writer.println(str);
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

        ArrayList<String> liste = new ArrayList<>();

        /* Alles aus der Datei auslesen */
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;
            String id = "" + personNummer;

            while ((line = br.readLine()) != null) {

                // Zeile, die mit 'personNummer' startet, wird rausgelassen (somit gelöscht)
                if (!line.startsWith(id)) {
                    liste.add(line);
                }
            }
        }

        /*
         * Alles in die Datei schreiben (die zu löschende Zeile ist nicht mehr dabei)
         */
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, false))) {

            for (String str : liste) {
                writer.println(str);
            }
        }

        return true;
    }

    /*
     * @see prg.vereinverwaltung.persister.api.Persister#finde(java.lang.String,
     * java.lang.String)
     */
    @Override
    public List<Person> finde(final String name, final String vorname) throws Exception {

        List<Person> liste = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(DELIMITER);

                if (name.equals(parts[1]) && vorname.equals(parts[2])) {
                    liste.add(getAsPerson(line));
                }
            }
        }

        return liste;
    }

    /*
     * @see prg.vereinverwaltung.persister.api.Persister#finde(int)
     */
    @Override
    public Person finde(final int personNummer) throws Exception {

        Person person = null;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(DELIMITER);

                if (Integer.parseInt(parts[0]) == personNummer) {
                    person = getAsPerson(line);
                }
            }
        }

        return person;
    }

    /*
     * @see prg.vereinverwaltung.persister.api.Persister#alle()
     */
    @Override
    public List<Person> alle() throws Exception {

        List<Person> liste = new ArrayList<>();

        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = null;

                while ((line = br.readLine()) != null) {
                    liste.add(getAsPerson(line));
                }
            }
        }

        return liste;
    }

    /**
     * HELPER - Initialisiert den nextId Wert.
     *
     * @throws IOException IOException.
     */
    private void initNextId() throws IOException {
        int maxValue = 0;

        /*
         * Es werden alle Zeilen gelesen und alle 'personNummer' ausgelesen. Der Wert
         * für 'nextId' wird der Wert sein, der um Eins grösser als 'maxValue' ist.
         */
        try (BufferedReader bReader = new BufferedReader(new FileReader(file))) {
            String line = null;

            while ((line = bReader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                int value = Integer.parseInt(parts[0]);

                if (value > maxValue) {
                    maxValue = value;
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
        }
    }

    /**
     * HELPER - Liefert die Stringdarstellung der Person-Instanz zurück.
     *
     * @param p Person.
     * @return String.
     */
    private String personAsString(final Person p) {

        String sBuilder = p.getPersonNummer() + DELIMITER + p.getName() + DELIMITER +
                p.getVorname() + DELIMITER + geburtsDatumAsString(p.getGeburtsDatum()) +
                DELIMITER + p.getAdresse().getStrasse() + DELIMITER + p.getAdresse().getPlz() +
                DELIMITER + p.getAdresse().getOrt() + DELIMITER + p.getKontakt().getTelefon() +
                DELIMITER + p.getKontakt().getEmail();

        return sBuilder;
    }

    /**
     * Erstellt das Person-Objekt aus dem übergebenen String.
     *
     * @param line Line.
     * @return Person.
     */
    private Person getAsPerson(final String line) {

        String[] parts = line.split(DELIMITER);

        int personNummer = Integer.parseInt(parts[0]);
        String name = parts[1];
        String vorname = parts[2];
        int jahr = Integer.parseInt(parts[3]);
        int monat = Integer.parseInt(parts[4]);
        int tag = Integer.parseInt(parts[5]);
        String strasse = parts[6];
        int plz = Integer.parseInt(parts[7]);
        String ort = parts[8];
        String telefon = parts[9];
        String email = parts[10];

        LocalDate datum = LocalDate.of(jahr, monat, tag);

        Person person = new Person(name, vorname, datum, new Kontakt(telefon, email), new Adresse(strasse, plz, ort));
        person.setPersonNummer(personNummer);

        return person;
    }

    /**
     * HELPER - Liefert die Stringdarstellung der Datums zurück.
     *
     * @param d Datum.
     * @return String.
     */
    private String geburtsDatumAsString(final LocalDate d) {
        return d.getYear() + DELIMITER + d.getMonth().getValue() + DELIMITER + d.getDayOfMonth();
    }

}
