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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "security_Id")
	private int securityId;
	
	@Column(name = "voter_Id",nullable = false)
	private int voterId;
	
	@Column(name = "original_Img",nullable = false)
	private byte[]  orignalImg;
	
	@Column(name = "shareone_Img",nullable = false)
	private byte[]  shareoneImg;
	
	@Column(name = "key_value",nullable = false,unique = true)
	private int keyValue;
	
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

	public byte[] getOrignalImg() {
		return orignalImg;
	}

	public void setOrignalImg(byte[] orignalImg) {
		this.orignalImg = orignalImg;
	}

	public byte[] getShareoneImg() {
		return shareoneImg;
	}

	public void setShareoneImg(byte[] shareoneImg) {
		this.shareoneImg = shareoneImg;
	}

	public int getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(int keyValue) {
		this.keyValue = keyValue;
	}

	@Override
	public String toString() {
		return "Security [securityId=" + securityId + ", voterId=" + voterId + ", orignalImg=" + orignalImg
				+ ", shareoneImg=" + shareoneImg + ", keyValue=" + keyValue + "]";
	}

}
