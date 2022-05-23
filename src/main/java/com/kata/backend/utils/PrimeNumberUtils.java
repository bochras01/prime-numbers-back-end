package com.kata.backend.utils;

import java.util.stream.IntStream;

public class PrimeNumberUtils {

    private PrimeNumberUtils() {
    }

    public static boolean isPrime(Long number) {
        return !IntStream.rangeClosed(2, (int) (number / 2)).anyMatch(i -> number % i == 0);
    }
}
