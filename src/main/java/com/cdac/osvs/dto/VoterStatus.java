package com.cdac.osvs.dto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class VoterStatus extends Status {


    private int voterId;

    private String fullName;

    private long adharNo;


    private String email;


    private String password;

    private String employeeId;




    private Set<Election> voterElectionList = new HashSet<>();

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

    public Set<Election> getVoterElectionList() {
        return voterElectionList;
    }

    public void setVoterElectionList(Set<Election> voterElectionList) {
        this.voterElectionList = voterElectionList;
    }



    @Override
    public String toString() {
        return "Voter{" +
                "voterId=" + voterId +
                ", fullName='" + fullName + '\'' +
                ", adharNo=" + adharNo +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", voterElectionList=" + voterElectionList +
                '}';
    }


}
