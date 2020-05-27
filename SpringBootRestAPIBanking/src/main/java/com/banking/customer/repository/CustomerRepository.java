package com.banking.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.banking.customer.model.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
	@Query(value = "SELECT c.user_name FROM customer c WHERE c.user_name=:username", nativeQuery = true)
	List<String> findByUserName(@Param("username") String userName);

	@Query(value = "SELECT c.user_name FROM customer c WHERE c.user_name like %:likeusername%", nativeQuery = true)
	List<String> findByLikeUserName(@Param("likeusername") String likeUsername);

	@Query(value = "SELECT * FROM customer c WHERE c.user_name=:username", nativeQuery = true)
	List<CustomerEntity> findByUser(@Param("username") String username);

	@Query(value = "SELECT * from (select rownum() row_num, * from customer order by customerid) where row_num>=:min_val and row_num<:max_val", nativeQuery = true)
	List<CustomerEntity> findBySpecificUser(@Param("min_val") int min_val, @Param("max_val") int max_val);

	@Query(value = "SELECT * from customer c WHERE c.account_accountid=:accountid", nativeQuery = true)
	List<CustomerEntity> findUserByAccountId(@Param("accountid") long accountid);
	
	@Transactional
	@Modifying
	@Query(value = "delete from customer c where c.user_name=:username", nativeQuery = true)
	int deleteCustomerByUserName(@Param("username") String username);
	
	@Transactional
	@Modifying
	@Query(value = "delete from customer", nativeQuery = true)
	int deleteALLCustomer();

}