package com.example.springrestwebhook1;

public class UserRequest {
    private String userId;
    private String webhookUrl;

    public UserRequest(String userId, String webhookUrl) {
        this.userId = userId;
        this.webhookUrl = webhookUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }
}
