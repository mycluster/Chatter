package com.revature.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.beans.AccessLevel;
import com.revature.daos.AccessLevelDao;
import com.revature.daos.AccessLevelDaoImpl;

public class AccessLevelTest {
	private static AccessLevel al;
	private static AccessLevelDao ald;
	private static List<AccessLevel> all;
	
  @Test
  public void TestSelectLevel() {
	  al = ald.selectAccessLevelById(1);
	  String testAccess = al.getName();
	  String expected = "READ";
 	  assertEquals(testAccess, expected);
  }
  @Test
  public void TestSelectAllLevels() {
	  all = ald.selectAllAccessLevel();
	  String expected0 = "READ";
	  String expected1 = "READ AND WRITE";
	  String testAccess = all.get(0).getName();
	  assertEquals(testAccess, expected0);
	  testAccess = all.get(1).getName();
	  assertEquals(testAccess, expected1);
  }
  @Test
  public void TestSelectAccessLevel() {
	  AccessLevel alTest = new AccessLevel(1,"READ");
	  al=ald.setAccessLevel(alTest);
	  assertTrue(al.equals(alTest));
  }
  

  @BeforeClass
  public void beforeClass() {
	  ald = new AccessLevelDaoImpl();
	  
  }

 

}
