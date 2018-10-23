package com.revature.dao;

import java.util.List;

import com.revature.beans.Note;

public interface NoteDao {
	
	public List<Note> selectAllNote();
	public Note selectNoteById(Integer id);
	public void insertNote(Note note);
	public Integer deleteNoteById(Integer id);
	public Integer updateNote(Note note);
	
	

}
