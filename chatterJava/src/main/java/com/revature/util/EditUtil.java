package com.revature.util;

/**
 * A Data type object for the Edit table. Contains an Integer field named after each record
 * in the Edit table. Purpose is to account for changes to the primary keys of the records in
 * the database without having to change code throughout the app
 * 
 * @author kbarnes
 *
 */
public class EditUtil {
	private static Integer UNEDITED = 1;
	private static Integer EDITED = 2;
	
	/**
	 * Returns the primary key asscociated with the Unedited edit level
	 * @return Integer
	 */
	public static Integer uneditedId() {
		return UNEDITED;
	}
	
	/**
	 * Returns the primary key associated with the Edited edit level
	 * @return
	 */
	public static Integer editedId() {
		return EDITED;
	}
}
