package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class SessionCountFunction implements Function<List<SleepSession>, SleepAnalysisResult<? extends Number>> {
    private static final String description = "Функция для посчета количества сессий сна";

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepSession> sleepSessions) {
        Objects.requireNonNull(sleepSessions);
        return new SleepAnalysisResult<>(description, sleepSessions.stream().count());
    }
}
