package com.example.practical.util;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDateGenerator {

    private static final long maxDay = LocalDate.now().toEpochDay();
    private static final long minDay = LocalDate.of(2010, Month.JANUARY, 1).toEpochDay();

    public static LocalDate generateDate() {
        long randomDay = minDay + ThreadLocalRandom.current().nextLong(maxDay - minDay);
        return LocalDate.ofEpochDay(randomDay);
    }

}
