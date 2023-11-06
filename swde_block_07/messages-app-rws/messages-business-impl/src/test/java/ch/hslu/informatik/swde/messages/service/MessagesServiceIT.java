package ch.hslu.informatik.swde.messages.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.hslu.informatik.swde.messages.business.api.MessagesService;
import ch.hslu.informatik.swde.messages.business.impl.MessagesServiceImpl;
import ch.hslu.informatik.swde.messages.domain.Message;

class MessagesServiceIT {

	MessagesService service = new MessagesServiceImpl();

	@BeforeEach
	void setUp() throws Exception {

		for (Message m : service.allMessages()) {
			service.deleteById(m.getId());
		}
	}

	@Test
	void testAdd() {
		int anzahl = service.allMessages().size();
		service.addMessage(new Message("Das ist eine neue Meldung!"));
		assertEquals(anzahl + 1, service.allMessages().size());
	}

	@Test
	void testAddNullMessage_shouldThrowException() {
		assertThrows(IllegalArgumentException.class, () -> service.addMessage(null));
	}

	@Test
	void testUpdate() {
		int anzahl = service.allMessages().size();
		Message msgSaved = service.addMessage(new Message("Das ist eine neue Meldung!"));
		assertEquals(anzahl + 1, service.allMessages().size());

		String newContent = msgSaved.getContent() + "_UPDATED";
		msgSaved.setContent(newContent);
		service.updateMessage(msgSaved);

		assertEquals(newContent, service.findById(msgSaved.getId()).getContent());
	}

	@Test
	void testDeleteMessage() {
		int anzahl = service.allMessages().size();
		Message msgSaved = service.addMessage(new Message("Das ist eine neue Meldung!"));
		assertEquals(anzahl + 1, service.allMessages().size());

		service.deleteMessage(msgSaved);
		assertEquals(anzahl, service.allMessages().size());
	}

	@Test
	void testDeleteMessageById() {
		int anzahl = service.allMessages().size();
		Message msgSaved = service.addMessage(new Message("Das ist eine neue Meldung!"));
		assertEquals(anzahl + 1, service.allMessages().size());

		service.deleteById(msgSaved.getId());
		assertEquals(anzahl, service.allMessages().size());
	}

	@Test
	void testFindById() {

		int anzahl = service.allMessages().size();
		Message msgSaved = service.addMessage(new Message("Das ist eine neue Meldung!"));
		assertEquals(anzahl + 1, service.allMessages().size());

		assertEquals(msgSaved, service.findById(msgSaved.getId()));
	}

	@Test
	void testAllMessages() {
		int anzahl = service.allMessages().size();
		service.addMessage(new Message("Das ist eine neue Meldung!"));
		assertEquals(anzahl + 1, service.allMessages().size());
	}

}
