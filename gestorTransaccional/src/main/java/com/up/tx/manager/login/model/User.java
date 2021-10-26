package com.up.tx.manager.login.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name= "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name", nullable = false, unique = true, length = 45)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @JsonProperty
    @Column(name = "is_commerce")
    private boolean isCommerce;
    
    @Column(name = "social_reason")
    private String socialReason;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "cuit")
    private String cuit;
    
    @Column(name = "dni")
    private String dni;

    public User() {

    }

    public User(Long id, String userName, String password, String firstName, String lastName, boolean isCommerce, String dni) {
        this.id = id;
    	this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.isCommerce = isCommerce;
    }
    
    public User(Long id, String userName, String password, String firstName, String lastName, boolean isCommerce, String socialReason, String address, String cuit) {
    	this.id = id;
    	this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isCommerce = isCommerce;
        this.socialReason = socialReason;
        this.address = address;
        this.cuit = cuit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isCommerce() {
		return isCommerce;
	}

	public void setCommerce(boolean isCommerce) {
		this.isCommerce = isCommerce;
	}

	public String getSocialReason() {
		return socialReason;
	}

	public void setSocialReason(String socialReason) {
		this.socialReason = socialReason;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
    
}
