package com.banking.customer.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Check;

@Entity
@Table(name = "customer")
@Check(constraints = "gender IN ('MALE','FEMALE') AND status IN ('ACTIVE','DEAD')")
@SequenceGenerator(name = "customer_seq", initialValue = 1)
public class CustomerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
	@Column(name = "customerid")
	private long customerid;

	@OneToOne(targetEntity = AccountEntity.class, cascade = CascadeType.ALL)
	AccountEntity account;

	@NotNull(message = "Please enter first name of cutomer")
	@NotBlank(message = "First name sholdn't be blank")
	@Column(name = "first_name", length = 30)
	private String firstName;

	@NotNull(message = "Please enter user name of cutomer")
	@NotBlank(message = "User name sholdn't be blank")
	@Pattern(regexp = "^[A-Za-z0-9]{5,13}$")
	@Column(name = "user_name", unique = true, length = 30)
	private String userName;

	@NotNull(message = "Please enter last name of cutomer")
	@NotBlank(message = "Last name sholdn't be blank")
	@Column(name = "last_name", length = 30)
	private String lastName;

	@NotNull(message = "Please enter gender")
	@NotBlank(message = "Gender sholdn't be blank")
	@Column(name = "gender", length = 20)
	private String gender;

	@NotNull(message = "Please enter Customer DOB")
	@NotBlank(message = "Customer DOB sholdn't be blank")
	@Column(name = "customer_dob", length = 20)
	private String customerDob;

	@NotNull(message = "Please enter age")
	@Column(name = "age")
	private int age;

	@NotNull(message = "Please enter email")
	@NotBlank(message = "Email sholdn't be blank")
	@Email(message = "Please Enter proper mail id")
	@Column(name = "email_id", length = 50)
	private String email;

	@NotNull(message = "Please enter mobile no")
	@NotBlank(message = "Mobile No sholdn't be blank")
	@Pattern(regexp = "^[0-9]{10,11}$", message = "Please Enter Proper Mobile no")
	@Column(name = "mobileno", length = 20)
	private String mobileno;

	@NotNull(message = "Please enter aadhaar no")
	@NotBlank(message = "aadhaar No sholdn't be blank")
	@Pattern(regexp = "^[0-9]{16}$")
	@Column(name = "aadhaarno", length = 30)
	private String aadhaarno;

	@NotNull(message = "Please enter father name")
	@NotBlank(message = "Father name sholdn't be blank")
	@Column(name = "fathername", length = 30)
	private String fathername;

	@NotNull(message = "Please enter state")
	@NotBlank(message = "State sholdn't be blank")
	@Column(name = "state", length = 30)
	private String state;

	@NotNull(message = "Please enter district")
	@NotBlank(message = "District sholdn't be blank")
	@Column(name = "district", length = 30)
	private String district;

	@NotNull(message = "Please enter pincode")
	@NotBlank(message = "Pincode sholdn't be blank")
	@Pattern(regexp = "^[0-9]{6}$")
	@Column(name = "pincode", length = 10)
	private String pincode;

	@NotNull(message = "Please enter address")
	@NotBlank(message = "Address sholdn't be blank")
	@Column(name = "address", length = 100)
	private String address;

	@NotNull(message = "Please enter Landmark")
	@NotBlank(message = "Landmark sholdn't be blank")
	@Column(name = "landmark", length = 100)
	private String landmark;

	@Column(name = "status", length = 20)
	private String status;

	public CustomerEntity() {
		customerid = 0;
	}

	public CustomerEntity(String firstName, String lastName, String userName, String gender, String customerDob,
			int age, String email, String mobileno, String aadhaarno, String fathername, String state, String district,
			String pincode, String address, String landmark, String status, AccountEntity account) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.customerDob = customerDob;
		this.age = age;
		this.email = email;
		this.mobileno = mobileno;
		this.aadhaarno = aadhaarno;
		this.fathername = fathername;
		this.state = state;
		this.district = district;
		this.pincode = pincode;
		this.address = address;
		this.landmark = landmark;
		this.status = status;
		this.account = account;
		this.userName = userName;
	}

	public CustomerEntity(String firstName, String lastName, String userName, String email, String mobileno,
			String state, String district, String pincode, String address, String landmark) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.mobileno = mobileno;
		this.state = state;
		this.district = district;
		this.pincode = pincode;
		this.address = address;
		this.landmark = landmark;
	}

	public long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCustomerDob() {
		return customerDob;
	}

	public void setCustomerDob(String customerDob) {
		this.customerDob = customerDob;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getAadhaarno() {
		return aadhaarno;
	}

	public void setAadhaarno(String aadhaarno) {
		this.aadhaarno = aadhaarno;
	}

	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "CustomerEntity [customerid=" + customerid + ", account=" + account + ", firstName=" + firstName
				+ ", userName=" + userName + ", lastName=" + lastName + ", gender=" + gender + ", customerDob="
				+ customerDob + ", age=" + age + ", email=" + email + ", mobileno=" + mobileno + ", aadhaarno="
				+ aadhaarno + ", fathername=" + fathername + ", state=" + state + ", district=" + district
				+ ", pincode=" + pincode + ", address=" + address + ", landmark=" + landmark + ", status=" + status
				+ "]";
	}

}