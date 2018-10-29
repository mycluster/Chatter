package com.revature.daos;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.beans.Activation;
import com.revature.beans.ClassMembership;
import com.revature.beans.ClassMembership;
import com.revature.beans.Cls;
import com.revature.beans.User;
import com.revature.util.HibernateUtil;

public class ClassMembershipDaoImpl implements ClassMembershipDao {
	private final static Logger logger = Logger.getLogger(ClassMembershipDaoImpl.class);

	/**
	 * Returns a list of all records in the ClassMembershipDaoImpl
	 */
	@Override
	public List<ClassMembership> selectAllClassMembership() {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a list of ClassMemberships
		List<ClassMembership> classMemberships = null;
		logger.info("Selecting all ClassMemberships via DAO");

		try {
			// attempt to get all the access levels
			logger.info("Executing FROM ClassMemberships query");
			classMemberships = session.createQuery("FROM ClassMembership").list();
		} catch (HibernateException e) {
			// if there is a hibernate exception, catch it
			// and print the stack trace
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up after ourselves
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning list of ClassMemberships");
		logger.debug("ClassMemberships: " + classMemberships.toString());
		return classMemberships;
	}

	/**
	 * Returns the record in the ClassMembership table of the database with the
	 * primary key id as a ClassMembership
	 */
	@Override
<<<<<<< HEAD
	public ClassMembership selectClassMemebershipById(Integer id) {
=======
	public ClassMembership selectClassMembershipById(Integer id) {
>>>>>>> 78e6d76f3ded2a0287329aee6f0db53f4bdf8c10
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a ClassMembership object
		ClassMembership classMembership = null;
		logger.info("Selecting ClassMembership by id via DAO");
		logger.info("With id" + id);

		try {
			// attempt to get the ClassMembership
			classMembership = (ClassMembership) session.get(ClassMembership.class, id);
			logger.info("Retrieved ClassMembership");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning ClassMembership");
		logger.debug("ClassMembership: " + classMembership.toString());
		// return the ClassMembership
		return classMembership;
	}

	/**
	 * Takes in a ClassMembership Object and then updates the values of the record
	 * in the ClassMembership table in the database with the primary key that
	 * matches the object's id field to match the values in the fields of the
	 * ClassMembership Object
	 */
	@Override
	public ClassMembership updateClassMembership(ClassMembership classMembership) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to a ClassMembership
		ClassMembership cMembership = null;

		logger.info("Updating ClassMembership via DAO");
		logger.debug("ClassMembership: " + classMembership.toString());
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Transaction begins");

			// get the version from the database
			cMembership = (ClassMembership) session.get(ClassMembership.class, classMembership.getId());
			logger.info("ClassMembership retrieved from the database");

			// set the class, role and user to match the input ClassMembership
			// we do not update id because they already match
			// also, we should not be going arounding changing primary keys
			cMembership.setCls(classMembership.getCls());
			cMembership.setRole(classMembership.getRole());
			cMembership.setUser(classMembership.getUser());
			logger.info("ClassMembership from database updated");

			// save the changes
			session.save(cMembership);
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
		logger.info("Returning updated ClassMembership");
		logger.debug("ClassMembership: " + cMembership.toString());
		// return the updated ClassMembership
		return cMembership;
	}
	/**
	 * Takes in an id which corresponds to the primary key of a record in the
	 * ClassMembership table and deletes the record with that primary key
	 * from the table
	 */
	@Override
<<<<<<< HEAD
	public void deleteClassMemebershipById(Integer id) {
=======
	public void deleteClassMembershipById(Integer id) {
>>>>>>> 78e6d76f3ded2a0287329aee6f0db53f4bdf8c10
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null Transaction reference
		Transaction tx = null;
		logger.info("Deleting ClassMembership via DAO");
		logger.debug("With ID: " + id);
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");
			// delete the ClassMembership
			session.delete(session.get(ClassMembership.class, id));
			logger.info("ClassMembership deleted");
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
	 * Takes in a ClassMembership object and inserts the values stored in the field of that object
	 * into the ClassMembership table. Returns the primary key of the newly inserted record
	 */
	@Override
<<<<<<< HEAD
	public Integer insertClassMemebership(ClassMembership classMembership) {
=======
	public Integer insertClassMembership(ClassMembership classMembership) {
>>>>>>> 78e6d76f3ded2a0287329aee6f0db53f4bdf8c10
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to an Integer
		Integer id = null;
		logger.info("Inserting ClassMembership via DAO");
		logger.debug("ClassMembership: " + classMembership.toString());

		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");
			// perfom the insertion
			id = (Integer) session.save(classMembership);
			logger.info("ClassMembership inserted");
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
	 * Takes in a User object and selects all records in the ClassMembership table
	 * with that user as the value in the user column and returns those records as a
	 * list of ClassMembership Objects
	 */
	@Override
	public List<ClassMembership> selectClassMembershipByUser(User user) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a query
		Query query = null;
		// create the HQL string
		String hql = "FROM ClassMembership WHERE user = :user";
		// create a null reference to a list of ClassMemberships
		List<ClassMembership> classMemberships = null;

		logger.info("Get ClassMembership by User via DAO");
		logger.debug("User :" + user.toString());

		try {
			// generate a query
			query = session.createQuery(hql);
			logger.info("Query generated");

			// set the user parameter
			query.setParameter("user", user);
			logger.info("Parameter set");

			// attempt to execute the query
			classMemberships = query.list();
			logger.info("ClassMembershipes retrieved");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// close the session
			session.close();
			logger.info("Session closed");
		}
		// return the list of ClassMemberships
		logger.info("Returning ClassMembership list");
		logger.debug("ClassMemberships: " + classMemberships);
		return classMemberships;
	}
	
	/**
	 * Takes in a Cls object and returns as a list of ClassMembership objects all
	 * records in the ClassMembership table with that Cls in their cls column
	 */
	@Override
	public List<ClassMembership> selectClassMembershipByClass(Cls cls) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a query
		Query query = null;
		// create the HQL string
		String hql = "FROM ClassMembership WHERE cls = :cls";
		// create a null reference to a list of ClassMemberships
		List<ClassMembership> classMemberships = null;

		logger.info("Get ClassMembership by Class via DAO");
		logger.debug("Cls :" + cls.toString());

		try {
			// generate a query
			query = session.createQuery(hql);
			logger.info("Query generated");

			// set the cls parameter
			query.setParameter("cls", cls);
			logger.info("Parameter set");

			// attempt to execute the query
			classMemberships = query.list();
			logger.info("ClassMemberships retrieved");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// close the session
			session.close();
			logger.info("Session closed");
		}
		// return the list of ClassMemberships
		logger.info("Returning ClassMembership list");
		logger.debug("ClassMemberships: "+ classMemberships.toString());
		return classMemberships;
	}

	/**
	 * Takes in a User object and a Cls object and returns the record in the
	 * ClassMembership table with that Cls and User value. Only one record is
	 * returned because a User can only have one class membership per class
	 */
	@Override
	public ClassMembership getUserMembershipOfClass(User user, Cls cls) {
		// create new session
		Session session = HibernateUtil.getSession();
		// create null criteria
		Criteria crit = null;
		// create null reference to a list of ClassMemberships
		ClassMembership classMembership = null;

		logger.info("Getting ClassMemberships by Cls and User via DAO");
		logger.debug("Cls: " + cls.toString());
		logger.debug("User: " + user.toString());
		try {
			// get a new criteria from the session
			crit = session.createCriteria(ClassMembership.class);
			logger.info("New criteria created");
			// add restrictions to the criteria
			crit.add(Restrictions.and(Restrictions.eq("cls", cls), Restrictions.eq("user", user)));
			logger.info("Retrictions added");
			// execute the criteria
			classMembership = (ClassMembership) crit.uniqueResult();
			logger.info("ClassMemberships recieved");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning ClassMembership");
		logger.debug("ClassMembership: "+ classMembership.toString());
		// return the ClassMembership
		return classMembership;
	}

}
