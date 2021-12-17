package com.up.tx.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "creditCard")
public class CreditCard {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "idUser")
	private Long idUser;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "expirationDate")
	private String expirationDate;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "verificationCode")
	private Integer verificationCode;
	
	public CreditCard() {
		
	}

	public CreditCard(Long idUser, String number, String name, String expirationDate, String brand, Integer verificationCode) {
		this.idUser = idUser;
		this.number = number;
		this.name = name;
		this.expirationDate = expirationDate;
		this.brand = brand;
		this.verificationCode = verificationCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
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
	
	
}
