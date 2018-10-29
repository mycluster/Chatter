package com.revature.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.beans.Edit;
import com.revature.beans.Message;
import com.revature.beans.User;
import com.revature.daos.EditDao;
import com.revature.daos.EditDaoImpl;
import com.revature.daos.MessageDao;
import com.revature.daos.MessageDaoImpl;
import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpl;
import com.revature.dtos.MessageDto;
import com.revature.dtos.UserDto;
import com.revature.util.EditUtil;

public class MessageService {
	private final static Logger logger = Logger.getLogger(MessageService.class);
	private static MessageDao md;
	private static UserDao ud;
	private static EditDao ed;
	
	/**
	 * Selects all records from the Message table and returns them as
	 * a list of MessageDtos
	 * @return
	 */
	public static List<MessageDto> selectAllMessageDto() {
		// create a new MessageDaoImpl
		md = new MessageDaoImpl();
		logger.info("MessageDaoImpl created");

		// get all of the messages from the database
		List<Message> messages = md.selectAllMessage();
		logger.info("All messages selected from the database");

		// create an empty list of MessageDtos to hold them
		List<MessageDto> messageDtos = new ArrayList<>();
		logger.info("New list of MessageDtos created");

		// iterate through the messages to make messageDtos from them
		logger.info("Interating through Message list");
		for (Message m : messages) {
			logger.debug("Message: " + m);

			// create the UserDto to be used in the messageDto
			UserDto sender = UserService.createUserDtoFromUser(m.getSender());
			logger.info("UserDto created from sender");
			logger.debug("User: " + m.getSender());
			logger.debug("UserDto: " + sender);

			// create the UserDto to be used in the messageDto
			UserDto receiver = UserService.createUserDtoFromUser(m.getReceiver());
			logger.info("UserDto created from receiver");
			logger.debug("User: " + m.getReceiver());
			logger.debug("UserDto: " + receiver);

			// create a new MessageDto
			MessageDto messageDto = new MessageDto(m.getId(), sender, receiver, m.getMessage(), m.getEdited(),
					m.getSentAt());
			logger.info("MessageDto created");
			logger.debug("MessageDto: " + messageDto);

			// add the MessageDto to the list
			messageDtos.add(messageDto);
			logger.info("MessageDto added to list");
		}
		// return the list of MessageDtos
		logger.info("Returning list of MessageDtos");
		return messageDtos;
	}

	/**
	 * Takes in an Integer and selects the record from the message with that
	 * value for a primary key. Returns that record as a MessageDto
	 * @param id
	 * @return
	 */
	public static MessageDto selectMessageDtoById(Integer id) {
		// create a new MessageDaoImpl
		md = new MessageDaoImpl();
		logger.info("MessageDaoImpl created");

		// get the record that with id as the primary key as a Message Object
		Message message = md.selectMessageById(id);
		logger.info("Message selected from database");
		logger.debug("Message: " + message);

		// create the UserDto to be used in the messageDto
		UserDto sender = UserService.createUserDtoFromUser(message.getSender());
		logger.info("UserDto created from sender");
		logger.debug("User: " + message.getSender());
		logger.debug("UserDto: " + sender);

		// create the UserDto to be used in the messageDto
		UserDto receiver = UserService.createUserDtoFromUser(message.getReceiver());
		logger.info("UserDto created from receiver");
		logger.debug("User: " + message.getReceiver());
		logger.debug("UserDto: " + receiver);

		// create the MessageDto
		MessageDto messageDto = new MessageDto(message.getId(), sender, receiver, message.getMessage(),
				message.getEdited(), message.getSentAt());
		logger.info("MessageDto created");
		logger.debug("MessageDto: " + messageDto);
		logger.info("Returning MessageDto");
		// return the MessageDto
		return messageDto;
	}

