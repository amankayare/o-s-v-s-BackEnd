package com.cdac.osvs.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class ElectionStatus extends Status{


        private int electionId;


        private String electionName;

        private String startDate;

        private String endDate;

        private String resultDate;


        private Set<Voter> voterList = new HashSet<>();


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



        private String organization_id;



        private String cin;


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

        public String getOrganization_id() {
            return organization_id;
        }

        public void setOrganization_id(String organization_id) {
            this.organization_id = organization_id;
        }

        public String getCin() {
            return cin;
        }

        public void setCin(String cin) {
            this.cin = cin;
        }

}
