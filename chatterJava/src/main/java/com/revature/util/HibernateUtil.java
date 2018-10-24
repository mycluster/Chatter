package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
 * SessionFactory is configured as a singleton.
 * WE call the configure method of the configuration component which
 * by default, will use the "hibernate.cfg.xml" file.
 * If named differently, you will have to supply the name as a parameter
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	public static Session getSession() {
		return sessionFactory.openSession();
	}
	
}
