package com.revature.dao;

import java.util.List;

import com.revature.beans.Message;

public interface MessageDao {
	
	//select all for past message history upon click
	public List<Message> selectAllMessage();
	//messages user will immediately see upon opening chat with another user
	public List<Message> selectMostRecentMessage();
	public void insertMessage(Message message);
	//user can click a delete icon to remove a message
	public Integer deleteMessageById(Integer id);
	//user can edit their message
	//indicates 'edited'
	public Integer updateNote(Message message);
}
