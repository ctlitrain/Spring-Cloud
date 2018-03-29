package com.centurylink.microservices.customer.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centurylink.microservices.customer.model.Account;
import com.centurylink.microservices.customer.model.Customer;
import com.centurylink.microservices.customer.model.CustomerType;
import com.centurylink.microservices.customer.intercomm.AccountClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
public class Api {
	
	@Autowired
	private AccountClient accountClient;
	
	@Autowired
	private Customer customer;
	
	protected Logger logger = Logger.getLogger(Api.class.getName());
	
	private List<Customer> customers;
	
	int counter = 0;
	
	public Api() {
		customers = new ArrayList<>();
		customers.add(new Customer(1, "12345", "Adam Kowalski", CustomerType.INDIVIDUAL));
		customers.add(new Customer(2, "12346", "Anna Malinowska", CustomerType.INDIVIDUAL));
		customers.add(new Customer(3, "12347", "Pawel Michalski", CustomerType.INDIVIDUAL));
		customers.add(new Customer(4, "12348", "Karolina Lewandowska", CustomerType.INDIVIDUAL));
	}
	
	@RequestMapping("/customers/pesel/{pesel}")
	public Customer findByPesel(@PathVariable("pesel") String pesel) {
		logger.info(String.format("Customer.findByPesel(%s)", pesel));
		return customers.stream().filter(it -> it.getPesel().equals(pesel)).findFirst().get();	
	}
	
	@RequestMapping("/customers")
	public List<Customer> findAll() {
		logger.info("Customer.findAll()");
		return customers;
	}
	
	@HystrixCommand(fallbackMethod="getFallbackCust")
	@RequestMapping("/customers/{id}")
	public Customer findById(@PathVariable("id") Integer id)  {
		logger.info(String.format("Customer.findById(%s)", id));
		
		Customer customer = customers.stream().filter(it -> it.getId().intValue()==id.intValue()).findFirst().get();
//		if(counter >4 && counter <8) {
//			logger.info("Counter->"+counter); 
//			counter ++;
//			throw new RuntimeException();
//		}else
//			counter ++;
		List<Account> accounts = new ArrayList<Account>();
		//for(int i=0;i<5;i++)
			accounts =  accountClient.getAccounts(id);
		customer.setAccounts(accounts);
		return customer;
	}
	
	
	public Customer getFallbackCust(@PathVariable("id") Integer id) {
		ArrayList<Account> defaultActList = new ArrayList<Account>(); 
		System.out.println("Inside fallback method");		
		defaultActList.add(new Account(200,201, "Customer Service Fallback Default->"+id)); 
		customer.setAccounts(defaultActList);
		return customer; 		
	}
	
}
