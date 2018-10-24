package com.revature.daos;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.ClassRole;
import com.revature.util.HibernateUtil;

public class ClassRoleDaoImpl implements ClassRoleDao {
	private final static Logger logger = Logger.getLogger(ClassRoleDaoImpl.class);
	/**
	 * Selects all records from the ClassRole table and returns them as a list of 
	 * ClassRole objects
	 */
	@Override
	public List<ClassRole> selectAllClassRole() {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a list of ClassRoles
		List<ClassRole> classRoles = null;
		logger.info("Selecting all ClassRoles via DAO");

		try {
			// attempt to get all the access levels
			logger.info("Executing FROM ClassRoles query");
			classRoles = session.createQuery("FROM ClassRole").list();
		} catch (HibernateException e) {
			// if there is a hibernate exception, catch it
			// and print the stack trace
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up after ourselves
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning list of ClassRoles");
		logger.debug("ClassRoles: " + classRoles.toString());
		return classRoles;
	}
	/**
	 * Takes in an Integer that corresponds to the primary key of
	 * a record in the ClassRole table. Returns that record as a 
	 * ClassRole object
	 */
	@Override
	public ClassRole selectClassRoleById(Integer id) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a ClassRole object
		ClassRole classRole = null;
		logger.info("Selecting ClassRole by id via DAO");
		logger.info("With id" + id);

		try {
			// attempt to get the ClassRole
			classRole = (ClassRole) session.get(ClassRole.class, id);
			logger.info("Retrieved ClassRole");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning ClassRole");
		logger.debug("ClassRole: " + classRole.toString());
		// return the ClassRole
		return classRole;
	}
	
	/**
	 * Takes in a ClassRole object and updates the record with the same primary key
	 * as that object's id field so that the data in the record matches the ClassRole object's fields
	 * then returns a new ClassRole object that corresponds to the updated record
	 */
	@Override
	public ClassRole updateClassRole(ClassRole classRole) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to a ClassRole
		ClassRole cRole = null;

		logger.info("Updating ClassRole via DAO");
		logger.debug("ClassRole: " + classRole.toString());
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Transaction begins");

			// get the version from the database
			cRole = (ClassRole) session.get(ClassRole.class, classRole.getId());
			logger.info("ClassRole retrieved from the database");

			// set the name to match the input ClassRole
			// we do not update id because they already match
			// also, we should not be going around changing primary keys
			cRole.setName(classRole.getName());
			logger.info("ClassRole from database updated");

			// save the changes
			session.save(cRole);
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
		logger.info("Returning updated ClassRole");
		logger.debug("ClassRole: " + cRole.toString());
		// return the updated ClassRole
		return cRole;
	}
	
	/**
	 * Takes in a ClassRole object and then inserts a record into the 
	 * ClassRole table whose data matches the fields of the ClassRole object.
	 * Returns the primary key of the newly inserted record as an Integer object
	 */
	@Override
	public Integer insertClassRole(ClassRole classRole) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to an Integer
		Integer id = null;
		logger.info("Inserting ClassRole via DAO");
		logger.debug("ClassRole: " + classRole.toString());

		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");
			// perform the insertion
			id = (Integer) session.save(classRole);
			logger.info("ClassRole inserted");
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
	 * Takes an Integer that corresponds to the primary key of an object in the ClassRole table.
	 * Then deletes that record from the table. Sets the ClassRole foriegn key of ClassMembership
	 * records to null if the current foreign key corresponds to the record to be deleted
	 */
	@Override
	public void deleteClassRoleById(Integer id) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null Transaction reference
		Transaction tx = null;
		// create a null reference to a Query
		Query membershipQuery = null;
		// create an HQL string
		String membershipHql = "UPDATE ClassMembership SET role = null WHERE role = :role";
		
		logger.info("Deleting ClassRole via DAO");
		logger.debug("With ID: " + id);
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");
			
			// retrieve the role to be deleted
			ClassRole role = (ClassRole) session.get(ClassRole.class, id);
			logger.info("ClassRole to be deleted has been retrieved");
			
			//generate the query
			membershipQuery = session.createQuery(membershipHql);
			logger.info("Membership query generated");
			
			//set the role parameter
			membershipQuery.setParameter("role", role);
			logger.info("Role parameter set");
			
			// update the classMemberships
			membershipQuery.executeUpdate();
			logger.info("Memberships updated");
			
			// delete the ClassRole
			session.delete(role);
			logger.info("ClassRole deleted");
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
	}

}
