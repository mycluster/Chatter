package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.beans.AccessLevel;
import com.revature.beans.Note;
import com.revature.beans.User;
import com.revature.beans.UserAccess;
import com.revature.daos.NoteDao;
import com.revature.daos.NoteDaoImpl;
import com.revature.daos.UserAccessDao;
import com.revature.daos.UserAccessDaoImpl;
import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpl;
import com.revature.dtos.NoteDto;
import com.revature.dtos.UserAccessDto;
import com.revature.dtos.UserDto;

public class UserAccessService {
	private final static Logger logger = Logger.getLogger(UserAccessService.class);
	private static UserAccessDao ad;
	private static UserDao ud;
	private static NoteDao nd;

	public List<UserAccessDto> selectAllUserAccessDto() {
		// create a new UserAccessDaoImpl
		ad = new UserAccessDaoImpl();
		logger.info("UserAccessDaoImpl created");

		// get all of the userAccesses from the database
		List<UserAccess> userAccesses = ad.selectAllUserAccess();
		logger.info("All userAccesses selected from the database");

		// create an empty list of UserAccessDtos to hold them
		List<UserAccessDto> userAccessDtos = new ArrayList<>();
		logger.info("New list of UserAccessDtos created");

		// iterate through the userAccesses to make userAccessDtos from them
		logger.info("Interating through UserAccess list");
		for (UserAccess u : userAccesses) {
			logger.debug("UserAccess: " + u);

			// create the UserDto to be used in the userAccessDto
			UserDto user = UserService.createUserDtoFromUser(u.getUser());
			logger.info("UserDto created from user");
			logger.debug("User: " + u.getUser());
			logger.debug("UserDto: " + user);

			// create the NoteDto to be used in the UserAccessDto
			NoteDto note = NoteService.createNoteDtoFromNote(u.getNote());
			logger.info("NoteDto created from Note");
			logger.debug("Note: " + u.getNote());
			logger.debug("NoteDto: " + note);

			// create a new UserAccessDto
			UserAccessDto userAccessDto = new UserAccessDto(u.getId(), u.getAccess(), note, user);
			logger.info("UserAccessDto created");
			logger.debug("UserAccessDto: " + userAccessDto);

			// add the UserAccessDto to the list
			userAccessDtos.add(userAccessDto);
			logger.info("UserAccessDto added to list");
		}
		// return the list of UserAccessDtos
		logger.info("Returning list of UserAccessDtos");
		return userAccessDtos;
	}

	/**
	 * Takes in an Integer and selects the record in the UserAccess table of the
	 * database that has that integer as the primary key. Returns that record as a
	 * UserAccessDto
	 * 
	 * @param id
	 * @return
	 */
	public UserAccessDto selectUserAccessById(Integer id) {
		// create a new UserAccessDaoImpl
		ad = new UserAccessDaoImpl();
		logger.info("UserAccessDaoImpl created");

		// get the corresponding UserAccess
		UserAccess userAccess = ad.selectUserAccessById(id);
		logger.info("UserAccess retrieved from database");
		logger.debug("UserAccess: " + userAccess);

		// create the UserDto to be used in the userAccessDto
		UserDto user = UserService.createUserDtoFromUser(userAccess.getUser());
		logger.info("UserDto created from user");
		logger.debug("User: " + userAccess.getUser());
		logger.debug("UserDto: " + user);

		// create the NoteDto to be used in the UserAccessDto
		NoteDto note = NoteService.createNoteDtoFromNote(userAccess.getNote());
		logger.info("NoteDto created from Note");
		logger.debug("Note: " + userAccess.getNote());
		logger.debug("NoteDto: " + note);

		// create a new UserAccessDto
		UserAccessDto userAccessDto = new UserAccessDto(userAccess.getId(), userAccess.getAccess(), note, user);
		logger.info("UserAccessDto created");
		logger.debug("UserAccessDto: " + userAccessDto);

		// return the UserAccessDto
		logger.info("Returning UserAccessDto");
		return userAccessDto;
	}

