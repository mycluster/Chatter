package com.revature.dao;

import java.util.List;

import com.revature.beans.Message;
import com.revature.beans.User;

public interface MessageDao {
	
	//select all for past message history upon click
	public List<Message> selectAllMessage();
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
}
