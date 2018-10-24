package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.beans.ClassMembership;
import com.revature.beans.ClassRole;
import com.revature.beans.Cls;
import com.revature.beans.User;
import com.revature.daos.ClassMembershipDao;
import com.revature.daos.ClassMembershipDaoImpl;
import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpl;
import com.revature.dtos.ClassMembershipDto;
import com.revature.dtos.UserDto;

public class ClassMembershipService {
	private final static Logger logger = Logger.getLogger(ClassMembershipService.class);
	private static ClassMembershipDao cd;
	private static UserDao ud;

	/**
	 * Selects all records in the ClassMembership table and returns them
	 * as a list of ClassMemberships
	 * @return
	 */
	public static List<ClassMembershipDto> selectAllClassMembership() {
		// create a new ClassMembershipDaoImpl
		cd = new ClassMembershipDaoImpl();
		logger.info("ClassMembershipDaoImpl created");

		// get all of the classMemberships from the database
		List<ClassMembership> classMemberships = cd.selectAllClassMembership();
		logger.info("All classMemberships selected from the database");

		// create an empty list of ClassMembershipDtos to hold them
		List<ClassMembershipDto> classMembershipDtos = new ArrayList<>();
		logger.info("New list of ClassMembershipDtos created");

		// iterate through the classMemberships to make classMembershipDtos from them
		logger.info("Interating through ClassMembership list");
		for (ClassMembership cMembership : classMemberships) {
			logger.debug("ClassMembership: " + cMembership);

			// create the UserDto to be used in the classMembershipDto
			UserDto user = UserService.createUserDtoFromUser(cMembership.getUser());
			logger.info("UserDto created from user");
			logger.debug("User: " + cMembership.getUser());
			logger.debug("UserDto: " + user);

			// create a new ClassMembershipDto
			ClassMembershipDto classMembershipDto = new ClassMembershipDto(cMembership.getId(), cMembership.getCls(),
					cMembership.getRole(), user);
			logger.info("ClassMembershipDto created");
			logger.debug("ClassMembershipDto: " + classMembershipDto);

			// add the ClassMembershipDto to the list
			classMembershipDtos.add(classMembershipDto);
			logger.info("ClassMembershipDto added to list");
		}
		// return the list of ClassMembershipDtos
		logger.info("Returning list of ClassMembershipDtos");
		return classMembershipDtos;
	}
	
	/**
	 * Takes in an Integer value and selects the record in the ClassMembership table with that value
	 * as the primary key. Returns a ClassMembershipDto relating to that record
	 * @param id
	 * @return
	 */
	public static ClassMembershipDto selectClassMemebershipById(Integer id) {
		// create a new ClassMembershipDaoImpl
		cd = new ClassMembershipDaoImpl();
		logger.info("ClassMembershipDaoImpl created");

		// get the record that with id as the primary key as a ClassMembership Object
		ClassMembership classMembership = cd.selectClassMembershipById(id);
		logger.info("ClassMembership selected from database");
		logger.debug("ClassMembership: " + classMembership);

		// create the UserDto for the classMembership
		UserDto user = UserService.createUserDtoFromUser(classMembership.getUser());
		logger.info("UserDto created from User");
		logger.debug("User: " + classMembership.getUser());
		logger.debug("UserDto: " + user);

		// create the ClassMembershipDto
		ClassMembershipDto classMembershipDto = new ClassMembershipDto(classMembership.getId(),
				classMembership.getCls(), classMembership.getRole(), user);
		logger.info("ClassMembershipDto created");
		logger.debug("ClassMembershipDto: " + classMembershipDto);
		logger.info("Returning ClassMembershipDto");
		// return the ClassMembershipDto
		return classMembershipDto;
	}

