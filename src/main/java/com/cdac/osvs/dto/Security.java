package com.cdac.osvs.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "security")
public class Security {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "security_Id")
    private int securityId;

    @Column(name = "voter_Id", nullable = false)
    private int voterId;

    @Column(name = "original_Img", nullable = false, length = 1000)
    private String orignalImg;

    @Column(name = "shareone_Img", nullable = false, length = 1000)
    private String shareoneImg;

    @Column(name = "key_value", nullable = false, unique = true)
    private int keyValue;

    @Column(name = "election_Id", nullable = false)
    private int electionId;


    public Security() {
        super();

    }

    public int getSecurityId() {
        return securityId;
    }

    public void setSecurityId(int securityId) {
        this.securityId = securityId;
    }

    public int getVoterId() {
        return voterId;
    }

    public void setVoterId(int voterId) {
        this.voterId = voterId;
    }

    public String getOrignalImg() {
        return orignalImg;
    }

    public void setOrignalImg(String orignalImg) {
        this.orignalImg = orignalImg;
    }

    public String getShareoneImg() {
        return shareoneImg;
    }

    public void setShareoneImg(String shareoneImg) {
        this.shareoneImg = shareoneImg;
    }

    public int getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(int keyValue) {
        this.keyValue = keyValue;
    }

    public int getElectionId() {
        return electionId;
    }

    public void setElectionId(int electionId) {
        this.electionId = electionId;
    }

    @Override
    public String toString() {
        return "Security{" +
                "securityId=" + securityId +
                ", voterId=" + voterId +
                ", orignalImg='" + orignalImg + '\'' +
                ", shareoneImg='" + shareoneImg + '\'' +
                ", keyValue=" + keyValue +
                ", electionId=" + electionId +
                '}';
    }


}
