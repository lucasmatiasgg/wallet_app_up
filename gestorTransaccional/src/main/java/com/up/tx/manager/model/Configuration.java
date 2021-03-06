package com.up.tx.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "configuration")
public class Configuration {

	@Id
	@Column(name = "id")
    private String id;

	@Column(name = "value")
    private String value;
	
	public Configuration () {
		
	}
	
	public Configuration (String id, String value) {
		this.id = id;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
