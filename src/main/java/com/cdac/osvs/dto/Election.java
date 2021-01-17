package com.cdac.osvs.dto;

import org.hibernate.annotations.ColumnDefault;

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

	public int getElectionId() {
		return electionId;
	}

	public void setElectionId(int electionId) {
		this.electionId = electionId;
	}

	private String name;
	
	@Column(name = "start_Date")
	private String startDate;
	
	@Column(name = "end_Date")
	private String endDate;
	
	@Column(name = "result_Date")
	private String resultDate;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="election_voter")
	private Set<Voter> voterList = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="election_candidate")
	private Set<Candidate> candidateList = new HashSet<>();

	@Column(name = "organization_id")
	private String organization_id;

	@Column(name = "cin" )
	@ColumnDefault(value="gjgjhjg")
	private String cin;

	public Election() {
		super();
		
	}

	

}