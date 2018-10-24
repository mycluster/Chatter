package com.revature.daos;

import java.util.List;

import com.revature.beans.NoteType;

public interface NoteTypeDao {
	
	public List<NoteType> selectAllNoteType();
	public NoteType selectNoteTypeById(Integer id);
	public NoteType updateNoteType(NoteType noteType);
	public void deleteNoteTypeById(Integer id);
	public Integer insertNoteType(NoteType noteType);

}
