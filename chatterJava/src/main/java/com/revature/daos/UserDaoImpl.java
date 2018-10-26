package com.revature.daos;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Note;
import com.revature.beans.User;
import com.revature.util.HibernateUtil;

public class UserDaoImpl implements UserDao {
	private final static Logger logger = Logger.getLogger(UserDaoImpl.class);

	/**
	 * Selects all records from the User table and returns them as a list of User
	 * objects
	 */
	@Override
	public List<User> selectAllUser() {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a list of Users
		List<User> users = null;
		logger.info("Selecting all Users via DAO");

		try {
			// attempt to get all the access levels
			logger.info("Executing FROM Users query");
			users = session.createQuery("FROM User").list();
		} catch (HibernateException e) {
			// if there is a hibernate exception, catch it
			// and print the stack trace
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up after ourselves
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning list of Users");
		logger.debug("Users: " + users.toString());
		return users;
	}

	/**
	 * Takes in an Integer and selects the record from the User table with that
	 * value as its primary key. Returns that record as a User object
	 */
	@Override
	public User selectUserById(Integer id) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// make a null reference to a User object
		User user = null;
		logger.info("Selecting User by id via DAO");
		logger.info("With id" + id);

		try {
			// attempt to get the User
			user = (User) session.get(User.class, id);
			logger.info("Retrieved User");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// clean up
			session.close();
			logger.info("Session closed");
		}
		logger.info("Returning User");
		logger.debug("User: " + user.toString());
		// return the User
		return user;
	}

	/**
	 * Takes in a String and selects the record from the user table with that string
	 * as the Username value. Returns that record as a User object
	 */
	@Override
	public User selectUserByUsername(String username) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a query
		Query query = null;
		// create the HQL string
		String hql = "FROM User WHERE username = :username";
		// create a null reference to a user
		User user = null;

		logger.info("Get User by Username via DAO");
		logger.debug("Username :" + username);

		try {
			// generate a query
			query = session.createQuery(hql);
			logger.info("Query generated");

			// set the user parameter
			query.setParameter("username", username);
			logger.info("Parameter set");

			// attempt to execute the query
			user = (User) query.uniqueResult();
			logger.info("Users retrieved");
		} catch (HibernateException e) {
			// if a Hibernate Exception is triggered, catch
			// it and log it
			logger.error("HibernateException triggered", e);
		} finally {
			// close the session
			session.close();
			logger.info("Session closed");
		}
		// return the list of Users
		logger.info("Returning User list");
		logger.debug("User: " + user);
		return user;
	}

	/**
	 * Takes in a User object and inserts a new record in the User table with the
	 * same values as those stored in the User object. Returns the primary key of
	 * the newly inserted record as an Integer
	 */
	@Override
	public Integer insertNewUser(User user) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to an Integer
		Integer id = null;
		logger.info("Inserting User via DAO");
		logger.debug("User: " + user.toString());

		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");
			// perform the insertion
			id = (Integer) session.save(user);
			logger.info("User inserted");
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
	 * Takes in an Integer and deletes the record in the User table with that
	 * primary key value. Also deletes all notes, accesses, and class memberships
	 * associated with that user
	 */
	@Override
	public void deleteUserById(Integer id) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null Transaction reference
		Transaction tx = null;
		// create a null reference to a query
		Query membershipQuery = null;
		Query accessQuery = null;
		Query noteQuery = null;
		Query userAccessNoteQuery = null;
		Query classAccessNoteQuery = null;
		// create an HQL string from removing all class memberships
		String membershipHql = "DELETE FROM ClassMembership WHERE user = :user";
		String accessHql = "DELETE FROM UserAccess WHERE user = :user";
		String noteHql = "SELECT FROM Note WHERE owner = :owner";
		String userAccessNoteHql = "DELETE FROM UserAccess WHERE note = :note";
		String classAccessNoteHql = "DELTE FROM ClassAccess WHERE note = :note";
		logger.info("Deleting User via DAO");
		logger.debug("With ID: " + id);
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Beginning transaction");

			// grab the user to be deleted
			User user = (User) session.get(User.class, id);
			logger.info("User to be deleted retrieved");

			// generate a query
			membershipQuery = session.createQuery(membershipHql);
			logger.info("Membership deletion query generated");

			// set the user parameter
			membershipQuery.setParameter("user", user);
			logger.info("User parameter set");

			// execute the delete statement
			membershipQuery.executeUpdate();
			logger.info("Class Memberships deleted");

			// generate the access deletion query
			accessQuery = session.createQuery(accessHql);
			logger.info("Access Deletion query generated");

			// set the user parameter
			accessQuery.setParameter("user", user);
			logger.info("User parameter set");

			// execute the delete statement
			accessQuery.executeUpdate();
			logger.info("Class Accesses deleted");

			// create the query
			noteQuery = session.createQuery(noteHql);
			logger.info("Created note retrieval query");

			// set the owner parameter
			noteQuery.setParameter("owner", user);
			logger.info("Owner parameter set");

			// retireve the notes to be deleted
			List<Note> notes = noteQuery.list();
			logger.info("Notes to be deleted have been retrieved");

			// iterate through the retrieved notes and delete all accesses to the notes
			logger.info("Iterating through notes to delete accesses");
			for (Note n : notes) {
				logger.debug("Note: " + n);
				// create the user access query
				userAccessNoteQuery = session.createQuery(userAccessNoteHql);
				logger.info("UserAccess deletion query created");

				// set the note parameter
				userAccessNoteQuery.setParameter("note", n);
				logger.info("Note parameter set");

				// execute the userAccess deletion query
				userAccessNoteQuery.executeUpdate();
				logger.info("UserAccesses deleted");

				// create the class access query
				classAccessNoteQuery = session.createQuery(classAccessNoteHql);
				logger.info("ClassAccess deletion query created");

				// set the note parameter
				classAccessNoteQuery.setParameter("note", n);
				logger.info("Note parameter set");

				// execute the classAccess deletion query
				classAccessNoteQuery.executeUpdate();
				logger.info("ClassAccesses deleted");

				// delete the note from the database
				session.delete(n);
				logger.info("Note deleted");
			}

			// delete the User
			session.delete(session.get(User.class, id));
			logger.info("User deleted");
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

	/**
	 * Takes in a User object and updates the record in the User table with the
	 * primary key that matches the object's id field to match the input User
	 * object. Returns a new User object corresponding to the updated record
	 */
	@Override
	public User updateUser(User user) {
		// create a new session
		Session session = HibernateUtil.getSession();
		// create a null reference to a transaction
		Transaction tx = null;
		// create a null reference to a User
		User u = null;

		logger.info("Updating User via DAO");
		logger.debug("User: " + user.toString());
		try {
			// begin the transaction
			tx = session.beginTransaction();
			logger.info("Transaction begins");

			// get the version from the database
			u = (User) session.get(User.class, user.getId());
			logger.info("User retrieved from the database");

			// set values to match the input User
			// we do not update id because they already match
			// also, we should not be going around changing primary keys
			u.setActivation(user.getActivation());
			u.setfName(user.getfName());
			u.setlName(user.getlName());
			u.setPassword(user.getPassword());
			u.setPriv(user.getPriv());
			u.setUsername(user.getUsername());
			u.setEmail(user.getEmail());
			logger.info("User from database updated");

			// save the changes
			session.save(u);
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
		logger.info("Returning updated User");
		logger.debug("User: " + u.toString());
		// return the updated User
		return u;
	}

}
