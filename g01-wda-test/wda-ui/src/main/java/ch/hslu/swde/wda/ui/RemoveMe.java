/*
 * Copyright 2022 Roland Gisler, HSLU Informatik, Switzerland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.swde.wda.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Dummy-Klasse. Löschen, sobald eigene Quellen vorhanden sind.
 */
public final class RemoveMe {

    private static final Logger LOG = LogManager.getLogger(RemoveMe.class);

    private final ch.hslu.swde.wda.business.RemoveMe business;

    /**
     * Konstruktor.
     */
    RemoveMe() {
        this.business = new ch.hslu.swde.wda.business.RemoveMe();
        LOG.info("Log-Message aus dem CLI: {}", this.getFoo());
    }

    /**
     * Dummy-Methode.
     *
     * @return Hallo.
     */
    public String getFoo() {
        return "Hallo " + this.business.getFoo();
    }

    /**
     * main()-Methode für Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        new RemoveMe();
    }
}
