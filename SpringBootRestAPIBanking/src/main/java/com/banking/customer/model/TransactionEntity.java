package com.banking.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

@Entity
@Table(name = "transaction")
@Check(constraints = "status IN ('ACTIVE','DEAD')")
@SequenceGenerator(name = "transaction_seq", initialValue = 1)
public class TransactionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
	@Column(name = "transactionid")
	private long transactionid;

	@Column(name = "date_time_transaction", length = 50, nullable = false)
	private String datetimetransaction;

	@Column(name = "account_no_to", nullable = false)
	private String accountnoto;

	@Column(name = "amount", nullable = false)
	private int amount;

	@Column(name = "status", length = 20, nullable = false)
	private String status;

	public TransactionEntity() {
		super();
	}

	public TransactionEntity(String datetimetransaction, int amount, String accountnoto, String status) {
		super();
		this.datetimetransaction = datetimetransaction;
		this.amount = amount;
		this.accountnoto = accountnoto;
		this.status = status;
	}

	public long getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(long transactionid) {
		this.transactionid = transactionid;
	}

	public String getDatetimetransaction() {
		return datetimetransaction;
	}

	public void setDatetimetransaction(String datetimetransaction) {
		this.datetimetransaction = datetimetransaction;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getAccountnoto() {
		return accountnoto;
	}

	public void setAccountnoto(String accountnoto) {
		this.accountnoto = accountnoto;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TransactionEntity [transactionid=" + transactionid + ", datetimetransaction=" + datetimetransaction
				+ ", accountnoto=" + accountnoto + ", amount=" + amount + ", status=" + status + "]";
	}

}
