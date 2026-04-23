package ru.yandex.practicum.sleeptracker;

public enum SleepQuality {
    GOOD("GOOD"),
    NORMAL("NORMAL"),
    BAD("BAD");

    private final String text;

    SleepQuality(String text) {
        this.text = text;
    }
}
