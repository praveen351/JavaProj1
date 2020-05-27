package com.OAS.main;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.OAS.util.HibernateCUInterface;
import com.OAS.util.HibernateConnect;

@Configuration
@PropertySource("classpath:application.properties")
public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
		HibernateCUInterface uiconnect = new HibernateConnect();
		//HibernateCUInterface uiconnect = new HibernateOracle();
		Session session = uiconnect.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
	}

}