	/**
	 * Takes in a UserAccessDto and updates the corresponding record in the
	 * UserAccess table of the database. Returns a UserAccessDto associted with the
	 * updated record
	 * 
	 * @param userAccessDto
	 * @return
	 */
	public UserAccessDto updateUserAccess(UserAccessDto userAccessDto) {
		// create a new UserAccessDao
		ad = new UserAccessDaoImpl();
		logger.info("UserAccessDaoImpl created");

		// create a new UserDao
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// create a new NoteDao
		nd = new NoteDaoImpl();
		logger.info("NoteDaoImpl created");

		// get the UserAccess object that matches from the database
		UserAccess userAccess = ad.selectUserAccessById(userAccessDto.getId());
		logger.info("Retrieved corresponding UserAccess from database");
		logger.debug("UserAccess: " + userAccess);
		logger.debug("UserAccessDto: " + userAccessDto);

		// get the User that corresponds to the UserDto
		User user = ud.selectUserById(userAccessDto.getUser().getId());
		logger.info("User retrieved from database");
		logger.debug("User: " + user);

		// get the Note that corresponds to the NoteDto
		Note note = nd.selectNoteById(userAccessDto.getNote().getId());
		logger.info("Note retrieved from database");
		logger.debug("Note: " + note);

		// update the UserAccess object to match the UserAccessDto
		// we do not change the id because the
		// id values must already be equal because of how
		// the UserAccess object was retrieved
		userAccess.setAccess(userAccessDto.getAccess());
		userAccess.setNote(note);
		userAccess.setUser(user);
		logger.info("UserAccess object modified to match UserAccessDto");

		// update the UserAccess
		UserAccess updatedUserAccess = ad.updateUserAccess(userAccess);
		logger.info("UserAccess object updated, fresh UserAccess object returned");

		// get a UserDto to match the inserted user
		UserDto updatedUser = UserService.createUserDtoFromUser(updatedUserAccess.getUser());
		logger.info("UserDto created for updated user");
		logger.debug("User: " + updatedUserAccess.getUser());
		logger.debug("UserDto: " + updatedUser);

		// create the NoteDto to be used in the UserAccessDto
		NoteDto updatedNote = NoteService.createNoteDtoFromNote(userAccess.getNote());
		logger.info("NoteDto created from Note");
		logger.debug("Note: " + updatedUserAccess.getNote());
		logger.debug("NoteDto: " + updatedNote);

		// create the new UserAccessDto to be returned
		UserAccessDto updated = new UserAccessDto(updatedUserAccess.getId(), updatedUserAccess.getAccess(), updatedNote,
				updatedUser);
		logger.info("UserAccessDto created");
		logger.debug("Updated UserAccessDto: " + updated);

		// return the UserAccessDto
		logger.info("Returning updated UserAccessDto");
		return updated;
	}

	/**
	 * Takes in an AccessLevel, NoteDto, and UserDto and inserts a new record into
	 * the UserAccess table of the database that corresponds to them. Then returns a
	 * UserAccessDto that corresponds to the newly inserted record
	 * 
	 * @param access
	 * @param note
	 * @param user
	 * @return
	 */
	public UserAccessDto insertUserAccess(AccessLevel access, NoteDto noteDto, UserDto userDto) {
		// create a new UserAccessDao
		ad = new UserAccessDaoImpl();
		logger.info("UserAccessDaoImpl created");

		// create a new UserDao
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// create a new NoteDao
		nd = new NoteDaoImpl();
		logger.info("NoteDaoImpl created");

		// get the User that corresponds to the UserDto
		User user = ud.selectUserById(userDto.getId());
		logger.info("User retrieved from database");
		logger.debug("User: " + user);
		logger.debug("UserDto: " + userDto);

		// get the Note that corresponds to the NoteDto
		Note note = nd.selectNoteById(noteDto.getId());
		logger.info("Note retrieved from database");
		logger.debug("Note: " + note);
		logger.debug("NoteDto: " + noteDto);

		// create the UserAccess to insert
		UserAccess toInsert = new UserAccess(0, user, note, access);
		logger.info("User to insert created");

		// insert the UserAccess
		Integer id = ad.insertUserAccess(toInsert);
		logger.info("UserAccess inserted");
		logger.debug("Id: " + id);

		// retrieve the now inserted userAccess
		UserAccess inserted = ad.selectUserAccessById(id);
		logger.info("UserAccess retrieved from database");
		logger.debug("UserAccess: " + inserted.toString());

		// create the UserDto for the UserAccessDto
		UserDto insertedUser = UserService.createUserDtoFromUser(inserted.getUser());
		logger.info("UserDto created from insered UserAccess");
		logger.debug("User: " + inserted.getUser());
		logger.debug("UserDto: " + insertedUser);

		// create the NoteDto for the UserAccessDto
		NoteDto insertedNote = NoteService.createNoteDtoFromNote(inserted.getNote());
		logger.info("NoteDto created from insered UserAccess");
		logger.debug("Note: " + inserted.getNote());
		logger.debug("NoteDto: " + insertedNote);

		// create a UserAccessDto to return
		UserAccessDto userAccess = new UserAccessDto(inserted.getId(), inserted.getAccess(), insertedNote,
				insertedUser);
		logger.info("UserAccessDto created");
		logger.debug("UserAccessDto: " + userAccess);

		// return the UserAccessDto
		logger.info("Returning UserAccessDto");
		return userAccess;
	}

