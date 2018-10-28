package com.revature.servlets;
import static com.revature.services.UserService.insertNewUserDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

//import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Register_Servlet extends HttpServlet {
	private final static Logger logger = Logger.getLogger(Register_Servlet.class);
	String firstname;//variables accessible instance scope
	String lastname;
	String username;//variables accessible instance scope
	String email;
	String password;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//retrieve the values inserted in fields from Angular
		firstname = request.getParameter("firstname");
		lastname = request.getParameter("lastname");
		username = request.getParameter("username");
		email = request.getParameter("email");
		password = request.getParameter("password");
		logger.info("fname "+firstname + "\nlastname " + lastname + "\nusername " + username + "\nemail " + email + "\npassword " + password);
		//passing values to UserService method insertNewUserDto(blah)
		insertNewUserDto(username, firstname, lastname, password, null);
	
		
		
	}//end doPost()

}//end class



