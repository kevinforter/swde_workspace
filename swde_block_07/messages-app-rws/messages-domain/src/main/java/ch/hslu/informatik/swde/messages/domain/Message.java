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
package ch.hslu.informatik.swde.messages.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

/**
 * Diese Klasse bildet eine einfache Nachricht ab.
 *
 * @author Jordan Sucur
 * @version 1.0
 */
@Entity
public class Message implements Serializable {

	private static final long serialVersionUID = -899140087103121778L;
	
	@Id
	@GeneratedValue
	private int id;
	private String content;

	/**
	 * Standardkonstruktor.
	 */
	public Message() {

	}

	/**
	 * Der parametrisierte Konstruktor.
	 * @param content der Inhalt der Nachricht
	 */
	public Message(String content) {
		this.content = content;
	}

	/**
	 * Liefert die Id zurück.
	 * @return die Id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setzt die Id.
	 * @param id die Id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Liefert den Inhalt der Nachricht zurück.
	 * @return der Inhalt
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Setzt den Inhalt der Nachricht.
	 * @param content der Inhalt
	 */
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Message)) {
			return false;
		}
		Message other = (Message) obj;
		return Objects.equals(content, other.content) && id == other.id;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + "]";
	}

}
