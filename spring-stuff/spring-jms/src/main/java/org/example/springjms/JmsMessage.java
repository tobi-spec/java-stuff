package org.example.springjms;

public class JmsMessage {
    String message;
    int count = 0;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count += 1;
    }
}
