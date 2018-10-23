package com.revature.daos;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Priv;
import com.revature.util.HibernateUtil;

public class PrivDaoImpl implements PrivDao {
	private final static Logger logger = Logger.getLogger(PrivDaoImpl.class);

	/**
	 * Selects all records from the Priv table and returns them as a list of Priv
	 * objects
	 */
	@Override
	public List<Priv> selectAllPriv() {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a list of Privs
		List<Priv> privs = null;
		logger.info("Selecting all Privs via DAO");

		try {
			// attempt to get all the access levels
			logger.info("Executing FROM Privs query");
			privs = session.createQuery("FROM Priv").list();
		} catch (HibernateException e) {
			// if there is a hibernate exception, catch it
			// and print the stack trace
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up after ourselves
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning list of Privs");
		logger.debug("Privs: " + privs.toString());
		return privs;
	}

	/**
	 * Takes in an Integer and selects the record from the Priv table with that
	 * Integer value as its primary key. It returns that record as a Priv object
	 */
	@Override
	public Priv selectAllPrivById(Integer id) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a Priv object
		Priv priv = null;
		logger.info("Selecting Priv by id via DAO");
		logger.info("With id" + id);

		try {
			// attempt to get the Priv
			priv = (Priv) session.get(Priv.class, id);
			logger.info("Retrieved Priv");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning Priv");
		logger.debug("Priv: " + priv.toString());
		// return the Priv
		return priv;
	}

	/**
	 * Takes a Priv object and inserts a record into the Priv table containing the
	 * same data as the object contains in its fields. Returns the primary key of
	 * that record
	 */
	@Override
	public Integer insertPriv(Priv priv) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to an Integer
		Integer id = null;
		logger.info("Inserting Priv via DAO");
		logger.debug("Priv: " + priv.toString());

		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");
			// perform the insertion
			id = (Integer) session.save(priv);
			logger.info("Priv inserted");
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
		// return the id of the newly inserted priv
		logger.info("Returning Id");
		logger.debug("id: " + id);
		return id;
	}

	/**
	 * Takes in an Integer and deletes the record in the Priv table with that value
	 * as the primary key, also sets all records in the users table with that priv
	 * to instead have a priv of null
	 */
	@Override
	public void deletePrivById(Integer id) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null Transaction reference
		Transaction tx = null;
		// create a null query reference
		Query userQuery = null;
		// create an HQL statement to delete all associated notes
		String userHql = "UPDATE Usr SET priv = null WHERE priv = :priv";
		logger.info("Deleting Priv via DAO");
		logger.debug("With ID: " + id);
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");
			
			// get the Priv to be deleted
			Priv priv = (Priv) session.get(Priv.class, id);
			logger.info("Priv to be deleted retrieved");
			
			// create the query
			userQuery = session.createQuery(userHql);
			logger.info("User update query");

			// set the parameter
			userQuery.setParameter("priv", priv);
			logger.info("Priv parameter set");

			// execute the query
			userQuery.executeUpdate();
			logger.info("Users have been updated");

			// delete the Priv
			session.delete(session.get(Priv.class, id));
			logger.info("Priv deleted");
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
	 * Takes in a Priv object and updates the record in the Priv table with the same
	 * primary key as the Priv objects id field to match the object. Returns a new
	 * Priv object that corresponds to the updated record
	 */
	@Override
	public Priv updatePriv(Priv priv) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to a Priv
		Priv p = null;

		logger.info("Updating Priv via DAO");
		logger.debug("Priv: " + priv.toString());
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Transaction begins");

			// get the version from the database
			p = (Priv) session.get(Priv.class, priv.getId());
			logger.info("Priv retrieved from the database");

			// set the name to match the input Priv
			// we do not update id because they already match
			// also, we should not be going around changing primary keys
			p.setName(priv.getName());
			logger.info("Priv from database updated");

			// save the changes
			session.save(p);
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
		logger.info("Returning updated Priv");
		logger.debug("Priv: " + p.toString());
		// return the updated Priv
		return p;
	}

}
