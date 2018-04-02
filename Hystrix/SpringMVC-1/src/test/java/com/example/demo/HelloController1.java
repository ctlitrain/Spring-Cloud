package com.example.demo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController1 {

	@Autowired
	RestTemplate restTemplate;
	
   @RequestMapping("/")
   public String index() {
      return "index";
   }

   @PostMapping("/hello")
   public String sayHello(@RequestParam("name") String name, ModelAndView  modelAndView) {
	  // restTemplate.
	   List<Account> accounts = (List<Account>) restTemplate.getForObject("http://localhost:2222/accounts", Account.class);
	   modelAndView.addObject("accounts", accounts);
       return "hello";
      
   }   

}