package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.SessionCountFunction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class SleepTrackerAppTest {
    private static final String log = """
                01.10.25 23:15;02.10.25 07:30;GOOD
                02.10.25 23:50;03.10.25 06:40;NORMAL
                03.10.25 14:10;03.10.25 15:00;NORMAL
                03.10.25 23:40;04.10.25 08:00;BAD
                05.10.25 00:10;05.10.25 06:20;GOOD
                05.10.25 13:30;05.10.25 14:15;NORMAL
            """;
    private static List<SleepSession> sessionLog;

    @BeforeAll
    static void setUp() {
        sessionLog = SleepLogLoader.loadFromString(log);
    }

    @Test
    void shouldReturnCorrectSessionCountWhenMultiple() {
        assertEquals(6, new SessionCountFunction().apply(sessionLog).getResult());
    }

    @Test
    void shouldReturnSessionCountWhenEmpty() {
        assertEquals(0, new SessionCountFunction().apply(new ArrayList<>()).getResult());
    }
}