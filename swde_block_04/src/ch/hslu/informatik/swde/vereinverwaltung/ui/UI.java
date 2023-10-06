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
package ch.hslu.informatik.swde.vereinverwaltung.ui;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import ch.hslu.informatik.swde.vereinverwaltung.business.api.Verwaltung;
import ch.hslu.informatik.swde.vereinverwaltung.domain.Adresse;
import ch.hslu.informatik.swde.vereinverwaltung.domain.Kontakt;
import ch.hslu.informatik.swde.vereinverwaltung.domain.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Diese Klasse repräsentiert das User-Interface (CLI).
 *
 * @author Jordan Sucur
 * @version 1.0
 */
public final class UI {

    private static final Logger LOG = LogManager.getLogger(UI.class);

    /* Menue-Konstanten */
    private static final String MENU_1_0_0 = "Person hinzufuegen [1]     Daten Laden [2]     Beenden [0]";
    private static final String MENU_2_0_0 = "Person hinzufuegen [1]     Personendaten anzeigen [2]     Zurueck [0]";
    private static final String MENU_2_1_0 = "Suchkriterium waehlen:\nPerson-Nummer [1]     Name und Vorname [2]     Alle [3]     Zurueck [0]";
    private static final String MENU_2_1_1 = "Persondaten bearbeiten [1]     Person loeschen [2]     Zurueck [0]";
    private static final String MENU_2_1_2 = "Sortieren nach Person-Nummer [1]     Sortieren nach Name und Vorname [2]     Exportieren [3]     Zurueck [0]";

    /* Meldungen-Konstanten */
    private static final String LOG_MESSAGE_ERROR_DATEN_LADEN = "Fehler beim Versuch, daten zu laden: ";
    private static final String UI_MESSAGE_ERROR_DATEN_LADEN = "ERROR:\nDaten konnten nicht geladen werden!";
    private static final String UI_MESSAGE_KEINE_DATEN_GEFUNDEN = "\n Keine Daten gefunden!";

    /* Liste, in der Personen-Instanzen verwaltet werden */
    private List<Person> personListe = new ArrayList<>();

    private Person person;

    /* Start-Menu */
    private String menu = MENU_1_0_0;

    /**
     * Anwendungslogik-Komponente.
     */
    private Verwaltung verwaltung;

    public UI(final Verwaltung verwaltung) {
        this.verwaltung = verwaltung;
    }

