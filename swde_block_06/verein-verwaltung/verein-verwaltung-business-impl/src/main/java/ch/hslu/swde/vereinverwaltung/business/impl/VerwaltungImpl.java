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
package ch.hslu.swde.vereinverwaltung.business.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.swde.vereinverwaltung.business.api.Verwaltung;
import ch.hslu.swde.vereinverwaltung.domain.Person;
import ch.hslu.swde.vereinverwaltung.persister.api.Persister;

/**
 * Diese Klasse stellt eine konkrete Implementeirung der Schnittstelle
 * 'Verwaltung' dar.
 */
public final class VerwaltungImpl implements Verwaltung {

    private static final Logger LOG = LogManager.getLogger(VerwaltungImpl.class);

    /**
     * Persister-Komponente.
     */
    private final Persister persister;

    public VerwaltungImpl(final Persister persister) {
        this.persister = persister;
    }

    /*
     * @see prg.vereinverwaltung.business.api.Verwaltung#personHinzuguefen(prg.
     * vereinverwaltung.domain.Person)
     */
    @Override
    public Person personHinzuguefen(final Person person) throws Exception {
        Person newPerson = persister.speichern(person);
        LOG.info("PERSON HINZUGEFUEGT: " + newPerson.toString());
        return newPerson;
    }

    /*
     * @see prg.vereinverwaltung.business.api.Verwaltung#personAktualisieren(prg.
     * vereinverwaltung.domain.Person)
     */
    @Override
    public Person personAktualisieren(final Person person) throws Exception {
        LOG.info("PERSON WIRD AKTUALISIERT: " + person.toString());
        Person pAktualisiert = persister.aktualisieren(person);
        LOG.info("PERSON AKTUALISIERT: " + pAktualisiert.toString());

        return pAktualisiert;
    }

    /*
     * @see prg.vereinverwaltung.business.api.Verwaltung#personLoeschen(prg.
     * vereinverwaltung.domain.Person)
     */
    @Override
    public boolean personLoeschen(final Person person) throws Exception {
        boolean success = persister.loeschen(person);
        LOG.info("PERSON GELOESCHT: " + person.toString());

        return success;
    }

    /*
     * @see prg.vereinverwaltung.business.api.Verwaltung#finde(java.lang.String,
     * java.lang.String)
     */
    @Override
    public List<Person> finde(final String name, final String vorname) throws Exception {
        return persister.finde(name, vorname);
    }

    /*
     * @see prg.vereinverwaltung.business.api.Verwaltung#finde(int)
     */
    @Override
    public Person finde(final int personNummer) throws Exception {
        return persister.finde(personNummer);
    }

    /*
     * @see prg.vereinverwaltung.business.api.Verwaltung#alle()
     */
    @Override
    public List<Person> alle() throws Exception {
        return persister.alle();
    }
}
