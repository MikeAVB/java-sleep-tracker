package ru.yandex.practicum.sleeptracker.functions.util;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;

import java.util.List;
import java.util.function.Function;

//Вынес в общий интерфейс для сокращения сложной записи типа функции
public interface AnalysisFunction<R> extends Function<List<SleepSession>, SleepAnalysisResult<R>> {

}
