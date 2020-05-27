package com.OAS.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TestQuestionOption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "testquestionoptionid")
	private int testquestionoptionid;
	
	@Column(name = "TestQuestionOption")
	private String TestQuestionOption;
	
	@Column(name = "TestCorrectOption")
	private String TestOptionCode;

	
	public TestQuestionOption() {
	}

	public TestQuestionOption(int testquestionoptionid, String testQuestionOption, String testOptionCode) {
		this.testquestionoptionid = testquestionoptionid;
		TestQuestionOption = testQuestionOption;
		TestOptionCode = testOptionCode;
	}

	public int getTestquestionoptionid() {
		return testquestionoptionid;
	}

	public void setTestquestionoptionid(int testquestionoptionid) {
		this.testquestionoptionid = testquestionoptionid;
	}

	public String getTestQuestionOption() {
		return TestQuestionOption;
	}

	public void setTestQuestionOption(String testQuestionOption) {
		TestQuestionOption = testQuestionOption;
	}

	public String getTestOptionCode() {
		return TestOptionCode;
	}

	public void setTestOptionCode(String testOptionCode) {
		TestOptionCode = testOptionCode;
	}
	
	
}
