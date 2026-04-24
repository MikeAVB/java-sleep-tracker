package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SleepTrackerApp {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Передайте в качестве аргумента при запуске приложения путь к файлу лога.");
            return;
        }

        try {
            List<SleepSession> sessions = loadFromFile(args[0]);
            sessions.forEach(System.out::println);

        } catch (SessionFormatException exception) {
            System.out.println("Ошибка при парсинге строки " + exception.getSessionString());
        } catch (IOException exception) {
            System.out.println("Ошибка ввода\\вывода: " + exception.getMessage());
        }
    }

    private static List<SleepSession> loadFromFile(String filePath) throws IOException, SessionFormatException {
        Path file = Paths.get(filePath);
        List<SleepSession> sessions = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            while (reader.ready()) {
                sessions.add(SleepSession.fromString(reader.readLine()));
            }
        }
        return sessions;
    }
}