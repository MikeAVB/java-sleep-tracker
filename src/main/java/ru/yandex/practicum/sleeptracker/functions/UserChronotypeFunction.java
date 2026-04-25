package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.UserChronotype;
import ru.yandex.practicum.sleeptracker.functions.util.AnalysisFunction;
import ru.yandex.practicum.sleeptracker.functions.util.NightBoundaries;
import ru.yandex.practicum.sleeptracker.functions.util.isDaytimeSleep;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserChronotypeFunction implements AnalysisFunction<UserChronotype> {
    private static final String description = "Хронотип пользователя";

    private static final LocalTime owlStart = LocalTime.of(23, 0);
    private static final LocalTime owlEnd = LocalTime.of(9, 0);
    private static final LocalTime larkStart = LocalTime.of(22, 0);
    private static final LocalTime larkEnd = LocalTime.of(7, 0);

    //Служебный метод для определения типа сессии
    private UserChronotype getChronotypeOfSession(SleepSession session) {
        LocalDateTime start = session.getStart();
        LocalDateTime end = session.getEnd();

        //Сессия жаворонка считается, если лег спать сегодня до 22:00 И проснулся завтра до 07:00
        if (start.toLocalTime().isBefore(larkStart) && end.plusDays(1).toLocalTime().isBefore(larkEnd)) {
            return UserChronotype.LARK;
        //Сессия совы считается, если лег спать сегодня с 23:00 до 00:00, либо завтра до 06:00 И проснулся завтра после 09:00
        } else if (
                (start.toLocalTime().isAfter(owlStart) || start.plusDays(1).toLocalTime().isBefore(NightBoundaries.END.getValue())) &&
                        end.toLocalTime().isAfter(owlEnd)
        ) {
            return UserChronotype.OWL;
        //Во всех остальных случаях - сессия голубя
        } else {
            return UserChronotype.PIGEON;
        }
    }

    @Override
    public SleepAnalysisResult<UserChronotype> apply(List<SleepSession> sleepSessions) {
        Objects.requireNonNull(sleepSessions);
        Map<UserChronotype, Long> statistics = sleepSessions.stream()
                .filter(new isDaytimeSleep().negate())  //убираем дневные сессии сна
                .map(this::getChronotypeOfSession)  //сопоставляем сесссии к их типу
                //и делаем словарь в котором сопоставляем количество сессий каждого типа
                .collect(Collectors.groupingBy(userChronotype -> userChronotype, Collectors.counting()));
        //Находим key по максимальному value, т.е. получаем самый частый тип сессий
        UserChronotype userChronotype = Collections.max(statistics.entrySet(), Map.Entry.comparingByValue()).getKey();

        return new SleepAnalysisResult<>(description, userChronotype);
    }
}
