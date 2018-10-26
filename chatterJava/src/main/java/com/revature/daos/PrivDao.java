package com.revature.daos;

import java.util.List;

import com.revature.beans.Priv;

public interface PrivDao {
	
	public List<Priv> selectAllPriv();
	public Priv selectAllPrivById(Integer id);
	public Integer insertPriv(Priv priv);
	public void deletePrivById(Integer id);
	public Priv updatePriv(Priv priv);
}
