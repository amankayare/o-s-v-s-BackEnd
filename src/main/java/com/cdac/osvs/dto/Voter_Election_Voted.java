package com.cdac.osvs.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Voter_Election_IsVoted")
public class Voter_Election_Voted {
	
	@Id
	@Column(name = "Voter_Election_IsVotedId")
	private int VoterElectionIsVotedId;
	
	@Column(nullable = false)
	private int electionId;
	
	@Column(nullable = false)
	private int voterId;
	
	@Column(nullable = false)
	private int isVoted;

	public int getVoterElectionIsVotedId() {
		return VoterElectionIsVotedId;
	}

	public void setVoterElectionIsVotedId(int voterElectionIsVotedId) {
		VoterElectionIsVotedId = voterElectionIsVotedId;
	}

	public int getElectionId() {
		return electionId;
	}

	public void setElectionId(int electionId) {
		this.electionId = electionId;
	}

	public int getVoterId() {
		return voterId;
	}

	public void setVoterId(int voterId) {
		this.voterId = voterId;
	}

	public int getIsVoted() {
		return isVoted;
	}

	public void setIsVoted(int isVoted) {
		this.isVoted = isVoted;
	}

	@Override
	public String toString() {
		return "Voter_Election_Voted [VoterElectionIsVotedId=" + VoterElectionIsVotedId + ", electionId=" + electionId
				+ ", voterId=" + voterId + ", isVoted=" + isVoted + "]";
	}
  
}
