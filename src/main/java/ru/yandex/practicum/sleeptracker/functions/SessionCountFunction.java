package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class SessionCountFunction implements Function<List<SleepSession>, SleepAnalysisResult<Long>> {
    @Override
    public SleepAnalysisResult<Long> apply(List<SleepSession> sleepSessions) {
        Objects.requireNonNull(sleepSessions);
        return new SleepAnalysisResult<>(sleepSessions.stream().count());
    }
}
