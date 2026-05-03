package ru.yandex.practicum.sleeptracker.functions.util;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.function.Predicate;

//Предикат, определящий является ли сессия сна дневной (внтри дня, в границах (06:00;00:00) не пересекающаяся с ночью)
public class IsDaytimeSleep implements Predicate<SleepSession> {

    @Override
    public boolean test(SleepSession session) {
        return session.getStart().toLocalTime().isAfter(NightBoundaries.END.getValue()) &&    //заснул после 06:00
                session.getEnd().toLocalDate().isEqual(session.getStart().toLocalDate());   //и проснулся в этот же день до 00:00
    }
}
