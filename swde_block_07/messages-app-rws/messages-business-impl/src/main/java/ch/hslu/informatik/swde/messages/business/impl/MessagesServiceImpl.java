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
package ch.hslu.informatik.swde.messages.business.impl;

import java.util.List;

import ch.hslu.informatik.swde.messages.business.api.MessagesService;
import ch.hslu.informatik.swde.messages.domain.Message;
import ch.hslu.informatik.swde.messages.persister.api.MessagesDAO;
import ch.hslu.informatik.swde.messages.persister.impl.MessagesDAOImpl;

/**
 * Diese Klasse stellt die Implementierung der Schnittstelle {@link ch.hslu.informatik.swde.messages.service.api.MessagesService} zur Verfügung.
 *
 * @author Jordan Sucur
 * @version 1.0
 */
public class MessagesServiceImpl implements MessagesService {

	private MessagesDAO persister = new MessagesDAOImpl();

	@Override
	public Message addMessage(Message message) {
		return persister.add(message);
	}

	@Override
	public Message updateMessage(Message message) {
		// update
		return persister.update(message);
	}

	@Override
	public Message deleteMessage(Message message) {
		return persister.delete(message.getId());
	}

	@Override
	public Message deleteById(int id) {
		return persister.delete(id);
	}
	
	@Override
	public Message findById(int id) {
		return persister.find(id);
	}

	@Override
	public List<Message> allMessages() {
		return persister.all();
	}
}