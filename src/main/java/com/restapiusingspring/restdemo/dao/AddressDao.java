package com.restapiusingspring.restdemo.dao;

import com.restapiusingspring.restdemo.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDao extends JpaRepository<Address,Integer>{
}
