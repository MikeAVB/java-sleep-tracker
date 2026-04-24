package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class SessionMaxFunction implements AnalysisFunction<Long> {
    private static final String description = "Функция определяет максимальную продолжительность сна в минутах";
    @Override
    public SleepAnalysisResult<Long> apply(List<SleepSession> sleepSessions) {
        return sleepSessions.stream()
                .map(SleepSession::getDuration)
                .max(Duration::compareTo)
                .map(duration -> new SleepAnalysisResult<>(description, duration.toMinutes()))
                .orElseThrow();
    }
}