package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class SleepSession {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    private final LocalDateTime start;
    private final LocalDateTime end;
    private final SleepQuality quality;
    private final Duration duration;

    public static SleepSession fromString(String sessionString) throws SessionFormatException {
        try {
            Objects.requireNonNull(sessionString);

            String[] parts = sessionString.split(";");
            if (parts.length != 3)
                throw new SessionFormatException("Количество параметров отличается от ожидаемых", sessionString);

            LocalDateTime start = LocalDateTime.parse(parts[0], formatter);
            LocalDateTime end = LocalDateTime.parse(parts[1], formatter);
            SleepQuality quality = SleepQuality.valueOf(parts[2]);

            return new SleepSession(start, end, quality);
        } catch (DateTimeParseException exception) {
            throw new SessionFormatException("Неверный формат строки сессии: ", exception.getParsedString());
        }
    }

    public SleepSession(LocalDateTime start, LocalDateTime end, SleepQuality quality) {
        this.start = Objects.requireNonNull(start);
        this.end = Objects.requireNonNull(end);
        this.quality = Objects.requireNonNull(quality);
        this.duration = Duration.between(start, end);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public SleepQuality getQuality() {
        return quality;
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SleepSession that = (SleepSession) o;
        return Objects.equals(start, that.start) && Objects.equals(end, that.end) && quality == that.quality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, quality);
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%s", formatter.format(start), formatter.format(end), quality);
    }
}
