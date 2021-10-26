package com.up.tx.manager.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionResponse {

    @JsonProperty("transaction_id")
	private Long id;

    private ZonedDateTime date;

    public TransactionResponse(final Long id, final ZonedDateTime date) {
        this.id = id;
        this.date = date;
    }

    public TransactionResponse(){

    }

    public Long getId() {
        return id;
    }

    public ZonedDateTime getDate() {
        return date;
    }
}
