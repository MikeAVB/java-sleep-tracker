package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.functions.util.AnalysisFunction;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class SessionAverageFunction implements AnalysisFunction<Long> {
    private static final String description = "Cредняя продолжительность сна в минутах";

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepSession> sleepSessions) {
        Objects.requireNonNull(sleepSessions);
        Double average = sleepSessions.stream()
                .map(SleepSession::getDuration)
                .mapToLong(Duration::toMinutes)
                .average()
                .orElseThrow();
        return new SleepAnalysisResult<>(description, average.longValue());
    }
}