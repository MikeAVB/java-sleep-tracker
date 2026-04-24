package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class SessionAverageFunction implements Function<List<SleepSession>, SleepAnalysisResult<? extends Number>> {
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