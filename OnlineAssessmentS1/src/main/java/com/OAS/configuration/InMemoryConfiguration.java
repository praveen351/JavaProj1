package com.OAS.configuration;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.OAS.dao.DaoInteraction;
import com.OAS.extractTxtData.ExtractDataFrmFile;
import com.OAS.model.TestSubject;
import com.OAS.model.Users;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.OAS.configuration", "com.OAS.controller", "com.OAS.util", "com.OAS.dao",
		"com.OAS.extractTxtData" })
public class InMemoryConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public org.h2.tools.Server h2WebConsonleServer() throws SQLException {
		return org.h2.tools.Server.createWebServer("-web", "-webAllowOthers", "-webDaemon", "-webPort", "8091");
	}

	@Bean
	@Autowired
	public Object viewSetData(DaoInteraction dao, ExtractDataFrmFile dataextract) {
		Map<String, Object> mapData = dataextract.extraxtTextFileValues();
		List<Users> users = (List<Users>) mapData.get("user");
		List<TestSubject> tsubjects = (List<TestSubject>) mapData.get("taskSubject");
		dao.addData(users, tsubjects);
		return new Object();
	}

	public void addResourceHandlers(ResourceHandlerRegistry register) {
		register.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}