package com.revature.daos;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Activation;
import com.revature.util.HibernateUtil;

public class ActivationDaoImpl implements ActivationDao {
	private final static Logger logger = Logger.getLogger(ActivationDaoImpl.class);

	/**
	 * Returns a list of all records in the Activated table as Activation objects
	 */
	@Override
	public List<Activation> selectAllActivation() {
		// create a new session
		Session session = HibernateUtil.getSession();

		// make a null reference to a list of activations
		List<Activation> activations = null;

		logger.info("Selecting all Activations via DAO");
		try {
			// attempt to get all the access levels
			logger.info("Executing FROM Activation query");
			activations = session.createQuery("FROM Activation").list();

		} catch (HibernateException e) {
			// if there is a hibernate exception, catch it
			// and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up after ourselves
			session.close();
			logger.info("Session closed");
		}
		// return the activations
		logger.info("Returning list of Activations");
		logger.debug("Activations: " + activations.toString());
		return activations;
	}

	/**
	 * Returns the record in the Activation table of the database with the primary
	 * key id as an Activation Object
	 */
	@Override
	public Activation selectActivationById(Integer id) {
		// create new session
		Session session = HibernateUtil.getSession();

		// make a null reference to an activation
		Activation activation = null;
		logger.info("Selecting Activation by id via DAO");
		logger.debug("With id" + id);

		try {
			// attempt to get the Activation
			activation = (Activation) session.get(Activation.class, id);
			logger.info("Retrieved Activation");
		} catch (HibernateException e) {
			// if there was a hibernate exception
			// catch it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		// return the activation
		logger.info("Returning Activation");
		logger.debug("Activation: "+ activation.toString());
		return activation;
	}

	/**
	 * Takes in an Activation Object and then updates the values of the record in
	 * the Activation table in the database with the primary key that matches the
	 * object's id field to match the values in the fields of the Activation object
	 */
	@Override
	public Activation setActivation(Activation activation) {
		// create new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to an Activation
		Activation a = null;

		logger.info("Updating Activation via DAO");
		logger.debug("Activation :" + activation.toString());

		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Transaction begins");

			// get the version from the database
			a = (Activation) session.get(Activation.class, activation.getId());
			logger.info("Activation retrieved from the database");

			// set the name to the name in the activation
			// we do not update id because they already match
			// also, we should not be going arounding changing primary keys
			a.setName(activation.getName());
			logger.info("Activation from database updated");

			// save the changes
			session.save(a);
			logger.info("Changes saved in session");

			// commit the changes
			tx.commit();
			logger.info("Changes committed");
		} catch (HibernateException e) {
			// if a HibernateException is triggered, catch
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
		logger.info("Returning updated Activation");
		// return the updated Activation
		logger.debug("Activation: "+ a.toString());
		return a;
	}

}
