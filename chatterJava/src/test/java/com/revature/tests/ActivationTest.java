package com.revature.tests;

import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.beans.Activation;
import com.revature.daos.ActivationDao;
import com.revature.daos.ActivationDaoImpl;

public class ActivationTest {
	private static ActivationDao ad;
	private static Activation activation;
	private static List<Activation> al;
	
	
  @Test
  public void TestSelectAllActivation() {
	  al = ad.selectAllActivation();
	  String expected0 = "NOT ACTIVATED";
	  String expected1 = "ACTIVATED";
	  String expected2 = "DEACTIVATED";
	  String testActivation = al.get(0).getName();
	  assertEquals(testActivation, expected0);
	  testActivation = al.get(1).getName();
	  assertEquals(testActivation, expected1);
	  testActivation = al.get(2).getName();
	  assertEquals(testActivation, expected2);
	  
  }
  @Test
  public void TestSelectActivationById() {
	  activation = ad.selectActivationById(1);
	  String expected = "NOT ACTIVATED";
	  String testActivation = activation.getName();
	  assertEquals(testActivation, expected);
  }
  
  @Test
  public void TestSetActivation() {
	  Activation actTest = new Activation(1,"NOT ACTIVATED");
	  activation = ad.setActivation(actTest);
	  assertTrue(activation.equals(actTest));
  }
  
  @BeforeClass
  public void beforeClass() {
	  ad = new ActivationDaoImpl();
  }

}
