package ru.yandex.practicum.sleeptracker.functions;

public class SleepAnalysisResult <T extends Number> {
    private final String description;
    private final T result;

    public SleepAnalysisResult(String description, T result) {
        this.description = description;
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public T getResult() {
        return result;
    }
}
