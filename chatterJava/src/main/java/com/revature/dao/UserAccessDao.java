package com.revature.dao;

import java.util.List;

import com.revature.beans.Note;
import com.revature.beans.User;
import com.revature.beans.UserAccess;

public interface UserAccessDao {

	public List<UserAccess> selectAllUserAccess();
	public UserAccess selectUserAccessById(Integer id);
	public Integer updateUserAccess(UserAccess userAccess);
	
	
	// Same reasoning for these as with classaccesses
	public List<UserAccess> getClassAccessByUser(User user);
	public List<UserAccess> getClassAccessByNote(Note note);
	public List<UserAccess> getClassAccessBytUserAndAccess(User user, UserAccess userAccess);
	
}
