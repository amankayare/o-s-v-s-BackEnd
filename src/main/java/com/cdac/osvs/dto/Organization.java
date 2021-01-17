package com.cdac.osvs.dto;

import javax.persistence.*;

@Entity
@Table(name = "organization")
public class Organization {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "orgnization_name",nullable = false)
    private String orgnizationName;

    @Column(name = "cin_no",nullable = false)
    private String cin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrgnizationName() {
		return orgnizationName;
	}

	public void setOrgnizationName(String orgnizationName) {
		this.orgnizationName = orgnizationName;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", orgnizationName=" + orgnizationName + ", cin=" + cin + "]";
	}

    // file for read and mail
    
    

}
