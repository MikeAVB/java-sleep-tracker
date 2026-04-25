package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.functions.util.AnalysisFunction;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class SessionMinFunction implements AnalysisFunction<Long> {
    private static final String description = "Минимальная продолжительность сна в минутах";

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepSession> sleepSessions) {
        Objects.requireNonNull(sleepSessions);
        Duration minDuration = sleepSessions.stream()
                .map(SleepSession::getDuration)
                .min(Duration::compareTo)
                .orElseThrow();
        return new SleepAnalysisResult<>(description, minDuration.toMinutes());
    }
}
