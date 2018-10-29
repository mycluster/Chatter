package com.revature.tests;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.revature.beans.ClassRole;
import com.revature.daos.ClassRoleDao;
import com.revature.daos.ClassRoleDaoImpl;

public class ClassRoleTest {
	private static ClassRoleDao crd;
	private static ClassRole cr;
	private static List<ClassRole> crl;
	
	@Test
  public void TestSelectAllClassRole() {
		crl = crd.selectAllClassRole();
		String expected0 = "OWNER";
		String expected2 = "MEMBER";
		String testClassRole = crl.get(0).getName();
		assertEquals(expected0, testClassRole);
		testClassRole = crl.get(2).getName();
		assertEquals(expected2,testClassRole);
  }
	
	@Test
	public void TestSelectClassRoleById() {
		cr = crd.selectClassRoleById(1);
		String expected0 = "OWNER";
		String testClassRole = cr.getName();
		assertEquals(expected0,testClassRole);
	}
	
	@Test
	public void TestInsertUpdateAndDeleteClassRole() {
		//TODO 
	}
  @BeforeClass
  public void beforeClass() {
	  crd = new ClassRoleDaoImpl();	
  }
  

}
