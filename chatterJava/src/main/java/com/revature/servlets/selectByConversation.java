package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.MessageDto;
import com.revature.dtos.UserDto;
import com.revature.services.MessageService;
import com.revature.services.UserService;

/**
 * Servlet implementation class selectByConversation
 */
public class selectByConversation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(selectByConversation.class);

    /**
     * Default constructor. 
     */
    public selectByConversation() {
        logger.info("Servlet initializing");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("SelectByConversation doGet method starting");
		// set the response content type
		response.setContentType("application/json");
		logger.info("Response content type set");
		logger.debug("Content type: "+ response.getContentType());
		
		// get a new print writer
		PrintWriter out = response.getWriter();
		logger.info("PrintWriter created");
		
		// get the the username of User1
		String username1 = request.getParameter("username1");
		logger.info("First username retrieved from request");
		logger.debug("Username1: "+ username1);
		
		// get the username of User2
		String username2 = request.getParameter("username2");
		logger.info("Second username retreived from request");
		logger.debug("Username2: "+ username2);
		
		// get the user with username1
		UserDto user1 = UserService.getUserDtoByUsername(username1);
		logger.info("User with username1 retrieved from database");
		logger.debug("User: "+ user1.toString());
		
		// get the user with username2
		UserDto user2 = UserService.getUserDtoByUsername(username2);
		logger.info("User with username2 retrieved from database");
		logger.debug("User: "+ user2.toString());
	
		// get the conversation messages from the database
		List<MessageDto> conversation = MessageService.selectMessageDtoByConversation(user1, user2);
		logger.info("Conversation messages retrieved from database");
		
		// create a new ObjectMapper
		ObjectMapper om = new ObjectMapper();
		logger.info("ObjectMapper created");
		
		// add the object to the PrintWiter as a string
		out.print(om.writeValueAsString(conversation));
		logger.info("Conversation messages added to printWriter");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
