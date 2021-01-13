package com.cdac.osvs.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="voters")
public class Voter {
	@Id
	@GeneratedValue
	@Column(name="voter_Id")
	private int voterId;
	@Column(name="voter_Name")
	private String fullName;
	@Column(name="Adhar_Card_No")
	private long adharNo;
	@Column(name="voted")
	private int voted;
	@Column(name="voter_Email")
	private String email;
	@Column(name="election_Id")
	private int electionId;
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
	public int getVoted() {
		return voted;
	}
	public void setVoted(int voted) {
		this.voted = voted;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getElectionId() {
		return electionId;
	}
	public void setElectionId(int electionId) {
		this.electionId = electionId;
	}
	@Override
	public String toString() {
		return "Voter [voterId=" + voterId + ", fullName=" + fullName + ", adharNo=" + adharNo + ", voted=" + voted
				+ ", email=" + email + ", electionId=" + electionId + "]";
	}
	
}
