package com.restapiusingspring.restdemo.controller;

import com.restapiusingspring.restdemo.entities.Address;
import com.restapiusingspring.restdemo.entities.Customer;
import com.restapiusingspring.restdemo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Validated
@RestController
public class MyController {

    Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    private CustomerService custservice;

    //Get home page
    @RequestMapping("/")
    public String home() {
        logger.info("Rest API Application Stared");
        return "Welcome to Customer API Demo";
    }

    //Get all Customers
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> customers = this.custservice.getCustomers();
        if (customers.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Getting List of customer from DB");
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    //Get Specific Customer by CustID
    @GetMapping("/customers/{custID}")
    public ResponseEntity<Customer> getSelectedCustomer(@PathVariable String custID) {
        Customer customerbyid = this.custservice.getselectedCustomer(custID);
        if (customerbyid == null) {
            logger.debug("Customer ID is blank");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Customer retrived as per CustID" + custID);
        return new ResponseEntity<Customer>(customerbyid, HttpStatus.OK);

    }

    //Get Customers whose age is less than 18 years
    @GetMapping("/customers/lessthan18")
    public Iterable<Customer> getCustomerslessthan18() {
        logger.info("Customers who are less than 18");
        return this.custservice.getCustomerslessthan18();
    }

    //Get Address of customer as per country
    @GetMapping("/customers/{customerId}/addresses")
    public Iterable<Address> getAddressAsPerCountry(@RequestParam(value = "countryName") String countryName, @PathVariable String customerId) {
        logger.info("Customers who are living in country" + countryName);
        return this.custservice.getAddressAsPerCountry(countryName, customerId);
    }

    //Get Customer by Employers
    @GetMapping("/customers/getCustomerByEmployer")
    public Iterable<Customer> getCustomerByEmployer(@RequestParam(value = "employerName") String employerName) {
        logger.info("Customers whos employer is " + employerName);
        return this.custservice.getCustomerByEmployer(employerName);
    }

    //Add new Customer
    @PostMapping(value = "/customers", consumes = "application/json")
    public ResponseEntity<String> addNewCustomer(@Valid @RequestBody Customer customer) {
        String custId;
        custId = this.custservice.addCustomer(customer);
        logger.info("Customer Entry Created");
        return new ResponseEntity<String>(custId, HttpStatus.CREATED);
    }

    //Update Existing Customer
    @PutMapping("/customers/{custID}")
    public ResponseEntity<Customer> updateSelectedCustomer(@PathVariable String custID, @RequestBody Customer customer) {
        Customer customer1;
        try {
            customer1 = this.custservice.updateSelectedCustomer(custID, customer);
            logger.info("Customer Updated");
            return new ResponseEntity<Customer>(customer1, HttpStatus.CREATED);
         } catch (Exception e) {
            logger.error("Customer Not Found");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    //Delete Customer
    @DeleteMapping("/customers/{custID}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable String custID) {
        try {
            this.custservice.deleteCustomer(custID);
            logger.info("Customer Deleted");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Customer Not Found");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
