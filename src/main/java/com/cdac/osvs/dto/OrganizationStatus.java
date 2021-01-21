package com.cdac.osvs.dto;

import javax.persistence.Column;

public class OrganizationStatus extends Status{

    private int id;

    private String orgnizationName;

    private String excelFile;

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
