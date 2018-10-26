package com.revature.daos;

import java.util.List;

import com.revature.beans.ClassCategory;

public interface ClassCategoryDao {
	public List<ClassCategory> selectAllClassCategory();
	public ClassCategory selectAllClassCategoryById(Integer id);
	public Integer insertClassCategory(ClassCategory classCategory);
	public ClassCategory updateClassCategory(ClassCategory classCategory);
}
