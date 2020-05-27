package com.h2DB.model;

import org.springframework.stereotype.Component;

@Component
public class CreditCardEligibility {

	private int id;

	private String PANNumber;

	public CreditCardEligibility() {
		id = 0;
		System.out.println(id + "default");
	}

	public CreditCardEligibility(int id, String pANNumber) {
		this.id = id;
		this.PANNumber = pANNumber;
		System.out.println(id + "parameterized");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPANNumber() {
		return PANNumber;
	}

	public void setPANNumber(String pANNumber) {
		PANNumber = pANNumber;
	}

}
