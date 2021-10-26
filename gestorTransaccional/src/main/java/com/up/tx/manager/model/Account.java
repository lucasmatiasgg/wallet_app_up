package com.up.tx.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "account")
public class Account {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "idUser")
    private Long idUser;
	
    @Column(name = "cvu")
    private String cvu;

    @Column(name = "aliasCVU")
    private String aliasCVU;
    
    @Column(name = "accountBalance")
    private Double accountBalance;
    
    public Account () {
    	
    }

    public Account (Long idUser, String cvu, String aliasCVU, Double accountBalance) {
    	this.idUser = idUser;
    	this.cvu = cvu;
    	this.aliasCVU = aliasCVU;
    	this.accountBalance = accountBalance;
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

	public String getCvu() {
		return cvu;
	}

	public void setCvu(String cvu) {
		this.cvu = cvu;
	}

	public String getAliasCVU() {
		return aliasCVU;
	}

	public void setAliasCVU(String aliasCVU) {
		this.aliasCVU = aliasCVU;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
    
    
}
