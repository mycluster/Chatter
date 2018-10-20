package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Cls;
import com.revature.beans.Cls;
import com.revature.util.HibernateUtil;

public class ClsDaoImpl implements ClsDao {
	private final static Logger logger = Logger.getLogger(ClsDaoImpl.class);

	/**
	 * Selects all records from the Cls table and returns them as a list of Cls
	 * objects
	 */
	@Override
	public List<Cls> selectAllClass() {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a list of Clss
		List<Cls> clss = null;
		logger.info("Selecting all Clss via DAO");

		try {
			// attempt to get all the access levels
			logger.info("Executing FROM Clss query");
			clss = session.createQuery("FROM Cls").list();
		} catch (HibernateException e) {
			// if there is a hibernate exception, catch it
			// and print the stack trace
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up after ourselves
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning list of Clss");
		logger.debug("Clss: " + clss.toString());
		return clss;
	}

	/**
	 * Takes in an Integer object whose value corresponds to the primary key of a
	 * record in the Cls table and returns that record as a Cls object
	 */
	@Override
	public Cls selectClassById(Integer id) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a Cls object
		Cls cls = null;
		logger.info("Selecting Cls by id");
		logger.info("With id" + id);

		try {
			// attempt to get the Cls
			cls = (Cls) session.get(Cls.class, id);
			logger.info("Retrieved Cls");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning Cls");
		logger.debug("Cls: " + cls.toString());
		// return the Cls
		return cls;
	}

	/**
	 * Takes in a Cls object and inserts a record into the Cls table that
	 * corresponds to that object. Returns the primary key of that record as an
	 * Integer object
	 */
	@Override
	public Integer insertNewClass(Cls cls) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to an Integer
		Integer id = null;
		logger.info("Inserting Cls via DAO");
		logger.debug("Cls: " + cls.toString());

		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");
			// perform the insertion
			id = (Integer) session.save(cls);
			logger.info("Cls inserted");
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
	 * record in the Cls table, and deletes that record from the table
	 */
	@Override
	public void deleteClassById(Integer id) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null Transaction reference
		Transaction tx = null;
		// create a null reference to a query
		Query membershipQuery = null;
		Query accessQuery = null;
		// create an HQL string from removing all class memberships 
		String membershipHql = "DELETE FROM ClassMembership WHERE cls = :cls";
		String accessHql = "DELETE FROM ClassAccess WHERE cls = :cls";
		logger.info("Deleting Cls via DAO");
		logger.debug("With ID: " + id);
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");
			
			// grab the class to be deleted
			Cls cls  = (Cls) session.get(Cls.class, id);
			logger.info("Class to be deleted retrieved");
			
			// generate a query
			membershipQuery = session.createQuery(membershipHql);
			logger.info("Membership deletion query generated");

			// set the cls parameter
			membershipQuery.setParameter("cls", cls);
			logger.info("Cls parameter set");
			
			// execute the delete statement
			membershipQuery.executeUpdate();
			logger.info("Class Memberships deleted");
			
			// generate the access deletion query
			accessQuery = session.createQuery(accessHql);
			logger.info("Access Deletion query generated");
			
			//set the cls parameter
			accessQuery.setParameter("cls", cls);
			logger.info("Cls parameter set");
			
			// execute the delete statement
			accessQuery.executeUpdate();
			logger.info("Class Accesses deleted");
			
			// delete the Cls
			session.delete(session.get(Cls.class, id));
			logger.info("Cls deleted");
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
	 * Takes in a Cls object and then updates the corresponding record in the Cls
	 * table to match it. Returns a new Cls object that represents the updated
	 * record
	 */
	@Override
	public Cls updateClass(Cls cls) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to a Cls
		Cls c = null;

		logger.info("Updating Cls");
		logger.debug("Cls: " + cls.toString());
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Transaction begins");

			// get the version from the database
			c = (Cls) session.get(Cls.class, cls.getId());
			logger.info("Cls retrieved from the database");

			// set the name to match the input Cls
			// we do not update id because they already match
			// also, we should not be going around changing primary keys
			c.setName(cls.getName());
			logger.info("Cls from database updated");

			// save the changes
			session.save(c);
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
		logger.info("Returning updated Cls");
		logger.debug("Cls: " + c.toString());
		// return the updated Cls
		return c;
	}
}
