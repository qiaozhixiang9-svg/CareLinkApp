package com.carelink.app.data.remote.dto;

public class LoginResponse {
    private String token;
    private long userId;
    private String phone;
    private String nickname;
    private boolean needSelectRole;

    public String getToken() { return token; }
    public long getUserId() { return userId; }
    public String getPhone() { return phone; }
    public String getNickname() { return nickname; }
    public boolean isNeedSelectRole() { return needSelectRole; }
}
