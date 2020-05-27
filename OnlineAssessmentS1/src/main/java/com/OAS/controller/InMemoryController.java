package com.OAS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.OAS.dao.DaoInteraction;
import com.OAS.model.TestQuestionAnswer;
import com.OAS.model.UserAuthenticate;
import com.OAS.model.Users;

@Controller
@SessionAttributes("user")
public class InMemoryController {
	
	private DaoInteraction dao;
	
	@Autowired
	public InMemoryController(DaoInteraction dao) {
		this.dao = dao;
	}
	
	@ModelAttribute("userAuthenticate")
	public UserAuthenticate setUpUserAuthenticate() {
		return new UserAuthenticate();
	}
	
	@RequestMapping(value = "/trial", method = RequestMethod.GET)
	public String viewIndex() {
//		List<TestQuestionAnswer> testQuestion = dao.getQuestionAnswer("Spring-L1");
//		System.out.println(testQuestion.size());
		return "demo";
	}
	
	@RequestMapping(value = "/login/do", method = RequestMethod.POST)
	public String userLogin(@ModelAttribute("userAuthenticate") UserAuthenticate authenticate) {
		return "index";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String viewUpdate(@ModelAttribute("user") Users users, @ModelAttribute("data") String st,
			@ModelAttribute("data1") String gt) {
		return "update";
	}
}