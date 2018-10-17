package com.revature.dao;

import java.util.List;

import com.revature.beans.ClassRole;

public interface ClassRoleDao {

	public List<ClassRole> selectAllClassRole();
	public ClassRole selectClassRoleById(Integer id);
	public Integer updateClassRole(ClassRole classRole);
	public void insertClassRole(ClassRole classRole);
	public Integer deleteClassRoleById(Integer id);
}
