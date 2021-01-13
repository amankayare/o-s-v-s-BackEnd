package com.cdac.osvs.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "candidate")
public class Candidate {
	@Id
	@GeneratedValue
	@Column(name = "candidate_Id")
	private int candidateId;
	@Column(name = "full_Name")
	private String fullName;
	
	private String email;
	private String symbol;
	@Column(name = "election_Id")
	private int electionId;
	private int voteEarned;
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
	@Override
	public String toString() {
		return  candidateId + " " + fullName + " " + email + " "
				+ symbol + " " + electionId + " " + voteEarned ;
	}
	
	
	
}
