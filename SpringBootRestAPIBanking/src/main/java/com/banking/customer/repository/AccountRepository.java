package com.banking.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.banking.customer.model.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
	@Query(value = "SELECT * FROM account a WHERE a.account_no=:accountno and a.ifsccode=:ifsccode", nativeQuery = true)
	AccountEntity findByAccountIfsc(@Param("accountno") String accountno, @Param("ifsccode") String ifsccode);

	@Transactional
	@Modifying
	@Query(value = "update account set current_balance=current_balance+:amount where accountid=:accountid", nativeQuery = true)
	int updateByAccountTo(@Param("accountid") long accountid, @Param("amount") int amount);

	@Transactional
	@Modifying
	@Query(value = "update account set current_balance=current_balance-:amount where accountid=:accountid", nativeQuery = true)
	int updateByAccountFrom(@Param("accountid") long accountid, @Param("amount") int amount);

}