	/**
	 * Takes in a sender, a receiver, and a message and inserts a record into the Message table
	 * with those values, a Timestamp telling when the message was sent, and the edit value set to
	 * UNEDITED. Returns a MessageDto associated with the newly inserted record
	 * @param senderDto
	 * @param receiverDto
	 * @param message
	 * @return
	 */
	public static MessageDto insertMessageDto(UserDto senderDto, UserDto receiverDto, String message) {
		// create a new MessageDaoImpl
		md = new MessageDaoImpl();
		logger.info("MessageDaoImpl created");

		// create a new UserDaoImpl
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// create a new EditDaoImpl
		ed = new EditDaoImpl();
		logger.info("EditDaoImpl created");

		// get the UNEDITED edit level
		// this is because a new message had not been edited
		Edit edit = ed.selectEditById(EditUtil.uneditedId());
		logger.info("Unedited edit level retrieved");
		logger.debug("Edit: " + edit.toString());

		// get the User that corresponds to the UserDto
		User sender = ud.selectUserById(senderDto.getId());
		logger.info("Sender retrieved from database");
		logger.debug("Sender: " + sender);

		// get the User that corresponds to the UserDto
		User receiver = ud.selectUserById(receiverDto.getId());
		logger.info("Receiver retrieved from database");
		logger.debug("Receiver: " + receiver);

		// create a message to insert
		// since we are inserting a new message the primary key does not matter
		// we will input 0 as the default value
		Message toInsert = new Message(0, sender, receiver, message, edit);
		logger.info("Message to insert generated");
		logger.debug("Message: " + toInsert.toString());

		// insert the Message
		Integer id = md.insertMessage(toInsert);
		logger.info("Message inserted");
		logger.debug("Id: " + id);

		// retrieve the now inserted message
		Message inserted = md.selectMessageById(id);
		logger.info("Message retrieved from database");
		logger.debug("Message: " + inserted.toString());

		// create the UserDto to be used in the messageDto
		UserDto insertedSender = UserService.createUserDtoFromUser(inserted.getSender());
		logger.info("UserDto created from sender");
		logger.debug("User: " + inserted.getSender());
		logger.debug("UserDto: " + insertedSender);

		// create the UserDto to be used in the messageDto
		UserDto insertedReceiver = UserService.createUserDtoFromUser(inserted.getReceiver());
		logger.info("UserDto created from receiver");
		logger.debug("User: " + inserted.getReceiver());
		logger.debug("UserDto: " + insertedReceiver);

		// create a MessageDto to return
		MessageDto messageDto = new MessageDto(inserted.getId(), insertedSender, insertedReceiver,
				inserted.getMessage(), inserted.getEdited(), inserted.getSentAt());
		logger.info("MessageDto created");
		logger.debug("MessageDto: " + messageDto);

		// return the MessageDto
		logger.info("Returning MessageDto");
		return messageDto;
	}

	/**
	 * Takes in an Integer and deletes the record in the Message table with that
	 * value for a primary key
	 * @param id
	 */
	public static void deleteMessageDtoById(Integer id) {
		// create a new MessageDaoImpl
		md = new MessageDaoImpl();
		logger.info("MessageDaoImpl created");

		// make the deletion call
		md.deleteMessageById(id);
		logger.info("Message deleted from the database");
	}

