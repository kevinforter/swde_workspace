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
package ch.hslu.swde.vereinverwaltung.persister.orm;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.swde.vereinverwaltung.domain.Person;
import ch.hslu.swde.vereinverwaltung.persister.api.Persister;

/**
 * Diese Klasse stellt eine konkrete Implementierung der Schnittstelle
 * 'Persister' dar. Dabei werden Objektinhalte als Plain-Text in der Datei
 * abgelegt.
 *
 * @author jsucur
 * @version 1.0
 */
public final class PersisterOrm implements Persister {

    private static final String DELIMITER = "#";

    private static final Logger LOGGER = LogManager.getLogger(PersisterOrm.class);

    /* Hilfsvariable, um ID-Werte (personNummer) zu generieren */
    private static int nextId;

    public PersisterOrm() throws Exception {
        nextId = 1;
    }

    /*
     * @see prg.vereinverwaltung.persister.api.Persister#speichern(prg.
     * vereinverwaltung.domain.Person)
     */
    @Override
    public Person speichern(final Person person) throws Exception{

        EntityManager em = JpaUtil.createEntityManager();

        try {

            if (person.getPersonNummer() == 0) {
                /* Neues Objekt: Person-Nummer setzen */
                person.setPersonNummer(nextId++);
            }

            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();

            return em.find(Person.class, person.getId());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /*
     * @see prg.vereinverwaltung.persister.api.Persister#aktualisieren(prg.
     * vereinverwaltung.domain.Person)
     */
    @Override
    public Person aktualisieren(final Person person) {

        EntityManager em = JpaUtil.createEntityManager();

        Person perToUpdate = em.find(Person.class, person.getId());


        try {
            em.getTransaction().begin();
            perToUpdate = person;
            em.merge(perToUpdate);
            em.getTransaction().commit();

            return em.find(Person.class, perToUpdate.getId());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
        return em.find(Person.class, perToUpdate.getId());
    }

    /*
     * @see prg.vereinverwaltung.persister.api.Persister#loeschen(prg.
     * vereinverwaltung.domain.Person)
     */
    @Override
    public boolean loeschen(final Person person) {
        return loeschen(person.getPersonNummer());
    }

    /*
     * @see prg.vereinverwaltung.persister.api.Persister#loeschen(int)
     */
    @Override
    public boolean loeschen(final int personNummer) {

        EntityManager em = JpaUtil.createEntityManager();

        Person perToDelete = em.find(Person.class, personNummer);

        if (perToDelete != null) {

            try {
                em.getTransaction().begin();
                em.remove(perToDelete);
                em.getTransaction().commit();
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            } finally {
                if (em.isOpen()) {
                    em.close();
                }
            }
        }
        return true;
    }

    /*
     * @see prg.vereinverwaltung.persister.api.Persister#finde(java.lang.String,
     * java.lang.String)
     */
    @Override
    public List<Person> finde(final String name, final String vorname) {

        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Person> tQry = em.createQuery("SELECT e FROM Person e WHERE e.name=:name AND e.vorname=:vorname" , Person.class);
        tQry.setParameter("name", name);
        tQry.setParameter("vorname", vorname);
        List<Person> perListe = tQry.getResultList();

        return perListe != null ? perListe : new ArrayList<>();
    }

    /*
     * @see prg.vereinverwaltung.persister.api.Persister#finde(int)
     */
    @Override
    public Person finde(final int personNummer) {

        EntityManager em = JpaUtil.createEntityManager();

        Person perFromDB = em.find(Person.class, personNummer);

        em.close();

        return perFromDB;
    }

    /*
     * @see prg.vereinverwaltung.persister.api.Persister#alle()
     */
    @Override
    public List<Person> alle() {

        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Person> tQry = em.createQuery("SELECT e FROM Person e", Person.class);
        List<Person> perListe = tQry.getResultList();

        return perListe != null ? perListe : new ArrayList<>();
    }
}