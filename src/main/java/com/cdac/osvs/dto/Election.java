package com.cdac.osvs.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name = "election")
public class Election {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "election_id")
	private int electionId;
	
	private String name;
	
	@Column(name = "start_Date",nullable = false)
	private String startDate;
	
	@Column(name = "end_Date",nullable = false)
	private String endDate;
	
	@Column(name = "result_Date",nullable = false)
	private String resultDate;
	
	
	public Election() {
		super();
		
	}
	public int getElectionId() {
		return electionId;
	}
	public void setElectionId(int electionId) {
		this.electionId = electionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
	
	

}