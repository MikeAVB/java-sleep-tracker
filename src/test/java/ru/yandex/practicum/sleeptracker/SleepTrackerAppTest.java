package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SleepTrackerAppTest {
    private static final String sessionLog = """
                01.10.25 23:15;02.10.25 07:30;GOOD
                02.10.25 23:50;03.10.25 06:40;NORMAL
                03.10.25 14:10;03.10.25 15:00;NORMAL
                03.10.25 23:40;04.10.25 08:00;BAD
                05.10.25 00:10;05.10.25 06:20;GOOD
                05.10.25 13:30;05.10.25 14:15;NORMAL
                06.10.25 22:30;07.10.25 05:50;GOOD
                07.10.25 23:45;08.10.25 06:30;GOOD
                08.10.25 23:50;09.10.25 07:10;GOOD
                10.10.25 13:00;10.10.25 14:30;NORMAL
                10.10.25 23:55;11.10.25 06:10;GOOD
                11.10.25 23:10;12.10.25 07:00;BAD
                30.10.25 23:50;31.10.25 06:30;GOOD
            """;
    private static List<SleepSession> sessions;
    private static List<SleepSession> emptySessionList;
    private static List<SleepSession> oneSessionList;
    private static List<SleepSession> twoSessionsList;

    @BeforeAll
    static void setUp() {
        sessions = SleepLogLoader.loadFromString(sessionLog);
        emptySessionList = new ArrayList<>();
        oneSessionList = List.of(SleepSession.fromString("24.04.26 01:00;24.04.26 02:00;GOOD"));
        twoSessionsList = List.of(
                SleepSession.fromString("24.04.26 01:00;24.04.26 02:00;GOOD"),
                SleepSession.fromString("25.04.26 01:00;25.04.26 02:00;BAD")
        );
    }

    /*
    SessionCountFunction
     */

    @Test
    void shouldReturnCorrectSessionCountWhenMultiple() {
        assertEquals(13, new SessionCountFunction().apply(sessions).getResult());
    }

    @Test
    void shouldReturnSessionCountWhenEmpty() {
        assertEquals(0, new SessionCountFunction().apply(emptySessionList).getResult());
    }

    /*
    SessionMinFunction
     */

    @Test
    void shouldReturnMinValue() {
        assertEquals(45, new SessionMinFunction().apply(sessions).getResult());
    }

    @Test
    void shouldThrowsMinNoValue() {
        assertThrows(NoSuchElementException.class, () -> new SessionMinFunction().apply(emptySessionList));
    }

    @Test
    void shouldReturnMinOneValue() {
        assertEquals(60, new SessionMinFunction().apply(oneSessionList).getResult());
    }

    /*
    SessionMaxFunction
     */

    @Test
    void shouldReturnMaxValue() {
        assertEquals(500, new SessionMaxFunction().apply(sessions).getResult());
    }

    @Test
    void shouldThrowsMaxNoValue() {
        assertThrows(NoSuchElementException.class, () -> new SessionMaxFunction().apply(emptySessionList));
    }

    @Test
    void shouldReturnMaxOneValue() {
        assertEquals(60, new SessionMaxFunction().apply(oneSessionList).getResult());
    }

    /*
    SessionAverageFunction
     */

    @Test
    void shouldReturnAverageValue() {
        assertEquals(345, new SessionAverageFunction().apply(sessions).getResult());
    }

    @Test
    void shouldThrowsAverageNoValue() {
        assertThrows(NoSuchElementException.class, () -> new SessionAverageFunction().apply(emptySessionList));
    }

    @Test
    void shouldReturnAverageOneValue() {
        assertEquals(60, new SessionAverageFunction().apply(oneSessionList).getResult());
    }

    @Test
    void shouldReturnAverageTwoValue() {
        assertEquals(60, new SessionAverageFunction().apply(twoSessionsList).getResult());
    }

    /*
    SessionBadCountFunction
     */

    @Test
    void shouldReturnBadCountCorrectValue() {
        assertEquals(2, new SessionBadCountFunction().apply(sessions).getResult());
    }

    @Test
    void shouldThrowsBadCountNoValue() {
        assertThrows(NoSuchElementException.class, () -> new SessionBadCountFunction().apply(emptySessionList));
    }

    @Test
    void shouldReturnBadCountOneValue() {
        assertEquals(0, new SessionBadCountFunction().apply(oneSessionList).getResult());
    }

    @Test
    void shouldReturnBadCountTwoValue() {
        assertEquals(1, new SessionBadCountFunction().apply(twoSessionsList).getResult());
    }
}