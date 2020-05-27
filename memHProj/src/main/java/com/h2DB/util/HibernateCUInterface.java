package com.h2DB.util;

import org.hibernate.SessionFactory;

public interface HibernateCUInterface {
	public SessionFactory getSessionFactory();
	public void shutdown();
}