	/**
	 * Takes in a ClassMembershipDto and updates the corresponding record in the ClassMembership
	 * table to match it. Returns a ClassMembershipDto associated with the newly updated record
	 * @param classMembershipDto
	 * @return
	 */
	public static ClassMembershipDto updateClassMembership(ClassMembership classMembershipDto) {
		// create a new ClassMembershipDao
		cd = new ClassMembershipDaoImpl();
		logger.info("ClassMembershipDaoImpl created");

		// create a new UserDao
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get the ClassMembership object that matches from the database
		ClassMembership classMembership = cd.selectClassMembershipById(classMembershipDto.getId());
		logger.info("Retrieved corresponding ClassMembership from database");
		logger.debug("ClassMembership: " + classMembership);
		logger.debug("ClassMembershipDto: " + classMembershipDto);

		// get the User that corresponds to the UserDto
		User user = ud.selectUserById(classMembershipDto.getUser().getId());
		logger.info("User retrieved from database");
		logger.debug("User: " + user);

		// update the ClassMembership object to match the ClassMembershipDto
		// we do not change the id because the
		// id values must already be equal because of how
		// the ClassMembership object was retrieved
		classMembership.setCls(classMembership.getCls());
		classMembership.setRole(classMembershipDto.getRole());
		classMembership.setUser(user);
		logger.info("ClassMembership object modified to match ClassMembershipDto");

		// update the ClassMembership
		ClassMembership updatedClassMembership = cd.updateClassMembership(classMembership);
		logger.info("ClassMembership object updated, fresh ClassMembership object returned");

		// get a UserDto to match the inserted user
		UserDto updatedUser = UserService.createUserDtoFromUser(updatedClassMembership.getUser());
		logger.info("UserDto created for updated user");
		logger.debug("User: " + updatedClassMembership.getUser());
		logger.debug("UserDto: " + updatedUser);

		// create the new ClassMembershipDto to be returned
		ClassMembershipDto updated = new ClassMembershipDto(updatedClassMembership.getId(),
				updatedClassMembership.getCls(), updatedClassMembership.getRole(), updatedUser);
		logger.info("ClassMembershipDto created");
		logger.debug("Updated ClassMembershipDto: " + updated);

		// return the ClassMembershipDto
		logger.info("Returning updated ClassMembershipDto");
		return updated;
	}
	/**
	 * Takes in an Integer and deletes the record with that primary key from the ClassMembership table
	 * @param id
	 */
	public static void deleteClassMembershipById(Integer id) {
		// create a new ClassMembershipDaoImpl
		cd = new ClassMembershipDaoImpl();
		logger.info("ClassMembershipDaoImpl created");

		// make the deletion call
		cd.deleteClassMembershipById(id);
		logger.info("ClassMembership deleted from the database");
	}

	/**
	 * Takes in a Cls, Role, and a UserDto and inserts a record into the ClassMemberhsip table
	 * with those Cls, Role, and User values. Returns a ClassMembershipDto representation of that record
	 * @param cls
	 * @param role
	 * @param userDto
	 * @return
	 */
	public static ClassMembershipDto insertClassMembership(Cls cls, ClassRole role, UserDto userDto) {
		// create a new ClassMembershipDaoImpl
		cd = new ClassMembershipDaoImpl();
		logger.info("ClassMembershipDaoImpl created");

		// create a new UserDaoImpl
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get the User that corresponds to the UserDto
		User user = ud.selectUserById(userDto.getId());
		logger.info("User retrieved from database");
		logger.debug("User: " + user);

		// create a classMembership to insert
		// since we are inserting a new classMembership the primary key does not matter
		// we will input 0 as the default value
		ClassMembership toInsert = new ClassMembership(0, user, cls, role);
		logger.info("ClassMembership to insert generated");
		logger.debug("ClassMembership: " + toInsert.toString());

		// insert the ClassMembership
		Integer id = cd.insertClassMembership(toInsert);
		logger.info("ClassMembership inserted");
		logger.debug("Id: " + id);

		// retrieve the now inserted classMembership
		ClassMembership inserted = cd.selectClassMembershipById(id);
		logger.info("ClassMembership retrieved from database");
		logger.debug("ClassMembership: " + inserted.toString());

		// create the UserDto for the ClassMembershipDto
		UserDto insertedUser = UserService.createUserDtoFromUser(inserted.getUser());
		logger.info("UserDto created from insered ClassMembership");
		logger.debug("User: " + inserted.getUser());
		logger.debug("UserDto: " + insertedUser);

		// create a ClassMembershipDto to return
		ClassMembershipDto classMembership = new ClassMembershipDto(inserted.getId(), inserted.getCls(),
				inserted.getRole(), insertedUser);
		logger.info("ClassMembershipDto created");
		logger.debug("ClassMembershipDto: " + classMembership);

		// return the ClassMembershipDto
		logger.info("Returning ClassMembershipDto");
		return classMembership;
	}

