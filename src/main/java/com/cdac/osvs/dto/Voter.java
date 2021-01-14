package com.cdac.osvs.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="voters")
public class Voter {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="voter_Id")
	private int voterId;
	
	@Column(name="voter_Name",nullable = false)
	private String fullName;
	
	@Column(name="Adhar_Card_No",nullable = false)
	private long adharNo;
	
	
	@Column(name="voter_Email",nullable = false,unique = true)
	private String email;
	
	 
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

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Voter [voterId=" + voterId + ", fullName=" + fullName + ", adharNo=" + adharNo + ", voted="
				+ ", email=" + email + ", electionId=";
	}
	
}
