package com.cdac.osvs.dto;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.ColumnDefault;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_Id")
    private int candidateId;

    @Column(name = "full_Name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "adharCardNo", nullable = false, unique = true)
    private long adharNo;

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "employee_Id", nullable = false)
    private int employeeId;

    @Column(name = "election_Id", nullable = false)
    private int electionId;


    @Column(name = "votedEarned", nullable = false)
    @ColumnDefault(value = "0")
    private int voteEarned;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, mappedBy = "candidateList")
    private Set<Election> candidateElectionList = new HashSet<>();

   /*@ManyToMany(fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
   @JoinTable(
           name="candidate_election",
           joinColumns = @JoinColumn(name ="candidate_Id"),
           inverseJoinColumns = @JoinColumn(name="election_Id")
   )
   private List<Election> listOfElection;
*/

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

    public Set<Election> getCandidateElectionList() {
        return candidateElectionList;
    }

    public void setCandidateElectionList(Set<Election> candidateElectionList) {
        this.candidateElectionList = candidateElectionList;
    }
}