	/**
	 * Takes in a UserDto and selects all records from the ClassMembership table with
	 * that user in the user column. Returns them as a list of ClassMembershipDtos
	 * @param usr
	 * @return
	 */
	public static List<ClassMembershipDto> selectClassMembershipByUser(UserDto usr) {
		// create a new ClassMembershipDaoImpl
		cd = new ClassMembershipDaoImpl();
		logger.info("ClassMembershipDaoImpl created");

		// create a new UserDaoImpl
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get the user of the classMembership as a User object
		User user = ud.selectUserById(usr.getId());
		logger.info("User retrieved from database");
		logger.debug("User: " + user);
		logger.debug("UserDto: " + usr);

		// get all of the classMemberships from the database
		List<ClassMembership> classMemberships = cd.selectClassMembershipByUser(user);
		logger.info("All classMemberships with specified user selected from the database");

		// create an empty list of ClassMembershipDtos to hold them
		List<ClassMembershipDto> classMembershipDtos = new ArrayList<>();
		logger.info("New list of ClassMembershipDtos created");

		// iterate through the classMemberships to make classMembershipDtos from them
		logger.info("Interating through ClassMembership list");
		for (ClassMembership cMembership : classMemberships) {
			logger.debug("ClassMembership: " + cMembership);

			// create the UserDto to be used in the classMembershipDto
			UserDto userDto = UserService.createUserDtoFromUser(cMembership.getUser());
			logger.info("UserDto created from user");
			logger.debug("User: " + cMembership.getUser());
			logger.debug("UserDto: " + userDto);

			// create a new ClassMembershipDto
			ClassMembershipDto classMembershipDto = new ClassMembershipDto(cMembership.getId(), cMembership.getCls(),
					cMembership.getRole(), userDto);
			logger.info("ClassMembershipDto created");
			logger.debug("ClassMembershipDto: " + classMembershipDto);

			// add the ClassMembershipDto to the list
			classMembershipDtos.add(classMembershipDto);
			logger.info("ClassMembershipDto added to list");
		}
		// return the list of ClassMembershipDtos
		logger.info("Returning list of ClassMembershipDtos");
		return classMembershipDtos;
	}

	/**
	 * Takes in a Cls and selects all records from the ClassMembership table with
	 * that cls in the cls column. Returns them as a list of ClassMembershipDtos
	 * @param cls
	 * @return
	 */
	public static List<ClassMembershipDto> selectClassMembershipByClass(Cls cls) {
		// create a new ClassMembershipDaoImpl
		cd = new ClassMembershipDaoImpl();
		logger.info("ClassMembershipDaoImpl created");

		// get all of the classMemberships from the database
		List<ClassMembership> classMemberships = cd.selectClassMembershipByClass(cls);
		logger.info("All classMemberships with specified class selected from the database");

		// create an empty list of ClassMembershipDtos to hold them
		List<ClassMembershipDto> classMembershipDtos = new ArrayList<>();
		logger.info("New list of ClassMembershipDtos created");

		// iterate through the classMemberships to make classMembershipDtos from them
		logger.info("Interating through ClassMembership list");
		for (ClassMembership cMembership : classMemberships) {
			logger.debug("ClassMembership: " + cMembership);

			// create the UserDto to be used in the classMembershipDto
			UserDto user = UserService.createUserDtoFromUser(cMembership.getUser());
			logger.info("UserDto created from user");
			logger.debug("User: " + cMembership.getUser());
			logger.debug("UserDto: " + user);

			// create a new ClassMembershipDto
			ClassMembershipDto classMembershipDto = new ClassMembershipDto(cMembership.getId(), cMembership.getCls(),
					cMembership.getRole(), user);
			logger.info("ClassMembershipDto created");
			logger.debug("ClassMembershipDto: " + classMembershipDto);

			// add the ClassMembershipDto to the list
			classMembershipDtos.add(classMembershipDto);
			logger.info("ClassMembershipDto added to list");
		}
		// return the list of ClassMembershipDtos
		logger.info("Returning list of ClassMembershipDtos");
		return classMembershipDtos;
	}
	/**
	 * Takes in a UserDto and a Cls and selects all records from the ClassMembership table with
	 * that user in the user column and that cls in the cls column. Returns them as a list of ClassMembershipDtos
	 * @param usr
	 * @param cls
	 * @return
	 */
	public static ClassMembershipDto getUserMembershipOfClass(UserDto usr, Cls cls) {
		// create a new ClassMembershipDaoImpl
		cd = new ClassMembershipDaoImpl();
		logger.info("ClassMembershipDaoImpl created");

		// create a new UserDaoImpl
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get the user of the classMembership as a User object
		User user = ud.selectUserById(usr.getId());
		logger.info("User retrieved from database");
		logger.debug("User: " + user);
		logger.debug("UserDto: " + usr);

		// get all of the classMemberships from the database
		ClassMembership classMembership = cd.getUserMembershipOfClass(user, cls);
		logger.info("ClassMemberships of specified cls for specified user selected");
		logger.debug("classMembership: " + classMembership.toString());

		// create the UserDto for the classMembership
		UserDto userDto = UserService.createUserDtoFromUser(classMembership.getUser());
		logger.info("UserDto created from User");
		logger.debug("User: " + classMembership.getUser());
		logger.debug("UserDto: " + user);

		// create the ClassMembershipDto
		ClassMembershipDto classMembershipDto = new ClassMembershipDto(classMembership.getId(),
				classMembership.getCls(), classMembership.getRole(), userDto);
		logger.info("ClassMembershipDto created");
		logger.debug("ClassMembershipDto: " + classMembershipDto);
		logger.info("Returning ClassMembershipDto");
		// return the ClassMembershipDto
		return classMembershipDto;
	}
}
