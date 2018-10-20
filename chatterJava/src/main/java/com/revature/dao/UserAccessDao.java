package com.revature.dao;

import java.util.List;

import com.revature.beans.UserAccess;

public interface UserAccessDao {

	public List<UserAccess> selectAllUserAccess();
	public UserAccess selectUserAccessById(Integer id);
	public Integer updateUserAccess(UserAccess userAccess);
	
}
