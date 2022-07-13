package com.restapiusingspring.restdemo.services;

import com.restapiusingspring.restdemo.entities.Address;
import com.restapiusingspring.restdemo.entities.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> getCustomers();

    public Customer getselectedCustomer(String custID);

    public String addCustomer(Customer customer);

    public Customer updateSelectedCustomer(String custID, Customer customer);

    public Iterable<Address> getAddressAsPerCountry(String countryName, String custID);

    public Iterable<Customer> getCustomerByEmployer(String employerName);

    public void deleteCustomer(String custID);

    public Iterable<Customer> getCustomerslessthan18();

}
