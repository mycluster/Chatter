package com.revature.dao;

import java.util.List;

import com.revature.beans.Priv;

public interface PrivDao {
	
	public List<Priv> selectAllPriv();
	public Priv selectAllPrivById(Integer id);
	
}
