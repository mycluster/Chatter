package com.revature.tests;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;


import com.revature.beans.Priv;
import com.revature.daos.PrivDao;
import com.revature.daos.PrivDaoImpl;

public class PrivTest {
	private static PrivDao pd;
	private static Priv p;
	private static List<Priv> pl;
	@Test
  public void TestSelectAllPirv() {
		pl = pd.selectAllPriv();
		String expected0 = "STANDARD";
		String expected2 = "ADMIN";
		String result = pl.get(0).getName();
		assertEquals(result, expected0);
		result = pl.get(2).getName();
		assertEquals(result, expected2);
  }
	
	@Test
	public void TestSelectPrivById() {
		p = pd.selectAllPrivById(1);
		String expected0 = "STANDARD";
		String result = p.getName();
		assertEquals(result, expected0);
	}
  @BeforeClass
  public void beforeClass() {
	  pd = new PrivDaoImpl();
  }

}
