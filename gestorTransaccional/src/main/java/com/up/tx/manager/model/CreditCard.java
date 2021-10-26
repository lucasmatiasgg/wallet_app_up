package com.up.tx.manager.model;

import java.util.Date;

public class CreditCard {

	private String number;
	private String name;
	private Date expirationDate;
	private String brand;
	private Integer verificationCode;
	
	public CreditCard() {
		
	}

	public CreditCard(String number, String name, Date expirationDate, String brand, Integer verificationCode) {
		this.number = number;
		this.name = name;
		this.expirationDate = expirationDate;
		this.brand = brand;
		this.verificationCode = verificationCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(Integer verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	
}
