package com.revature.daos;

import java.util.List;

import com.revature.beans.ClassRole;

public interface ClassRoleDao {

	public List<ClassRole> selectAllClassRole();
	public ClassRole selectClassRoleById(Integer id);
	public ClassRole updateClassRole(ClassRole classRole);
	public Integer insertClassRole(ClassRole classRole);
	public void deleteClassRoleById(Integer id);
}
