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
package ch.hslu.swde.vereinverwaltung.business.api;

import java.util.List;

import ch.hslu.swde.vereinverwaltung.domain.Person;

/**
 * Diese Schnittstelle gibt die Funktionalitäten vor, welche für die Verwaltung
 * von Pesonendaten benötigt werden.
 *
 * @author Jordan Sucur
 * @version 1.0
 */
public interface Verwaltung {

    /**
     * Diese Methode fügt die übergebene Person in den Datenbestand ein.
     *
     * @param person die Person
     * @return die Person
     * @throws Exception falls das Hinzufügen misslingen sollte
     */
    Person personHinzuguefen(Person person) throws Exception;

    /**
     * Diese Methode aktualisiert die übergebene Person.
     *
     * @param person die neue Person
     * @return die aktualisierte Person
     * @throws Exception falls das Aktualisieren misslingen sollte
     */
    Person personAktualisieren(Person person) throws Exception;

    /**
     * Diese Methode entfernt die übergebene Person aus dem Datenbestand.
     *
     * @param person die zu löschende Person
     * @return <b>true</b> falls erfolgreich sonst <b>false</b>
     * @throws Exception falls das Löschen misslingen sollte
     */
    boolean personLoeschen(Person person) throws Exception;

    /**
     * Diese Methode liefert alle Personen für die übergebenen Namen und
     * Vornamen zurück.
     *
     * @param name der Name
     * @param vorname der Vorname
     * @return die Liste mit allen gefundenen Personen
     * @throws Exception falls die Suche misslingen sollte.
     */
    List<Person> finde(String name, String vorname) throws Exception;

    /**
     * Diese Methode liefert die Personen für die übergebene Person-Nummer
     * zurück.
     *
     * @param personNummer die Person-Nummer
     * @return die Person
     * @throws Exception falls die Suche misslingen sollte
     */
    Person finde(int personNummer) throws Exception;

    /**
     * Diese Methode liefert alle Personen zurück.
     *
     * @return die Liste mit allen im Datenbestand gefundenen Personen
     * @throws Exception falls die Suche misslingen sollte
     */
    List<Person> alle() throws Exception;
}
