package com.banking.customer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.customer.model.AccountEntity;
import com.banking.customer.model.TransactionModel;
import com.banking.customer.repository.AccountRepository;

@Service
public class AccountService {
	private AccountRepository account_repo;

	@Autowired
	public AccountService(AccountRepository account_repo) {
		this.account_repo = account_repo;
	}

	public AccountEntity getAccountById(Long id) {
		Optional<AccountEntity> account = account_repo.findById(id);
		if (account.isPresent())
			return account.get();
		else
			return null;
	}

	public AccountEntity getAccountByAcctNoIfsccode(String accountno, String ifsccode) {
		AccountEntity response = account_repo.findByAccountIfsc(accountno, ifsccode);
		if(response != null)
			return response;
		else
			return null; 
	}

	public int doBalanceTransfer(AccountEntity accountFrom, AccountEntity accountTo, TransactionModel transaction) {
		if (accountFrom.getCurrentbalance() >= transaction.getAmount()) {
			int statusFrom = account_repo.updateByAccountFrom(accountFrom.getAccountid(), transaction.getAmount());
			int statusTo = account_repo.updateByAccountTo(accountTo.getAccountid(), transaction.getAmount());
			if (statusFrom != 0 && statusTo != 0)
				return 1;
			else
				return 0;
		} else {
			return -1;
		}
	}

}
