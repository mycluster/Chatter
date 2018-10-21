package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Edit;
import com.revature.beans.Edit;
import com.revature.util.HibernateUtil;

public class EditDaoImpl implements EditDao {
	private final static Logger logger = Logger.getLogger(EditDaoImpl.class);

	/**
	 * Takes in an Integer and selects the record in the Edit table with that value
	 * as its primary key. Returns that record as an Edit object
	 */
	@Override
	public Edit selectEditById(Integer id) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a Edit object
		Edit edit = null;
		logger.info("Selecting Edit by id");
		logger.info("With id" + id);

		try {
			// attempt to get the Edit
			edit = (Edit) session.get(Edit.class, id);
			logger.info("Retrieved Edit");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning Edit");
		logger.debug("Edit: " + edit.toString());
		// return the Edit
		return edit;
	}

	/**
	 * Selects all records from the Edit table and returns them as a List of Edit
	 * objects
	 */
	@Override
	public List<Edit> selectAllEdit() {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a list of Edits
		List<Edit> edits = null;
		logger.info("Selecting all Edits via DAO");

		try {
			// attempt to get all the access levels
			logger.info("Executing FROM Edits query");
			edits = session.createQuery("FROM Edit").list();
		} catch (HibernateException e) {
			// if there is a hibernate exception, catch it
			// and print the stack trace
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up after ourselves
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning list of Edits");
		logger.debug("Edits: " + edits.toString());
		return edits;
	}

	/**
	 * Takes in an Edit object and selects the record in the table with the primary
	 * key that matches the id field of the Edit object. Then updates the column
	 * values of that record to match the Edit object's field values. Returns a new
	 * Edit object corresponding to updated record
	 */
	@Override
	public Edit updateEdit(Edit edit) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to a Edit
		Edit ed = null;

		logger.info("Updating Edit");
		logger.debug("Edit: " + edit.toString());
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Transaction begins");

			// get the version from the database
			ed = (Edit) session.get(Edit.class, edit.getId());
			logger.info("Edit retrieved from the database");

			// set the name to match the input Edit
			// we do not update id because they already match
			// also, we should not be going around changing primary keys
			ed.setName(edit.getName());
			logger.info("Edit from database updated");

			// save the changes
			session.save(ed);
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
		logger.info("Returning updated Edit");
		logger.debug("Edit: " + ed.toString());
		// return the updated Edit
		return ed;
	}

	/**
	 * Takes in an Edit object and inserts a record into the Edit table that
	 * corresponds to that object. Returns the primary key of the record
	 */
	@Override
	public Integer insertEdit(Edit edit) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to an Integer
		Integer id = null;
		logger.info("Inserting Edit via DAO");
		logger.debug("Edit: " + edit.toString());

		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");
			// perform the insertion
			id = (Integer) session.save(edit);
			logger.info("Edit inserted");
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
	 * Takes in an Integer and deletes the record in the Edit table with that value
	 * as its primary key. Sets the Edit value of all records in the Message table
	 * with foriegn keys corresponding to the record to be deleted to null
	 */
	@Override
	public void deleteEdit(Integer id) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null Transaction reference
		Transaction tx = null;
		// create a null reference to a Query
		Query messageQuery = null;
		// create an HQL string
		String messageHql = "UPDATE Message SET edited = null WHERE edited = :edited";

		logger.info("Deleting Edit via DAO");
		logger.debug("With ID: " + id);
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");

			// retrieve the edit to be deleted
			Edit edit = (Edit) session.get(Edit.class, id);
			logger.info("Edit to be deleted has been retrieved");

			// generate the query
			messageQuery = session.createQuery(messageHql);
			logger.info("Membership query generated");

			// set the edit parameter
			messageQuery.setParameter("edited", edit);
			logger.info("Edit parameter set");

			// update the Messages
			messageQuery.executeUpdate();
			logger.info("Messages updated");

			// delete the Edit
			session.delete(edit);
			logger.info("Edit deleted");
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

}
