package com.revature.dao;

import java.util.List;

import com.revature.beans.Edit;

public interface EditDao {
	public Edit selectEditById(Integer id);
	public List<Edit> selectAllEdit();
	public Edit updateEdit(Edit edit);
	public Integer insertEdit(Edit edit);
	public void deleteEdit(Integer id);
}