	/**
	 * Takes in an Integer and deletes the record in the UserAccess table with that
	 * number as its primary key
	 * 
	 * @param id
	 */
	public void deleteUserAccess(Integer id) {
		// create a new UserDaoImpl
		ad = new UserAccessDaoImpl();
		logger.info("UserAccessDaoImpl created");

		// make the deletion call
		ad.deleteUserAccess(id);
		;
		logger.info("UserAccess deleted from the database");
	}

	/**
	 * Takes in a UserDto and selects all records from the UserAccess table with
	 * that user as the user column. Returns those records as a List of
	 * UserAccessDtos.
	 * 
	 * @param user
	 * @return
	 */
	public List<UserAccessDto> getUserAccessByUser(UserDto userDto) {
		// create a new UserAccessDaoImpl
		ad = new UserAccessDaoImpl();
		logger.info("UserAccessDaoImpl created");

		// create a new UserDaoImpl
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get the User that corresponds to the UserDto
		User usr = ud.selectUserById(userDto.getId());
		logger.info("User retrieved from database");
		logger.debug("User: " + usr);
		logger.debug("UserDto: " + userDto);

		// get the userAccesses from the database with the specified user
		List<UserAccess> userAccesses = ad.getUserAccessByUser(usr);
		logger.info("UserAccesses with requested user selected from the database");

		// create an empty list of UserAccessDtos to hold them
		List<UserAccessDto> userAccessDtos = new ArrayList<>();
		logger.info("New list of UserAccessDtos created");

		// iterate through the userAccesses to make userAccessDtos from them
		logger.info("Interating through UserAccess list");
		for (UserAccess u : userAccesses) {
			logger.debug("UserAccess: " + u);

			// create the UserDto to be used in the userAccessDto
			UserDto user = UserService.createUserDtoFromUser(u.getUser());
			logger.info("UserDto created from user");
			logger.debug("User: " + u.getUser());
			logger.debug("UserDto: " + user);

			// create the NoteDto to be used in the UserAccessDto
			NoteDto note = NoteService.createNoteDtoFromNote(u.getNote());
			logger.info("NoteDto created from Note");
			logger.debug("Note: " + u.getNote());
			logger.debug("NoteDto: " + note);

			// create a new UserAccessDto
			UserAccessDto userAccessDto = new UserAccessDto(u.getId(), u.getAccess(), note, user);
			logger.info("UserAccessDto created");
			logger.debug("UserAccessDto: " + userAccessDto);

			// add the UserAccessDto to the list
			userAccessDtos.add(userAccessDto);
			logger.info("UserAccessDto added to list");
		}
		// return the list of UserAccessDtos
		logger.info("Returning list of UserAccessDtos");
		return userAccessDtos;
	}

