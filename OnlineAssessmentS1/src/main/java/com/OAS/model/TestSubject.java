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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TestSubject")
@SequenceGenerator(name = "testsubject_seq", initialValue = 1)
public class TestSubject {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "testsubject_seq")
	@Column(name = "testsubjectid")
	private int testsubjectid;
	
	@Column(name = "Subject_Name")
	private String Subject_Name;
	
	@Column(name = "Subject_Total_Mark")
	private int Subject_Total_Mark;
	
	@Column(name = "Subject_Pass_Mark")
	private int Subject_Pass_Mark;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tscsid")
//	@OrderColumn(name = "type")
	private List<CandidateStatus> CandidateStatusList;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tstqaid")
//	@OrderColumn(name = "type")
	private List<TestQuestionAnswer> TestQuestionAnswerList;

	public TestSubject() {
	}
	
	public TestSubject(String subject_Name, int subject_Total_Mark, int subject_Pass_Mark,List<CandidateStatus> candidateStatusList, List<TestQuestionAnswer> testQuestionAnswerList) {
		this.Subject_Name = subject_Name;
		this.Subject_Total_Mark = subject_Total_Mark;
		this.Subject_Pass_Mark = subject_Pass_Mark;
		this.CandidateStatusList = candidateStatusList;
		this.TestQuestionAnswerList = testQuestionAnswerList;
	}

	public int getTestsubjectid() {
		return testsubjectid;
	}

	public void setTestsubjectid(int testsubjectid) {
		this.testsubjectid = testsubjectid;
	}

	public String getSubject_Name() {
		return Subject_Name;
	}

	public void setSubject_Name(String subject_Name) {
		Subject_Name = subject_Name;
	}

	public int getSubject_Total_Mark() {
		return Subject_Total_Mark;
	}

	public void setSubject_Total_Mark(int subject_Total_Mark) {
		Subject_Total_Mark = subject_Total_Mark;
	}

	public int getSubject_Pass_Mark() {
		return Subject_Pass_Mark;
	}

	public void setSubject_Pass_Mark(int subject_Pass_Mark) {
		Subject_Pass_Mark = subject_Pass_Mark;
	}

	public List<CandidateStatus> getCandidateStatusList() {
		return CandidateStatusList;
	}

	public void setCandidateStatusList(List<CandidateStatus> candidateStatusList) {
		CandidateStatusList = candidateStatusList;
	}

	public List<TestQuestionAnswer> getTestQuestionAnswerList() {
		return TestQuestionAnswerList;
	}

	public void setTestQuestionAnswerList(List<TestQuestionAnswer> testQuestionAnswerList) {
		TestQuestionAnswerList = testQuestionAnswerList;
	}
	
	
}