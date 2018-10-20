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
import com.revature.beans.ClassAccess;
import com.revature.beans.Cls;
import com.revature.beans.Note;
import com.revature.util.HibernateUtil;

public class ClassAccessDaoImpl implements ClassAccessDao {
	private final static Logger logger = Logger.getLogger(ClassAccessDaoImpl.class);

	/**
	 * Selects all records in the ClassAccess table in the database and returns them
	 * as a list of ClassAccess Objects
	 */
	@Override
	public List<ClassAccess> selectAllClassAccess() {
		// create a new session
		Session session = HibernateUtil.getSession();

		// make a null reference to a list of ClassAccesses
		List<ClassAccess> classAccesses = null;

		logger.info("Selecting all ClassAccesses via DAO");
		try {
			// attempt to get all of the ClassAccesses
			logger.info("Executing FROM ClassAccess query");
			classAccesses = session.createQuery("FROM ClassAccess").list();
		} catch (HibernateException e) {
			// if a HibernateException is triggered, catch it
			// and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// close the session
			session.close();
			logger.info("Session closed");
		}
		// return the ClassAccesses
		logger.info("Returning list of ClassAccesses");
		logger.debug("ClassAccesses: "+ classAccesses.toString());
		return classAccesses;
	}

	/**
	 * Takes in an integer value and returns a ClassAccess object with field values
	 * match the record in the database with the primary key value id
	 */
	@Override
	public ClassAccess selectClassAccessById(Integer id) {
		// create new session
		Session session = HibernateUtil.getSession();

		// make a null reference to a ClassAccess
		ClassAccess classAccess = null;
		logger.info("Selecting ClassAccess by id");
		logger.debug("With id" + id);

		try {
			// attempt to get the ClassAccess
			classAccess = (ClassAccess) session.get(ClassAccess.class, id);
			logger.info("Retrieved ClassAccess");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered,
			// catch it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		// return the ClassAccess
		logger.info("Returning ClassAccess");
		logger.debug("ClassAccess: "+ classAccess.toString());
		return classAccess;
	}

	/**
	 * Takes in a ClassAccess object and updates the records in the database with
	 * the same primary key in the ClassAccess table so that they match the input
	 * object's field values. Returns a new ClassAccess objct that corresponds to
	 * the updated record
	 */
	@Override
	public ClassAccess updateClassAccess(ClassAccess classAccess) {
		// create new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to a ClassAccess
		ClassAccess cAccess = null;

		logger.info("Update ClassAccess");
		logger.debug("ClassAccess :" + classAccess.toString());
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Transaction begins");

			// get the version from the database
			cAccess = (ClassAccess) session.get(ClassAccess.class, classAccess.getId());
			logger.info("ClassAccess retrieved from the database");

			// update the Access, Note, and Cls values
			// do not change the id field as it should already
			// match
			cAccess.setAccess(classAccess.getAccess());
			cAccess.setCls(classAccess.getCls());
			cAccess.setNote(classAccess.getNote());
			logger.info("ClassAccess from database updated");

			// save the changes
			session.save(cAccess);
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
		logger.info("Returning updated ClassAccess");
		logger.debug("ClassAccess: "+ cAccess.toString());
		// return the updated ClassAccess
		return cAccess;
	}

	/**
	 * Takes in a Cls object and returns all records in the ClassAccess table with
	 * that Cls as their cls value as a list of ClassAccess objects
	 */
	@Override
	public List<ClassAccess> getClassAccessByClass(Cls cls) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a query
		Query query = null;
		// create the HQL string
		String hql = "FROM ClassAccess WHERE cls = :cls";
		// create a null reference to a list of ClassAccesses
		List<ClassAccess> classAccesses = null;

		logger.info("Get ClassAccess by Class");
		logger.debug("Cls :" + cls.toString());

		try {
			// generate a query
			query = session.createQuery(hql);
			logger.info("Query generated");

			// set the cls parameter
			query.setParameter("cls", cls);
			logger.info("Parameter set");

			// attempt to execute the query
			classAccesses = query.list();
			logger.info("ClassAccesses retrieved");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// close the session
			session.close();
			logger.info("Session closed");
		}
		// return the list of ClassAccesses
		logger.info("Returning ClassAccess list");
		logger.debug("ClassAccesses: "+ classAccesses.toString());
		return classAccesses;
	}

