package com.banking.customer.model;

public class TransactionModel {
	private String accountno;
	private String ifsccode;
	private int amount;
	
	
	public TransactionModel() {
		super();
	}
	public TransactionModel(String accountno, String ifsccode, int amount) {
		super();
		this.accountno = accountno;
		this.ifsccode = ifsccode;
		this.amount = amount;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
