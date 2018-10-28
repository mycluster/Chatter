package com.revature.daos;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.revature.beans.Message;
import com.revature.beans.User;
import com.revature.util.HibernateUtil;

public class MessageDaoImpl implements MessageDao {
	private final static Logger logger = Logger.getLogger(MessageDaoImpl.class);

	/**
	 * Selects all records from the Message table and returns them as a list of
	 * Message objects
	 * 
	 * @return
	 */
	@Override
	public List<Message> selectAllMessage() {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a list of Messages
		List<Message> messages = null;
		logger.info("Selecting all Messages via DAO");

		try {
			// attempt to get all the access levels
			logger.info("Executing FROM Messages query");
			messages = session.createQuery("FROM Message").list();
		} catch (HibernateException e) {
			// if there is a hibernate exception, catch it
			// and print the stack trace
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up after ourselves
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning list of Messages");
		logger.debug("Messages: " + messages.toString());
		return messages;
	}

	/**
	 * Takes in a Message object and inserts a record into the Message table that
	 * corresponds to the Message Object. Returns the primary key of that new record
	 */
	@Override
	public Integer insertMessage(Message message) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to an Integer
		Integer id = null;
		logger.info("Inserting Message via DAO");
		logger.debug("Message: " + message.toString());

		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");
			// perform the insertion
			id = (Integer) session.save(message);
			logger.info("Message inserted");
			// commit the changes
			tx.commit();
			logger.info("Changes committed");
		} catch (HibernateException e) {
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		// return the id of the newly inserted session
		logger.info("Returning Id");
		logger.debug("id: " + id);
		return id;
	}

	/**
	 * Takes in an Integer and deletes the record in the Message table with that
	 * integer as the primary key
	 */
	@Override
	public void deleteMessageById(Integer id) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null Transaction reference
		Transaction tx = null;

