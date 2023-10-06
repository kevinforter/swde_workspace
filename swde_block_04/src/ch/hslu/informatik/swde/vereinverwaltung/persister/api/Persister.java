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
package ch.hslu.informatik.swde.vereinverwaltung.persister.api;

import java.util.List;

import ch.hslu.informatik.swde.vereinverwaltung.domain.Person;

/**
 * Diese Schnittstelle gibt die Funktionalitäten vor, welche für die
 * Persistierung von Pesonendaten benötigt werden.
 *
 * @author Jordan Sucur
 * @version 1.0
 */
public interface Persister {

    /**
     * Speichert die übergebene Person.
     *
     * @param person die zu speichernde Person
     * @return die gespeicherte Person
     * @throws Exception falls das Speichern misslingen sollte
     */
    Person speichern(Person person) throws Exception;

    /**
     * Aktualisiert die übergebene Person.
     *
     * @param person die neue Person
     * @return die aktualisierte Person
     * @throws Exception falls die Aktualisierung misslingen sollte
     */
    Person aktualisieren(Person person) throws Exception;

    /**
     * Entfernt die übergehbene Person aus dem Datenbestand.
     *
     * @param person die zu löschende Person
     * @return <b>true</b> falls erfolgreich sonst <b>false</b>.
     * @throws Exception falls das Löschen misslingen sollte
     */
    boolean loeschen(Person person) throws Exception;

    /**
     * Entfernt die Person für die übergebene Person-Nummer aus dem Datenbestand.
     *
     * @param personNummer die Person-Nummer der zu löschende Person
     * @return <b>true</b> falls erfolgreich sonst <b>false</b>.
     * @throws Exception falls das Löschen misslingen sollte
     */
    boolean loeschen(int personNummer) throws Exception;

    /**
     * Liefert die Personen für die übergebenen Namen und Vornamen zurück.
     *
     * @param name der Name
     * @param vorname der Vorname
     * @return die Liste mit allen gefundenen Personen
     * @throws Exception falls die Suche misslingen sollte
     */
    List<Person> finde(String name, String vorname) throws Exception;

    /**
     * Liefert die Person für die übergebene Person-Nummer zurück.
     *
     * @param personNummer die Person-Nummer
     * @return die gefundene Person
     * @throws Exception falls die Suche misslingen sollte
     */
    Person finde(int personNummer) throws Exception;

    /**
     * Liefert alle Personen zurück.
     *
     * @return die Liste mit allen im Datenbestand enthaltenen Personen
     * @throws Exception falls die Suche misslingen sollte
     */
    List<Person> alle() throws Exception;
}
