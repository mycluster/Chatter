package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.beans.Activation;
import com.revature.beans.Priv;
import com.revature.beans.User;
import com.revature.daos.ActivationDao;
import com.revature.daos.ActivationDaoImpl;
import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpl;
import com.revature.dtos.UserDto;
import com.revature.util.ActivationUtil;

public class UserService {
	private final static Logger logger = Logger.getLogger(UserService.class);
	private UserDao ud;
	private ActivationDao ad;

	/**
	 * 
	 * @param user
	 * @param password
	 * @return Boolean
	 * 
	 *         Takes in a UserDto and a String and checks if the String matches the
	 *         stored password in the database for the User in question. If returns
	 *         true if the passwords match and false otherwise
	 */
	public Boolean checkPassword(UserDto user, String password) {
		// create a new UserDao
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get the User object from the database
		User usr = ud.selectUserById(user.getId());
		logger.info("User retrieved from database");
		logger.debug("User: " + usr.toString());
		logger.debug("UserDto: " + user.toString());
		logger.debug("Password: " + password);

		// compare the password
		logger.info("Comparing passwords");
		if (usr.getPassword().equals(password)) {
			// if the passwords match return true
			logger.info("Passwords match, returning true");
			return new Boolean(true);
		} else {
			// if the passwords do not match return false
			logger.info("Passwords do not match, returning false");
			return new Boolean(false);
		}
	}

	/**
	 * 
	 * @param username the username to be looked up
	 * @return UserDto that contains the values associated with the record in the
	 *         User table with the corresponding username
	 */
	public UserDto getUserDtoByUsername(String username) {
		// create a new UserDao
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get the User from the database
		User user = ud.selectUserByUsername(username);
		logger.info("User selected from database");
		logger.debug("User: " + user.toString());

		// create the UserDto
		UserDto usr = new UserDto(user.getId(), user.getUsername(), user.getfName(), user.getlName(), user.getPriv(),
				user.getActivation());
		logger.info("UserDto created");
		logger.debug("UserDto: " + usr);

		// return the UserDto
		logger.info("Returning UserDto");
		return usr;
	}

	/**
	 * Takes in a UserDto and updates the corresponding record in the User table in
	 * the database to match it. Returns a new UserDto corresponding to the freshly
	 * updated object
	 * 
	 * @param user
	 * @return UserDto
	 */
	public UserDto updateUserDto(UserDto user) {
		// create a new UserDao
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get the User object that matches from the database
		User usr = ud.selectUserById(user.getId());
		logger.info("Retrieved corresponding User from database");
		logger.debug("User: " + usr);
		logger.debug("UserDto: " + user);

		// update the User object to match the UserDto
		// we do not change the id because the
		// id values must already be equal because of how
		// the User object was retrieved
		usr.setUsername(user.getUsername());
		usr.setActivation(user.getAcvtivation());
		usr.setfName(user.getfName());
		usr.setlName(user.getlName());
		logger.info("User object modified to match UserDto");

		// update the User
		User updatedUsr = ud.updateUser(usr);
		logger.info("User object updated, fresh User object returned");

		// create the new UserDto to be returned
		UserDto updated = new UserDto(updatedUsr.getId(), updatedUsr.getUsername(), updatedUsr.getfName(),
				updatedUsr.getlName(), updatedUsr.getPriv(), updatedUsr.getActivation());
		logger.info("UserDto created");
		logger.debug("Updated UserDto: " + updated);

		// return the UserDto
		logger.info("Returning updated UserDto");
		return updated;

	}

	/**
	 * Takes in a username, first name, last name, and Priv and inserts a fresh
	 * record into a the User table of the database. By default, inserting a new
	 * user creates a user that is not activated
	 * 
	 * @param username
	 * @param fName
	 * @param lName
	 * @param password
	 * @return The UserDto created from the record of the freshly inserted User
	 */
	public UserDto insertNewUserDto(String username, String fName, String lName, String password, Priv priv) {
		// create a new UserDaoImpl
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// create a new ActivationDaoImpl
		ad = new ActivationDaoImpl();
		logger.info("ActivationDaoImpl created");

		// get the NOT ACTIVATED activation level from the database as an activation
		// bean
		Activation activation = ad.selectActivationById(ActivationUtil.notActivatedId());
		logger.info("Activation for NOT ACTIVATED level retrieved");
		logger.debug("Activation: " + activation.toString());

		// create a user to insert
		// since we are inserting a new user the primary key does not matter
		// we will input 0 as the default value
		User toInsert = new User(0, username, fName, lName, password, priv, activation);
		logger.info("User to insert generated");
		logger.debug("User: " + toInsert.toString());

		// insert the User
		Integer id = ud.insertNewUser(toInsert);
		logger.info("User inserted");
		logger.debug("Id: " + id);

		// retrieve the now inserted user
		User inserted = ud.selectUserById(id);
		logger.info("User retrieved from database");
		logger.debug("User: " + inserted.toString());

		// create a UserDto to return
		UserDto user = new UserDto(inserted.getId(), inserted.getUsername(), inserted.getfName(), inserted.getlName(),
				inserted.getPriv(), inserted.getActivation());
		logger.info("UserDto created");
		logger.debug("UserDto: " + user);

		// return the UserDto
		logger.info("Returning UserDto");
		return user;
	}

	/**
	 * Takes in a id associated with a user and deletes the associated record from
	 * the database table Effectively a wrapper for com.revature.Daos.UserDao method
	 * deleteUserById
	 * 
	 * @param id
	 */
	public void deleteUserDtoById(Integer id) {
		// create a new UserDaoImpl
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// make the deletion call
		ud.deleteUserById(id);
		logger.info("User deleted from the database");
	}

	/**
	 * Returns a list of all users in the database as UserDto objects
	 * 
	 * @return
	 */
	public List<UserDto> selectAllUserDto() {
		// create a new UserDaoImpl
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get all users from the database
		List<User> users = ud.selectAllUser();
		logger.info("Users retrieved from database");

		// create an empty list of UseDtos
		List<UserDto> userDtos = new ArrayList<>();
		logger.info("Empty UserDto list created");

		logger.info("Iterating through list of users");
		for (User u : users) {
			logger.debug("User: " + u);

			// create a new UserDto to add to the list
			UserDto userDto = new UserDto(u.getId(), u.getUsername(), u.getfName(), u.getlName(), u.getPriv(),
					u.getActivation());
			logger.info("UserDto created");
			logger.debug("UserDto: "+ userDto);
			
			// adding UserDto to list
			logger.info("Adding UserDto to list");
			userDtos.add(userDto);
		}
		
		// return the list of UserDtos
		logger.info("Returning UserDto list");
		return userDtos;
	}
}
