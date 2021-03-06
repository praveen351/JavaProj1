package com.OAS.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CandidateStatus")
public class CandidateStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candidatestatusid")
	private int candidatestatusid;
	
	@Column(name = "TestDate",unique=true)
	private String TestDate;
	
	@Column(name = "TestMark",unique=true)
	private int TestMark;
	
	@Column(name = "Result",unique=true)
	private String Result;
	
	public CandidateStatus() {
	}
	
	public CandidateStatus(String testDate, int testMark, String result) {
		this.TestDate = testDate;
		this.TestMark = testMark;
		this.Result = result;
	}

	public int getCandidatestatusid() {
		return candidatestatusid;
	}

	public void setCandidatestatusid(int candidatestatusid) {
		this.candidatestatusid = candidatestatusid;
	}

	public String getTestDate() {
		return TestDate;
	}

	public void setTestDate(String testDate) {
		TestDate = testDate;
	}
	
	public int getTestMark() {
		return TestMark;
	}

	public void setTestMark(int testMark) {
		TestMark = testMark;
	}

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

}
