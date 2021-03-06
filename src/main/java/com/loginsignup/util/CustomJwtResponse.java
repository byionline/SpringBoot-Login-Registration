package com.loginsignup.util;
public class CustomJwtResponse {
    private String accessToken;
    private String accessTokenType = "Bearer";
    public CustomJwtResponse(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getAccessTokenType() {
        return accessTokenType;
    }
    public void setAccessTokenType(String accessTokenType) {
        this.accessTokenType = accessTokenType;
    }
}