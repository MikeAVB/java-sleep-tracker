package ru.yandex.practicum.sleeptracker;

public class SessionFormatException extends Exception {
    private final String sessionString;

    public SessionFormatException(String message, String sessionString) {
        super(message);
        this.sessionString = sessionString;
    }

    public String getSessionString() {
        return sessionString;
    }
}
