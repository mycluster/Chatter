package com.revature.dao;

import java.util.List;

import com.revature.beans.User;

public interface UserDao {
	
	public List<User> selectAllUser();
	public User selectUserById(Integer id);
	public User selectUserByUsername(String username);
	public void insertNewUser(User user);
	public Integer deleteUserById(Integer id);
	public Integer updateUser(User user);
	
	
	//determine where to store passwords

}
