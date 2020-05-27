package com.h2DB.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

import com.h2DB.model.UserDetail;

@Component("Connect")
public class HibernateConnect implements HibernateCUInterface{
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "org.h2.Driver");
				settings.put(Environment.URL, "jdbc:h2:mem:test");
				settings.put(Environment.USER, "sa");
				settings.put(Environment.PASS, "");
				settings.put(Environment.POOL_SIZE, 1);
				settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, "create-drop");
				configuration.setProperties(settings);

				configuration.addAnnotatedClass(UserDetail.class);

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
