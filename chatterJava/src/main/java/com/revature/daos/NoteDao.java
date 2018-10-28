package com.revature.daos;

import java.util.List;

import com.revature.beans.Note;
import com.revature.beans.User;

public interface NoteDao {
	
	public List<Note> selectAllNote();
	public Note selectNoteById(Integer id);
	public Integer insertNote(Note note);
	public void deleteNoteById(Integer id);
	public Note updateNote(Note note);
	
	// we will want to retrieve all notes a user has
	public List<Note> selectAllNotesByOwner(User owner);
	public Note selectNoteByName(String id);

}
