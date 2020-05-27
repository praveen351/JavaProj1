package com.banking.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.banking.customer.model.TransactionEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
	@Transactional
	@Modifying
	@Query(value = "update transaction set accountid=:accountid where transactionid=:transactionid", nativeQuery = true)
	int updateAccountIdTransaction(@Param("accountid") long accountid, @Param("transactionid") long transactionid);
}
