package com.revature.dao;

import java.util.List;

import com.revature.beans.AccessLevel;
import com.revature.beans.ClassAccess;
import com.revature.beans.Cls;
import com.revature.beans.Note;


public interface ClassAccessDao {
	
	public List<ClassAccess> selectAllClassAccess();
	public ClassAccess selectClassAccessById(Integer id);
	public ClassAccess updateClassAccess(ClassAccess classAccess);
	public Integer insertClassAccess(ClassAccess classAccess);
	public void deleteClassAccess(Integer id);
	
	// We might want to retrieve ClassAccesses by a class, or by a specific note
	// or by class and access level (ie all the notes that class A can write to)
	// this may or may not save us some loop iterations
	public List<ClassAccess> getClassAccessByClass(Cls cls);
	public List<ClassAccess> getClassAccessByNote(Note note);
	public List<ClassAccess> getClassAccessByClassAndAccess(Cls cls, AccessLevel access);

}
