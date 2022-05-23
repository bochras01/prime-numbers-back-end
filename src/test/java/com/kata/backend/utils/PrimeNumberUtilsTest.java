package com.kata.backend.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PrimeNumberUtilsTest {

    @DisplayName("Test check is  prime number")
    @Test
    void testisPrimeNumber() {
        //GIVEN
        //WHEN
        //THEN
        assertTrue(PrimeNumberUtils.isPrime(5L));
    }
    @DisplayName("Test check is not prime number")
    @Test
    void testIsNotPrimeNumber() {
        //GIVEN
        //WHEN
        //THEN
        assertFalse(PrimeNumberUtils.isPrime(10L));
    }

}