		logger.info("Deleting Message via DAO");
		logger.debug("With ID: " + id);
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");
			// delete the Message
			session.delete(session.get(Message.class, id));
			logger.info("Message deleted");
			// commit the changes
			tx.commit();
			logger.info("Changes committed");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);

			// since something went wrong, rollback the transaction
			tx.rollback();
			logger.info("Rolling back the transaction");
		} finally {
			// close the session
			session.close();
			logger.info("Session closed");
		}

	}

	/**
	 * Takes in a Message object and selects the record in the Message table that
	 * corresponds to the object. Then updates the values of that record to match
	 * those of the object. Returns a new Message object that corresponds to the
	 * updated record
	 */
	@Override
	public Message updateMessage(Message message) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to a Message
		Message m = null;

		logger.info("Updating Message via DAO");
		logger.debug("Message: " + message.toString());
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Transaction begins");

			// get the version from the database
			m = (Message) session.get(Message.class, message.getId());
			logger.info("Message retrieved from the database");

			// set the fields to match the input Message
			// we do not update id because they already match
			// also, we should not be going around changing primary keys
			// we also do not update the sent timestamp because
			// it records the initial sent time
			m.setEdited(message.getEdited());
			m.setMessage(message.getMessage());
			m.setReceiver(m.getReceiver());
			m.setSender(m.getSender());
			logger.info("Message from database updated");

			// save the changes
			session.save(m);
			logger.info("Changes saved in session");

			// commit the changes
			tx.commit();
			logger.info("Changes committed");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
			// since something went wrong, rollback the transaction
			tx.rollback();
			logger.info("Rolling back the transaction");
		} finally {
			// close the session
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning updated Message");
		logger.debug("Message: " + m.toString());
		// return the updated Message
		return m;
	}

	/**
	 * Takes in a User object and selects all records from the Message table with
	 * that user as the sender value. Returns those records as a list of Messages
	 */
	@Override
	public List<Message> selectMessageBySender(User sender) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a query
		Query query = null;
		// create the HQL string
		String hql = "FROM Message WHERE sender = :sender";
		// create a null reference to a message
		List<Message> messages = null;

		logger.info("Get Messages by Sender via DAO");
		logger.debug("Sender :" + sender);

		try {
			// generate a query
			query = session.createQuery(hql);
			logger.info("Query generated");

			// set the message parameter
			query.setParameter("sender", sender);
			logger.info("Parameter set");

			// attempt to execute the query
			messages = query.list();
			logger.info("Messages retrieved");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// close the session
			session.close();
			logger.info("Session closed");
		}
		// return the list of Messages
		logger.info("Returning Message list");
		logger.debug("Messages: " + messages);
		return messages;
	}

	/**
	 * Takes in a User object and selects all records from the Message table with
	 * that user as the receiver value. Returns those records as a list of Messages
	 */
	@Override
	public List<Message> selectMessageByReceiver(User receiver) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a query
		Query query = null;
		// create the HQL string
		String hql = "FROM Message WHERE receiver = :receiver";
		// create a null reference to a message
		List<Message> messages = null;

		logger.info("Get Messages by Receiver via DAO");
		logger.debug("Receiver :" + receiver);

		try {
			// generate a query
			query = session.createQuery(hql);
			logger.info("Query generated");

			// set the message parameter
			query.setParameter("receiver", receiver);
			logger.info("Parameter set");

			// attempt to execute the query
			messages = query.list();
			logger.info("Messages retrieved");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// close the session
			session.close();
			logger.info("Session closed");
		}
		// return the list of Messages
		logger.info("Returning Message list");
		logger.debug("Messages: " + messages);
		return messages;
	}

	/**
	 * Takes in two User objects a sender and a receiver and selects all records
	 * from the Message table with the sender as the sender value and the receiver
	 * as the receiver value. Returns those records as a list of Messages
	 */
	@Override
	public List<Message> selectMessageBySenderAndReceiver(User sender, User receiver) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a Criteria
		Criteria crit = null;
		// create a null reference to a message
		List<Message> messages = null;

		logger.info("Get Messages by Sender and Receiver via DAO");
		logger.debug("Sender :" + sender);
		logger.debug("Receiver: " + receiver);

		try {
			// generate a criteria
			crit = session.createCriteria(Message.class);
			logger.info("Criteria generated");

			// add the sender and receiver restrictions
			crit.add(Restrictions.and(Restrictions.eq("sender", sender), Restrictions.eq("receiver", receiver)));
			logger.info("Sender and Receiver restrictions added to the query");
			// attempt to execute the query
			messages = crit.list();
			logger.info("Messages retrieved");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// close the session
			session.close();
			logger.info("Session closed");
		}
		// return the list of Messages
		logger.info("Returning Message list");
		logger.debug("Message: " + messages);
		return messages;
	}

	/**
	 * Takes in two User objects a sender and a receiver as well as an Integer value
	 * n and selects all records from the Message table with the sender as the
	 * sender value and the receiver as the receiver value. Returns only the n most
	 * recent records as a list of Messages
	 */
	@Override
	public List<Message> selectNMostRecentBySenderAndReceiver(User sender, User receiver, Integer n) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a Criteria
		Criteria crit = null;
		// create a null reference to a message
		List<Message> messages = null;

		logger.info("Get N most recent Messages by Sender and Receiver via DAO");
		logger.debug("Sender :" + sender);
		logger.debug("Receiver: " + receiver);
		logger.debug("Number of messages: " + n);

		try {
			// generate a criteria
			crit = session.createCriteria(Message.class);
			logger.info("Criteria generated");

			// add the sender and receiver restrictions
			crit.add(Restrictions.and(Restrictions.eq("sender", sender), Restrictions.eq("receiver", receiver)));
			logger.info("Sender and Receiver restrictions added to the query");

			// set the order by
			crit.addOrder(Order.desc("sentAt"));
			logger.info("Ordering set to desc based on sentAt");

			// set the number of messages we want
			crit.setMaxResults(n);
			logger.info("Max number of messages to retrieve set to" + n);

			// attempt to execute the query
			messages = crit.list();
			logger.info("Messages retrieved");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// close the session
			session.close();
			logger.info("Session closed");
		}
		// return the list of Messages
		logger.info("Returning Message list");
		logger.debug("Message: " + messages);
		return messages;
	}

	/**
	 * Takes in two User objects and selects all records from the Message table
	 * where one of the users is the sender and the other is the receiver. Returns
	 * the records as a list of Message objects
	 */
	@Override
	public List<Message> selectMessageByConversation(User user1, User user2) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a Criteria
		Criteria crit = null;
		// create a null reference to a message
		List<Message> messages = null;

		logger.info("Get Messages by Conservation via DAO");
		logger.debug("User1:" + user1);
		logger.debug("User2: " + user2);

		try {
			// generate a criteria
			crit = session.createCriteria(Message.class);
			logger.info("Criteria generated");

			// add the sender and receiver restrictions
			// specifically, all messages from user1 to user 2 OR from user2 to user1
			crit.add(Restrictions.or(
					Restrictions.and(Restrictions.eq("sender", user1), Restrictions.eq("receiver", user2)),
					Restrictions.and(Restrictions.eq("sender", user2), Restrictions.eq("receiver", user1))));
			logger.info("Sender and Receiver restrictions added to the query");

			// set the order by
			crit.addOrder(Order.desc("sentAt"));
			logger.info("Ordering set to desc based on sentAt");
			
			// attempt to execute the query
			messages = crit.list();
			logger.info("Messages retrieved");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// close the session
			session.close();
			logger.info("Session closed");
		}
		// return the list of Messages
		logger.info("Returning Message list");
		logger.debug("Message: " + messages);
		return messages;
	}

	/**
	 * Takes in two User objects and an Integer n and selects all records from the
	 * Message table where one of the users is the sender and the other is the
	 * receiver. Returns only the n most recent records as a list of Message objects
	 */
	@Override
	public List<Message> selectNMostRecentByConversation(User user1, User user2, Integer n) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a Criteria
		Criteria crit = null;
		// create a null reference to a message
		List<Message> messages = null;

		logger.info("Get N most recent Messages by Conservation via DAO");
		logger.debug("User1:" + user1);
		logger.debug("User2: " + user2);
		logger.debug("Number of messages: " + n);

		try {
			// generate a criteria
			crit = session.createCriteria(Message.class);
			logger.info("Criteria generated");

			// add the sender and receiver restrictions
			// specifically, all messages from user1 to user 2 OR from user2 to user1
			crit.add(Restrictions.or(
					Restrictions.and(Restrictions.eq("sender", user1), Restrictions.eq("receiver", user2)),
					Restrictions.and(Restrictions.eq("sender", user2), Restrictions.eq("receiver", user1))));
			logger.info("Sender and Receiver restrictions added to the query");

			// set the order by
			crit.addOrder(Order.desc("sentAt"));
			logger.info("Ordering set to desc based on sentAt");

			// set the number of messages we want
			crit.setMaxResults(n);
			logger.info("Max number of messages to retrieve set to" + n);
			// attempt to execute the query
			messages = crit.list();
			logger.info("Messages retrieved");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// close the session
			session.close();
			logger.info("Session closed");
		}
		// return the list of Messages
		logger.info("Returning Message list");
		logger.debug("Message: " + messages);
		return messages;
	}

	@Override
	public Message selectMessageById(Integer id) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a Message object
		Message message = null;
		logger.info("Selecting Message by id via DAO");
		logger.info("With id" + id);

		try {
			// attempt to get the Message
			message = (Message) session.get(Message.class, id);
			logger.info("Retrieved Message");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning Message");
		logger.debug("Message: " + message.toString());
		// return the Message
		return message;
	}

}
