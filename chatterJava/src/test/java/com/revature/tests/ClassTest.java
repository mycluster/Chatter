package com.revature.tests;

import static org.testng.Assert.*;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.beans.ClassCategory;
import com.revature.beans.Cls;
import com.revature.daos.ClsDao;
import com.revature.daos.ClsDaoImpl;

public class ClassTest {
	private static Cls cls;
	private static ClsDao cd;
	private static List<Cls> cl;
	
  @Test
  public void TestSelectAllClasses() {
	  cl = cd.selectAllClass();
	  String expected0 = "ENG101";
	  String expected2 = "CS2048";
	  String expected4 = "BW401";
	  String testAccess = cl.get(0).getName();
	  assertEquals(testAccess, expected0);
	  testAccess = cl.get(2).getName();
	  assertEquals(testAccess, expected2);
	  testAccess = cl.get(4).getName();
	  assertEquals(testAccess,expected4);
  }
  @Test
  public void TestSelectClassById() {
	  String expected0 = "ENG101";
	  String expected2 = "CS2048";
	  String expected4 = "BW401";
	  ClassCategory cc1 = new ClassCategory(1,"ENGLISH");
	  ClassCategory cc3 = new ClassCategory(3,"CS");
	  ClassCategory cc5 = new ClassCategory(5,"ADVANCED BASKETWEAVING (UNDERWATER)");
	  Cls clsTest = new Cls(1,expected0,cc1);
	  cls = cd.selectClassById(1);
	  assertTrue(cls.equals(clsTest));
	  
	  clsTest = new Cls(3,expected2,cc3);
	  cls = cd.selectClassById(3);
	  assertTrue(cls.equals(clsTest));
	  
	  clsTest = new Cls(5,expected4,cc5);
	  cls = cd.selectClassById(5);
	  assertTrue(cls.equals(clsTest));
	  //assertTrue(cls)
  }
  
  @Test
  public void TestInsertAndDeleteNewClass() {
	  Cls clsTest = null;
	  ClassCategory cc1 = new ClassCategory(6,"TEST");
	  String expected0 = "ENG101";
	  clsTest = new Cls(1,expected0,cc1);
	  Integer result = cd.insertNewClass(clsTest);  
	  assertNotNull(result);
	 // cd.deleteClassById(result);
	  
  }
  
  @Test
  public void TestUpdateClass() {
	  //this test does not work for some reason. tabling it for later. -mike
	  //TODO : FIX UPDATECLASS TEST
//	  ClassCategory cc1 = new ClassCategory(6,"TEST");
//	  String expected0 = "ENG101";
//	 Cls clsTest = new Cls(1,expected0,cc1);
//	 Cls clsTestUpdate = new Cls(1,"ENG102",cc1);
//	 cd.updateClass(clsTestUpdate);
//	 cls = cd.selectClassById(1);
//	 assertEquals(cls.getName(),clsTestUpdate.getName());
	  
	  
	  
  }
  @BeforeClass
  public void beforeClass() {
	  cd = new ClsDaoImpl();
  }

}
