package com.revature.util;

/**
 * A Data type object for the Activation table. Contains an Integer field named after each record
 * in the activation table. Purpose is to account for changes to the primary keys of the records in
 * the database without having to change code throughout the app
 * 
 * @author kbarnes
 *
 */
public class ActivationUtil {
	// each of these correspond to the primary keys of their activation level in the database
	private static Integer NOT_ACTIVATED = 1;
	private static Integer ACTIVATED = 2;
	private static Integer DEACTIVATED = 3;
	
	
	/**
	 * Returns the primary key asscociated with the Not Activated activation level
	 * @return Integer
	 */
	public static Integer notActivatedId() {
		return NOT_ACTIVATED;
	}
	
	/**
	 * Returns the primary key associated with the Activated activation level
	 * @return
	 */
	public static Integer activatedId() {
		return ACTIVATED;
	}
	
	/**
	 * Returns the primary key associated with the Deactivated isolation level
	 * @return
	 */
	public static Integer deactivatedId() {
		return DEACTIVATED;
	}
}
