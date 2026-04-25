package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.functions.util.AnalysisFunction;
import ru.yandex.practicum.sleeptracker.functions.util.isDaytimeSleep;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.Objects;

public class SleeplessNightCountFunction implements AnalysisFunction<Long> {
    private static final String description = "Количество бессонных ночей";

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepSession> sleepSessions) {
        Objects.requireNonNull(sleepSessions);
        SleepSession firstSession = sleepSessions.getFirst();
        LocalDate firstDay;

        //Если первая сессия сна началась до 12:00, отсчет ведем от этого дня
        if (firstSession.getStart().toLocalTime().isBefore(LocalTime.of(12, 0))) {
            firstDay = firstSession.getStart().toLocalDate();
        } else {
        //Инчае считаем со следующего
            firstDay = firstSession.getStart().toLocalDate().plusDays(1);
        }

        //Последний день периода
        LocalDate lastDay = sleepSessions.getLast().getEnd().toLocalDate();
        //Считаем количество дней для расчета
        Period period = Period.between(firstDay, lastDay);
        //Добавляем один день, так как Period.between не учитывает последний день
        Long allNightsCount = (long) period.getDays() + 1;

        Long nightSleepCount = sleepSessions.stream()
                .filter(new isDaytimeSleep().negate())  //убираем все сессии дневного сна, остаются только ночи
                .count();  //получаем количество сессий ночного сна

        //в результате вычитаем из общего количества ночей количество сессий ночного сна, в итоге получится
        //количество бессонных ночей
        return new SleepAnalysisResult<>(description, allNightsCount - nightSleepCount);
    }
}