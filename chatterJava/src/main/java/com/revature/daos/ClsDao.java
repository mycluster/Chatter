package com.revature.daos;

import java.util.List;
import com.revature.beans.Cls;

public interface ClsDao {
	
	public List<Cls> selectAllClass();
	public Cls selectClassById(Integer id);
	public Integer insertNewClass(Cls cls);
	public void deleteClassById(Integer id);
	public Cls updateClass(Cls cls);
}
