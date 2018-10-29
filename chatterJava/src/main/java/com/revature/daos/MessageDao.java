package com.revature.daos;

import java.util.List;

import com.revature.beans.Message;
import com.revature.beans.User;

public interface MessageDao {
	
	//select all for past message history upon click
	public List<Message> selectAllMessage();
<<<<<<< HEAD
=======
	public Message selectMessageById(Integer id);
>>>>>>> 78e6d76f3ded2a0287329aee6f0db53f4bdf8c10
	public Integer insertMessage(Message message);
	//user can click a delete icon to remove a message
	public void deleteMessageById(Integer id);
	//user can edit their message
	//indicates 'edited'
	public Message updateMessage(Message message);
	// we want to be able to select messages by the sender
	public List<Message> selectMessageBySender(User sender);
	// we want to be able to select messages by the receiver
	public List<Message> selectMessageByReceiver(User receiver);
	// we want to be able to select messages by the sender and receiver
	public List<Message> selectMessageBySenderAndReceiver(User sender, User receiver);
	// we want to be able to select the n most recent messages between a sender and a receiver
	public List<Message> selectNMostRecentBySenderAndReceiver(User sender, User receiver, Integer n);
	
	// we might also want to select by conversation
	// ie we care about communication between two users
	// regardless of which was the sender or reciever
	public List <Message> selectMessageByConversation(User user1, User user2);
	public List <Message> selectNMostRecentByConversation(User user1, User user2, Integer n);
}
