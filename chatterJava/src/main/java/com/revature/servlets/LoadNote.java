package com.revature.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.dtos.NoteDto;
import com.revature.services.NoteService;

/**
 * Servlet implementation class LoadNote
 */
public class LoadNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(LoadNote.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadNote() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get the Note id that we want to retrieve
		String idString = ""; //TODO ACTUALLY RETRIEVE THE NOTE ID
		logger.info("Note id retrieved from front-end");
		logger.debug("Id: "+ idString);
		
		// convert to an Integer
		Integer id = Integer.parseInt(idString);
		logger.info("Converted id to integer value");
		
		// get the corresponding Note
		NoteDto note = NoteService.selectNoteDtoById(id);
		logger.info("Note retrieved from backend");
		logger.debug("Note: "+ note);
		
		// reads input file from an absolute path
        String filePath = note.getLocation();
        logger.info("Note location acquired");
        logger.debug("Location: "+ filePath);
        
        // make a string reference
	    String noteContents = null;
	    logger.info("Note container string generated");
        // if the note is a text file make it back into a string
        if (note.getType().getId() == 1) {
        	logger.info("Note is text-based, generating string");
        	// make a new object input stream reference
    	    ObjectInputStream ois = null;
    	    logger.info("ObjectInputStream reference created");
    	  
    	    
    		try {
    			// create a new Object Input Stream
    			ois = new ObjectInputStream(new FileInputStream(filePath));
    			logger.info("ObjectInputStream created for "+filePath);
    			
    			// Cast that object as a String
    			noteContents = (String) ois.readObject();	
    			logger.info("Note contents retireved from file");
    			logger.debug("Note: "+ noteContents);
    		} catch (IOException | ClassNotFoundException e) {
    			logger.error("Error loading note", e);
    		} finally {
    			logger.info("Closing object input stream");
    			if (ois != null) {
    				ois.close(); 
    			}
    			logger.info("Object input stream closed");
    		}
        }
        
        // TODO I DON'T KNOW WHAT FORM YOU WANT THINGS HANDED BACK IN
        //  ¯\_(ツ)_/¯
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
