package com.revature.dao;

import java.util.List;
import com.revature.beans.Cls;

public interface ClsDao {
	
	public List<Cls> selectAllClass();
	public Cls selectClassById(Integer id);
	public void insertNewClass(Cls cls);
	public Integer deleteClassById(Integer id);
	public Integer updateClass(Cls cls);
}
