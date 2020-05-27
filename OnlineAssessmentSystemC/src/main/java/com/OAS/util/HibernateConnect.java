package com.OAS.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.OAS.model.CandidateStatus;
import com.OAS.model.TestQuestionAnswer;
import com.OAS.model.TestQuestionOption;
import com.OAS.model.TestSubject;
import com.OAS.model.Users;

public class HibernateConnect implements HibernateCUInterface{
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				
				Properties settings = new 	Properties();
				settings.put(Environment.DRIVER, "org.h2.Driver");
				settings.put(Environment.URL, "jdbc:h2:mem:test");
				settings.put(Environment.USER, "sa");
				settings.put(Environment.PASS, "");
				settings.put(Environment.POOL_SIZE, 1);
				settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, "update");
				configuration.setProperties(settings);

				configuration.addAnnotatedClass(Users.class);
				configuration.addAnnotatedClass(CandidateStatus.class);
				configuration.addAnnotatedClass(TestSubject.class);
				configuration.addAnnotatedClass(TestQuestionAnswer.class);
				configuration.addAnnotatedClass(TestQuestionOption.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

	public void shutdown() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}
}
