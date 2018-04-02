package com.ctl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
@EnableHystrixDashboard
@SpringBootApplication
public class HystrixDashboard1Application {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboard1Application.class, args);
	}
}
