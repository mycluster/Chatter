package com.revature.dao;

import java.util.List;

import com.revature.beans.ClassMembership;
import com.revature.beans.ClassRole;
import com.revature.beans.User;

public interface ClassMembershipDao {

	public List<ClassMembership> selectAllClassMembership();
	public ClassMembership selectClassMemebershipById(Integer id);
	public ClassMembership selectClassMemberhipByUser(User user);
	public Integer updateClassMembership(ClassMembershipDao classmebership);
	public Integer deleteClassMemebershipById(Integer id);
	public void insertClassMemebership(ClassMembership classMembership);
	
}
