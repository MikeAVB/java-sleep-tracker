package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.functions.util.AnalysisFunction;

import java.util.List;
import java.util.Objects;

public class SessionCountFunction implements AnalysisFunction<Long> {
    private static final String description = "Количество сессий сна";

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepSession> sleepSessions) {
        Objects.requireNonNull(sleepSessions);
        return new SleepAnalysisResult<>(description, sleepSessions.stream().count());
    }
}
