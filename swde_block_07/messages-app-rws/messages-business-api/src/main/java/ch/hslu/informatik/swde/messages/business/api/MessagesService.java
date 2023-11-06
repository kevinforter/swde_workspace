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
package ch.hslu.informatik.swde.messages.business.api;

import java.util.List;

import ch.hslu.informatik.swde.messages.domain.Message;

/**
 * Diese Schnittstelle gibt die Funktionalitäten vor, welche für die Verwaltung
 * von Nachrichten benötigt werden.
 *
 * @author Jordan Sucur
 * @version 1.0
 */
public interface MessagesService {

	/**
	 * Fügt die übergebene Nachricht dem Datenbestand hinzu.
	 * @param message die hinzufügende Nachricht
	 * @return die hinzugefügte Nachricht
	 */
	Message addMessage(Message message);

	/**
	 * Aktualisiert die spezifizierte Nachricht.
	 * @param message die zu aktualisierende Nachricht
	 * @return die aktualisierte Nachricht
	 */
	Message updateMessage(Message message);

	/**
	 * Löscht die spezifizierte Nachricht.
	 * @param message die zu löschende Nachricht
	 * @return die gelöschte Nachricht
	 */
	Message deleteMessage(Message message);

	/**
	 * Löscht die Nachricht für die spezifizierte Id.
	 * @param id die Id der zu löschende Nachricht
	 * @return die gelöschte Nachricht
	 */
	Message deleteById(int id);

	/**
	 * Liefert die Nachricht für die spezifizierte Id zurück, falls vorhanden, sonst
	 * <b>null</b>.
	 * 
	 * @param id die Nachricht Id
	 * @return die Nachricht
	 */
	Message findById(int id);

	/**
	 * Liefert alle Nachrichten zurück.
	 * 
	 * @return liste mit allen Nachrichten
	 */
	List<Message> allMessages();

}