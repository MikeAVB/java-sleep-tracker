package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;
import java.util.function.Function;

public interface AnalysisFunction<R> extends Function<List<SleepSession>, SleepAnalysisResult<R>> {

}
