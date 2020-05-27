package com.OAS.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TestQuestionAnswer")
public class TestQuestionAnswer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "testquestionanswerid")
	private int testquestionanswerid;
	
	@Column(name = "TestQuestion")
	private String TestQuestion;
	
	@Column(name = "TestAnswer")
	private String TestAnswer;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tqotqa")
	@OrderColumn(name = "type")
	private List<TestQuestionOption> TestQuestionOptionList;

	public TestQuestionAnswer() {
	}

	public TestQuestionAnswer(String testQuestion, String testAnswer, List<TestQuestionOption> testQuestionOptionList) {
		super();
		TestQuestion = testQuestion;
		TestAnswer = testAnswer;
		TestQuestionOptionList = testQuestionOptionList;
	}

	public int getTestquestionanswerid() {
		return testquestionanswerid;
	}

	public void setTestquestionanswerid(int testquestionanswerid) {
		this.testquestionanswerid = testquestionanswerid;
	}

	public String getTestQuestion() {
		return TestQuestion;
	}

	public void setTestQuestion(String testQuestion) {
		TestQuestion = testQuestion;
	}

	public String getTestAnswer() {
		return TestAnswer;
	}

	public void setTestAnswer(String testAnswer) {
		TestAnswer = testAnswer;
	}

	public List<TestQuestionOption> getTestQuestionOptionList() {
		return TestQuestionOptionList;
	}

	public void setTestQuestionOptionList(List<TestQuestionOption> testQuestionOptionList) {
		TestQuestionOptionList = testQuestionOptionList;
	}
	
	
}
