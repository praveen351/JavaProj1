package com.banking.customer.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.customer.model.AccountEntity;
import com.banking.customer.model.TransactionEntity;
import com.banking.customer.model.TransactionModel;
import com.banking.customer.repository.TransactionRepository;

@Service
public class TransactionService {
	private TransactionRepository transaction_repo;

	@Autowired
	public TransactionService(TransactionRepository transaction_repo) {
		this.transaction_repo = transaction_repo;
	}

	public TransactionEntity addTransaction(TransactionModel transaction) {
		SimpleDateFormat ft = new SimpleDateFormat("dd-mm-yyyy hh:mm");
		TransactionEntity tranasactionObj = new TransactionEntity();
		tranasactionObj.setAccountnoto(transaction.getAccountno());
		tranasactionObj.setAmount(transaction.getAmount());
		tranasactionObj.setDatetimetransaction(ft.format(new Date()));
		tranasactionObj.setStatus("ACTIVE");
		transaction_repo.save(tranasactionObj);
		if (tranasactionObj.getTransactionid() != 0)
			return tranasactionObj;
		return null;
	}

	public int updateAcctAccount(AccountEntity account, TransactionEntity transaction) {
		int response = transaction_repo.updateAccountIdTransaction(account.getAccountid(),
				transaction.getTransactionid());
		if(response != 0)
			return 1;
		else
			return 0;
	}
}
