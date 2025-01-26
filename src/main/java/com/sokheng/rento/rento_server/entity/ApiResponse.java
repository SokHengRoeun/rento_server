package com.sokheng.rento.rento_server.entity;

public class ApiResponse {
    private String message;
    private String status;

    public ApiResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
