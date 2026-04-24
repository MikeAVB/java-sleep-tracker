package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.util.List;

import ru.yandex.practicum.sleeptracker.functions.*;

public class SleepTrackerApp {


    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Передайте в качестве аргумента при запуске приложения путь к файлу лога.");
            return;
        }

        try {
            List<SleepSession> sessions = SleepLogLoader.loadFromFile(args[0]);
            System.out.println(new SessionCountFunction().apply(sessions));

        } catch (SessionFormatException exception) {
            System.out.println("Ошибка при парсинге строки " + exception.getSessionString());
        } catch (IOException exception) {
            System.out.println("Ошибка ввода\\вывода: " + exception.getMessage());
        }
    }


}