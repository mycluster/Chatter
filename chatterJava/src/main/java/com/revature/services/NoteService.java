package com.revature.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.beans.Note;
import com.revature.beans.NoteType;
import com.revature.beans.User;
import com.revature.daos.NoteDao;
import com.revature.daos.NoteDaoImpl;
import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpl;
import com.revature.dtos.NoteDto;
import com.revature.dtos.UserDto;

public class NoteService {
	private final static Logger logger = Logger.getLogger(NoteService.class);
	private static NoteDao nd;
	private static UserDao ud;

	/**
	 * Selects all notes in the database and returns them as a list of NoteDtos
	 * 
	 * @return
	 */
	public static List<NoteDto> selectAllNote() {
		// create a new NoteDaoImpl
		nd = new NoteDaoImpl();
		logger.info("NoteDaoImpl created");

		// get all of the notes from the database
		List<Note> notes = nd.selectAllNote();
		logger.info("All notes selected from the database");

		// create an empty list of NoteDtos to hold them
		List<NoteDto> noteDtos = new ArrayList<>();
		logger.info("New list of NoteDtos created");

		// iterate through the notes to make noteDtos from them
		logger.info("Interating through Note list");
		for (Note n : notes) {
			logger.debug("Note: " + n);

			// create the UserDto to be used in the noteDto
			UserDto owner = UserService.createUserDtoFromUser(n.getOwner());
			logger.info("UserDto created from user");
			logger.debug("User: " + n.getOwner());
			logger.debug("UserDto: " + owner);

			// create a new NoteDto
			NoteDto noteDto = new NoteDto(n.getId(), n.getLastEdited(), n.getLocation(), owner, n.getType(),
					n.getName());
			logger.info("NoteDto created");
			logger.debug("NoteDto: " + noteDto);

			// add the NoteDto to the list
			noteDtos.add(noteDto);
			logger.info("NoteDto added to list");
		}
		// return the list of NoteDtos
		logger.info("Returning list of NoteDtos");
		return noteDtos;
	}

	/**
	 * Takes in an Integer and returns the record in the Note table of the database
	 * as a NoteDto object
	 * 
	 * @param id
	 * @return
	 */
	public static NoteDto selectNoteDtoById(Integer id) {
		// create a new NoteDaoImpl
		nd = new NoteDaoImpl();
		logger.info("NoteDaoImpl created");

		// get the record that with id as the primary key as a Note Object
		Note note = nd.selectNoteById(id);
		logger.info("Note selected from database");
		logger.debug("Note: " + note);

		// create the UserDto for the note
		UserDto owner = UserService.createUserDtoFromUser(note.getOwner());
		logger.info("UserDto created from User");
		logger.debug("User: " + note.getOwner());
		logger.debug("UserDto: " + owner);

		// create the NoteDto
		NoteDto noteDto = new NoteDto(note.getId(), note.getLastEdited(), note.getLocation(), owner, note.getType(),
				note.getName());
		logger.info("NoteDto created");
		logger.debug("NoteDto: " + noteDto);
		logger.info("Returning NoteDto");
		// return the NoteDto
		return noteDto;
	}

	/**
	 * Takes in a lastEdited Timestamp, a location String, an owner UserDto, a type
	 * NoteType, and a name String and inserts a record into the Note table of the
	 * database with the specified values. Returns a NoteDto representation of that
	 * record
	 * 
	 * @param lastEdited
	 * @param location
	 * @param ownerDto
	 * @param type
	 * @param name
	 * @return
	 */
	public static NoteDto insertNoteDto(Timestamp lastEdited, String location, UserDto ownerDto, NoteType type,
			String name) {
		// create a new NoteDaoImpl
		nd = new NoteDaoImpl();
		logger.info("NoteDaoImpl created");

		// create a new UserDaoImpl
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get the User that corresponds to the UserDto
		User owner = ud.selectUserById(ownerDto.getId());
		logger.info("User retrieved from database");
		logger.debug("User: " + owner);

		// create a note to insert
		// since we are inserting a new note the primary key does not matter
		// we will input 0 as the default value
		Note toInsert = new Note(0, owner, type, location, lastEdited, name);
		logger.info("Note to insert generated");
		logger.debug("Note: " + toInsert.toString());

		// insert the Note
		Integer id = nd.insertNote(toInsert);
		logger.info("Note inserted");
		logger.debug("Id: " + id);

		// retrieve the now inserted note
		Note inserted = nd.selectNoteById(id);
		logger.info("Note retrieved from database");
		logger.debug("Note: " + inserted.toString());

		// create the UserDto for the NoteDto
		UserDto insertedOwner = UserService.createUserDtoFromUser(inserted.getOwner());
		logger.info("UserDto created from insered Note");
		logger.debug("Owner: " + inserted.getOwner());
		logger.debug("OwnerDto: " + insertedOwner);

		// create a NoteDto to return
		NoteDto note = new NoteDto(inserted.getId(), inserted.getLastEdited(), inserted.getLocation(), insertedOwner,
				inserted.getType(), inserted.getName());
		logger.info("NoteDto created");
		logger.debug("NoteDto: " + note);

		// return the NoteDto
		logger.info("Returning NoteDto");
		return note;
	}

