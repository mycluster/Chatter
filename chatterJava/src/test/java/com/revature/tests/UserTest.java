package com.revature.tests;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpl;

public class UserTest {
	private static UserDao ud;
	private static User u;
	private static List<User> ul;
  @Test
  public void TestSelectAllUser() {
	  ul = ud.selectAllUser();
	  String expected0 = "standard_test";
	  String expected1 = "chatter_test";
	  String result = ul.get(0).getUsername();
	  assertEquals(result,expected0);
	  result = ul.get(1).getUsername();
	  assertEquals(result,expected1);
  }
  @Test
  public void TestSelectUserById() {
	  u = ud.selectUserById(1);
	  String expected0 = "standard_test";
	  assertEquals(u.getUsername(),expected0);
  }
  @BeforeClass
  public void beforeClass() {
	  ud = new UserDaoImpl();
  }

}
