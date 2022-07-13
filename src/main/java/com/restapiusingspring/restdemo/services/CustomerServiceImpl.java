package com.restapiusingspring.restdemo.services;

import com.restapiusingspring.restdemo.controller.MyController;
import com.restapiusingspring.restdemo.dao.AddressDao;
import com.restapiusingspring.restdemo.dao.CustomerDao;
import com.restapiusingspring.restdemo.dao.EmployerDao;
import com.restapiusingspring.restdemo.entities.Address;
import com.restapiusingspring.restdemo.entities.Customer;
import com.restapiusingspring.restdemo.exception.EmptyInputException;
import com.restapiusingspring.restdemo.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerDao custDao;

    @Override
    public List<Customer> getCustomers() {
        return custDao.findAll();
    }

    @Override
    public Customer getselectedCustomer(String custID) {
        return custDao.findById(custID)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Not found for this ID"));

    }

    @Override
    public String addCustomer(Customer customer) {
        if (customer.getCustName().isEmpty() || customer.getCustName().length() == 0)
            throw new EmptyInputException("Request Body fields are Empty");
        custDao.save(customer);
        return customer.getCustID();
    }

    @Override
    public Customer updateSelectedCustomer(String custID, Customer customer) {
        custDao.findById(custID)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Not found for this ID"));
        Customer custToBeUpdated = custDao.save(customer);
        return custToBeUpdated;
    }

    @Override
    public Iterable<Address> getAddressAsPerCountry(String countryName, String custID) {
        return custDao.getAddressAsPerCountry(countryName, custID);
    }

    @Override
    public Iterable<Customer> getCustomerByEmployer(String employerName) {
        return custDao.getCustomerByEmployer(employerName);
    }


    @Override
    public void deleteCustomer(String custID) {
        custDao.deleteById(custID);
        logger.info("Customer of ID" + custID + "Deleted" );
    }

    @Override

    public Iterable<Customer> getCustomerslessthan18() {
        return custDao.getCustomerslessthan18();
    }

}

