package com.kata.backend.services;

import com.kata.backend.models.dto.SearchResultDto;
import com.kata.backend.models.repositories.PrimeNumberRepository;
import com.kata.backend.models.repositories.SearchResultRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class PrimeNumbersSearchServiceTest {

    @Autowired
    PrimeNumbersSearchService primeNumbersSearchService;

    @MockBean
    private  SearchResultRepository searchResultRepository;

    @MockBean
    private PrimeNumberRepository primeNumberRepository;

    @DisplayName("Test MAPPING SEARCH RESULT")
    @Test
    void testPrimeNumbersSearch() {
        Long[]  infNums = {3L, 2L, 1L, 0L};

        final Set<Long> expectedPrims = new HashSet<Long>(Arrays.asList(infNums));

        Long[] supNums = {5L, 7L, 11L, 13L, 17L};

        final Set<Long> expecteduPPrims = new HashSet<Long>(Arrays.asList(supNums));
        Mockito.when(primeNumberRepository.save(any()))
                .thenReturn(null);
        Mockito.when(searchResultRepository.save(any()))
                .thenReturn(null);
        final SearchResultDto searchResultDto =primeNumbersSearchService.mappSearchResult(4l);
        assertNotNull(searchResultDto);
        assertEquals(4L, searchResultDto.getSearchNumber());
        assertEquals(expectedPrims, searchResultDto.getDownStreamPrimeNumbers());
        assertEquals(expecteduPPrims, searchResultDto.getUpStreamPrimeNumbers());
        assertEquals(59L, searchResultDto.getSumValue());
        assertEquals(6.555555555555555, searchResultDto.getAverageValue());
    }

}