package ch.hslu.informatik.swde.messages.persister.impl;

import java.util.ArrayList;
import java.util.List;

import ch.hslu.informatik.swde.messages.domain.Message;
import ch.hslu.informatik.swde.messages.persister.api.MessagesDAO;
import ch.hslu.informatik.swde.messages.persister.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class MessagesDAOImpl implements MessagesDAO {
	
	@Override
	public Message add(Message message) {

		EntityManager em = JpaUtil.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();
			em.persist(message);
			transaction.commit();

			return message;

		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			throw e;
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
	}

	@Override
	public Message update(Message message) {

		EntityManager em = JpaUtil.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();
			em.merge(message);
			transaction.commit();

			return message;

		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			throw e;
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
	}

	@Override
	public Message delete(int id) {

		EntityManager em = JpaUtil.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			Message msgToDelete = em.find(Message.class, id);

			transaction.begin();
			em.remove(em.merge(msgToDelete));
			transaction.commit();

			return msgToDelete;

		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			throw e;
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
	}

	@Override
	public Message find(int id) {

		EntityManager em = JpaUtil.createEntityManager();

		try {
			return em.find(Message.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
	}

	@Override
	public List<Message> all() {

		List<Message> messages;
		
		EntityManager em = JpaUtil.createEntityManager();

		try {
			TypedQuery<Message> tQry = em.createQuery("SELECT e FROM Message e ORDER BY e.id ASC", Message.class);
			messages = tQry.getResultList();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
		
		return messages != null ? messages : new ArrayList<Message>();
	}

}
