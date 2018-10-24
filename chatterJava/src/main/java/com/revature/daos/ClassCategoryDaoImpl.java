package com.revature.daos;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.ClassCategory;
import com.revature.beans.ClassCategory;
import com.revature.util.HibernateUtil;

public class ClassCategoryDaoImpl implements ClassCategoryDao {
	private final static Logger logger = Logger.getLogger(ClassCategoryDaoImpl.class);

	/**
	 * Selects all records from the ClassCategory table and returns them as a list of ClassCategory
	 * objects
	 */
	@Override
	public List<ClassCategory> selectAllClassCategory() {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a list of ClassCategories
		List<ClassCategory> classCategories = null;
		logger.info("Selecting all ClassCategories via DAO");

		try {
			// attempt to get all the access levels
			logger.info("Executing FROM ClassCategories query");
			classCategories = session.createQuery("FROM ClassCategory").list();
		} catch (HibernateException e) {
			// if there is a hibernate exception, catch it
			// and print the stack trace
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up after ourselves
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning list of ClassCategories");
		logger.debug("ClassCategories: " + classCategories.toString());
		return classCategories;
	}

	/**
	 * Takes in an Integer and selects the record from the ClassCategory table with that
	 * Integer value as its primary key. It returns that record as a ClassCategory object
	 */
	@Override
	public ClassCategory selectAllClassCategoryById(Integer id) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a ClassCategory object
		ClassCategory classCategory = null;
		logger.info("Selecting ClassCategory by id via DAO");
		logger.info("With id" + id);

		try {
			// attempt to get the ClassCategory
			classCategory = (ClassCategory) session.get(ClassCategory.class, id);
			logger.info("Retrieved ClassCategory");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning ClassCategory");
		logger.debug("ClassCategory: " + classCategory.toString());
		// return the ClassCategory
		return classCategory;
	}

	/**
	 * Takes a ClassCategory object and inserts a record into the ClassCategory table containing the
	 * same data as the object contains in its fields. Returns the primary key of
	 * that record
	 */
	@Override
	public Integer insertClassCategory(ClassCategory classCategory) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to an Integer
		Integer id = null;
		logger.info("Inserting ClassCategory via DAO");
		logger.debug("ClassCategory: " + classCategory.toString());

		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");
			// perform the insertion
			id = (Integer) session.save(classCategory);
			logger.info("ClassCategory inserted");
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
		// return the id of the newly inserted classCategory
		logger.info("Returning Id");
		logger.debug("id: " + id);
		return id;
	}

	/**
	 * Takes in a ClassCategory object and updates the record in the ClassCategory table with the same
	 * primary key as the ClassCategory objects id field to match the object. Returns a new
	 * ClassCategory object that corresponds to the updated record
	 */
	@Override
	public ClassCategory updateClassCategory(ClassCategory classCategory) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to a ClassCategory
		ClassCategory cCategory = null;

		logger.info("Updating ClassCategory via DAO");
		logger.debug("ClassCategory: " + classCategory.toString());
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Transaction begins");

			// get the version from the database
			cCategory = (ClassCategory) session.get(ClassCategory.class, classCategory.getId());
			logger.info("ClassCategory retrieved from the database");

			// set the name to match the input ClassCategory
			// we do not update id because they already match
			// also, we should not be going around changing primary keys
			cCategory.setName(classCategory.getName());
			logger.info("ClassCategory from database updated");

			// save the changes
			session.save(cCategory);
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
		logger.info("Returning updated ClassCategory");
		logger.debug("ClassCategory: " + cCategory.toString());
		// return the updated ClassCategory
		return cCategory;
	}

}
