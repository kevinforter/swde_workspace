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
package ch.hslu.swde.vereinverwaltung;

import java.io.File;

import ch.hslu.swde.vereinverwaltung.persister.txt.PersisterTxt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.swde.vereinverwaltung.business.api.Verwaltung;
import ch.hslu.swde.vereinverwaltung.business.impl.VerwaltungImpl;
import ch.hslu.swde.vereinverwaltung.persister.api.Persister;
import ch.hslu.swde.vereinverwaltung.ui.UI;

/**
 * Diese Klasse repräsentiert die Vereinverwaltung.
 *
 * @author Jordan Sucur
 * @version 1.0
 *
 */
public final class VereinVerwaltungApp {

    private static final Logger LOG = LogManager.getLogger(VereinVerwaltungApp.class);

    public static final int FILE_PERSISTER_PLAIN_TEXT = 1;
    public static final int FILE_PERSISTER_SERIALIZATION = 2;

    /**
     * Privater Konstruktor.
     */
    private VereinVerwaltungApp() {
    }

    public static void main(final String[] args) {

        try {

            /* Persister erstellen */
            Persister persister = getPersister(FILE_PERSISTER_PLAIN_TEXT);

            /* Verwaltung-Komponente */
            Verwaltung verwaltung = new VerwaltungImpl(persister);

            /* UI-Komponente */
            UI ui = new UI(verwaltung);

            ui.execute();

        } catch (Exception e) {
            LOG.error(">> Fehler: ", e);
            System.out.println("Die Applikation wurde aus einem unbekannten Grund heruntergefahren.");
        }
    }

    /* Hilfsmethode, um die Persisterwahl zu vereinfachen */
    private static Persister getPersister(final int persisterTyp) throws Exception {

        Persister persister = null;
        String fileName = null;
        String userHome = System.getProperty("user.home");

        /* File Name - Daten als Plain Text */
        String fileNameText = "vereinDatenText.txt";

        /* File Name - Daten serialisiert */
        String fileNameSerializierung = "vereinDatenSerialized.txt";

        if (persisterTyp == FILE_PERSISTER_PLAIN_TEXT) {
            fileName = fileNameText;
            File file = new File(userHome + File.separator + fileName);
            persister = new PersisterTxt(file);
        } else if (persisterTyp == FILE_PERSISTER_SERIALIZATION) {
            fileName = fileNameSerializierung;
            File file = new File(userHome + File.separator + fileName);
            persister = new ch.hslu.swde.vereinverwaltung.persister.ser.PersisterSer(file);
        }

        return persister;
    }
}
