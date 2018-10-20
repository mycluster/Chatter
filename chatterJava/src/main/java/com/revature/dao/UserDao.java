package com.revature.dao;

import java.util.List;

import com.revature.beans.User;

public interface UserDao {
	
	public List<User> selectAllUser();
	public User selectUserById(Integer id);
	public User selectUserByUsername(String username);
	public Integer insertNewUser(User user);
	public void deleteUserById(Integer id);
	public User updateUser(User user);
}
