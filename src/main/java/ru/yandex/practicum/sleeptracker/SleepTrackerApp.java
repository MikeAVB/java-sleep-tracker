package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import ru.yandex.practicum.sleeptracker.functions.*;

public class SleepTrackerApp {
    private static final List<AnalysisFunction<?>> functions = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Передайте в качестве аргумента при запуске приложения путь к файлу лога.");
            return;
        }

        try {
            List<SleepSession> sessions = SleepLogLoader.loadFromFile(args[0]);
            functions.add(new SessionCountFunction());
            functions.add(new SessionMinFunction());
            functions.add(new SessionMaxFunction());
            functions.add(new SessionAverageFunction());
            functions.add(new SessionBadCountFunction());
            functions.add(new UserChronotypeFunction());

            functions.stream()
                    .map(function -> function.apply(sessions))
                    .forEach(System.out::println);

        } catch (SessionFormatException exception) {
            System.out.println("Ошибка при парсинге строки " + exception.getSessionString());
        } catch (IOException exception) {
            System.out.println("Ошибка ввода\\вывода: " + exception.getMessage());
        }
    }
}