package com.cdac.osvs.dto;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class VoterLoginStatus extends Status {

    //if you want to return all the details
    //then we might do this:
    //private Customer customer;

    private int voterId;

    private String fullName;

    private long adharNo;

    private String email;

    private String password;

    private String employeeId;

    public int getVoterId() {
        return voterId;
    }

    public void setVoterId(int voterId) {
        this.voterId = voterId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getAdharNo() {
        return adharNo;
    }

    public void setAdharNo(long adharNo) {
        this.adharNo = adharNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }




}