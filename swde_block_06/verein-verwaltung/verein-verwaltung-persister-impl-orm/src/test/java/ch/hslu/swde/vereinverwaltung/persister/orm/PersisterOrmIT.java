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

import ch.hslu.swde.vereinverwaltung.domain.Person;
import ch.hslu.swde.vereinverwaltung.persister.api.Persister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testfälle für
 * {@link PersisterOrm}.
 */
public class PersisterOrmIT {

    /**
     * Test of
     * .
     */
    @BeforeEach
    void setUp() {
        Util.cleanDatabase();
    }

    @Test
    void speichern() throws Exception {

        List<Person> perList = Util.createPersonListe();

        Persister dao = new PersisterOrm();
        for (Person per : perList) {
            dao.speichern(per);
        }

        int anzahl = dao.alle().size();
        assertEquals(perList.size(), anzahl);

        for (Person per : dao.alle()) {
            Person perFromDb = dao.finde(per.getId());
            assertNotNull(perFromDb);
        }
    }
}
