package com.revature.daos;

import java.util.List;

import com.revature.beans.ClassMembership;
import com.revature.beans.Cls;
import com.revature.beans.User;

public interface ClassMembershipDao {

	public List<ClassMembership> selectAllClassMembership();
	public ClassMembership selectClassMembershipById(Integer id);
	public ClassMembership updateClassMembership(ClassMembership classMembership);
	public void deleteClassMembershipById(Integer id);
	public Integer insertClassMembership(ClassMembership classMembership);
	
	// we want to be able to select class membership by a user
	// and also by a class
	public List<ClassMembership> selectClassMembershipByUser(User user);
	public List<ClassMembership> selectClassMembershipByClass(Cls cls);
	
	// it might also be useful to be able to check if a user is a member of a specific class
	// returns the membership if so
	public ClassMembership getUserMembershipOfClass(User user, Cls cls);
	
	
}
