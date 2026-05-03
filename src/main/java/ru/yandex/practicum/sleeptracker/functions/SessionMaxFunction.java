package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.functions.util.AnalysisFunction;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class SessionMaxFunction implements AnalysisFunction<Long> {
    private static final String description = "Максимальная продолжительность сна в минутах";

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepSession> sleepSessions) {
        Objects.requireNonNull(sleepSessions);
        Duration maxDuration = sleepSessions.stream()
                .map(SleepSession::getDuration)
                .max(Duration::compareTo)
                .orElseThrow();
        return new SleepAnalysisResult<>(description, maxDuration.toMinutes());
    }
}