package com.revature.servlets;
import static com.revature.services.UserService.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.revature.dtos.UserDto;
import com.revature.services.UserService;
public class Login_Servlet extends HttpServlet {
	private final static Logger logger = Logger.getLogger(Login_Servlet.class);
	String username;
	String password;
	UserDto uDto;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		username = request.getParameter("username");
		password = request.getParameter("password");
		//accessing this method from UserService
		uDto = getUserDtoByUsername(username);
		logger.info(checkPassword(uDto, password));
		
		System.out.println("username"+username);
		System.out.println("password"+password);
		
		PrintWriter out = response.getWriter();//use for responding to Angular
		response.setContentType("application/json");
		//create an json object on the mapper
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseObject = mapper.createObjectNode();
		
		if(checkPassword(uDto, password)) {
		//Create a JSON object from scratch
		responseObject.put("doesPass", "TRUE");
		}
		else {
			responseObject.put("doesPass", "FALSE");
		}
		
		//these next two lines are only for display to console for guidance
		logger.info("before sending"+ responseObject.toString());
		System.out.println("obj"+responseObject.toString());
		//ouput to Angular
		out.print(responseObject.toString());
		
	}
}