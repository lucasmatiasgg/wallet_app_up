package com.up.tx.manager.dto;

public class RecipientsDto {

	private Long id;
	private String name;
	private String cvu;
	private String aliasCvu;
    private Long idUser;
    
    public RecipientsDto() {
    	
    }
    
    public RecipientsDto(Long id, Long idUser, String name, String cvu, String aliasCvu) {
		this.id = id;
		this.name = name;
		this.cvu = cvu;
		this.aliasCvu = aliasCvu;
		this.idUser = idUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCvu() {
		return cvu;
	}

	public void setCvu(String cvu) {
		this.cvu = cvu;
	}

	public String getAliasCvu() {
		return aliasCvu;
	}

	public void setAliasCvu(String aliasCvu) {
		this.aliasCvu = aliasCvu;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

}
