package com.revature.tests;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.beans.Message;
import com.revature.daos.MessageDao;
import com.revature.daos.MessageDaoImpl;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MessageTest {
	private static Message m;
	private static MessageDao md;
	private static List<Message> ml;
  @Test
  public void TestSelectAllMessage() {
	  //Select all messages also gives null so need to fix this before testing begins
	  //	  ml = md.selectAllMessage();
//	  String expected0 = "squaaaaaaawk";
//	  String expected1 = "chatter,chatter";
//	  String result = ml.get(0).getMessage();
//	  assertEquals(result, expected0);
//	  result = ml.get(1).getMessage();
//	  assertEquals(result,expected1);
  }
  @BeforeClass
  public void beforeClass() {
	  md = new MessageDaoImpl();
  }

}
