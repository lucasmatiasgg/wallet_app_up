package com.up.tx.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "recipients")
public class Recipients {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "idUser")
	private Long idUser;
	
	@Column(name = "name")
    private String name;
	
    @Column(name = "cvu")
    private String cvu;

    @Column(name = "aliasCVU")
    private String aliasCVU;
    
    public Recipients () {
    	
    }

    public Recipients (String name, Long idUser, String cvu, String aliasCVU) {
    	this.name = name;
    	this.cvu = cvu;
    	this.idUser = idUser;
    	this.aliasCVU = aliasCVU;
    }

    public Recipients (Long id, String name, Long idUser, String cvu, String aliasCVU) {
    	this.id = id;
    	this.name = name;
    	this.cvu = cvu;
    	this.idUser = idUser;
    	this.aliasCVU = aliasCVU;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

    
}
