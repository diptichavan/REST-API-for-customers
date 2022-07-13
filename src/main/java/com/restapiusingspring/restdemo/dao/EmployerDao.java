package com.restapiusingspring.restdemo.dao;

import com.restapiusingspring.restdemo.entities.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerDao extends JpaRepository<Employer,String> {

}
