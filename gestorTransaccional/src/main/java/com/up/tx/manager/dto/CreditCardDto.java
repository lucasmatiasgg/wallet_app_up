package com.up.tx.manager.dto;

public class CreditCardDto {

	private Long id;
	private Long idUser;
	private String number;
	private String name;
	private String expirationDate;
	private String brand;
	private Integer verificationCode;
    
    public CreditCardDto() {
    	
    }
    
    public CreditCardDto(Long id, Long idUser, String number, String name, String expirationDate, String brand, Integer verificationCode) {
		this.id = id;
    	this.number = number;
		this.name = name;
		this.expirationDate = expirationDate;
		this.brand = brand;
		this.verificationCode = verificationCode;
		this.idUser = idUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
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

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

}
