package ru.yandex.practicum.sleeptracker;

public enum UserType {
    OWL("Сова"),
    LARK("Жаворонок"),
    PIGEON("Голубь");

    private final String description;

    UserType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
