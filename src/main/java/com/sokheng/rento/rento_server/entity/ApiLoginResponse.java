package com.sokheng.rento.rento_server.entity;

public class ApiLoginResponse {
    private String authToken;
    private String role;
    private String status;
    private String userId;
    private String name;

    public ApiLoginResponse(String authToken, String role, String status, String userId, String name) {
        this.authToken = authToken;
        this.role = role;
        this.status = status;
        this.userId = userId;
        this.name = name;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
