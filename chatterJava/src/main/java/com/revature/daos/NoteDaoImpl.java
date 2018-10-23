package com.revature.daos;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Note;
import com.revature.beans.User;
import com.revature.util.HibernateUtil;

public class NoteDaoImpl implements NoteDao {
	private final static Logger logger = Logger.getLogger(NoteDaoImpl.class);

	/**
	 * Selects all records from the Note table and returns them as a list of Note
	 * objects
	 */
	@Override
	public List<Note> selectAllNote() {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a list of Notes
		List<Note> notes = null;
		logger.info("Selecting all Notes via DAO");

		try {
			// attempt to get all the access levels
			logger.info("Executing FROM Notes query");
			notes = session.createQuery("FROM Note").list();
		} catch (HibernateException e) {
			// if there is a hibernate exception, catch it
			// and print the stack trace
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up after ourselves
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning list of Notes");
		logger.debug("Notes: " + notes.toString());
		return notes;
	}

	/**
	 * Takes in an Integer whose value corresponds to the primary key of a record in
	 * the Note table and returns that record as a Note object
	 */
	@Override
	public Note selectNoteById(Integer id) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a Note object
		Note note = null;
		logger.info("Selecting Note by id via DAO");
		logger.info("With id" + id);

		try {
			// attempt to get the Note
			note = (Note) session.get(Note.class, id);
			logger.info("Retrieved Note");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning Note");
		logger.debug("Note: " + note.toString());
		// return the Note
		return note;
	}

	/**
	 * Takes in a Note object and inserts a record in to the Note table whose data
	 * corresponds to the Note object. Returns the primary key of the newly inserted
	 * record as an Integer
	 */
	@Override
	public Integer insertNote(Note note) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to an Integer
		Integer id = null;
		logger.info("Inserting Note via DAO");
		logger.debug("Note: " + note.toString());

		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");
			// perform the insertion
			id = (Integer) session.save(note);
			logger.info("Note inserted");
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
	 * Takes in an Integer object whose value corresponds to the primary key of a
	 * record in the Note table and deletes that record.
	 */
	@Override
	public void deleteNoteById(Integer id) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null Transaction reference
		Transaction tx = null;
		logger.info("Deleting Note via DAO");
		logger.debug("With ID: " + id);
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");
			// delete the Note
			session.delete(session.get(Note.class, id));
			logger.info("Note deleted");
			// commit the changes
			tx.commit();
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
	 * Takes in a Note object and updates the record in the Note table with the
	 * primary key that corresponds to the Note object's id field so that the data
	 * in the record matches the values in the fields of the Note object. Returns a
	 * new Note object corresponding to the updated record
	 */
	@Override
	public Note updateNote(Note note) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to a Note
		Note n = null;

		logger.info("Updating Note via DAO");
		logger.debug("Note: " + note.toString());
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Transaction begins");

			// get the version from the database
			n = (Note) session.get(Note.class, note.getId());
			logger.info("Note retrieved from the database");

			// set the type, location, name, and owner to match the input Note
			// we do not update id because they already match
			// also, we should not be going around changing primary keys
			// we don't need to manually update lastEdited because
			// everytime a setter is called lastEdited is updated
			n.setLocation(note.getLocation());
			n.setOwner(note.getOwner());
			n.setType(note.getType());
			n.setName(note.getName());
			logger.info("Note from database updated");

			// save the changes
			session.save(n);
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
		logger.info("Returning updated Note");
		logger.debug("Note: " + n.toString());
		// return the updated Note
		return n;
	}

	/**
	 * Takes in a User object and returns all of the records in the Note table whose
	 * owner column matches that user as a list of Note objects
	 */
	@Override
	public List<Note> selectAllNotesByOwner(User owner) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a query
		Query query = null;
		// create the HQL string
		String hql = "FROM Note WHERE owner = :owner";
		// create a null reference to a list of Notes
		List<Note> notes = null;

		logger.info("Get Note by Owner via DAO");
		logger.debug("Owner :" + owner.toString());

		try {
			// generate a query
			query = session.createQuery(hql);
			logger.info("Query generated");

			// set the user parameter
			query.setParameter("owner", owner);
			logger.info("Parameter set");

			// attempt to execute the query
			notes = query.list();
			logger.info("Notees retrieved");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// close the session
			session.close();
			logger.info("Session closed");
		}
		// return the list of Notes
		logger.info("Returning Note list");
		logger.debug("Notes: " + notes);
		return notes;
	}

}
