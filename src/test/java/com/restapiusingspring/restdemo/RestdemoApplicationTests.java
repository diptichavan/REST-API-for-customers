package com.restapiusingspring.restdemo;

import com.restapiusingspring.restdemo.dao.CustomerDao;
import com.restapiusingspring.restdemo.entities.Customer;
import com.restapiusingspring.restdemo.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestdemoApplicationTests {

	@Autowired
	private CustomerDao custdao;

	private CustomerService custservice;


	@Test
	void isCustomerExist(){

		Customer cust = new Customer( );
	}

	void getAllCustomer(){

		custservice.getCustomers();

	}


}