	/**
	 * Takes in a MessageDto and updates the corresponding record in the Message table,
	 * also sets the Edited value to EDITED. Returns a MessageDto associated with the updated
	 * record
	 * @param messageDto
	 * @return
	 */
	public static MessageDto updateMessageDto(MessageDto messageDto) {
		// create a new MessageDaoImpl
		md = new MessageDaoImpl();
		logger.info("MessageDaoImpl created");

		// create a new UserDaoImpl
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// create a new EditDaoImpl
		ed = new EditDaoImpl();
		logger.info("EditDaoImpl created");

		// get the Edit that corresponds to the EDITED edit level
		Edit edit = ed.selectEditById(EditUtil.editedId());
		logger.info("Edit level edited retrieved");
		logger.debug("Edit: " + edit.toString());

		// get the User that corresponds to the UserDto
		User sender = ud.selectUserById(messageDto.getSender().getId());
		logger.info("Sender retrieved from database");
		logger.debug("Sender: " + sender);

		// get the User that corresponds to the UserDto
		User receiver = ud.selectUserById(messageDto.getReceiver().getId());
		logger.info("Receiver retrieved from database");
		logger.debug("Receiver: " + receiver);

		// retrieve the message to update
		Message toUpdate = md.selectMessageById(messageDto.getId());
		logger.info("Message to update retrieved");
		logger.debug("Message: " + toUpdate.toString());

		// retrieve the Edit object for state 'EDITED'

		// update the Message fields to match the MessageDto
		// set Edited to "EDITED"
		toUpdate.setMessage(messageDto.getMessage());
		toUpdate.setSender(sender);
		toUpdate.setReceiver(receiver);
		toUpdate.setEdited(edit);
		logger.info("Message object modified to match MessageDto");

		// update the message
		Message updated = md.updateMessage(toUpdate);
		logger.info("Message updated, new update object returned");

		// create the UserDto to be used in the messageDto
		UserDto updatedSender = UserService.createUserDtoFromUser(updated.getSender());
		logger.info("UserDto created from sender");
		logger.debug("User: " + updated.getSender());
		logger.debug("UserDto: " + updatedSender);

		// create the UserDto to be used in the messageDto
		UserDto updatedReceiver = UserService.createUserDtoFromUser(updated.getReceiver());
		logger.info("UserDto created from receiver");
		logger.debug("User: " + updated.getReceiver());
		logger.debug("UserDto: " + updatedReceiver);

		// create a MessageDto to return
		MessageDto updatedMessageDto = new MessageDto(updated.getId(), updatedSender, updatedReceiver,
				updated.getMessage(), updated.getEdited(), updated.getSentAt());
		logger.info("MessageDto created");
		logger.debug("MessageDto: " + updatedMessageDto);

		// return the MessageDto
		logger.info("Returning MessageDto");
		return updatedMessageDto;
	}
	/**
	 * Takes in a UserDto and selects all records from the Message table with that user as the
	 * sender. Returns them as a list of MessageDtos
	 * @param senderDto
	 * @return
	 */
	public static List<MessageDto> selectMessageDtoBySender(UserDto senderDto) {
		// create a new MessageDaoImpl
		md = new MessageDaoImpl();
		logger.info("MessageDaoImpl created");

		// create a new UserDaoImpl
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get the User that corresponds to the UserDto
		User sender = ud.selectUserById(senderDto.getId());
		logger.info("Sender retrieved from database");
		logger.debug("Sender: " + sender);

		// get all of the messages from the database
		List<Message> messages = md.selectMessageBySender(sender);
		logger.info("All messages from specified sender selected from the database");

		// create an empty list of MessageDtos to hold them
		List<MessageDto> messageDtos = new ArrayList<>();
		logger.info("New list of MessageDtos created");

		// iterate through the messages to make messageDtos from them
		logger.info("Interating through Message list");
		for (Message m : messages) {
			logger.debug("Message: " + m);

			// create the UserDto to be used in the messageDto
			UserDto sendr = UserService.createUserDtoFromUser(m.getSender());
			logger.info("UserDto created from sender");
			logger.debug("User: " + m.getSender());
			logger.debug("UserDto: " + sendr);

			// create the UserDto to be used in the messageDto
			UserDto receiver = UserService.createUserDtoFromUser(m.getReceiver());
			logger.info("UserDto created from receiver");
			logger.debug("User: " + m.getReceiver());
			logger.debug("UserDto: " + receiver);

			// create a new MessageDto
			MessageDto messageDto = new MessageDto(m.getId(), sendr, receiver, m.getMessage(), m.getEdited(),
					m.getSentAt());
			logger.info("MessageDto created");
			logger.debug("MessageDto: " + messageDto);

			// add the MessageDto to the list
			messageDtos.add(messageDto);
			logger.info("MessageDto added to list");
		}
		// return the list of MessageDtos
		logger.info("Returning list of MessageDtos");
		return messageDtos;
	}

