package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.functions.util.AnalysisFunction;

import java.util.List;
import java.util.Objects;

public class SessionBadCountFunction implements AnalysisFunction<Long> {
    private static final String description = "Количество сессий с плохим качеством сна";

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepSession> sleepSessions) {
        Objects.requireNonNull(sleepSessions);
        Long count = sleepSessions.stream()
                .filter(session -> session.getQuality() == SleepQuality.BAD)
                .count();
        return new SleepAnalysisResult<>(description, count);
    }
}