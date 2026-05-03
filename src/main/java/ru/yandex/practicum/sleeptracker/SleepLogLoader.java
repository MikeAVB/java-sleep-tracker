package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SleepLogLoader {
    public static List<SleepSession> loadFromFile(String filePath) throws IOException, SessionFormatException {
        Objects.requireNonNull(filePath);
        Path file = Paths.get(filePath);
        return loadFromFile(file);
    }

    public static List<SleepSession> loadFromFile(Path filePath) throws IOException, SessionFormatException {
        List<SleepSession> sessions;
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            sessions = reader.lines().map(SleepSession::fromString).toList();
        }
        return sessions;
    }

    public static List<SleepSession> loadFromString(String log) {
        Objects.requireNonNull(log);
        return Arrays.stream(log.split("\n"))
                .map(String::strip)
                .map(SleepSession::fromString)
                .toList();

    }
}
