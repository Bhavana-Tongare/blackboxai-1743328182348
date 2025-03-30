package com.mindease.model;

public class ChatMessage {
    private String message;
    private boolean isUser;
    private long timestamp;

    public ChatMessage(String message, boolean isUser) {
        this.message = message;
        this.isUser = isUser;
        this.timestamp = System.currentTimeMillis();
    }

    public String getMessage() {
        return message;
    }

    public boolean isUser() {
        return isUser;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getFormattedTime() {
        return new java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
            .format(new java.util.Date(timestamp));
    }
}