	/**
	 * Takes in a UserDto and selects all records from the Message table with that user as the
	 * receiver. Returns them as a list of MessageDtos
	 * @param receiverDto
	 * @return
	 */
	public static List<MessageDto> selectMessageDtoByReceiver(UserDto receiverDto) {
		// create a new MessageDaoImpl
		md = new MessageDaoImpl();
		logger.info("MessageDaoImpl created");

		// create a new UserDaoImpl
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get the User that corresponds to the UserDto
		User receiver = ud.selectUserById(receiverDto.getId());
		logger.info("Receiver retrieved from database");
		logger.debug("Receiver: " + receiver);

		// get all of the messages from the database
		List<Message> messages = md.selectMessageByReceiver(receiver);
		logger.info("All messages from specified receiver selected from the database");

		// create an empty list of MessageDtos to hold them
		List<MessageDto> messageDtos = new ArrayList<>();
		logger.info("New list of MessageDtos created");

		// iterate through the messages to make messageDtos from them
		logger.info("Interating through Message list");
		for (Message m : messages) {
			logger.debug("Message: " + m);

			// create the UserDto to be used in the messageDto
			UserDto sendr = UserService.createUserDtoFromUser(m.getSender());
			logger.info("UserDto created from sender");
			logger.debug("User: " + m.getSender());
			logger.debug("UserDto: " + sendr);

			// create the UserDto to be used in the messageDto
			UserDto receivr = UserService.createUserDtoFromUser(m.getReceiver());
			logger.info("UserDto created from receivr");
			logger.debug("User: " + m.getReceiver());
			logger.debug("UserDto: " + receivr);

			// create a new MessageDto
			MessageDto messageDto = new MessageDto(m.getId(), sendr, receivr, m.getMessage(), m.getEdited(),
					m.getSentAt());
			logger.info("MessageDto created");
			logger.debug("MessageDto: " + messageDto);

			// add the MessageDto to the list
			messageDtos.add(messageDto);
			logger.info("MessageDto added to list");
		}
		// return the list of MessageDtos
		logger.info("Returning list of MessageDtos");
		return messageDtos;
	}

