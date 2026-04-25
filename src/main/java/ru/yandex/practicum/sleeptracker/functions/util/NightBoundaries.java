package ru.yandex.practicum.sleeptracker.functions.util;

import java.time.LocalTime;

//Границы ночи - [00:00; 06:00]
public enum NightBoundaries {
    START(LocalTime.of(0, 0)),
    END(LocalTime.of(6, 0));

    private final LocalTime value;

    NightBoundaries(LocalTime value) {
        this.value = value;
    }

    public LocalTime getValue() {
        return value;
    }
}
