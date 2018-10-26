package com.revature.daos;

import java.util.List;

import com.revature.beans.AccessLevel;

public interface AccessLevelDao {

	public List<AccessLevel> selectAllAccessLevel();
	public AccessLevel selectAccessLevelById(Integer id);
	public AccessLevel setAccessLevel(AccessLevel accessLevel);
}
