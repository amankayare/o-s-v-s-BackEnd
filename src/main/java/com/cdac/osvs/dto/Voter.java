package com.cdac.osvs.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "voters")
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voter_Id")
    private int voterId;

    @Column(name = "voter_Name", nullable = false)
    private String fullName;

    @Column(name = "Adhar_Card_No", nullable = false)
    private long adharNo;


    @Column(name = "voter_Email", nullable = false, unique = true)
    private String email;


    @Column(name = "password", nullable = false)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String EmployeeId;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, mappedBy = "voterList")
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

    public Set<Election> getVoterElectionList() {
        return voterElectionList;
    }

    public void setVoterElectionList(Set<Election> voterElectionList) {
        this.voterElectionList = voterElectionList;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Voter [voterId=" + voterId + ", fullName=" + fullName + ", adharNo=" + adharNo + ", voted="
                + ", email=" + email + ", electionId=";
    }

}