	/**
	 * Takes in a sender UserDto and a receiver UserDto and selects all records from the Message table with the
	 * respective sender and receiver. Returns them as a list of MessageDtos
	 * @param senderDto
	 * @param receiverDto
	 * @return
	 */
	public static List<MessageDto> selectMessageDtoBySenderAndReceiver(UserDto senderDto, UserDto receiverDto) {
		// create a new MessageDaoImpl
		md = new MessageDaoImpl();
		logger.info("MessageDaoImpl created");

		// create a new UserDaoImpl
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get the User that corresponds to the UserDto
		User sender = ud.selectUserById(senderDto.getId());
		logger.info("Sender retrieved from database");
		logger.debug("Sender: " + sender);

		// get the User that corresponds to the UserDto
		User receiver = ud.selectUserById(receiverDto.getId());
		logger.info("Receiver retrieved from database");
		logger.debug("Receiver: " + receiver);

		// get all of the messages from the database
		List<Message> messages = md.selectMessageBySenderAndReceiver(sender, receiver);
		logger.info("All messages from specified receiver selected from the database");

		// create an empty list of MessageDtos to hold them
		List<MessageDto> messageDtos = new ArrayList<>();
		logger.info("New list of MessageDtos created");

		// iterate through the messages to make messageDtos from them
		logger.info("Interating through Message list");
		for (Message m : messages) {
			logger.debug("Message: " + m);

			// create the UserDto to be used in the messageDto
			UserDto sendr = UserService.createUserDtoFromUser(m.getSender());
			logger.info("UserDto created from sender");
			logger.debug("User: " + m.getSender());
			logger.debug("UserDto: " + sendr);

			// create the UserDto to be used in the messageDto
			UserDto receivr = UserService.createUserDtoFromUser(m.getReceiver());
			logger.info("UserDto created from receivr");
			logger.debug("User: " + m.getReceiver());
			logger.debug("UserDto: " + receivr);

			// create a new MessageDto
			MessageDto messageDto = new MessageDto(m.getId(), sendr, receivr, m.getMessage(), m.getEdited(),
					m.getSentAt());
			logger.info("MessageDto created");
			logger.debug("MessageDto: " + messageDto);

			// add the MessageDto to the list
			messageDtos.add(messageDto);
			logger.info("MessageDto added to list");
		}
		// return the list of MessageDtos
		logger.info("Returning list of MessageDtos");
		return messageDtos;
	}
	/**
	 * Takes in an Integer n, a sender UserDto and a receiver UserDto and selects the n most recent 
	 * records from the Message table with the respective sender and receiver. Returns them as a list of MessageDtos
	 * @param senderDto
	 * @param receiverDto
	 * @param n
	 * @return
	 */
	public static List<MessageDto> selectNMostRecentBySenderAndReceiver(UserDto senderDto, UserDto receiverDto, Integer n) {
		// create a new MessageDaoImpl
		md = new MessageDaoImpl();
		logger.info("MessageDaoImpl created");

		// create a new UserDaoImpl
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get the User that corresponds to the UserDto
		User sender = ud.selectUserById(senderDto.getId());
		logger.info("Sender retrieved from database");
		logger.debug("Sender: " + sender);

		// get the User that corresponds to the UserDto
		User receiver = ud.selectUserById(receiverDto.getId());
		logger.info("Receiver retrieved from database");
		logger.debug("Receiver: " + receiver);

		// get all of the messages from the database
		List<Message> messages = md.selectNMostRecentBySenderAndReceiver(sender, receiver, n);
		logger.info("All messages from specified receiver selected from the database");

		// create an empty list of MessageDtos to hold them
		List<MessageDto> messageDtos = new ArrayList<>();
		logger.info("New list of MessageDtos created");

		// iterate through the messages to make messageDtos from them
		logger.info("Interating through Message list");
		for (Message m : messages) {
			logger.debug("Message: " + m);

			// create the UserDto to be used in the messageDto
			UserDto sendr = UserService.createUserDtoFromUser(m.getSender());
			logger.info("UserDto created from sender");
			logger.debug("User: " + m.getSender());
			logger.debug("UserDto: " + sendr);

			// create the UserDto to be used in the messageDto
			UserDto receivr = UserService.createUserDtoFromUser(m.getReceiver());
			logger.info("UserDto created from receivr");
			logger.debug("User: " + m.getReceiver());
			logger.debug("UserDto: " + receivr);

			// create a new MessageDto
			MessageDto messageDto = new MessageDto(m.getId(), sendr, receivr, m.getMessage(), m.getEdited(),
					m.getSentAt());
			logger.info("MessageDto created");
			logger.debug("MessageDto: " + messageDto);

			// add the MessageDto to the list
			messageDtos.add(messageDto);
			logger.info("MessageDto added to list");
		}
		// return the list of MessageDtos
		logger.info("Returning list of MessageDtos");
		return messageDtos;
	}
	/**
	 * Takes in two UserDtos and selects all records in the Message table where one of the users is
	 * the sender and the other is the receiver.
	 * @param userDto1
	 * @param userDto2
	 * @return
	 */
	public static List<MessageDto> selectMessageDtoByConversation(UserDto userDto1, UserDto userDto2) {
		// create a new MessageDaoImpl
		md = new MessageDaoImpl();
		logger.info("MessageDaoImpl created");

		// create a new UserDaoImpl
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get the User that corresponds to the UserDto
		User user1 = ud.selectUserById(userDto1.getId());
		logger.info("Sender retrieved from database");
		logger.debug("Sender: " + user1);

		// get the User that corresponds to the UserDto
		User user2 = ud.selectUserById(userDto2.getId());
		logger.info("Receiver retrieved from database");
		logger.debug("Receiver: " + user2);

		// get all of the messages from the database
		List<Message> messages = md.selectMessageByConversation(user1, user2);
		logger.info("All messages from specified receiver selected from the database");

		// create an empty list of MessageDtos to hold them
		List<MessageDto> messageDtos = new ArrayList<>();
		logger.info("New list of MessageDtos created");

		// iterate through the messages to make messageDtos from them
		logger.info("Interating through Message list");
		for (Message m : messages) {
			logger.debug("Message: " + m);

			// create the UserDto to be used in the messageDto
			UserDto sendr = UserService.createUserDtoFromUser(m.getSender());
			logger.info("UserDto created from sender");
			logger.debug("User: " + m.getSender());
			logger.debug("UserDto: " + sendr);

			// create the UserDto to be used in the messageDto
			UserDto receivr = UserService.createUserDtoFromUser(m.getReceiver());
			logger.info("UserDto created from receivr");
			logger.debug("User: " + m.getReceiver());
			logger.debug("UserDto: " + receivr);

			// create a new MessageDto
			MessageDto messageDto = new MessageDto(m.getId(), sendr, receivr, m.getMessage(), m.getEdited(),
					m.getSentAt());
			logger.info("MessageDto created");
			logger.debug("MessageDto: " + messageDto);

			// add the MessageDto to the list
			messageDtos.add(messageDto);
			logger.info("MessageDto added to list");
		}
		// return the list of MessageDtos
		logger.info("Returning list of MessageDtos");
		return messageDtos;
	}

