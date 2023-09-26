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

/**
 * Diese Klasse repräsentiert eine Adresse.
 *
 * @author Jordan Sucur
 * @version 1.0
 */
import java.io.Serializable;
import java.util.Objects;

public final class Adresse implements Comparable<Adresse>, Serializable {

    private static final long serialVersionUID = -1915527071263231266L;

    /**
     * Die Strasse.
     */
    private String strasse;
    /**
     * Die PLZ.
     */
    private int plz;
    /**
     * Die Ortschaft.
     */
    private String ort;

    /**
     * Erstellt eine neues Adresse-Objekt.
     */
    public Adresse() {
    }

    /**
     * Erstellt ein Adresse-Objekt und initialisiert es vollständig.
     *
     * @param strasse die Strasse.
     * @param plz die PLZ
     * @param ort die Ortschaft
     */
    public Adresse(final String strasse, final int plz, final String ort) {
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
    }

    /**
     * Liefert die Strasse zurück.
     *
     * @return die Strasse
     */
    public String getStrasse() {
        return strasse;
    }

    /**
     * Setzt die Strasse.
     *
     * @param strasse die Strasse
     */
    public void setStrasse(final String strasse) {
        this.strasse = strasse;
    }

    /**
     * Liefert die PLZ zurück.
     *
     * @return die PLZ
     */
    public int getPlz() {
        return plz;
    }

    /**
     * Setzt die PLZ.
     *
     * @param plz die PLZ
     */
    public void setPlz(final int plz) {
        this.plz = plz;
    }

    /**
     * Liefert den Ort zurück.
     *
     * @return der Ort
     */
    public String getOrt() {
        return ort;
    }

    /**
     * Setzt den Ort.
     *
     * @param ort der Ort
     */
    public void setOrt(final String ort) {
        this.ort = ort;
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.strasse, this.plz, this.ort);
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Adresse other)) {
            return false;
        }
        return Objects.equals(this.strasse, other.strasse)
                && Objects.equals(this.plz, other.plz)
                && Objects.equals(this.ort, other.ort);
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Adresse [strasse=" + strasse + ", plz=" + plz + ", ort=" + ort + "]";
    }

    @Override
    public int compareTo(final Adresse param) {
        if (Integer.compare(this.plz, param.plz) == 0) {
            if (this.ort.compareTo(param.ort) == 0) {
                return strasse.compareTo(param.strasse);
            }
            return this.ort.compareTo(param.ort);
        }
        return Integer.compare(this.plz, param.plz);
    }
}
