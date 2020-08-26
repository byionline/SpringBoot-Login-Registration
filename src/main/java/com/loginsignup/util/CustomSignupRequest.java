package com.loginsignup.util;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CustomSignupRequest {
    @NotBlank
    @Size(min = 3, max = 70)
    private String name;

    @NotBlank
    @Size(max = 70)
    @Email
    private String emailId;

    @NotBlank
    @Size(min = 6, max = 20)
    private String username;

    @NotBlank
    @Size(min = 10, max = 70)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}