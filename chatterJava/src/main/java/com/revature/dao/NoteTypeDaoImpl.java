package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Note;
import com.revature.beans.NoteType;
import com.revature.util.HibernateUtil;

public class NoteTypeDaoImpl implements NoteTypeDao {
	private final static Logger logger = Logger.getLogger(NoteTypeDaoImpl.class);

	/**
	 * Selects all records from the NoteType table and returns them as a list of
	 * NoteType objects
	 */
	@Override
	public List<NoteType> selectAllNoteType() {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a list of NoteTypes
		List<NoteType> noteTypes = null;
		logger.info("Selecting all NoteTypes via DAO");

		try {
			// attempt to get all the access levels
			logger.info("Executing FROM NoteTypes query");
			noteTypes = session.createQuery("FROM NoteType").list();
		} catch (HibernateException e) {
			// if there is a hibernate exception, catch it
			// and print the stack trace
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up after ourselves
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning list of NoteTypes");
		logger.debug("NoteTypes: " + noteTypes.toString());
		return noteTypes;
	}

	/**
	 * Takes in an Integer and returns the record from the NoteType table with that
	 * Integer value as its primary key as a NoteType object
	 */
	@Override
	public NoteType selectNoteTypeById(Integer id) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a NoteType object
		NoteType noteType = null;
		logger.info("Selecting NoteType by id via DAO");
		logger.info("With id" + id);

		try {
			// attempt to get the NoteType
			noteType = (NoteType) session.get(NoteType.class, id);
			logger.info("Retrieved NoteType");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning NoteType");
		logger.debug("NoteType: " + noteType.toString());
		// return the NoteType
		return noteType;
	}

	/**
	 * Takes in a NoteType object and updates the corresponding record in the
	 * NoteType table so that it's data matches the fields of the object. Returns a
	 * new NoteType object that corresponds to the updated record
	 */
	@Override
	public NoteType updateNoteType(NoteType noteType) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to a NoteType
		NoteType nType = null;

		logger.info("Updating NoteType via DAO");
		logger.debug("NoteType: " + noteType.toString());
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Transaction begins");

			// get the version from the database
			nType = (NoteType) session.get(NoteType.class, noteType.getId());
			logger.info("NoteType retrieved from the database");

			// set the name to match the input NoteType
			// we do not update id because they already match
			// also, we should not be going around changing primary keys
			nType.setName(noteType.getName());
			logger.info("NoteType from database updated");

			// save the changes
			session.save(nType);
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
		logger.info("Returning updated NoteType");
		logger.debug("NoteType: " + nType.toString());
		// return the updated NoteType
		return nType;
	}

	/**
	 * Takes in an Integer and deletes the record from the NoteType table with that
	 * value as its id, also deletes all associated notes and accesses to those notes
	 * 
	 * DO NOT CALL THIS UNLESS YOU KNOW WHAT YOU ARE DOING AS IT IS HIGHLY DESTRUCTIVE
	 */
	@Override
	public void deleteNoteTypeById(Integer id) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null Transaction reference
		Transaction tx = null;
		// create a null query reference
		Query noteQuery = null;
		Query userAccessQuery = null;
		Query classAccessQuery = null;
		// create an HQL statement to delete all associated notes
		String noteHql = "SELECT FROM Note WHERE type = :type";
		String userAccessHql = "DELETE FROM UserAccess WHERE note = :note";
		String classAccessHql = "DELTE FROM ClassAccess WHERE note = :note";
		logger.info("Deleting NoteType via DAO");
		logger.debug("With ID: " + id);
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");

			// get the note type we want to delete
			NoteType noteType = (NoteType) session.get(NoteType.class, id);
			logger.info("NoteType to be deleted has been retrieved");

			// create the query
			noteQuery = session.createQuery(noteHql);
			logger.info("Created note retrieval query");

			// set the parameter
			noteQuery.setParameter("type", noteType);
			logger.info("NoteType parameter set");

			// execute the query
			List<Note> notes = noteQuery.list();
			logger.info("Notes to be deleted have been retrieved");

			// iterate through the retrieved notes and delete all accesses to the notes
			logger.info("Iterating through notes to delete accesses");
			for (Note n : notes) {
				logger.debug("Note: " + n);
				// create the user access query
				userAccessQuery = session.createQuery(userAccessHql);
				logger.info("UserAccess deletion query created");

				// set the note parameter
				userAccessQuery.setParameter("note", n);
				logger.info("Note parameter set");

				// execute the userAccess deletion query
				userAccessQuery.executeUpdate();
				logger.info("UserAccesses deleted");

				// create the class access query
				classAccessQuery = session.createQuery(classAccessHql);
				logger.info("ClassAccess deletion query created");

				// set the note parameter
				classAccessQuery.setParameter("note", n);
				logger.info("Note parameter set");

				// execute the classAccess deletion query
				classAccessQuery.executeUpdate();
				logger.info("ClassAccesses deleted");

				// delete the note from the database
				session.delete(n);
				logger.info("Note deleted");
			}

			// delete the NoteType
			session.delete(session.get(NoteType.class, id));
			logger.info("NoteType deleted");
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
	 * Takes in a NoteType object and inserts a record into the NoteType table that
	 * corresponds to that object. Returns the primary key of the newly inserted
	 * record
	 */
	@Override
	public Integer insertNoteType(NoteType noteType) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to an Integer
		Integer id = null;
		logger.info("Inserting NoteType via DAO");
		logger.debug("NoteType: " + noteType.toString());

		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");
			// perform the insertion
			id = (Integer) session.save(noteType);
			logger.info("NoteType inserted");
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

}
