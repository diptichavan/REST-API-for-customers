package com.restapiusingspring.restdemo.dao;

import com.restapiusingspring.restdemo.entities.Address;
import com.restapiusingspring.restdemo.entities.Customer;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.function.Function;

public interface CustomerDao extends JpaRepository<Customer, String> {

    @Query("select a from address a  where a.country =:country and custid =:custid")
    public Iterable<Address> getAddressAsPerCountry(@Param("country") String countryName, @Param("custid") String custID);

    @Query("select c from customer c where DATEDIFF(  sysdate() , c.dob ) /365 > 18")
    public Iterable<Customer> getCustomerslessthan18();

    @Query("select c from customer c join employer e where e.employer_name = :employer")
    public Iterable<Customer> getCustomerByEmployer(@Param("employer") String employerName);



}
