package ch.hslu.informatik.swde.messages.persister;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.hslu.informatik.swde.messages.domain.Message;
import ch.hslu.informatik.swde.messages.persister.api.MessagesDAO;
import ch.hslu.informatik.swde.messages.persister.impl.MessagesDAOImpl;

class MessagesDaoImplIT {

	MessagesDAO dao = new MessagesDAOImpl();
	
	@BeforeEach
	void setUp() throws Exception {
		for (Message m : dao.all()) {
			dao.delete(m.getId());
		}
	}

	@Test
	void testAdd() {
		int anzahl = dao.all().size();
		dao.add(new Message("Das ist eine neue Meldung!"));
		assertEquals(anzahl+1, dao.all().size());
	}

	@Test
	void testUpdate() {
		int anzahl = dao.all().size();
		Message msgSaved = dao.add(new Message("Das ist eine neue Meldung!"));
		assertEquals(anzahl+1, dao.all().size());
		
		String newContent = msgSaved.getContent() + "_UPDATED";
		msgSaved.setContent(newContent);
		dao.update(msgSaved);
		
		assertEquals(newContent, dao.find(msgSaved.getId()).getContent());
	}

	@Test
	void testDeleteMessage() {
		int anzahl = dao.all().size();
		Message msgSaved = dao.add(new Message("Das ist eine neue Meldung!"));
		assertEquals(anzahl+1, dao.all().size());
		
		dao.delete(msgSaved.getId());
		assertEquals(anzahl, dao.all().size());
	}

	@Test
	void testDeleteMessageById() {
		int anzahl = dao.all().size();
		Message msgSaved = dao.add(new Message("Das ist eine neue Meldung!"));
		assertEquals(anzahl+1, dao.all().size());
		
		dao.delete(msgSaved.getId());
		assertEquals(anzahl, dao.all().size());
	}

	@Test
	void testFindById() {
		
		int anzahl = dao.all().size();
		Message msgSaved = dao.add(new Message("Das ist eine neue Meldung!"));
		assertEquals(anzahl+1, dao.all().size());
		
		assertEquals(msgSaved, dao.find(msgSaved.getId()));
	}

	@Test
	void testAllMessages() {
		int anzahl = dao.all().size();
		dao.add(new Message("Das ist eine neue Meldung!"));
		assertEquals(anzahl+1, dao.all().size());
	}

}
