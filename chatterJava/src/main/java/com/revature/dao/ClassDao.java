package com.revature.dao;

import java.util.List;

public interface ClassDao {
	
	public List<Class> selectAllClass();
	public Class selectClassById(Integer id);
	public void insertNewClass(Class cls);
	public Integer deleteClassById(Integer id);
	public Integer updateClass(Class cls);
}
