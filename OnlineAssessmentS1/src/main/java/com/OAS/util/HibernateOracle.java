package com.OAS.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

import com.OAS.model.CandidateStatus;
import com.OAS.model.TestQuestionAnswer;
import com.OAS.model.TestQuestionOption;
import com.OAS.model.TestSubject;
import com.OAS.model.Users;

@Component("OracleDB")
public class HibernateOracle implements HibernateCUInterface{
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				
				Properties settings = new 	Properties();
				settings.put(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver");
                settings.put(Environment.URL, "jdbc:oracle:thin:@localhost:1521:xe");
                settings.put(Environment.USER, "OnlineAssessmentSystem");
                settings.put(Environment.PASS, "Oracle1234");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.Oracle9Dialect");
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
		// TODO Auto-generated method stub
		
	}

}
