package com.revature.servlets;
import static com.revature.services.NoteService.insertNoteDto;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
public class NoteForm_Servlet extends HttpServlet {
	private final static Logger logger = Logger.getLogger(Login_Servlet.class);
	String notename;
	String note;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		notename = request.getParameter("notename");
		note = request.getParameter("note");
		
		logger.info("notename "+notename);
		logger.info("note "+note);
		
		insertNoteDto(note,null,null,notename);
		
	}
}
