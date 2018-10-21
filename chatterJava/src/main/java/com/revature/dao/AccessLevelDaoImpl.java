package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.AccessLevel;
import com.revature.util.HibernateUtil;


/**
 * 
 * @author kbarnes
 * 
 *         Returns a list of all AccessLevels in the database if there are none
 *         the returned List Reference is null
 * 
 */
public class AccessLevelDaoImpl implements AccessLevelDao {
	private final static Logger logger = Logger.getLogger(AccessLevelDaoImpl.class);

	@Override
	public List<AccessLevel> selectAllAccessLevel() {
		// create a new session
		Session session = HibernateUtil.getSession();

		// make a null reference to a list of accessLevels
		List<AccessLevel> accessLevels = null;

		logger.info("Selecting all AccessLevels via DAO");
		try {
			// attempt to get all the access levels
			logger.info("Executing FROM AccessLevel query");
			accessLevels = session.createQuery("FROM AccessLevel").list();

		} catch (HibernateException e) {
			// if there is a hibernate exception, catch it
			// and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up after ourselves
			session.close();
			logger.info("Session closed");
		}
		// return the list of accesslevels
		logger.info("Returning list of AccessLevels");
		logger.debug("AccessLevels: "+ accessLevels.toString());
		return accessLevels;
	}

	/**
	 * Returns an AccessLevel object with field values that match the record in the
	 * database with the primary key value id
	 */
	@Override
	public AccessLevel selectAccessLevelById(Integer id) {
		// create new session
		Session session = HibernateUtil.getSession();

		// make a null reference to an accessLevel
		AccessLevel accessLevel = null;
		logger.info("Selecting AccessLevel by id via DAO");
		logger.debug("With id" + id);

		try {
			// attempt to get the AccessLevel
			accessLevel = (AccessLevel) session.get(AccessLevel.class, id);
			logger.info("Retrieved AccessLevel");
		} catch (HibernateException e) {
			// if there is a hibernate exception catch it
			// and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning AccessLevel");
		// return the accessLevel
		logger.debug("AccessLevel: "+ accessLevel.toString());
		return accessLevel;
	}

	/**
	 * Takes in an AccessLevel object and updates the records with the same primary
	 * key in the AccessLevels table to match the input object's values. Returns a
	 * new AccessLevel object that corresponds to the updated record
	 */
	@Override
	public AccessLevel setAccessLevel(AccessLevel accessLevel) {
		// create new session
		Session session = HibernateUtil.getSession();
		// Create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to an AccessLevel
		AccessLevel aLevel = null;

		logger.info("Updating AccessLevel via DAO");
		logger.debug("AccessLevel " + accessLevel.toString());

		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Transaction begins");

			// get the version from the database
			aLevel = (AccessLevel) session.get(AccessLevel.class, accessLevel.getId());
			logger.info("AccessLevel retrieved from the database");

			// set the name to the name in accessLevel
			// we do not update id because they are the same already
			// also we shouldn't be changing primary key values
			aLevel.setName(accessLevel.getName());
			logger.info("AccessLevel from database updated");

			// save the changes
			session.save(aLevel);
			logger.info("Changes saved in session");

			// commit the changes
			tx.commit();
			logger.info("Changes committed");
		} catch (HibernateException e) {
			// if there is a hibernate exception triggered
			// catch it and log it
			logger.error("HibernateException triggered", e);
			// since something went wrong, rollback the transaction
			tx.rollback();
			logger.info("Rolling back the transaction");
		} finally {
			// close the session
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning updated AccessLevel");
		// return the updated AccessLevel
		logger.debug("AccessLevel: " + aLevel.toString());
		return aLevel;
	}

}
