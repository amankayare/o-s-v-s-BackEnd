package com.cdac.osvs.dto;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class CandidateStatus extends Status{

    private int candidateId;

    private String fullName;

    private String email;

    private long adharNo;

    private String symbol;

    private int employeeId;

    private int electionId;

    private int voteEarned;



    private Set<Election> listOfElection;

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getAdharNo() {
        return adharNo;
    }

    public void setAdharNo(long adharNo) {
        this.adharNo = adharNo;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getElectionId() {
        return electionId;
    }

    public void setElectionId(int electionId) {
        this.electionId = electionId;
    }

    public int getVoteEarned() {
        return voteEarned;
    }

    public void setVoteEarned(int voteEarned) {
        this.voteEarned = voteEarned;
    }

    public Set<Election> getListOfElection() {
        return listOfElection;
    }

    public void setListOfElection(Set<Election> listOfElection) {
        this.listOfElection = listOfElection;
    }
}