	/**
	 * Takes in a Note object and returns all records in the ClassAccess table with
	 * the given note in the note column as a list of ClassAccess objects
	 */
	@Override
	public List<ClassAccess> getClassAccessByNote(Note note) {
		// create new session
		Session session = HibernateUtil.getSession();
		// create null criteria
		Criteria crit = null;
		// create null reference to a list of ClassAccesses
		List<ClassAccess> classAccesses = null;

		logger.info("Getting ClassAccesses by Note");
		logger.debug("Note: " + note.toString());
		try {
			// get a new criteria from the session
			crit = session.createCriteria(ClassAccess.class);
			logger.info("New criteria created");
			// add a restriction to the criteria
			crit.add(Restrictions.eq("note", note));
			logger.info("Retriction added");
			// execute the criteria
			classAccesses = crit.list();
			logger.info("ClassAccesses recieved");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning ClassAccesses");
		logger.debug("classAccesses: "+ classAccesses.toString());
		// return the ClassAccesses
		return classAccesses;
	}

	/**
	 * Takes in both a Class object and an Access object and returns as a list of
	 * ClassAccess objects all records in the ClassAccess table with the specified
	 * Class and Access values
	 */
	@Override
	public List<ClassAccess> getClassAccessByClassAndAccess(Cls cls, AccessLevel access) {
		// create new session
		Session session = HibernateUtil.getSession();
		// create null criteria
		Criteria crit = null;
		// create null reference to a list of ClassAccesses
		List<ClassAccess> classAccesses = null;

		logger.info("Getting ClassAccesses by Cls and Access");
		logger.debug("Cls: " + cls.toString());
		logger.debug("Access: " + access.toString());
		try {
			// get a new criteria from the session
			crit = session.createCriteria(ClassAccess.class);
			logger.info("New criteria created");
			// add restrictions to the criteria
			crit.add(Restrictions.and(Restrictions.eq("cls", cls), Restrictions.eq("access", access)));
			logger.info("Retrictions added");
			// execute the criteria
			classAccesses = crit.list();
			logger.info("ClassAccesses recieved");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning ClassAccesses");
		logger.debug("classAccesses: "+ classAccesses.toString());
		// return the ClassAccesses
		return classAccesses;
	}

	@Override
	public Integer insertClassAccess(ClassAccess classAccess) {
		// create a new session
				Session session = HibernateUtil.getSession();
				// create a null reference to a transaction
				Transaction tx = null;
				// create a null reference to an Integer
				Integer id = null;
				logger.info("Inserting ClassAccess via DAO");
				logger.debug("ClassAccess: " + classAccess.toString());

				try {
					// begin the transaction
					tx = session.beginTransaction();
					logger.info("Beginning transaction");
					// perform the insertion
					id = (Integer) session.save(classAccess);
					logger.info("ClassAccess inserted");
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
				// return the id of the newly inserted ClassAccess
				logger.info("Returning Id");
				logger.debug("id: " + id);
				return id;
	}

	@Override
	public void deleteClassAccess(Integer id) {
		// create a new session
				Session session = HibernateUtil.getSession();
				// create a null Transaction reference
				Transaction tx = null;
				logger.info("Deleting ClassAccess via DAO");
				logger.debug("With ID: " + id);
				try {
					// begin the transaction
					tx = session.beginTransaction();
					logger.info("Beginning transaction");

					// delete the ClassAccess
					session.delete(session.get(ClassAccess.class, id));
					logger.info("ClassAccess deleted");
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