    /**
     * Steuert die Ausführung des Programms.
     */
    public void execute() {

        int wahl = 0;
        menu = MENU_1_0_0;

        showMenu();
        wahl = eingabeEinlesen();

        do {

            if (menu.equals(MENU_1_0_0)) {

                if (wahl == 1) {
                    personHinzufuegen();
                    menu = MENU_2_0_0;
                    showMenu();
                    wahl = eingabeEinlesen();
                } else if (wahl == 2) {
                    personListe = datenLaden();

                    /*
                     * Falls Daten vorhanden, daten anzeigen und zu MENU_2_0_0 gehen, sonst bei
                     * MENU_1_0_0 bleiben
                     */
                    if (personListe != null && !personListe.isEmpty()) {
                        show(personListe);
                        menu = MENU_2_0_0;
                    } else {
                        System.out.println("\nKeine Daten gefunden.");
                    }

                    showMenu();

                    wahl = eingabeEinlesen();
                } else if (wahl == 0) {
                    // beenden
                    break;
                }

            } else if (menu.equals(MENU_2_0_0)) {

                if (wahl == 1) {
                    personHinzufuegen();
                    menu = MENU_2_0_0;
                    showMenu();
                    wahl = eingabeEinlesen();
                } else if (wahl == 2) {
                    menu = MENU_2_1_0;
                    showMenu();
                    wahl = eingabeEinlesen();
                } else if (wahl == 0) {
                    menu = MENU_1_0_0;
                    showMenu();
                    wahl = eingabeEinlesen();
                }
            } else if (menu.equals(MENU_2_1_0)) {
                if (wahl == 1) {

                    person = null;

                    person = findePersonByNummer();

                    if (person != null) {
                        showPerson(person);
                        menu = MENU_2_1_1;
                    } else {
                        System.out.println(UI_MESSAGE_KEINE_DATEN_GEFUNDEN);
                        menu = MENU_2_1_0;
                    }

                    showMenu();
                    wahl = eingabeEinlesen();
                } else if (wahl == 2) {
                    personListe = findePersonenByNameUndVorname();

                    if (personListe != null && !personListe.isEmpty()) {
                        show(personListe);
                        menu = MENU_2_1_2;
                    } else {
                        System.out.println(UI_MESSAGE_KEINE_DATEN_GEFUNDEN);
                        menu = MENU_2_1_0;
                    }

                    showMenu();
                    wahl = eingabeEinlesen();
                } else if (wahl == 3) {
                    personListe = findeAllePersonen();

                    if (personListe != null && !personListe.isEmpty()) {
                        menu = MENU_2_1_2;
                        show(personListe);
                    } else {
                        System.out.println(UI_MESSAGE_KEINE_DATEN_GEFUNDEN);
                        menu = MENU_2_1_0;
                    }
                    showMenu();
                    wahl = eingabeEinlesen();
                } else if (wahl == 0) {
                    menu = MENU_2_0_0;
                    showMenu();
                    wahl = eingabeEinlesen();
                }
            } else if (menu.equals(MENU_2_1_1)) {
                if (wahl == 1) {
                    personendatenBearbeiten();
                    menu = MENU_2_1_0;
                    showMenu();
                    wahl = eingabeEinlesen();
                } else if (wahl == 2) {
                    personLoeschen();
                    menu = MENU_2_1_0;
                    showMenu();
                    wahl = eingabeEinlesen();
                } else if (wahl == 0) {
                    menu = MENU_2_1_0;
                    showMenu();
                    wahl = eingabeEinlesen();
                }
            } else if (menu.equals(MENU_2_1_2)) {
                if (wahl == 1) {
                    showSortiertNachPersonNummer();
                    menu = MENU_2_1_2;
                    showMenu();
                    wahl = eingabeEinlesen();
                } else if (wahl == 2) {
                    showSortiertNachNameUndVorname();
                    menu = MENU_2_1_2;
                    showMenu();
                    wahl = eingabeEinlesen();
                } else if (wahl == 3) {
                    datenExportierenAlsCSV();
                    menu = MENU_2_1_2;
                    showMenu();
                    wahl = eingabeEinlesen();
                } else if (wahl == 0) {
                    menu = MENU_2_1_0;
                    showMenu();
                    wahl = eingabeEinlesen();
                }
            }
        } while (true);

        System.out.println("\nProgramm wird beendet.");
    }

    /**
     * Zeit das aktuelle Menu an.
     */
    private void showMenu() {
        System.out.println();
        System.out.println(menu);
        System.out.print("Ihre Wahl: ");
    }

    /**
     * Liest die Benutzereingabe ein.
     *
     * @return Auswahl.
     */
    private int eingabeEinlesen() {

        Scanner sc = new Scanner(System.in);

        int eingabe = -1;

        List<Integer> values = new ArrayList<>();

        switch (menu) {
            case MENU_1_0_0:
                values = Arrays.asList(1, 2, 0);
                break;

            case MENU_2_0_0:
                values = Arrays.asList(1, 2, 0);
                break;

            case MENU_2_1_0:
                values = Arrays.asList(1, 2, 3, 0);
                break;

            case MENU_2_1_1:
                values = Arrays.asList(1, 2, 0);
                break;

            case MENU_2_1_2:
                values = Arrays.asList(1, 2, 3, 0);
                break;
            default:
            // Zurzeit nicht nötig ...
        }

        do {
            try {
                eingabe = sc.nextInt();

                if (!values.contains(eingabe)) {
                    Toolkit.getDefaultToolkit().beep();
                    showMenu();
                }
            } catch (Exception e) {
                /* Buffer leeren */
                sc.nextLine();

                Toolkit.getDefaultToolkit().beep();
                showMenu();
            }

        } while (!values.contains(eingabe));

        return eingabe;
    }

