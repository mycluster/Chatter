package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.beans.AccessLevel;
import com.revature.util.HibernateUtil;

/**
 * 
 * @author kbarnes
 * 
 * Returns a list of all AccessLevels in the database
 * if there are none the returned List Reference is
 * null
 * 
 */
public class AccessLevelDaoImpl implements AccessLevelDao {

	@Override
	public List<AccessLevel> selectAllAccessLevel() {
		// create a new session
		Session session = HibernateUtil.getSession();
		
		// make a null reference to a list of accessLevels
		List<AccessLevel> accessLevels = null;
		
		try {
			// attempt to get all the access levels
			accessLevels = session.createQuery("FROM AccessLevel").list();
		
		} catch (HibernateException e) {
			// if there is a hibernate exception, catch it
			// and print the stack trace
			e.printStackTrace();
		} finally {
			// clean up after ourselves
			session.close();
		}
		return accessLevels;
	}

	@Override
	public AccessLevel selectAccessLevelById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccessLevel setAccessLevel(AccessLevel accessLevel) {
		// TODO Auto-generated method stub
		return null;
	}

}
