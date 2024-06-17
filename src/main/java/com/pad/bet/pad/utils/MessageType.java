package com.pad.bet.pad.utils;

public enum MessageType {

    BIENVENIDO("src/main/resources/messages/bienvenido.txt");

    private String message;
    MessageType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
