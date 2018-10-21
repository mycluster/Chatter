package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.beans.AccessLevel;
import com.revature.beans.UserAccess;
import com.revature.beans.Note;
import com.revature.beans.User;
import com.revature.util.HibernateUtil;

public class UserAccessDaoImpl implements UserAccessDao {
	private final static Logger logger = Logger.getLogger(UserAccessDaoImpl.class);

	/**
	 * Selects all records from the UserAccess table and returns them as a list of
	 * UserAccess objects
	 */
	@Override
	public List<UserAccess> selectAllUserAccess() {
		// create a new session
		Session session = HibernateUtil.getSession();

		// make a null reference to a list of UserAccesses
		List<UserAccess> userAccesses = null;

		logger.info("Selecting all UserAccesses via DAO");
		try {
			// attempt to get all of the UserAccesses
			logger.info("Executing FROM UserAccess query");
			userAccesses = session.createQuery("FROM UserAccess").list();
		} catch (HibernateException e) {
			// if a HibernateException is triggered, catch it
			// and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// close the session
			session.close();
			logger.info("Session closed");
		}
		// return the UserAccesses
		logger.info("Returning list of UserAccesses");
		logger.debug("UserAccesses: " + userAccesses.toString());
		return userAccesses;
	}

	/**
	 * Takes an Integer and returns the record in the UserAccess table with that
	 * value as its primary key as an UserAccess object
	 */
	@Override
	public UserAccess selectUserAccessById(Integer id) {
		// create new session
		Session session = HibernateUtil.getSession();

		// make a null reference to a UserAccess
		UserAccess userAccess = null;
		logger.info("Selecting UserAccess by id via DAO");
		logger.debug("With id" + id);

		try {
			// attempt to get the UserAccess
			userAccess = (UserAccess) session.get(UserAccess.class, id);
			logger.info("Retrieved UserAccess");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered,
			// catch it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		// return the UserAccess
		logger.info("Returning UserAccess");
		logger.debug("UserAccess: " + userAccess.toString());
		return userAccess;
	}

	/**
	 * Takes a UserAccess object and updates the record in the UserAccess table so
	 * that data in the record matches the fields of the UserAccess object. Returns
	 * a new UserAccess object that corresponds to the updated record
	 */
	@Override
	public UserAccess updateUserAccess(UserAccess userAccess) {
		// create new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to a UserAccess
		UserAccess uAccess = null;

		logger.info("Update UserAccess via DAO");
		logger.debug("UserAccess :" + userAccess.toString());
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Transaction begins");

			// get the version from the database
			uAccess = (UserAccess) session.get(UserAccess.class, userAccess.getId());
			logger.info("UserAccess retrieved from the database");

			// update the Access, Note, and User values
			// do not change the id field as it should already
			// match
			uAccess.setAccess(userAccess.getAccess());
			uAccess.setUser(userAccess.getUser());
			uAccess.setNote(userAccess.getNote());
			logger.info("UserAccess from database updated");

			// save the changes
			session.save(uAccess);
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
		logger.info("Returning updated UserAccess");
		logger.debug("UserAccess: " + uAccess.toString());
		// return the updated UserAccess
		return uAccess;
	}

	/**
	 * Takes in a UserAccess object and inserts a record into the UserAccess table
	 * whose values match the values in the fields of the UserAccess object. Returns
	 * the primary key of newly inserted record
	 */
	@Override
	public Integer insertUserAccess(UserAccess userAccess) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to an Integer
		Integer id = null;
		logger.info("Inserting UserAccess via DAO");
		logger.debug("UserAccess: " + userAccess.toString());

		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");
			// perform the insertion
			id = (Integer) session.save(userAccess);
			logger.info("UserAccess inserted");
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
	 * Takes in an Integer and deletes the record in the UserAccess table with that
	 * value as its primary key.
	 */
	@Override
	public void deleteUserAccess(Integer id) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null Transaction reference
		Transaction tx = null;
		logger.info("Deleting UserAccess via DAO");
		logger.debug("With ID: " + id);
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");

			// delete the UserAccess
			session.delete(session.get(UserAccess.class, id));
			logger.info("UserAccess deleted");
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
	 * Takes in a User object and selects all records in the UserAccess table that
	 * have that user in their user column. Returns those records as a List of
	 * UserAccess Objects
	 */
	@Override
	public List<UserAccess> getUserAccessByUser(User user) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a query
		Query query = null;
		// create the HQL string
		String hql = "FROM UserAccess WHERE user = :user";
		// create a null reference to a list of UserAccesses
		List<UserAccess> userAccesses = null;

		logger.info("Get UserAccess by User via DAO");
		logger.debug("User :" + user.toString());

		try {
			// generate a query
			query = session.createQuery(hql);
			logger.info("Query generated");

			// set the user parameter
			query.setParameter("user", user);
			logger.info("Parameter set");

			// attempt to execute the query
			userAccesses = query.list();
			logger.info("UserAccesses retrieved");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// close the session
			session.close();
			logger.info("Session closed");
		}
		// return the list of UserAccesses
		logger.info("Returning UserAccess list");
		logger.debug("UserAccesses: " + userAccesses.toString());
		return userAccesses;
	}

	/**
	 * Takes in a Note object and selects all records in the UserAccess table with
	 * that note. Returns them as a List of UserAccess objects
	 */
	@Override
	public List<UserAccess> getUserAccessByNote(Note note) {
		// create new session
		Session session = HibernateUtil.getSession();
		// create null criteria
		Criteria crit = null;
		// create null reference to a list of UserAccesses
		List<UserAccess> userAccesses = null;

		logger.info("Getting UserAccesses by Note via DAO");
		logger.debug("Note: " + note.toString());
		try {
			// get a new criteria from the session
			crit = session.createCriteria(UserAccess.class);
			logger.info("New criteria created");
			// add a restriction to the criteria
			crit.add(Restrictions.eq("note", note));
			logger.info("Retriction added");
			// execute the criteria
			userAccesses = crit.list();
			logger.info("UserAccesses recieved");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning UserAccesses");
		logger.debug("userAccesses: " + userAccesses.toString());
		// return the UserAccesses
		return userAccesses;
	}

	/**
	 * Takes in a User object and an Access object then selects all records in the
	 * UserAccess table with the same user and access values and returns them as a
	 * List of UserAccess objects
	 */
	@Override
	public List<UserAccess> getUserAccessByUserAndAccess(User user, AccessLevel access) {
		// create new session
		Session session = HibernateUtil.getSession();
		// create null criteria
		Criteria crit = null;
		// create null reference to a list of UserAccesses
		List<UserAccess> userAccesses = null;

		logger.info("Getting UserAccesses by User and Access via DAO");
		logger.debug("User: " + user.toString());
		logger.debug("Access: " + access.toString());
		try {
			// get a new criteria from the session
			crit = session.createCriteria(UserAccess.class);
			logger.info("New criteria created");
			// add restrictions to the criteria
			crit.add(Restrictions.and(Restrictions.eq("user", user), Restrictions.eq("access", access)));
			logger.info("Retrictions added");
			// execute the criteria
			userAccesses = crit.list();
			logger.info("UserAccesses recieved");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning UserAccesses");
		logger.debug("userAccesses: " + userAccesses.toString());
		// return the UserAccesses
		return userAccesses;
	}

}
