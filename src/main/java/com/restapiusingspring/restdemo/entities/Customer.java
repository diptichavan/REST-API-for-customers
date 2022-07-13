package com.restapiusingspring.restdemo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.restapiusingspring.restdemo.generator.CustIDGenerator;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Entity(name = "customer")
@Table(name = "customer")
@DynamicInsert
public class Customer {
    @Id
    @Column(name = "custid")
    @GenericGenerator(name = "idgenerator", strategy = "com.restapiusingspring.restdemo.generator.CustIDGenerator")
    @GeneratedValue(generator = "idgenerator")
    private String custID;

    @NotNull(message = "Name should not be null")
    @Column(name = "cust_name")
    private String custName;

    @NotNull(message = "Date of Birth should not be empty")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "dob")
    private Date dob;

    @NotBlank
    @Column(name = "is_employed")
    private boolean isEmployed;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "custid")
    private List<Address> addresses;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employerId")
    private Employer employerDetails;


    public Customer(String custID, String custName, Date dob, Boolean isEmployed, List<Address> addresses, Employer employerDetails) {
        super();
        this.custID = custID;
        this.custName = custName;
        this.dob = dob;
        this.isEmployed = isEmployed;
        this.addresses = addresses;

    }

    public Customer() {
        super();
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Employer getEmployerDetails() {
        return employerDetails;
    }

    public void setEmployerDetails(Employer employerDetails) {
        this.employerDetails = employerDetails;
    }

    public Boolean getEmployed() {
        return isEmployed;
    }

    public void setEmployed(Boolean employed) {
        isEmployed = employed;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custID='" + custID + '\'' +
                ", custName='" + custName + '\'' +
                ", dob=" + dob +
                ", isEmployed=" + isEmployed +
                ", addresses=" + addresses +

                '}';
    }
}
