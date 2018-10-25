package com.revature.servlets;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.revature.beans.NoteType;
import com.revature.daos.NoteTypeDao;
import com.revature.daos.NoteTypeDaoImpl;
import com.revature.dtos.UserDto;
import com.revature.services.NoteService;
import com.revature.services.UserService;

/**
 * Servlet implementation class Upload
 */
public class Upload extends HttpServlet {
	private String filePath;
	private int maxFileSize = 700 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file;
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(Upload.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Upload() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		// Get the file location where it would be stored.
		filePath = getServletContext().getInitParameter("file-upload");
		logger.info("Servlet initiated");
		logger.debug("Uploading files to: " + filePath);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// create a new DiskFileItem factory
		DiskFileItemFactory factory = new DiskFileItemFactory();
		logger.info("DiskFileItemFactory created");

		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		logger.info("Max file size to store in MEMORY set to :" + maxMemSize + " bytes");

		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File("c:\\temp"));
		logger.info("Storing data larger than max file size to store in memory in c:\\temp");

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		logger.info("New file upload handler created");

		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);
		logger.info("Max file upload size set");
		logger.debug("MaxFileSize: " + maxFileSize + " bytes");

		// create a HashMap to contain the form fields
		HashMap<String, String> formFields = new HashMap<String, String>();
		logger.info("FormFields HashMap created");
		
		// create a HashMap to contain the Files as FileItems
		HashMap<String, FileItem> files = new HashMap<String, FileItem>();
		logger.info("Files HashMap created");
		
		/*
		 * We do this because we need the user's information to properly upload the
		 * file but we do not know what order the List of FileItems will be returned in.
		 * So we will iterate through the list and move the FileItems in their respective
		 * HashMaps
		 */
		try {
			// get all of the fields of the form as FileItem
			// a FileItem is either a FormField or a File
			// we can check this to make sure both are handled correctly
			List<FileItem> items = upload.parseRequest(request);
			logger.info("Request parsed as a List of FileItems");

			logger.info("Beginning iteration through list of FileItems");
			for (FileItem item : items) {

				if (item.isFormField()) {
					// if its not a file its request info
					logger.info("FileItem is a FormField");
					formFields.put(item.getFieldName(), item.getString());
					logger.info("FormField entered into HashMap");
					logger.debug("Key: "+ item.getFieldName());
					logger.debug("Value: " + item.getString());
				} else {
					logger.info("FileItem is a File");
					// add the file to the file HashMap
					files.put(item.getFieldName(), item);
					logger.info("File added to files HashMap");
					logger.debug("Field Name of File: "+ item.getFieldName());
					logger.debug("File name: "+ item.getName());
				}

			}
			
			// Get the username of the user uploading the file
			String username = "";
			//TODO GET THE USERNAME
			logger.info("Username retrieved from request");
			logger.debug("Username: "+ username);
			
			//get the user associated with the username
			UserDto user = UserService.getUserDtoByUsername(username);
			logger.info("User retrieved from database");
			logger.debug("User: "+ user);
			
			// upload the associated files one by one
			logger.info("Iterating through the key set of the files hashMap");
			for(String fileField : files.keySet()) {
				// Get the FileItem relating to that key
				FileItem fileItem = files.get(fileField);
				logger.info("FileItem retrieve from HashMap");
				logger.debug("Key: "+ fileField);
				logger.debug("FileItem: "+ fileItem.getName());
				
				// Get the name of the File
				String fileName = fileItem.getName();
				logger.info("Name of file stored");
				
				// Create a null reference to a String
				String fileLocation = null;
				logger.info("Location string generated");
				
				// create the location of the file
				logger.info("Creating path to file");
				file = new File(filePath + "uId" + user.getId() + fileName.substring(fileName.lastIndexOf("\\") + 1));
				fileLocation = filePath + "uId" + user.getId() + fileName.substring(fileName.lastIndexOf("\\"));
				logger.info("Path to file created");
				logger.debug("File path: "+ fileLocation);
				
				// write the actual file
				logger.info("Writing file");
				fileItem.write(file);
				logger.info("File written");
				
				// get the type of the file
				String typeString = "";
				// TODO GET THE TYPE STRING FROM THE REQUEST
				logger.info("Type string recieved from request");
				logger.debug("Type string: "+ typeString);
				
				// get the note name
				String name = "";
				//TODO GET THE NOTE NAME FROM THE REQUEST
				logger.info("Note name received from request");
				logger.debug("Note name: "+ name);
				
				// convert the string into an integer
				Integer id = Integer.parseInt(typeString);
				logger.info("Type string converted into integer id");
				logger.debug("Id: "+ id);
				
				// create a reference to a NoteTypeDao
				NoteTypeDao ntd = new NoteTypeDaoImpl();
				logger.info("NoteTypeDao created");
				
				// get the respective note type from the database
				NoteType type = ntd.selectNoteTypeById(id);
				logger.info("NoteType retrieved from database");
				logger.debug("NoteType: "+type.toString());
				
				// insert the new note into the database
				NoteService.insertNoteDto(fileLocation, user, type, name);
				logger.info("Note successfully inserted");
				
			}
		} catch (FileUploadException e) {
			logger.error("File upload failed", e);

		} catch (ParseException e) {
			logger.error("Parsing request failed", e);
		} catch (Exception e) {
			logger.error("Something unknown went wrong", e);
		}
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
