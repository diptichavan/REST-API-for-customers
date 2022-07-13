package com.restapiusingspring.restdemo.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity(name = "employer")
@Table(name = "employer")
public class Employer {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String employerId;
    private String employer_name;

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public String getEmployerName() {
        return employer_name;
    }

    public void setEmployerName(String employerName) {
        this.employer_name = employerName;
    }

    public Employer(String employerId, String employerName) {
        super();
        this.employerId = employerId;
        this.employer_name = employerName;
    }

    public Employer() {
        super();
    }
}