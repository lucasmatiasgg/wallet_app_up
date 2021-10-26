package com.up.tx.manager.model;

public class Payment {
	private String cuit;
	private Double amount;
	private Long originUserId;
	private String number;
	private String name;
	private String expiry;
	private String brand;
	private Integer verificationCode;
	private String userIdentificationNumber;
	
	public Payment() {
		
	}
	
	public Payment(String cuit, Double amount, Long originUserId, String number, String name, String expiry, String brand, Integer verificationCode, String userIdentificationNumber) {
		this.cuit = cuit;
		this.amount = amount;
		this.originUserId = originUserId;
		this.number = number;
		this.name = name;
		this.expiry = expiry;
		this.brand = brand;
		this.verificationCode = verificationCode;
		this.userIdentificationNumber = userIdentificationNumber;
	}

	public String getUserIdentificationNumber() {
		return userIdentificationNumber;
	}

	public void setUserIdentificationNumber(String userIdentificationNumber) {
		this.userIdentificationNumber = userIdentificationNumber;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getOriginUserId() {
		return originUserId;
	}

	public void setOriginUserId(Long originUserId) {
		this.originUserId = originUserId;
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

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
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
