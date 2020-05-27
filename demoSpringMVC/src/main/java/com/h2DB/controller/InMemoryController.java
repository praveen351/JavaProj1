package com.h2DB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.h2DB.model.CreditCardEligibility;

@Controller
public class InMemoryController {

	@Autowired
	CreditCardEligibility credit_card;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewIndex() {
		credit_card.setId(credit_card.getId()+10);
		System.out.println(credit_card.getId());
		return "index";
	}

	@RequestMapping(value = "/trial1", method = RequestMethod.GET)
	public String sayHelloA() {
		credit_card.setId(credit_card.getId()+20);
		System.out.println(credit_card.getId());
		return "index";
	}

}