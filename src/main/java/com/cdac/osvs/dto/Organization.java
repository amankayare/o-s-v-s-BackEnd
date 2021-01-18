package com.cdac.osvs.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "organization")
public class Organization {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "organization_name",nullable = false)
    private String orgnizationName;

	@Column(name = "organization_file",nullable = false)
	private byte[] excelFile;

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

	public byte[] getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(byte[] excelFile) {
		this.excelFile = excelFile;
	}

	@Column(name = "cin_no",nullable = false)
    private String cin;

	@Override
	public String toString() {
		return "Organization{" +
				"id=" + id +
				", orgnizationName='" + orgnizationName + '\'' +
				", excelFile=" + Arrays.toString(excelFile) +
				", cin='" + cin + '\'' +
				'}';
	}
}

    // file for read and mail
    
    


