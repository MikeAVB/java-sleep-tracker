package ru.yandex.practicum.sleeptracker.functions;

public class SleepAnalysisResult <T extends Number> {
    private final String description;
    private final T result;

    public SleepAnalysisResult(T result) {
        this.description = "Функция для посчета количества сессий сна";
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public T getResult() {
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s, результат: %s", description, result.toString());
    }
}