    /**
     * Organisiert das Hinzufügen einer neuen Person.
     */
    private void personHinzufuegen() {

        try {

            Person p = readPerson("Persondaten eingeben: ");

            /* Person speichern */
            Person personSaved = verwaltung.personHinzuguefen(p);

            if (personSaved != null) {
                System.out.println("Person erfolgreich hinzugefuegt!");
            } else {
                System.err.println("Person konnte nicht hinzugefuegt werden!");
            }

        } catch (Exception e) {
            LOG.error("Fehler beim Versuch, eine neue Person zu hinzufuegen: ", e);
            System.err.println("\n Person konnte nicht hinzugefuegt werden!");
        }

    }

    /**
     * Organisiert das Editieren von Daten einer Person.
     */
    private void personendatenBearbeiten() {

        try {

            Person p = readPerson("Personendaten eingeben: ");

            /* Id übernehmen */
            p.setPersonNummer(person.getPersonNummer());

            /* Person aktualisieren */
            person = verwaltung.personAktualisieren(p);

            if (person != null) {
                System.out.println("\n Person erfolgreich aktualisiert!");
            } else {
                System.out.println("\n Person konnte nicht aktualisiert werden!");
            }

        } catch (Exception e) {
            LOG.error("Fehler beim Versuch, eine neue Person zu hinzufuegen: ", e);
            System.err.println("\n Person konnte nicht aktualisiert werden!");
        }

    }

    /*
     * Helper-Methode, die Personendaten einliest und ein Person-Objekt zurück
     * liefert.
     */
    private Person readPerson(final String message) throws DateTimeParseException {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n" + message);

        System.out.print(" Name:                      ");
        String name = sc.nextLine();

        System.out.print(" Vorname:                   ");
        String vorname = sc.nextLine();

        System.out.print(" Geburtsdatum [dd.MM.yyyy]: ");
        String strGebDatum = sc.nextLine();

        System.out.print(" Strasse:                   ");
        String strasse = sc.nextLine();

        System.out.print(" PLZ:                       ");
        int plz = sc.nextInt();

        // Buffer leeren
        sc.nextLine();

        System.out.print(" Ort:                       ");
        String ort = sc.nextLine();

        System.out.print(" Telefon:                   ");
        String telefon = sc.nextLine();

        System.out.print(" E-Mail:                    ");
        String email = sc.nextLine();

        LocalDate gebDatum = LocalDate.parse(strGebDatum, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        /* Person erzeugen und zurückgeben */
        return new Person(name, vorname, gebDatum, new Kontakt(telefon, email), new Adresse(strasse, plz, ort));

    }

    private List<Person> datenLaden() {

        List<Person> personen = null;

        try {
            personen = verwaltung.alle();
        } catch (Exception e) {
            LOG.error("Fehler beim Versuch, daten zu Laden: ", e);
            System.out.println("\n Daten konnten nicht geladen werden!");
        }

        return personen;
    }

    private Person findePersonByNummer() {

        Person p = null;
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println();

            System.out.print(" Person-Nummer:              ");
            int personNummer = sc.nextInt();

            p = verwaltung.finde(personNummer);

        } catch (Exception e) {
            LOG.error(LOG_MESSAGE_ERROR_DATEN_LADEN, e);
            System.err.println(UI_MESSAGE_ERROR_DATEN_LADEN);
        }

        return p;
    }

    private List<Person> findePersonenByNameUndVorname() {

        List<Person> liste = null;

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println();

            System.out.print(" Name:                      ");
            String name = sc.nextLine();

            System.out.print(" Vorname:                   ");
            String vorname = sc.nextLine();

            liste = verwaltung.finde(name, vorname);

        } catch (Exception e) {
            LOG.error(LOG_MESSAGE_ERROR_DATEN_LADEN, e);
            System.err.println(UI_MESSAGE_ERROR_DATEN_LADEN);
        }

