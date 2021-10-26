package com.up.tx.manager.model;

public class GenericResponse {
    private String message;
    private Integer code;
    private Boolean success;

    public  GenericResponse(){

    }

    public GenericResponse(String message, Integer code, Boolean success){
        this.message = message;
        this.code = code;
        this.success = success;
    }
    
    public GenericResponse(Boolean success){
        this.message = Constants.GENERIC_OK_MESSAGE;
        this.code = Constants.RESPONSE_OK_CODE;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}