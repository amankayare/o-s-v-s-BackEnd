package com.cdac.osvs.dto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "security")
public class Security {

	@Id
	@GeneratedValue
	@Column(name = "security_Id")
	private int securityId;
	
	@Column(name = "voter_Id")
	private int voterId;
	
	@Column(name = "original_Img")
	private String orignalImg;
	
	@Column(name = "shareone_Img")
	private String shareoneImg;
	
	@Column(name = "key_value")
	private String keyValue;
	
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

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	
}
