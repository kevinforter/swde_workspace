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
package ch.hslu.swde.vereinverwaltung.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Diese Klasse repräsentiert einen Kontakt.
 *
 * @author Jordan Sucur
 * @version 1.0
 */
public final class Kontakt implements Serializable, Comparable<Kontakt> {

    private static final long serialVersionUID = 2078708549270457759L;

    /**
     * Die Telefon-Nummer.
     */
    private String telefon;

    /**
     * Die E-Mail Adresse.
     */
    private String email;

    /**
     * Erstellt eine neues Kontakt-Objekt.
     */
    public Kontakt() {

    }

    /**
     * Erstellt ein neues Kontakt-Objekt und initialisier es vollständig.
     *
     * @param telefon die Telefon-Nummer
     * @param email die E-Mail Adresse
     */
    public Kontakt(final String telefon, final String email) {
        this.telefon = telefon;
        this.email = email;
    }

    /**
     * Liefert die Telefon-Nummer zurück.
     *
     * @return die Telefon-Nummer
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * Setzt die Telefon-Nummer.
     *
     * @param telefon die Telefon-Nummer
     */
    public void setTelefon(final String telefon) {
        this.telefon = telefon;
    }

    /**
     * Liefert die E-Mail Adresse zurück.
     *
     * @return die E-Mail Adresse
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setzt die E-Mail Adresse.
     *
     * @param email die E-Mail Adresse
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Kontakt other)) {
            return false;
        }
        return Objects.equals(this.telefon, other.telefon) && Objects.equals(this.email, other.email);
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(telefon, email);
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Kontakt [telefon=" + telefon + ", email=" + email + "]";
    }

    @Override
    public int compareTo(final Kontakt param) {
        if (email.compareTo(param.email) == 0) {
            return telefon.compareTo(param.telefon);
        }
        return email.compareTo(param.email);
    }
}
