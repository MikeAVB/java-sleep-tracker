package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.Duration;
import java.util.List;

public class SessionAverageFunction implements AnalysisFunction<Long> {
    private static final String description = "Функция определяет среднюю продолжительность сна в минутах";
    @Override
    public SleepAnalysisResult<Long> apply(List<SleepSession> sleepSessions) {
        Double average = sleepSessions.stream()
                .map(SleepSession::getDuration)
                .mapToLong(Duration::toMinutes)
                .average()
                .orElseThrow();
        return new SleepAnalysisResult<>(description, average.longValue());
    }
}