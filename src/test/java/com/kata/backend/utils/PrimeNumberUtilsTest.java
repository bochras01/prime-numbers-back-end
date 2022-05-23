package com.kata.backend.utils;

import com.kata.backend.models.PrimeNumber;
import com.kata.backend.models.PrimeNumbersSearchResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PrimeNumberUtilsTest {

    @DisplayName("Test check is  prime number")
    @Test
    void testisPrimeNumber() {
        //GIVEN
        //WHEN
        //THEN
        assertTrue(NumberUtils.isPrime(5L));
    }

    @DisplayName("Test check is not prime number")
    @Test
    void testIsNotPrimeNumber() {
        //GIVEN
        //WHEN
        //THEN
        assertFalse(NumberUtils.isPrime(10L));
    }

    @DisplayName("Test upStream prime numbers of 1 are 2 3 5 7 11")
    @Test
    void testUpStreamPrimeNumber() {
        //GIVEN
        final PrimeNumbersSearchResult primeNumbersSearchResult = new PrimeNumbersSearchResult();
        primeNumbersSearchResult.setSearchNumber(1L);
        Long[] arr = {2L, 3L, 5L, 7L, 11L};

        final Set<Long> expectedPrims = new HashSet<Long>(Arrays.asList(arr));
        //WHEN
        final Set<PrimeNumber> upSteamPrimNumbers = NumberUtils.findFiveUpStreamPrimeNumbers(primeNumbersSearchResult);
        final Set<Long> prims = upSteamPrimNumbers.stream().map(PrimeNumber::getPrimeNumberValue).collect(Collectors.toSet());
        //THEN
        assertEquals(5, upSteamPrimNumbers.size());
        assertEquals(expectedPrims, prims);
    }

    @DisplayName("Test downStream prime numbers of 5 are  3 2 1 0")
    @Test
    void testDownStreamPrimeNumber() {
        //GIVEN
        final PrimeNumbersSearchResult primeNumbersSearchResult = new PrimeNumbersSearchResult();
        primeNumbersSearchResult.setSearchNumber(5L);
        Long[] arr = {3L, 2L, 1L, 0L};

        final Set<Long> expectedPrims = new HashSet<Long>(Arrays.asList(arr));
        //WHEN
        final Set<PrimeNumber> downSteamPrimNumbers = NumberUtils.findFiveDownStreamPrimeNumbers(primeNumbersSearchResult);
        final Set<Long> prims = downSteamPrimNumbers.stream().map(PrimeNumber::getPrimeNumberValue).collect(Collectors.toSet());
        //THEN
        assertEquals(4, downSteamPrimNumbers.size());
        assertEquals(expectedPrims, prims);
    }

}
