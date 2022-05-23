package com.kata.backend.utils;

import com.kata.backend.models.PrimeNumber;
import com.kata.backend.models.PrimeNumbersSearchResult;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class NumberUtils {

    private NumberUtils() {
    }

    public static boolean isPrime(Long number) {
        return !IntStream.rangeClosed(2, (int) (number / 2)).anyMatch(i -> number % i == 0);
    }

    public static Set<PrimeNumber> findFiveUpStreamPrimeNumbers(final PrimeNumbersSearchResult searchResult) {
        final Set<PrimeNumber> primeNumbers = new HashSet<>();
        int primeUpStreamCounter = 0;
        Long primeNumber = searchResult.getSearchNumber();
        while (primeUpStreamCounter < 5 && primeNumber >= 0) {
            primeNumber++;
            if (isPrime(primeNumber)) {
                primeUpStreamCounter++;
                PrimeNumber prime = new PrimeNumber();
                prime.setPrimeNumberValue(primeNumber);
                prime.setSearchNumber(searchResult);
                primeNumbers.add(prime);
            }
        }
        return primeNumbers;
    }

    public static Set<PrimeNumber> findFiveDownStreamPrimeNumbers(final PrimeNumbersSearchResult searchResult) {
        final Set<PrimeNumber> primeNumbers = new HashSet<>();
        int primeDownStreamCounter = 0;
        Long primeNumber = searchResult.getSearchNumber();
        while (primeDownStreamCounter < 5 && primeNumber > 0) {
            primeNumber--;
            if (isPrime(primeNumber)) {
                primeDownStreamCounter++;
                PrimeNumber prime = new PrimeNumber();
                prime.setPrimeNumberValue(primeNumber);
                prime.setSearchNumber(searchResult);
                primeNumbers.add(prime);
            }
        }
        return primeNumbers;
    }

}
