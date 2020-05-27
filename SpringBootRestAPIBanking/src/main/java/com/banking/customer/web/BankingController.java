package com.banking.customer.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.customer.model.AccountEntity;
import com.banking.customer.model.CustomerEntity;
import com.banking.customer.model.TransactionEntity;
import com.banking.customer.model.TransactionModel;
import com.banking.customer.model.UserAuthenticateModel;
import com.banking.customer.service.AccountService;
import com.banking.customer.service.CustomerService;
import com.banking.customer.service.TransactionService;

@RestController
@RequestMapping("/users")
public class BankingController {

	private CustomerService customer_service;
	private AccountService account_service;
	private TransactionService transaction_service;

	@Autowired
	public BankingController(CustomerService customer_service, AccountService account_service,
			TransactionService transaction_service) {
		this.customer_service = customer_service;
		this.account_service = account_service;
		this.transaction_service = transaction_service;
	}

	@GetMapping("/getData/{minVal}/{maxVal}")
	public ResponseEntity<Map<String, Object>> showEmployees(@PathVariable("minVal") int min_val,
			@PathVariable("maxVal") int max_val, HttpSession session) {
		Map<String, Object> cutomerlistData = new HashMap<String, Object>();
		if (session.getAttribute("username") != null && session.getAttribute("username").toString().equals("admin")) {
			List<CustomerEntity> custSpecificList = customer_service.getSpecificRangeData(min_val, max_val);
			if (custSpecificList != null) {
				cutomerlistData.put("customer_list", custSpecificList);
				cutomerlistData.put("customer_list_status", "Success");
			} else {
				cutomerlistData.put("customer_list", "Please select valid range for data extraction");
				cutomerlistData.put("customer_list_status", "Failure");
			}
		} else {
			cutomerlistData.put("response_data", "Please logout from the current user and logged in into admin ");
			cutomerlistData.put("response_status", "Failure");
		}

		return new ResponseEntity<Map<String, Object>>(cutomerlistData, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/acctNewCustomer")
	public ResponseEntity<Map<String, Object>> createCustomer(@Valid @RequestBody CustomerEntity customer,
			HttpSession session) {
		Map<String, Object> customerstatusdata = new HashMap<String, Object>();
		if (session.getAttribute("username") == null) {
			CustomerEntity insertstatus = customer_service.registerCustomer(customer);
			if (insertstatus.getCustomerid() > 0) {
				customerstatusdata.put("registration_status", "User is registered in successfully");
				customerstatusdata.put("customerid", insertstatus.getCustomerid());
				customerstatusdata.put("name_of_customer",
						insertstatus.getFirstName().concat(" ").concat(insertstatus.getLastName()));
				customerstatusdata.put("customer_username", insertstatus.getUserName());
			} else if (insertstatus.getCustomerid() == -1) {
				customerstatusdata.put("customer_bank_name", "Please Enter Proper Bank Name , i.e., BOI Bank");
				customerstatusdata.put("customer_data", insertstatus);
			} else if (insertstatus.getCustomerid() == -2) {
				customerstatusdata.put("customer_user_name",
						"Unable to create account no ref., User Name is likely to be present ");
				customerstatusdata.put("customer_data", insertstatus);
			}
		} else {
			customerstatusdata.put("response_data", "Please logout from the current admin user or customer user"
					+ session.getAttribute("username").toString());
			customerstatusdata.put("response_status", "Failure");
		}
		return new ResponseEntity<Map<String, Object>>(customerstatusdata, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserAuthenticateModel authenticateObject,
			HttpSession session) {
		Map<String, Object> customerstatusdata = new HashMap<String, Object>();
		if (session.getAttribute("username") == null) {
			int logstatus = customer_service.loginCustomer(authenticateObject);

			if (logstatus == 0) {
				customerstatusdata.put("warning", "Admin is trying to login , please give your proper password");
				customerstatusdata.put("status", "Login Failed");
			} else if (logstatus == 1) {
				customerstatusdata.put("login_status", "Admin is logged in successfully");
				customerstatusdata.put("login_data", "admin is logged in");
				session.setAttribute("username", "admin");
			} else if (logstatus == 2) {
				customerstatusdata.put("warning", "Customer is trying to login , please give your proper password");
				customerstatusdata.put("login_data", "customer");
			} else if (logstatus == 3) {
				customerstatusdata.put("login_status", "User is logged in successfully");
				customerstatusdata.put("login_data", "user " + authenticateObject.getUsername() + " logged in...");
				session.setAttribute("username", authenticateObject.getUsername());
			} else
				customerstatusdata.put("warning", "You are not a authorized User to Logged in...");
		} else {
			customerstatusdata.put("response_data",
					"Please logout from the current user" + session.getAttribute("username").toString());
			customerstatusdata.put("response_status", "Failure");
		}
		return new ResponseEntity<Map<String, Object>>(customerstatusdata, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/acctTransaction")
	public ResponseEntity<Map<String, Object>> createTransaction(@RequestBody TransactionModel transaction,
			HttpSession session) {
		Map<String, Object> customerresponse = new HashMap<String, Object>();
		if (session.getAttribute("username") != null && !session.getAttribute("username").toString().equals("admin")) {
			CustomerEntity customer1 = customer_service.getCustomerDetail(session.getAttribute("username").toString());
			AccountEntity account1 = account_service
					.getAccountById(customer_service.getSpecificAccountId(session.getAttribute("username").toString()));

			AccountEntity account2 = account_service.getAccountByAcctNoIfsccode(transaction.getAccountno(),
					transaction.getIfsccode());
			CustomerEntity customer2 = customer_service.getCustomerByAccountId(account2.getAccountid());

			if (!account2.equals(null)) {
				int response = account_service.doBalanceTransfer(account1, account2, transaction);
				if (response != 0) {
					TransactionEntity transactionresponse = transaction_service.addTransaction(transaction);
					if (!transactionresponse.equals(null)) {
						int updateresponse = transaction_service.updateAcctAccount(account1, transactionresponse);
						if (updateresponse != 0) {
							customerresponse.put("transaction_data", "Money is Successfully transfer from "
									+ customer1.getFirstName() + " to " + customer2.getFirstName());
							customerresponse.put("transfer_amount", transaction.getAmount());
						} else {
							customerresponse.put("transaction_data", "Your Transaction is not impacted");
							customerresponse.put("transfer_status", "Failure");
						}
					} else {
						customerresponse.put("transaction_data", "Transaction is not added");
						customerresponse.put("transfer_status", "Failure");
					}
				} else {
					customerresponse.put("transaction_data", "Sufficient balance is not there to transfer!!!");
					customerresponse.put("transfer_status", "Failure");
				}
			} else {
				customerresponse.put("transaction_data", "You have entered wrong account no or ifsc code");
				customerresponse.put("transfer_status", "Failure");
			}
		} else {
			customerresponse.put("response_data",
					"Please logout from the current admin user or login into any customer");
			customerresponse.put("response_status", "Failure");
		}
		return new ResponseEntity<Map<String, Object>>(customerresponse, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/editPersionalData")
	public ResponseEntity<Map<String, Object>> editPersionalData(@RequestBody CustomerEntity customer,
			HttpSession session) {
		Map<String, Object> customerresponse = new HashMap<String, Object>();
		
		if (session.getAttribute("username") != null && !session.getAttribute("username").toString().equals("admin")) {
			customer.setUserName(session.getAttribute("username").toString());
			Map<String, Object> responseMap = customer_service.editCustomer(customer);
			if (responseMap.get("update_result_status").toString().equals("Success")) {
				customerresponse.put("udated_response_status", "Updated Successfully");
				customerresponse.put("udated_response_data", (CustomerEntity) responseMap.get("update_result_data"));
			} else if (responseMap.get("update_result_status").toString().equals("Failure")) {
				customerresponse.put("udated_response_status", "Unauthorized column are not updated....");
				customerresponse.put("udated_response_data", customer);
			} else {
				customerresponse.put("udated_response_status", "Please Login Again...");
			}

		} else {
			customerresponse.put("response_data",
					"Please logout from the current admin user or login into any customer");
			customerresponse.put("response_status", "Failure");
		}
		return new ResponseEntity<Map<String, Object>>(customerresponse, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/viewPersionalData")
	public ResponseEntity<Map<String, Object>> viewPersionalData(HttpSession session) {
		Map<String, Object> customerresponse = new HashMap<String, Object>();
		Object obj = session.getAttribute("username");
		if (session.getAttribute("username") != null && !session.getAttribute("username").toString().equals("admin")) {
			CustomerEntity customer = customer_service.getCustomerDetail(session.getAttribute("username").toString());
			if (customer != null) {
				customerresponse.put("customer_detail_data", "Customer data is fetch Successfully");
				customerresponse.put("customer_data", customer);
			} else {
				customerresponse.put("customer_notification", "Please try to loggin after a few minute(5)");
			}
		} else {
			customerresponse.put("response_data",
					"Please logout from the current admin user or login into any customer");
			customerresponse.put("response_status", "Failure");
		}
		return new ResponseEntity<Map<String, Object>>(customerresponse, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/getAllCustomer")
	public ResponseEntity<Map<String, Object>> getAllCustomer(HttpSession session) {
		Map<String, Object> customerresponse = new HashMap<String, Object>();
		if (session.getAttribute("username") != null && session.getAttribute("username").toString().equals("admin")) {
			List<CustomerEntity> list = customer_service.getAllCustomers();
			customerresponse.put("response_data_status", "Success");
			customerresponse.put("response_data", list);
		} else {
			customerresponse.put("response_data", "Please logout from the current user and logged in into admin ");
			customerresponse.put("response_status", "Failure");
		}
		return new ResponseEntity<Map<String, Object>>(customerresponse, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/deleteCustomer/{username}")
	public ResponseEntity<Map<String, Object>> deleteCustomer(@PathVariable("username") String username,
			HttpSession session) {
		Map<String, Object> customerresponse = new HashMap<String, Object>();
		if (session.getAttribute("username") != null && session.getAttribute("username").toString().equals("admin")) {
			CustomerEntity customer = customer_service.deleteCustomer(username);
			if (customer != null) {
				String name = customer.getFirstName() + " " + customer.getLastName();
				customerresponse.put("delete_status", "Success");
				customerresponse.put("delete_data", "Customer " + name + " delete Successfully");
			} else {
				customerresponse.put("delete_status", "Failure");
				customerresponse.put("delete_data",
						"Customer with " + username + " is not exist...., Please try after sometime..");
			}
		} else {
			customerresponse.put("response_data", "Please logout from the current user and logged in into admin ");
			customerresponse.put("response_status", "Failure");
		}
		return new ResponseEntity<Map<String, Object>>(customerresponse, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/deleteALLCustomer")
	public ResponseEntity<Map<String, Object>> deleteAllCustomer(HttpSession session) {
		Map<String, Object> customerresponse = new HashMap<String, Object>();
		if (session.getAttribute("username") != null && session.getAttribute("username").toString().equals("admin")) {
			int response = customer_service.deleteAllCustomer();
			if (response != 0) {
				customerresponse.put("delete_status", "Success");
				customerresponse.put("delete_data", "All Customer are delete Successfully");
			} else {
				customerresponse.put("delete_status", "Failure");
				customerresponse.put("delete_data", "All Customer are not deleted...., Please try after sometime..");
			}
		} else {
			customerresponse.put("response_data", "Please logout from the current user and logged in into admin ");
			customerresponse.put("response_status", "Failure");
		}
		return new ResponseEntity<Map<String, Object>>(customerresponse, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/logout")
	public ResponseEntity<Map<String, Object>> logoutUser(HttpSession session) {
		Map<String, Object> customerresponse = new HashMap<String, Object>();
		if (session.getAttribute("username") != null) {
			customerresponse.put("logout_data", session.getAttribute("username").toString() + " logout successfully");
			customerresponse.put("logout_status", "Success");
			session.invalidate();
		} else {
			customerresponse.put("logout_data", "Please Login first (User is not available)");
			customerresponse.put("logout_status", "Failure");
		}
		return new ResponseEntity<Map<String, Object>>(customerresponse, new HttpHeaders(), HttpStatus.OK);
	}
}