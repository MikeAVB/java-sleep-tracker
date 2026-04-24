package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.UserChronotype;

import java.util.List;
import java.util.function.Function;

public class UserChronotypeFunction implements AnalysisFunction<UserChronotype> {
    @Override
    public SleepAnalysisResult<UserChronotype> apply(List<SleepSession> sleepSessions) {

        return null;
    }
}
