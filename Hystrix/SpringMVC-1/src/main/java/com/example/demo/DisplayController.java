package com.example.demo;


import java.util.logging.Logger;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@Controller
public class DisplayController {

	protected Logger logger = Logger.getLogger(DisplayController.class.getName());
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
		}

	
   @RequestMapping("/index")
   public String index() {
      return "index";
   }

  @RequestMapping("/showAllAccounts")
   public String showAllAccounts(RestTemplate restTemplate, Model  model) {	   
		ResponseEntity<Account[]> responseEntity = restTemplate.getForEntity("http://localhost:2222/accounts", Account[].class);
	 	Object[] accounts = responseEntity.getBody();		
		model.addAttribute("accounts",accounts);
       return "account";
      }   
  
  @RequestMapping("/showAccount")
  public String showAccount(@RequestParam("id") String id,RestTemplate restTemplate, Model  model) {
	  	String Uri = "http://localhost:2222/accounts/";
	  	logger.info(" ID ->>"+id+" and URI->>"+Uri+id); 
		ResponseEntity<Account> responseEntity = restTemplate.getForEntity(Uri+id, Account.class);
		Object account = responseEntity.getBody();		
		model.addAttribute("account",account);
      return "singleAccount";
     }  
  
  @RequestMapping("/showAllCustomer")
  public String showAllCustomer(RestTemplate restTemplate, Model  model) {	   
		ResponseEntity<Customer[]> responseEntity = restTemplate.getForEntity("http://localhost:3333/customers", Customer[].class);
		Object[] customers = responseEntity.getBody();		
		model.addAttribute("customers",customers);
      return "customer";
     }
  
  @RequestMapping("/showCustomer")
  public String showCustomer(@RequestParam("id") String id,RestTemplate restTemplate, Model  model) {
	  String Uri = "http://localhost:3333/customers/";
	  	logger.info(" ID ->>"+id+" and URI->>"+Uri+id);
		ResponseEntity<Customer> responseEntity = restTemplate.getForEntity(Uri+id, Customer.class);
		Object customer = responseEntity.getBody();		
		model.addAttribute("customer",customer);
      return "singleCustomer";
     }
}