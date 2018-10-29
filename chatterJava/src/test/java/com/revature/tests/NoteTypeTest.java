package com.revature.tests;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.beans.NoteType;
import com.revature.daos.NoteTypeDao;
import com.revature.daos.NoteTypeDaoImpl;

public class NoteTypeTest {
	  private static NoteTypeDao nd;
	  private static NoteType n;
	  private static List<NoteType> nl;
  @Test
  public void TestSelectAllNote() {
	 nl = nd.selectAllNoteType();
	 String expected0 = "TEXT";
	 String expected1 = "IMAGE";
	 String testNote = nl.get(0).getName();
	 assertEquals(testNote,expected0);
	 testNote = nl.get(1).getName();
	 assertEquals(testNote,expected1);
  }
  
  @Test
  public void TestSelectNoteById() {
	  String expected1 = "IMAGE";
	  n = nd.selectNoteTypeById(2);
	  String result = n.getName();
	  assertEquals(result,expected1);
	  
  }
  
  @Test
   public void TestAllNotesByOwner() {
	 //TODO
  }
  @BeforeClass
  public void beforeClass() {
	  nd = new NoteTypeDaoImpl();
  }

}
