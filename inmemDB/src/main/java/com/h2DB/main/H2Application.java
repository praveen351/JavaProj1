package com.h2DB.main;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.h2DB.model.UserDetail;
import com.h2DB.service.UserDetailService;
import com.h2DB.serviceImp.UserDetailServiceImpl;

//@Component
@Configuration
@ComponentScan(basePackages = { "com.h2DB.imp" , "com.h2DB.serviceImp" })
public class H2Application {

//	@Autowired
//	private UserDetailService service;
	
	@Bean
	public DataSource dataSource() {	
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2) // HSQL or DERBY
				.addScript("sql/create-table.sql").addScript("sql/insert-data.sql").build();
		return db;
	}

	@Bean
	@Autowired
	public JdbcTemplate createJdbcTeamplate(DataSource datasource) {
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(datasource);
		return template;
	}
	
//	@Bean
//	@Autowired
//	public Object createDemo(UserDetailService userservice) {
//		System.out.print(userservice.getAllUserDetail().size());
//		return new Object();
//	}
	
	public static void main(String[] args) {
		// ClassPathXmlApplicationContext ctx = new
		// ClassPathXmlApplicationContext("spring-config.xml");
		
//		 ApplicationContext ctx = new AnnotationConfigApplicationContext(H2Application.class);
//		 H2Application h2App = ctx.getBean(H2Application.class);
		 
		 ApplicationContext ctx = new AnnotationConfigApplicationContext(H2Application.class);
		 UserDetailService service = ctx.getBean(UserDetailServiceImpl.class);
		
		 List<UserDetail> userDetails = service.getAllUserDetail();
		 System.out.println("All User Details:");
		 for (UserDetail userDetail : userDetails) {
		 System.out.println(userDetail);
		 }
		 System.out.println();
		 UserDetail userDetail = service.getUserDetail(7);
		 System.out.println("User Detail: " + userDetail);
		 //((AnnotationConfigApplicationContext)ctx ).close();
	}
}
