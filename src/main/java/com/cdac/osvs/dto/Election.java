package com.cdac.osvs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.ColumnDefault;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "election")
public class Election {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "election_id")
    private int electionId;


    private String electionName;

    @Column(name = "start_Date")
    private String startDate;

    @Column(name = "end_Date")
    private String endDate;

    @Column(name = "result_Date")
    private String resultDate;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "election_voter")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Voter> voterList = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "election_candidate")
    private Set<Candidate> candidateList = new HashSet<>();

    /*
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "election_voter")
    private Set<Voter> voterList = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "election_candidate")
    private Set<Candidate> candidateList = new HashSet<>();


    @ManyToMany(fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(
            name="candidate_election",
            joinColumns = @JoinColumn(name ="election_Id"),
            inverseJoinColumns = @JoinColumn(name="candidate_Id")
    )
    private List<Candidate> candidateList;
*/



    @Column(name = "cin")
    @ColumnDefault(value = "0")
    private String cin;

    public Election() {
        super();

    }

    public int getElectionId() {
        return electionId;
    }

    public void setElectionId(int electionId) {
        this.electionId = electionId;
    }

    public String getElectionName() {
        return electionName;
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getResultDate() {
        return resultDate;
    }

    public void setResultDate(String resultDate) {
        this.resultDate = resultDate;
    }

    public Set<Voter> getVoterList() {
        return voterList;
    }

    public void setVoterList(Set<Voter> voterList) {
        this.voterList = voterList;
    }

    public Set<Candidate> getCandidateList() {
        return candidateList;
    }

    public void setCandidateList(Set<Candidate> candidateList) {
        this.candidateList = candidateList;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
}