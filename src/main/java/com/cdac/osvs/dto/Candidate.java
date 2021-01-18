package com.cdac.osvs.dto;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "candidate")
public class Candidate {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "candidate_Id")
	private int candidateId;
	
	@Column(name = "full_Name",nullable = false)
	private String fullName;
	
	@Column(name = "email",nullable = false,unique = true)
	private String email;
	
	@Column(name = "symbol",nullable = false)
	private String symbol;
	
	@Column(name = "election_Id",nullable = false)
	private int electionId;
	
	@Column(name = "votedEarned",nullable = false)
	@ColumnDefault(value ="0")
	private int voteEarned;


	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH},mappedBy = "candidateList")
	private Set<Election> candidateElectionList = new HashSet<>();


	public Candidate() {
		super();
		
	}
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
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
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
	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", fullName=" + fullName + ", email=" + email + ", symbol="
				+ symbol + ", electionId=" + electionId + ", voteEarned=" + voteEarned + ", candidateElectionList="
				+ candidateElectionList + "]";
	}

	
	
	
}
