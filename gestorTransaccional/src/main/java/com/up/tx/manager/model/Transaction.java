package com.up.tx.manager.model;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp")
    private ZonedDateTime timestamp;

    @Column(name = "transaction_id")
    private String transactionId;
    
    @Column(name = "amount")
    private Double amount;
    
    @Column(name = "transaction_type")
    private String transactionType;
    
    @Column(name = "origin_user_id")
    private Long originUserId;
    
    @Column(name = "target_cuit")
    private String targetCuit;
    
    @Column(name = "status")
    private String status;
    
    public Transaction() {
    	this.timestamp = ZonedDateTime.now();
    	this.transactionId = UUID.randomUUID().toString();
    }
    
    public Transaction(Double amount, String transactionType, Long originUserId, String targetCuit, String status) {
    	this.timestamp = ZonedDateTime.now();
    	this.transactionId = UUID.randomUUID().toString();
    	this.amount = amount;
    	this.transactionType = transactionType;
    	this.originUserId = originUserId;
    	this.targetCuit = targetCuit;
    	this.status = status;
    }

    public String getTargetCuit() {
		return targetCuit;
	}

	public void setTargetCuit(String targetCuit) {
		this.targetCuit = targetCuit;
	}

	public Long getId() {
        return id;
    }

    public ZonedDateTime getCreatedAt() {
        return timestamp;
    }

    public String getTransactionId() {
        return transactionId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    
}

