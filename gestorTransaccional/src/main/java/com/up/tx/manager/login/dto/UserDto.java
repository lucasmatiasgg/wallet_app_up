package com.up.tx.manager.login.dto;

public class UserDto {
	private Long id;
	private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isCommerce;
    private String socialReason;
    private String address;
    private String cuit;
    private String dni; 
    
    public UserDto(){

    }

    public UserDto(Long id, String userName, String password, String firstName, String lastName, boolean isCommerce, String socialReason, String address, String cuit, String dni) {
        this.id = id;
    	this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isCommerce = isCommerce;
        this.socialReason = socialReason;
        this.address = address;
        this.cuit = cuit;
        this.dni = dni;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
    
}
