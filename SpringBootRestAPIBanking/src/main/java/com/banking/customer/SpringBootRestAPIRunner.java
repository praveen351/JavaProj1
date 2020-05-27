package com.banking.customer;

//@SpringBootApplication
public class SpringBootRestAPIRunner {
//	@Value("${hibernate.connection.driver_class}")
//	private String DRIVER;
//
//	@Value("${hibernate.connection.password}")
//	private String PASSWORD;
//
//	@Value("${hibernate.connection.url}")
//	private String URL;
//
//	@Value("${hibernate.connection.username}")
//	private String USERNAME;
//
//	@Value("${hibernate.dialect}")
//	private String DIALECT;
//
//	@Value("${hibernate.show_sql}")
//	private String SHOW_SQL;
//
//	@Value("${hibernate.hbm2ddl}")
//	private String HBM2DDL_AUTO;
//
//	@Value("${hibernate.connection.pool_size}")
//	private String POOL;
//
//	@Value("${hibernate.current_session_context_class}")
//	private String CURRENT_SESSION_CONTEXT_CLASS;
//
//	@Bean
//	public Session getSessionFactory() {
//		SessionFactory sessionFactory = null;
//		try {
//			Configuration configuration = new Configuration();
//			Properties settings = new Properties();
//			settings.put(Environment.DRIVER, DRIVER.trim());
//			settings.put(Environment.URL, URL.trim());
//			settings.put(Environment.USER, USERNAME.trim());
//			settings.put(Environment.PASS, PASSWORD.trim());
//			settings.put(Environment.POOL_SIZE, POOL.trim());
//			settings.put(Environment.DIALECT, DIALECT.trim());
//			settings.put(Environment.SHOW_SQL, SHOW_SQL.trim());
//			settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, CURRENT_SESSION_CONTEXT_CLASS.trim());
//			settings.put(Environment.HBM2DDL_AUTO, HBM2DDL_AUTO.trim());
//			configuration.setProperties(settings);
//			configuration.addAnnotatedClass(CustomerEntity.class);
//			configuration.addAnnotatedClass(AccountEntity.class);
//			configuration.addAnnotatedClass(TransactionEntity.class);
//			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//					.applySettings(configuration.getProperties()).build();
//			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		Session session = sessionFactory.getCurrentSession();
//		return session;
//	}
}
