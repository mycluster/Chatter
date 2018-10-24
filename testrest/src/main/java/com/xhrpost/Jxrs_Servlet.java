package com.xhrpost;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;




public class Jxrs_Servlet extends HttpServlet {
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = request.getParameter("username");
		System.out.println("User is "+user);
		PrintWriter out = response.getWriter();
		//response.getWriter().println(responded);*/
		
	//	JSONObject jsonObj = new JSONObject("{\"username\":\"Hello\"}");
		System.out.println("In servlet");
	response.setContentType("application/json");
	ObjectMapper mapper = new ObjectMapper();
	ObjectNode responseObject = mapper.createObjectNode();
	
	//Create a JSON object from scratch
	responseObject.put("username", "SUCCESS");
	
	//these next two lines are only for display to console for guidance
	String output = responseObject.toString();
	System.out.println("obj"+output);
	//ouput to Angular
	out.print(responseObject.toString());
	
	
	
	
	}
	
}

