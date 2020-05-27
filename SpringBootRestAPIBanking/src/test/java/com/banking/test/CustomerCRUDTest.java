package com.banking.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.banking.customer.BankingApplication;
import com.banking.customer.model.AccountEntity;
import com.banking.customer.model.CustomerEntity;
import com.banking.customer.model.UserAuthenticateModel;
import com.banking.customer.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BankingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerCRUDTest {

	@Autowired
	private CustomerService customer_service;

	@Test
	public void contextLoads() {

	}

	@Test
	public void AtestCreateCustomer() {
		CustomerEntity customer = new CustomerEntity("Praja", "Samal", "pkaja6789", "MALE", "12-03-2020", 23,
				"kumarraja1234raja@gmail.com", "9114764472", "2345645678347890", "Panchanan Samal", "Odisha",
				"Jagatsiginghpur", "754141", "Madhuban", "100 Millions", "ACTIVE",
				new AccountEntity("SBI Bank", "individual"));
		CustomerEntity customerEntity = customer_service.registerCustomer(customer);
		assertNotNull(customerEntity);
	}

	@Test
	public void BtestLoginCustomer() {
		UserAuthenticateModel authenticate = new UserAuthenticateModel("pkaja6789", "pkaja67891234@");
		int logstatus = customer_service.loginCustomer(authenticate);
		assertEquals(new Integer(3), new Integer(logstatus));
	}

	@Test
	public void CtestGetCustomer() {
		CustomerEntity customer = customer_service.getCustomerDetail("pkaja6789");
		assertNotEquals(null, customer);
	}

	@Test
	public void DtestUpdateCustomer() {
		CustomerEntity customer = new CustomerEntity("Praja", "Samal", "pkaja6789" , "praja1234raja@gmail.com", "9114764472",
				"Karnataka", "Banglore", "864141", "Sarjapur", "Wipro Campus");
		Map<String, Object> result = customer_service.editCustomer(customer);

		Map<String, Object> objectMap = new HashMap<String, Object>();
		objectMap.put("update_result_status", "Failure");
		objectMap.put("update_result_data", null);
		Map<String, Object> objectMap1 = new HashMap<String, Object>();
		objectMap1.put("update_result_status", "Error");
		objectMap1.put("update_result_data", null);

		assertNotEquals(result, objectMap);
		assertNotEquals(result, objectMap1);
	}

	@Test
	public void GtestGetSpecificCustomer() {
		List<CustomerEntity> result = customer_service.getSpecificRangeData(1, 2);
		assertNotNull(result);
	}

	@Test
	public void HtestGetAllCustomer() {
		List<CustomerEntity> result = customer_service.getAllCustomers();
		assertNotEquals(new Integer(0), new Integer(result.size()));
	}

	@Test
	public void ItestDeleteAllCustomer() {
		long result = customer_service.deleteAllCustomer();
		assertNotEquals(new Long(0), new Long(result));
	}
}
