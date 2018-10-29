package com.revature.tests;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.beans.Note;
import com.revature.beans.User;
import com.revature.daos.NoteDao;
import com.revature.daos.NoteDaoImpl;

public class NoteTest {
	private static NoteDao nd;
	private static Note n;
	private static List<Note> nl;
  
	@Test
  public void TestSelectAllNote() {
		nl = nd.selectAllNote();
		String expected0 = "first note";
		String expected1 = "SQUAWK";
		String result = nl.get(0).getName();
		assertEquals(expected0,result);
		result = nl.get(1).getName();
		assertEquals(expected1,result);
	}
	
	@Test
	public void TestSelectNoteById() {
		n = nd.selectNoteById(1);
		String expected0 = "first note";
		String result = n.getName();
		assertEquals(expected0,result);
	}
	
	@Test
	public void TestSelectNoteByOwner() {
		//TODO
	}
  @BeforeClass
  public void beforeClass() {
	  nd = new NoteDaoImpl();
  }

}