	/**
	 * Takes in an Integer and deletes the record with that primary key from the
	 * Note table of the database
	 * 
	 * @param id
	 */
	public static void deleteNoteDtoById(Integer id) {
		// create a new UserDaoImpl
		nd = new NoteDaoImpl();
		logger.info("NoteDaoImpl created");

		// make the deletion call
		nd.deleteNoteById(id);
		logger.info("Note deleted from the database");
	}

	/**
	 * Takes in a NoteDto and updates the associated record in the Note table of the
	 * database. Returns a NoteDto associated with the updated record
	 * 
	 * @param note
	 * @return
	 */
	public static NoteDto updateNoteDto(NoteDto noteDto) {
		// create a new NoteDao
		nd = new NoteDaoImpl();
		logger.info("NoteDaoImpl created");

		// create a new UserDao
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get the Note object that matches from the database
		Note note = nd.selectNoteById(noteDto.getId());
		logger.info("Retrieved corresponding Note from database");
		logger.debug("Note: " + note);
		logger.debug("NoteDto: " + noteDto);

		// get the User that corresponds to the UserDto
		User owner = ud.selectUserById(noteDto.getOwner().getId());
		logger.info("User retrieved from database");
		logger.debug("User: " + owner);

		// update the Note object to match the NoteDto
		// we do not change the id because the
		// id values must already be equal because of how
		// the Note object was retrieved
		note.setLastEdited(noteDto.getLastEdited());
		note.setLocation(noteDto.getLocation());
		note.setName(noteDto.getName());
		note.setType(noteDto.getType());
		note.setOwner(owner);
		logger.info("Note object modified to match NoteDto");

		// update the Note
		Note updatedNote = nd.updateNote(note);
		logger.info("Note object updated, fresh Note object returned");

		// get a UserDto to match the inserted owner
		UserDto updatedOwner = UserService.createUserDtoFromUser(updatedNote.getOwner());
		logger.info("UserDto created for updated owner");
		logger.debug("Owner: " + updatedNote.getOwner());
		logger.debug("OwnerDto: " + updatedOwner);

		// create the new NoteDto to be returned
		NoteDto updated = new NoteDto(updatedNote.getId(), updatedNote.getLastEdited(), updatedNote.getLocation(),
				updatedOwner, updatedNote.getType(), updatedNote.getName());
		logger.info("NoteDto created");
		logger.debug("Updated NoteDto: " + updated);

		// return the NoteDto
		logger.info("Returning updated NoteDto");
		return updated;
	}

	/**
	 * Selects all records from the database with the specified owner and returns
	 * them as a List of NoteDto objects
	 * 
	 * @param owner
	 * @return
	 */
	public static List<NoteDto> selectAllNotesByOwner(UserDto owner) {
		// create a new NoteDaoImpl
		nd = new NoteDaoImpl();
		logger.info("NoteDaoImpl created");

		// create a new UserDaoImpl
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get the owner of the note as a User object
		User ownr = ud.selectUserById(owner.getId());
		logger.info("Owner retrieved from database");
		logger.debug("Owner: " + ownr);
		logger.debug("OwnerDto: " + owner);

		// get all of the notes from the database
		List<Note> notes = nd.selectAllNotesByOwner(ownr);
		logger.info("All notes selected from the database");

		// create an empty list of NoteDtos to hold them
		List<NoteDto> noteDtos = new ArrayList<>();
		logger.info("New list of NoteDtos created");

		// iterate through the notes to make noteDtos from them
		logger.info("Interating through Note list");
		for (Note n : notes) {
			logger.debug("Note: " + n);

			// create the UserDto to be used in the noteDto
			UserDto ownerDto = UserService.createUserDtoFromUser(n.getOwner());
			logger.info("UserDto created from user");
			logger.debug("User: " + n.getOwner());
			logger.debug("UserDto: " + ownerDto);

			// create a new NoteDto
			NoteDto noteDto = new NoteDto(n.getId(), n.getLastEdited(), n.getLocation(), ownerDto, n.getType(),
					n.getName());
			logger.info("NoteDto created");
			logger.debug("NoteDto: " + noteDto);

			// add the NoteDto to the list
			noteDtos.add(noteDto);
			logger.info("NoteDto added to list");
		}
		// return the list of NoteDtos
		logger.info("Returning list of NoteDtos");
		return noteDtos;
	}
	
	/**
	 * Takes in a Note object and constructs a NoteDto from it. Returns that
	 * NoteDto object
	 * @param note
	 * @return
	 */
	public static NoteDto createNoteDtoFromNote(Note note) {
		// create a new NoteDaoImpl
		nd = new NoteDaoImpl();
		logger.info("NoteDaoImpl created");

		// create the UserDto for the note
		UserDto owner = UserService.createUserDtoFromUser(note.getOwner());
		logger.info("UserDto created from User");
		logger.debug("User: " + note.getOwner());
		logger.debug("UserDto: " + owner);

		// create the NoteDto
		NoteDto noteDto = new NoteDto(note.getId(), note.getLastEdited(), note.getLocation(), owner, note.getType(),
				note.getName());
		logger.info("NoteDto created");
		logger.debug("NoteDto: " + noteDto);
		logger.info("Returning NoteDto");
		// return the NoteDto
		return noteDto;
	}
}
