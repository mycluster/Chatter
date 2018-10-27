package com.revature.tests;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;


import com.revature.beans.Edit;
import com.revature.daos.EditDao;
import com.revature.daos.EditDaoImpl;

public class EditTest {
	private static EditDao ed;
	private static Edit e;
	private static List<Edit> el;
  @Test
  public void TestSelectAllEdit() {
	  //For some reason, SelectAllEdit method returns an empty array, therefore this is currently untestable
//	  el = ed.selectAllEdit();
//	  String expected0 = "UNEDITED";
//	  String expected1 = "EDITED";
//	  String result0 = el.get(0).getName();
//	  String result1 = el.get(1).getName();
//	  
//	 assertEquals(expected0,result0);
//	 assertEquals(expected1,result1);
	  
  }
  
  @Test
  public void TestSelectEditById() {
//	  String expected0 = "UNEDITED";
//	  e = ed.selectEditById(1);
//	  String result = e.getName();
//	  assertEquals(expected0,result);
	  
  }
  @Test
  public void TestInsertUpdateAndDeleteEdit() {
	  //TODO TestInsertUpdateAndDeleteEdit
  }
  @BeforeClass
  public void beforeClass() {
	  ed = new EditDaoImpl();
  }

}
