package com.up.tx.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericApiResponse {

	private Data data;
	
	
	public GenericApiResponse() {
		
	}

	public Data getData() {
		return data;
	}


	public void setData(Data data) {
		this.data = data;
	}

	
}
