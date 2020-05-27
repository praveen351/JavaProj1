package com.banking.customer.service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.customer.model.CustomerEntity;
import com.banking.customer.model.UserAuthenticateModel;
import com.banking.customer.repository.CustomerRepository;

@Service
public class CustomerService {
	private CustomerRepository customer_repo;
	private List<String> bankNameList;
	private List<String> branchNameList;
	private List<String> custfieldname;

	@Autowired
	public CustomerService(CustomerRepository customer_repo) {
		this.customer_repo = customer_repo;
		this.bankNameList = new ArrayList<>(
				Arrays.asList("SBI", "ICIC", "KOTAK MAHINDRA", "HDFC", "AXIS", "BOB", "BOI"));
		this.branchNameList = new ArrayList<>(
				Arrays.asList("Delhi SBI Branch", "Ahmdabad ICIC Branch", "Odisha KOTAK Branch", "Banglore HDFC Branch",
						"Mumbai AXIS Branch", "Ajamgad BOB Branch", "Pune BOI Branch"));
		this.custfieldname = new ArrayList<>(Arrays.asList("firstName", "lastName", "email", "mobileno", "state",
				"district", "pincode", "address", "landmark", "status"));
	}

	public CustomerEntity registerCustomer(CustomerEntity customer) {
		SimpleDateFormat ft = new SimpleDateFormat("dd-mm-yyyy");
		List<String> customer_username = customer_repo.findByUserName(customer.getUserName());
		String ifsccode = "";
		String accountno = "";
		String branchname = "";
		String branchcode = "";

		// Check for unique Username
		if (customer_username.size() != 0)
			return null;
		// return customer;
		// Bank Name is Valid or Not
		if (!(bankNameList.indexOf(customer.getAccount().getBankname().trim().split(" ")[0].toUpperCase()) != -1
				&& customer.getAccount().getBankname().trim().split(" ").length == 2
				&& customer.getAccount().getBankname().trim().split(" ")[1].toLowerCase().equals("bank"))) {
			customer.setCustomerid(-1);
			return null;
			// return customer;
		}
		// IFSC Code Creation
		for (char c : customer.getAccount().getBankname().trim().substring(0, 3).toCharArray())
			ifsccode = ifsccode.concat(Integer.toString((int) c));
		accountno = ifsccode.substring(ifsccode.length() - 6, ifsccode.length());
		ifsccode = customer.getAccount().getBankname().split(" ")[0].toUpperCase()
				.concat(ifsccode.substring(ifsccode.length() - 5, ifsccode.length()));

		// Account No Creation
		String acctTemp = "";
		for (char c : customer.getUserName().trim().toCharArray())
			acctTemp = acctTemp.concat(Integer.toString((int) c));

		if (customer.getUserName().length() > 5) {
			int i = 0;
			for (i = 0; i + 4 < customer.getUserName().length(); i++) {
				String acctTemp1 = "";
				String substring_value = customer.getUserName().trim().substring(i, i + 5);
				List<String> cust_list = customer_repo.findByLikeUserName(substring_value);
				if (cust_list.size() == 0) {
					for (char c : substring_value.toCharArray())
						acctTemp1 = acctTemp1.concat(Integer.toString((int) c));
					if (acctTemp1.length() > 10)
						accountno = accountno.concat(acctTemp1.substring(acctTemp1.length() - 10, acctTemp1.length()));
					else
						accountno = accountno.concat(acctTemp1);
					break;
				}
			}
			if (i + 4 == customer.getUserName().length()) {
				customer.setCustomerid(-2);
				return null;
				//return customer;
			}
		} else
			accountno = accountno.concat(acctTemp);
		// Branch Name Creation
		branchname = branchNameList
				.get(bankNameList.indexOf(customer.getAccount().getBankname().trim().split(" ")[0].toUpperCase()));
		// Branch Code Creation
		for (char c : branchname.split(" ")[0].toCharArray())
			branchcode = branchcode.concat(Integer.toString((int) c));
		branchcode = branchcode.substring(0, 4);

		customer.getAccount().setAccountno(accountno);
		customer.getAccount().setIfsccode(ifsccode);
		customer.getAccount().setBranchname(branchname);
		customer.getAccount().setBranchcode(branchcode);
		customer.getAccount().setOpendate(ft.format(new Date()));
		customer.getAccount().setCurrentbalance(0);
		customer.setStatus("ACTIVE");
		customer.getAccount().setStatus("ACTIVE");

		CustomerEntity entity = customer_repo.save(customer);
		return entity;
	}

