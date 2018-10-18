package com.revature.dao;

import java.util.List;

import com.revature.beans.Note;
import com.revature.beans.User;

public interface NoteDao {
	
	public List<Note> selectAllNote();
	public Note selectNoteById(Integer id);
	public void insertNote(Note note);
	public Integer deleteNoteById(Integer id);
	public Integer updateNote(Note note);
	
	// we will want to retrieve all notes a user has
	public List<Note> selectAllNotesByOwner(User owner);

}
