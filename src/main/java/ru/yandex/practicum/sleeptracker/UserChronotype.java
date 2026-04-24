package ru.yandex.practicum.sleeptracker;

public enum UserChronotype {
    OWL("Сова"),
    LARK("Жаворонок"),
    PIGEON("Голубь");

    private final String description;

    UserChronotype(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
