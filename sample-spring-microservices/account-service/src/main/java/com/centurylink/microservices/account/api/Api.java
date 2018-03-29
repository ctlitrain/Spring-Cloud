package com.centurylink.microservices.account.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.centurylink.microservices.account.model.Account;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class Api {

	private List<Account> accounts;
	
	protected Logger logger = Logger.getLogger(Api.class.getName());
	int counter = 0;
	
	public Api() {
		accounts = new ArrayList<>();
		accounts.add(new Account(1, 1, "111111"));  
		accounts.add(new Account(2, 2, "222222")); 
		accounts.add(new Account(3, 3, "333333"));
		accounts.add(new Account(4, 4, "444444"));
		accounts.add(new Account(5, 1, "555555"));
		accounts.add(new Account(6, 2, "666666"));
		accounts.add(new Account(7, 2, "777777"));
	}
	
	@HystrixCommand(fallbackMethod="getDefaultAccountData")
	@RequestMapping("/accounts/{number}")
	public Account findByNumber(@PathVariable("number") String number) {
		logger.info(String.format("Account.findByNumber(%s)", number));
//		if(counter > 4 && counter <8) {
//			logger.info("counter->"+counter);
//			counter++;			
//			throw new RuntimeException();
//		}
//		else 
//			counter++;			
		return accounts.stream().filter(it -> it.getNumber().equals(number)).findFirst().get();
	}
	
	public Account getDefaultAccountData(@PathVariable("number") String number) {		
		return new Account(100, 101, "Default->"+number);
	}
	
	
	@HystrixCommand(fallbackMethod="getDefaultCustAccountData")
	@RequestMapping("/accounts/customer/{customer}")
	public List<Account> findByCustomer(@PathVariable("customer") Integer customerId) {
		logger.info(String.format("Account.findByCustomer(%s)", customerId));
		System.out.println("Inside getCustomer method");
		if(counter > 10 && counter <14) {
			logger.info("counter->"+counter);
			counter++;			
			throw new RuntimeException();
		}
		else 
			counter++;	
			
		return accounts.stream().filter(it -> it.getCustomerId().intValue()==customerId.intValue()).collect(Collectors.toList());
	}
	
	public List<Account> getDefaultCustAccountData(@PathVariable("customer") Integer customerId) {
		ArrayList<Account> defaultActList = new ArrayList<Account>(); 
		System.out.println("Inside fallback method");
		defaultActList.add(new Account(100, 101, "Default->"+customerId));
		return defaultActList;
	}
	
	@RequestMapping("/accounts")
	public List<Account> findAll() {
		logger.info("Account.findAll()");		
		return accounts;
	}
	
	
}