	/**
	 * Takes in two UserDtos and an Integer n and selects the n most recent records in the Message 
	 * table where one of the users is the sender and the other is the receiver.
	 * @param userDto1
	 * @param userDto2
	 * @param n
	 * @return
	 */
	public static List<MessageDto> selectNMostRecentByConversation(UserDto userDto1, UserDto userDto2, Integer n) {
		// create a new MessageDaoImpl
		md = new MessageDaoImpl();
		logger.info("MessageDaoImpl created");

		// create a new UserDaoImpl
		ud = new UserDaoImpl();
		logger.info("UserDaoImpl created");

		// get the User that corresponds to the UserDto
		User user1 = ud.selectUserById(userDto1.getId());
		logger.info("Sender retrieved from database");
		logger.debug("Sender: " + user1);

		// get the User that corresponds to the UserDto
		User user2 = ud.selectUserById(userDto2.getId());
		logger.info("Receiver retrieved from database");
		logger.debug("Receiver: " + user2);

		// get all of the messages from the database
		List<Message> messages = md.selectNMostRecentByConversation(user1, user2, n);
		logger.info("All messages from specified receiver selected from the database");

		// create an empty list of MessageDtos to hold them
		List<MessageDto> messageDtos = new ArrayList<>();
		logger.info("New list of MessageDtos created");

		// iterate through the messages to make messageDtos from them
		logger.info("Interating through Message list");
		for (Message m : messages) {
			logger.debug("Message: " + m);

			// create the UserDto to be used in the messageDto
			UserDto sendr = UserService.createUserDtoFromUser(m.getSender());
			logger.info("UserDto created from sender");
			logger.debug("User: " + m.getSender());
			logger.debug("UserDto: " + sendr);

			// create the UserDto to be used in the messageDto
			UserDto receivr = UserService.createUserDtoFromUser(m.getReceiver());
			logger.info("UserDto created from receivr");
			logger.debug("User: " + m.getReceiver());
			logger.debug("UserDto: " + receivr);

			// create a new MessageDto
			MessageDto messageDto = new MessageDto(m.getId(), sendr, receivr, m.getMessage(), m.getEdited(),
					m.getSentAt());
			logger.info("MessageDto created");
			logger.debug("MessageDto: " + messageDto);

			// add the MessageDto to the list
			messageDtos.add(messageDto);
			logger.info("MessageDto added to list");
		}
		// return the list of MessageDtos
		logger.info("Returning list of MessageDtos");
		return messageDtos;
	}
}
