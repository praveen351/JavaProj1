package com.OAS.util;

import org.hibernate.SessionFactory;

public interface HibernateCUInterface {
	public SessionFactory getSessionFactory();
	public void shutdown();
}
