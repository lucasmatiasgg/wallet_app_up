package com.up.tx.manager.dto;

import java.time.ZonedDateTime;

public class TransactionDto {

    private Long id;
    private ZonedDateTime timestamp;
    private String transactionId;
    private Double amount;
    private String transactionType;
    private Long originUserId;
    private String targetCuit;
    private String status;
    
    public TransactionDto() {
    	
    }
    
    public TransactionDto(Long id, ZonedDateTime timestamp, String transactionId, Double amount, String transactionType, Long originUserId, String targetCuit, String status) {
    	this.id = id;
    	this.timestamp = timestamp;
    	this.transactionId = transactionId;
    	this.amount = amount;
    	this.transactionType = transactionType;
    	this.originUserId = originUserId;
    	this.targetCuit = targetCuit;
    	this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(ZonedDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Long getOriginUserId() {
		return originUserId;
	}

	public void setOriginUserId(Long originUserId) {
		this.originUserId = originUserId;
	}

	public String getTargetCuit() {
		return targetCuit;
	}

	public void setTargetCuit(String targetCuit) {
		this.targetCuit = targetCuit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

}
