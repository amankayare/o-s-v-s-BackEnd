package com.cdac.osvs.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "organization")
public class Organization {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "organization_name", nullable = false)
    private String orgnizationName;

    @Column(name = "organization_file", nullable = false)
    private String excelFile;

    @Column(name = "cin_no", nullable = false)
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

    public String getExcelFile() {
        return excelFile;
    }

    public void setExcelFile(String excelFile) {
        this.excelFile = excelFile;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
}

// file for read and mail
    
    


