package com.revature.daos;

import java.util.List;

import com.revature.beans.AccessLevel;
import com.revature.beans.Note;
import com.revature.beans.User;
import com.revature.beans.UserAccess;

public interface UserAccessDao {

	public List<UserAccess> selectAllUserAccess();
	public UserAccess selectUserAccessById(Integer id);
	public UserAccess updateUserAccess(UserAccess userAccess);
	public Integer insertUserAccess(UserAccess userAccess);
	public void deleteUserAccess(Integer id);
	
	// Same reasoning for these as with classaccesses
	public List<UserAccess> getUserAccessByUser(User user);
	public List<UserAccess> getUserAccessByNote(Note note);
	public List<UserAccess> getUserAccessByUserAndAccess(User user, AccessLevel access);
	
}
