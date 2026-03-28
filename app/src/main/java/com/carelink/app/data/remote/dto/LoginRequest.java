package com.carelink.app.data.remote.dto;

public class LoginRequest {
    private String phone;
    private String code;

    public LoginRequest(String phone, String code) {
        this.phone = phone;
        this.code = code;
    }

    public String getPhone() { return phone; }
    public String getCode() { return code; }
}
