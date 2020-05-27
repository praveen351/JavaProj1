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
@Table(name = "Users")
@SequenceGenerator(name = "user_seq", initialValue = 1)
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@Column(name = "userid")
	private int userid;
	
	@Column(name = "Email",unique=true)
	private String Email;
	
	@Column(name = "Password")
	private String Password;
	
	@Column(name = "First_Name")
	private String First_Name;
	
	@Column(name = "Last_Name")
	private String Last_Name;
	
	@Column(name = "User_Type")
	private String User_Type;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ucsid")
	@OrderColumn(name = "type")
	private List<CandidateStatus> CandidateStatusList;
	
	public Users() {
	}
	
	

	public Users(String email, String password, String first_Name, String last_Name, String user_Type,
			List<CandidateStatus> candidateStatusList) {
		Email = email;
		Password = password;
		First_Name = first_Name;
		Last_Name = last_Name;
		User_Type = user_Type;
		CandidateStatusList = candidateStatusList;
	}



	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getFirst_Name() {
		return First_Name;
	}

	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

	public String getUser_Type() {
		return User_Type;
	}

	public void setUser_Type(String user_Type) {
		User_Type = user_Type;
	}

	public List<CandidateStatus> getCandidateStatusList() {
		return CandidateStatusList;
	}

	public void setCandidateStatusList(List<CandidateStatus> candidateStatusList) {
		CandidateStatusList = candidateStatusList;
	}



	@Override
	public String toString() {
		return "Users [userid=" + userid + ", Email=" + Email + ", Password=" + Password + ", First_Name=" + First_Name
				+ ", Last_Name=" + Last_Name + ", User_Type=" + User_Type ;
	}
	
	
}
