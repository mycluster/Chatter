package com.revature.dao;

import java.util.List;

import com.revature.beans.ClassAccess;


public interface ClassAccessDao {
	
	public List<ClassAccess> selectAllClassAccess();
	public ClassAccess selectClassAccessById(Integer id);
	public Integer updateClassAccess(ClassAccess classAccess);

}