	public int loginCustomer(UserAuthenticateModel userAuthenticate) {
		if (userAuthenticate.getUsername().trim().toLowerCase().equals("admin")) {
			if (userAuthenticate.getPassword().trim().toLowerCase().equals("adminpass1234@"))
				return 1;
			return 0;
		}
		List<String> loginresult = customer_repo.findByUserName(userAuthenticate.getUsername());
		if (loginresult.size() != 0) {
			if (userAuthenticate.getPassword().trim().toLowerCase()
					.equals(userAuthenticate.getUsername().concat("1234@")))
				return 3;
			return 2;
		}
		return -1;
	}

	public CustomerEntity getCustomerDetail(String user_name) {
		CustomerEntity responseEntity = customer_repo.findByUser(user_name).get(0);
		if (responseEntity != null)
			return responseEntity;
		else
			return null;
	}

	public Map<String, Object> editCustomer(CustomerEntity customer) {
		Map<String, Object> objectMap = new HashMap<String, Object>();
		CustomerEntity editresult = customer_repo.findByUser(customer.getUserName()).get(0);
		customer.setUserName(null);
		try {
			Class<CustomerEntity> custclass = (Class<CustomerEntity>) Class
					.forName("com.banking.customer.model.CustomerEntity");
			Field[] fieldArray = custclass.getDeclaredFields();
			for (Field field : fieldArray) {
				Field privateField = custclass.getDeclaredField(field.getName());
				privateField.setAccessible(true);
				Object value = privateField.get(customer);
				if (custfieldname.indexOf(field.getName()) != -1) {
					if (value != null)
						privateField.set(editresult, value);
				} else if (value != null && Integer.parseInt((value.toString())) != 0) {
					objectMap.put("update_result_status", "Failure");
					objectMap.put("update_result_data", null);
					return objectMap;
				}

			}
			customer_repo.save(editresult);
			objectMap.put("update_result_status", "Success");
			objectMap.put("update_result_data", editresult);
		} catch (Exception e) {
			objectMap.put("update_result_status", "Error");
			objectMap.put("update_result_data", null);
		}
		return objectMap;
	}

	public List<CustomerEntity> getSpecificRangeData(int min_val, int max_val) {
		List<CustomerEntity> specific_list = customer_repo.findBySpecificUser(min_val, max_val);
		for (int i = 0; i < specific_list.size(); i++)
			specific_list.get(i).setAccount(null);
		if (specific_list.size() != 0)
			return specific_list;
		else
			return null;
	}

	public long getSpecificAccountId(String username) {
		List<CustomerEntity> customerList = customer_repo.findByUser(username);
		if (customerList.size() != 0)
			return customerList.get(0).getAccount().getAccountid();
		else
			return 0;
	}

	public CustomerEntity getCustomerByAccountId(long id) {
		List<CustomerEntity> customerList = customer_repo.findUserByAccountId(id);
		if (customerList.size() != 0)
			return customerList.get(0);
		else
			return null;
	}

	public List<CustomerEntity> getAllCustomers() {
		List<CustomerEntity> customerList = customer_repo.findAll();

		if (customerList.size() > 0) {
			for (int i = 0; i < customerList.size(); i++)
				customerList.get(i).setAccount(null);
			return customerList;
		} else {
			return new ArrayList<CustomerEntity>();
		}
	}

	public CustomerEntity deleteCustomer(String username) {
		List<CustomerEntity> customerList = customer_repo.findByUser(username);
		if (customerList.size() != 0) {
			int response = customer_repo.deleteCustomerByUserName(username);
			if (response != 0)
				return customerList.get(0);
			else
				return null;
		} else {
			return null;
		}
	}

	public int deleteAllCustomer() {
		int response = customer_repo.deleteALLCustomer();
		if (response != 0)
			return 1;
		else
			return 0;
	}

}