        return liste;
    }

    private void personLoeschen() {

        try {
            verwaltung.personLoeschen(person);
            System.out.println("\n Person erfolgreich geloescht.");
        } catch (Exception e) {
            LOG.error("Fehler beim Versuch, eine neue Person zu loeschen: ", e);
            System.out.println("\n Person konnte nicht geloescht werden!");
        }

    }

    private List<Person> findeAllePersonen() {

        List<Person> liste = null;

        try {
            liste = verwaltung.alle();
        } catch (Exception e) {
            LOG.error(LOG_MESSAGE_ERROR_DATEN_LADEN, e);
            System.err.println(UI_MESSAGE_ERROR_DATEN_LADEN);
        }

        return liste;
    }

    private void datenExportierenAlsCSV() {

        final char delemiter = ';';

        File file = new File(System.getProperty("user.home") + File.separator + "vereinDaten.csv");

        try (PrintWriter printer = new PrintWriter(file)) {

            StringBuilder sBuilder = new StringBuilder();

            sBuilder.append("PERSON-NR.").append(delemiter).append("NAME").append(delemiter).append("VORNAME")
                    .append(delemiter).append("GEBURTSDATUM").append(delemiter).append("STRASSE").append(delemiter)
                    .append("PLZ").append(delemiter).append("ORT").append(delemiter).append("TELEFON").append(delemiter)
                    .append("E-MAIL");

            printer.println(sBuilder.toString());

            sBuilder.setLength(0);

            for (Person p : personListe) {

                sBuilder.append(p.getPersonNummer()).append(delemiter).append(p.getName()).append(delemiter)
                        .append(p.getVorname()).append(delemiter).append(datumToString(p.getGeburtsDatum()))
                        .append(delemiter).append(p.getAdresse().getStrasse()).append(delemiter)
                        .append(p.getAdresse().getPlz()).append(delemiter).append(p.getAdresse().getOrt())
                        .append(delemiter).append(p.getKontakt().getTelefon()).append(delemiter)
                        .append(p.getKontakt().getEmail());

                printer.println(sBuilder.toString());

                sBuilder.setLength(0);
            }

            System.out.println("\nDaten exportiert: " + file.getAbsolutePath());

        } catch (FileNotFoundException e) {
            LOG.error("Fehler beim Versuch, daten zu exportieren: ", e);
            System.err.println("ERROR:\nDaten konnten nicht exportiert werden!");
        }
    }

    private void showSortiertNachPersonNummer() {
        Collections.sort(personListe, Comparator.comparingInt(Person::getPersonNummer));
        show(personListe);
    }

    private void showSortiertNachNameUndVorname() {
        Collections.sort(personListe, Comparator.comparing(Person::getName).thenComparing(Person::getVorname));
        show(personListe);
    }

    private void showPerson(final Person person) {

        System.out.println();

        String str = "";
        str += " Person-Nummer:    " + person.getPersonNummer();
        str += "\n Name und Vorname: " + person.getName() + " " + person.getVorname();
        str += "\n Geburtsdatum:     " + datumToString(person.getGeburtsDatum());
        str += "\n Adresse:          " + person.getAdresse().getStrasse() + ", " + person.getAdresse().getPlz() + " "
                + person.getAdresse().getOrt();
        str += "\n Telefon:          " + person.getKontakt().getTelefon() + ", E-Mail: "
                + person.getKontakt().getEmail();

        System.out.println(str);
    }

    private void show(final List<Person> personen) {
        for (Person p : personen) {
            showPerson(p);
        }
    }

    private String datumToString(final LocalDate datum) {
        int monat = datum.getMonthValue();
        int tag = datum.getDayOfMonth();

        String strMonat = monat < 10 ? "0" + monat : "" + monat;
        String strTag = tag < 10 ? "0" + tag : "" + tag;

        return strTag + "." + strMonat + "." + datum.getYear();
    }
}