	/**
	 * Takes in a NoteDto and selects all records from the UserAccess table with
	 * that note in the note column. Returns a list of UserAccessDtos that
	 * correspond to those records
	 * 
	 * @param noteDto
	 * @return
	 */
	public List<UserAccessDto> getUserAccessByNote(NoteDto noteDto) {
		// create a new UserAccessDaoImpl
		ad = new UserAccessDaoImpl();
		logger.info("UserAccessDaoImpl created");

		// create a new NoteDaoImpl
		nd = new NoteDaoImpl();
		logger.info("NoteDaoImpl created");

		// get the User that corresponds to the UserDto
		Note n = nd.selectNoteById(noteDto.getId());
		logger.info("Note retrieved from database");
		logger.debug("Note: " + n);
		logger.debug("NoteDto: " + noteDto);

		// get the userAccesses from the database with the specified user
		List<UserAccess> userAccesses = ad.getUserAccessByNote(n);
		logger.info("UserAccesses with requested note selected from the database");

		// create an empty list of UserAccessDtos to hold them
		List<UserAccessDto> userAccessDtos = new ArrayList<>();
		logger.info("New list of UserAccessDtos created");

		// iterate through the userAccesses to make userAccessDtos from them
		logger.info("Interating through UserAccess list");
		for (UserAccess u : userAccesses) {
			logger.debug("UserAccess: " + u);

			// create the UserDto to be used in the userAccessDto
			UserDto user = UserService.createUserDtoFromUser(u.getUser());
			logger.info("UserDto created from user");
			logger.debug("User: " + u.getUser());
			logger.debug("UserDto: " + user);

			// create the NoteDto to be used in the UserAccessDto
			NoteDto note = NoteService.createNoteDtoFromNote(u.getNote());
			logger.info("NoteDto created from Note");
			logger.debug("Note: " + u.getNote());
			logger.debug("NoteDto: " + note);

			// create a new UserAccessDto
			UserAccessDto userAccessDto = new UserAccessDto(u.getId(), u.getAccess(), note, user);
			logger.info("UserAccessDto created");
			logger.debug("UserAccessDto: " + userAccessDto);

			// add the UserAccessDto to the list
			userAccessDtos.add(userAccessDto);
			logger.info("UserAccessDto added to list");
		}
		// return the list of UserAccessDtos
		logger.info("Returning list of UserAccessDtos");
		return userAccessDtos;
	}
	/**
	 * Takes in a UserDto and an AccessLevel and selects all records in the UserAccess table in the database
	 * with that user in the user column and that access level in the access column. Returns those records as
	 * a list of UserAccessDtos
	 * @param userDto
	 * @param access
	 * @return
	 */
	public List<UserAccessDto> getUserAccessByUserAndAccess(UserDto userDto, AccessLevel access) {
		// create a new UserAccessDaoImpl
		ad = new UserAccessDaoImpl();
		logger.info("UserAccessDaoImpl created");

		// create a new UserDaoImpl
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get the User that corresponds to the UserDto
		User usr = ud.selectUserById(userDto.getId());
		logger.info("User retrieved from database");
		logger.debug("User: " + usr);
		logger.debug("UserDto: " + userDto);

		// get the userAccesses from the database with the specified user
		List<UserAccess> userAccesses = ad.getUserAccessByUserAndAccess(usr, access);
		logger.info("UserAccesses with requested user and access level selected from the database");

		// create an empty list of UserAccessDtos to hold them
		List<UserAccessDto> userAccessDtos = new ArrayList<>();
		logger.info("New list of UserAccessDtos created");

		// iterate through the userAccesses to make userAccessDtos from them
		logger.info("Interating through UserAccess list");
		for (UserAccess u : userAccesses) {
			logger.debug("UserAccess: " + u);

			// create the UserDto to be used in the userAccessDto
			UserDto user = UserService.createUserDtoFromUser(u.getUser());
			logger.info("UserDto created from user");
			logger.debug("User: " + u.getUser());
			logger.debug("UserDto: " + user);

			// create the NoteDto to be used in the UserAccessDto
			NoteDto note = NoteService.createNoteDtoFromNote(u.getNote());
			logger.info("NoteDto created from Note");
			logger.debug("Note: " + u.getNote());
			logger.debug("NoteDto: " + note);

			// create a new UserAccessDto
			UserAccessDto userAccessDto = new UserAccessDto(u.getId(), u.getAccess(), note, user);
			logger.info("UserAccessDto created");
			logger.debug("UserAccessDto: " + userAccessDto);

			// add the UserAccessDto to the list
			userAccessDtos.add(userAccessDto);
			logger.info("UserAccessDto added to list");
		}
		// return the list of UserAccessDtos
		logger.info("Returning list of UserAccessDtos");
		return userAccessDtos;
	}
}
