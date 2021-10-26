package com.up.tx.manager.model;

public class Transfer {

	private Long originUserId;
	private Double amount;
	private String cvu;
	private String aliasCVU;
	
	public Transfer() {
		
	}
	
	public Transfer(Long originUserId, Double amount, String cvu, String aliasCVU) {
		this.originUserId = originUserId;
		this.amount = amount;
		this.cvu = cvu;
		this.aliasCVU = aliasCVU;
	}

	public Long getOriginUserId() {
		return originUserId;
	}

	public void setOriginUserId(Long originUserId) {
		this.originUserId = originUserId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	
}
