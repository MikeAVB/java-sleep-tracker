package ru.yandex.practicum.sleeptracker;

public enum SleepQuality {
    GOOD("GOOD"),
    NORMAL("NORMAL"),
    BAD("BAD");

    private final String description;

    SleepQuality(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
