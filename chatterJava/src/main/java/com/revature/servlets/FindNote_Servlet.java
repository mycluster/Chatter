package com.revature.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FindNote_Servlet extends HttpServlet{
	private final static Logger logger = Logger.getLogger(FindNote_Servlet.class);
	String notename;
	List<String>dbNotes;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		notename = request.getParameter("notename");
		PrintWriter out = response.getWriter();
		logger.info("received notename"+notename);
		getNoteByName();
		if(dbNotes.isEmpty()){
			logger.info("arraylist is empty!");
			String newnote = "New Note...";
			
		}else {
			ObjectMapper om = new ObjectMapper();
			out.print(om.writeValueAsString(dbNotes));
			logger.info(om.writeValueAsString(dbNotes));
		}
		
		logger.info("servlet end");
	}//doPost()
	
	public void getNoteByName() {
		Connection conn = null;
		conn=getConnection();
		dbNotes = new ArrayList<String>();

		PreparedStatement prepSt = null; //A simple SQL query to be executed
		ResultSet rs = null; //The object that holds query results
		String sql="SELECT loc FROM notes WHERE n_name = ?";
		try {
		  logger.info("Preparin to get from the DB");
		  prepSt= conn.prepareStatement(sql);
		  prepSt.setString(1, notename);
		  rs = prepSt.executeQuery();
		 
		  while (rs.next()) {
		    	dbNotes.add(rs.getString(1));
		    }
		
        for(String s: dbNotes) {
        	logger.info("values"+s);
        }
           
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(prepSt);
		}
	
	}//end getNoteByName()
	public static void close(PreparedStatement resource){
		if(resource != null){
			try{
				resource.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}//end close the st
	public static void close(ResultSet resource){
		if(resource != null){
			try{
				resource.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}//end close rs
	public static Connection getConnection(){
		Connection conn = null;
		try {
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:xe",
						//"jdbc:oracle:thin:@sandbox-180910.c7gydzn7nvzj.us-east-1.rds.amazonaws.com:1521:orcl",
						"trex",
						"trex"
					);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}//getConnection()
	

}
