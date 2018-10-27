package com.revature.tests;
import static org.testng.Assert.*;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.beans.ClassCategory;
import com.revature.daos.ClassCategoryDao;
import com.revature.daos.ClassCategoryDaoImpl;

public class ClassCategoryTest {
	private static ClassCategory cc;
	private static ClassCategoryDao ccd;
	private static List<ClassCategory> ccl;
  @Test
  public void TestSelectAllClassCategory() {
	  ccl = ccd.selectAllClassCategory();
	  String expected0 = "ENGLISH";
	  String expected2 = "CS";
	  String expected4 = "ADVANCED BASKETWEAVING (UNDERWATER)";
	  String testCC = ccl.get(0).getName();
	  assertEquals(testCC,expected0);
	  testCC = ccl.get(2).getName();
	  assertEquals(testCC,expected2);
	  testCC = ccl.get(4).getName();
	  assertEquals(testCC,expected4);
	  
  }
  
  @Test
  public void TestSelectAllClassCategoryByID() {
	  String expected = "ENGLISH";
	  cc = ccd.selectAllClassCategoryById(1);
	  String result = cc.getName();
	  assertEquals(result,expected);
  }
  
  @Test
  public void TestInsertAndUpdateClassCategory() {
	  ClassCategory ccTest = new ClassCategory(6,"TEST");
	  assertNotNull(ccTest);
	  
  }
  @BeforeClass
  public void beforeClass() {
	  ccd = new ClassCategoryDaoImpl();
  }

}
