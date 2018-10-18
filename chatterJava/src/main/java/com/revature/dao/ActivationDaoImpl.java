package com.revature.dao;

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
			// and print the stack trace
			logger.error("HibernateException triggered");
			e.printStackTrace();
		} finally {
			// clean up after ourselves
			session.close();
		}
		logger.info("Returning list of Activations");
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
		logger.info("Selecting Activation by id");
		logger.debug("With id" + id);

		try {
			// attempt to get the Activation
			activation = (Activation) session.get(Activation.class, id);
			logger.info("Retrieved Activation");
		} catch (HibernateException e) {
			logger.debug("HibernateException triggered");
		} finally {
			// clean up
			session.close();
		}
		logger.info("Returning Activation");
		return activation;
	}
	
	/**
	 * Takes in an Activation Object and then updates the
	 * values of the record in the Activation table in the database
	 * with the primary key that matches the object's id field 
	 * to match the values in the fields of the Activation object
	 */
	@Override
	public Activation setActivation(Activation activation) {
		// create new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to an Activation
		Activation a = null;
		
		logger.info("Updating Activation");
		logger.debug("Activation :"+activation.toString());
		
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
			
			//save the changes
			session.save(a);
			logger.info("Changes saved in session");
			
			//commit the changes
			tx.commit();
			logger.info("Changes committed");
		} catch (HibernateException e) {
			logger.debug("HibernateException triggered");
			e.printStackTrace();
		} finally {
			//close the session
			session.close();
		}
		logger.info("Returning updated Activation");
		// return the updated Activation
		return a;
	}

}
