package com.up.tx.manager.dto;

public class AccountDto {

	private Long id;
	private Long idUser;
	private String cvu;
    private String aliasCVU;
    private Double accountBalance;
    
    public AccountDto() {
    	
    }
    
    public AccountDto(Long id, Long idUser, String cvu, String aliasCVU, Double accountBalance) {
    	this.id = id;
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
