package com.up.tx.manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EchoResponse {

    @JsonProperty("user_agent")
    private final String userAgent;

    private EchoResponse(final String userAgent) {
        this.userAgent = userAgent;
    }

    public static EchoResponse of(final String userAgent){
        return new EchoResponse(userAgent);
    }

    public String getUserAgent(){
        return userAgent;
    }

}
