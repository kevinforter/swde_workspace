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
package ch.hslu.informatik.swde.vereinverwaltung;

import ch.hslu.informatik.swde.vereinverwaltung.business.api.Verwaltung;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.swde.vereinverwaltung.business.impl.VerwaltungImpl;
import ch.hslu.informatik.swde.vereinverwaltung.ui.UI;

/**
 * Diese Klasse repräsentiert die Vereinverwaltung.
 *
 * @author Jordan Sucur
 * @version 1.0
 *
 */
public final class VereinVerwaltungApp {

    private static final Logger LOG = LogManager.getLogger(VereinVerwaltungApp.class);


    /**
     * Privater Konstruktor.
     */
    private VereinVerwaltungApp() {
    }

    public static void main(final String[] args) {

        try {

            /* Verwaltung-Komponente */
            Verwaltung verwaltung = new VerwaltungImpl();

            /* UI-Komponente */
            UI ui = new UI(verwaltung);

            ui.execute();

        } catch (Exception e) {
            LOG.error(">> Fehler: ", e);
            System.out.println("Die Applikation wurde aus einem unbekannten Grund heruntergefahren.");
            System.exit(0);
        }
    }


}
