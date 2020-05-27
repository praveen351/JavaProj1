package com.banking.customer.model;

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Check;

@Entity
@Table(name = "account")
@Check(constraints = "account_type IN ('individual','joint','current') AND status IN ('ACTIVE','DEAD') AND bank_name IN ('SBI Bank','ICIC Bank','KOTAK Mahindra','HDFC Bank','AXIS Bank','BOB Bank','BOI Bank')")
@SequenceGenerator(name = "account_seq", initialValue = 1)
public class AccountEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
	@Column(name = "accountid")
	private long accountid;

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "accountid")
	@OrderColumn(name = "type")
	private List<TransactionEntity> transactionlist;

	@Column(name = "account_no", length = 20, unique = true)
	private String accountno;

	@Column(name = "ifsccode", length = 30)
	private String ifsccode;

	@NotNull(message = "Please enter bank name")
	@NotBlank(message = "Bank Name sholdn't be blank")
	@Column(name = "bank_name")
	private String bankname;

	@Column(name = "branch_name", length = 20)
	private String branchname;

	@Column(name = "branch_code", length = 20)
	private String branchcode;

	@NotNull(message = "Please enter account type")
	@NotBlank(message = "Account type sholdn't be blank")
	@Pattern(regexp = "^[A-Za-z]{10,15}$")
	@Column(name = "account_type", nullable = false, length = 20)
	private String accounttype;

	@Column(name = "open_date")
	private String opendate;

	@Column(name = "current_balance")
	private int currentbalance;

	@Column(name = "status", length = 20)
	private String status;

	public AccountEntity() {
		accountid = 0;
	}

	public AccountEntity(String accountno, String ifsccode, String bankname, String branchname, String branchcode,
			String accounttype, String opendate, int currentbalance, String status,
			List<TransactionEntity> transactionlist) {
		super();
		this.accountno = accountno;
		this.ifsccode = ifsccode;
		this.bankname = bankname;
		this.branchname = branchname;
		this.branchcode = branchcode;
		this.accounttype = accounttype;
		this.opendate = opendate;
		this.currentbalance = currentbalance;
		this.status = status;
		this.transactionlist = transactionlist;
	}
	public AccountEntity(String bankname,String accounttype) {
		super();
		this.accountno = null;
		this.ifsccode = null;
		this.bankname = bankname;
		this.branchname = null;
		this.branchcode = null;
		this.accounttype = accounttype;
		this.opendate = null;
		this.currentbalance = 0;
		this.status = null;
		this.transactionlist = null;
	}

	public long getAccountid() {
		return accountid;
	}

	public void setAccountid(long accountid) {
		this.accountid = accountid;
	}

	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public String getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public String getBranchcode() {
		return branchcode;
	}

	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public String getOpendate() {
		return opendate;
	}

	public void setOpendate(String opendate) {
		this.opendate = opendate;
	}

	public int getCurrentbalance() {
		return currentbalance;
	}

	public void setCurrentbalance(int currentbalance) {
		this.currentbalance = currentbalance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<TransactionEntity> getTransactionlist() {
		return transactionlist;
	}

	public void setTransactionlist(List<TransactionEntity> transactionlist) {
		this.transactionlist = transactionlist;
	}

	@Override
	public String toString() {
		return "AccountEntity [accountid=" + accountid + ", transactionlist=" + transactionlist + ", accountno="
				+ accountno + ", ifsccode=" + ifsccode + ", bankname=" + bankname + ", branchname=" + branchname
				+ ", branchcode=" + branchcode + ", accounttype=" + accounttype + ", opendate=" + opendate
				+ ", currentbalance=" + currentbalance + ", status=" + status + "]";
	}

}
