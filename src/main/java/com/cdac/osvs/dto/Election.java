package com.cdac.osvs.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "election")
public class Election {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "election_id")
	private int electionId;


	private String electionName;
	
	@Column(name = "start_Date",nullable = false)
	private String startDate;
	
	@Column(name = "end_Date",nullable = false)
	private String endDate;
	
	@Column(name = "result_Date",nullable = false)
	private String resultDate;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="election_voter")
	private Set<Voter> voterList = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="election_candidate")
	private Set<Candidate> candidateList = new HashSet<>();

	@Column(name = "organization_id",nullable = false)
	private String organization_id;

	@Column(name = "cin")
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

	@Override
	public String toString() {
		return "Election [electionId=" + electionId + ", name=" + electionName + ", startDate=" + startDate + ", endDate="
				+ endDate + ", resultDate=" + resultDate + ", voterList=" + voterList + ", candidateList="
				+ candidateList + ", organization_id=" + organization_id + ", cin=" + cin + "]";
	}

	

}