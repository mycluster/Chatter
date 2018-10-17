package com.revature.dao;

import java.util.List;

import com.revature.beans.Note;
import com.revature.beans.NoteType;

public interface NoteTypeDao {
	
	public List<NoteType> selectAllNoteType();
	public NoteType selectNoteTypeById(Integer id);
	public Integer updateNoteType(NoteType noteType);
	public Integer deleteNoteTypeById(Integer id);
	public void insertNoteType(NoteType noteType);

}
