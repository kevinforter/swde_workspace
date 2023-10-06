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
package ch.hslu.informatik.swde.vereinverwaltung.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Diese Klasse repräsentiert eine Person.
 *
 * @author Jordan Sucur
 * @version 1.0
 */
public final class Person implements Comparable<Person>, Serializable {

    private static final long serialVersionUID = 8599820025380556938L;

    /**
     * Die Person-Nummer.
     */
    private int personNummer;

    /**
     * Der Name.
     */
    private String name;

    /**
     * Der Vorname.
     */
    private String vorname;

    /**
     * Das Geburtsdatum.
     */
    private LocalDate geburtsDatum;

    /**
     * Der Kontakt.
     */
    private Kontakt kontakt;

    /**
     * Die Adresse.
     */
    private Adresse adresse;

    /**
     * Erstellt eine neues Person-Objekt.
     */
    public Person() {
        this(null, null, null, null, null);
    }

    /**
     * Erstellt ein neues Person-Objekt und initialisiert es.
     *
     * @param name der Name
     * @param vorname der Vorname
     * @param geburtsDatum das Gerurtsdatum
     * @param kontakt der Kontakt
     * @param adresse die Adresse.
     */
    public Person(final String name, final String vorname, final LocalDate geburtsDatum, final Kontakt kontakt, final Adresse adresse) {
        this.name = name;
        this.vorname = vorname;
        this.geburtsDatum = geburtsDatum;
        this.kontakt = kontakt;
        this.adresse = adresse;
    }

    /**
     * Liefert die Person-Nummer zurück.
     *
     * @return die Perosn-Nummer
     */
    public int getPersonNummer() {
        return personNummer;
    }

    /**
     * Setzt die Person-Nummer.
     *
     * @param personNummer die Person-Nummer
     */
    public void setPersonNummer(final int personNummer) {
        /*
         * Die 'personNummer' soll nicht explizit gesetzt werden! Der Wert wird bei der
         * Persistierung gesetzt!
         */
        this.personNummer = personNummer;
    }

    /**
     * Liefert den Namen zurück.
     *
     * @return der Name
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen.
     *
     * @param name der Name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Liefert den Vornamen zurück.
     *
     * @return der Vorname
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Setzt den Vornamen.
     *
     * @param vorname der Vorname
     */
    public void setVorname(final String vorname) {
        this.vorname = vorname;
    }

    /**
     * Liefert das Geburtsdatum zurück.
     *
     * @return das Geburtsdatum
     */
    public LocalDate getGeburtsDatum() {
        return geburtsDatum;
    }

    /**
     * Setzt das Geburtsdatum.
     *
     * @param geburtsDatum das Geburtsdatum
     */
    public void setGeburtsDatum(final LocalDate geburtsDatum) {
        this.geburtsDatum = geburtsDatum;
    }

    /**
     * Liefert den Kontant zurück.
     *
     * @return der Kontakt
     */
    public Kontakt getKontakt() {
        return kontakt;
    }

    /**
     * Setzt den Kontakt.
     *
     * @param kontakt der Kontakt
     */
    public void setKontakt(final Kontakt kontakt) {
        this.kontakt = kontakt;
    }

    /**
     * Liefert die Adresse zurück.
     *
     * @return die Adresse
     */
    public Adresse getAdresse() {
        return adresse;
    }

    /**
     * Setzt die Adresse.
     *
     * @param adresse die Adresse
     */
    public void setAdresse(final Adresse adresse) {
        this.adresse = adresse;
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Person other)) {
            return false;
        }
        return Objects.equals(this.personNummer, other.personNummer)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.vorname, other.vorname);
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(personNummer, name, vorname);
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Person [name=" + name + ", vorname=" + vorname + ", geburtsDatum=" + datumToString(geburtsDatum)
                + ", kontakt=" + kontakt + ", adresse=" + adresse + "]";
    }

    private String datumToString(final LocalDate datum) {
        return datum.getDayOfMonth() + "." + datum.getMonthValue() + "." + datum.getYear();
    }

    @Override
    public int compareTo(final Person param) {
        // Nach Nachname, Vorname und Geburtsdatum vergleichen
        if (name.compareTo(param.name) == 0) {
            if (vorname.compareTo(param.vorname) == 0) {
                return geburtsDatum.compareTo(param.geburtsDatum);
            }
            return vorname.compareTo(param.vorname);
        }
        return name.compareTo(param.name);
    }
}
