package com.kata.backend.services;

import com.kata.backend.models.PrimeNumbersSearchResult;
import com.kata.backend.models.dto.SearchResultDto;
import com.kata.backend.models.repositories.PrimeNumberRepository;
import com.kata.backend.models.repositories.SearchResultRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class PrimeNumbersSearchServiceTest {

    @Autowired
    PrimeNumbersSearchService primeNumbersSearchService;

    @MockBean
    private SearchResultRepository searchResultRepository;

    @MockBean
    private PrimeNumberRepository primeNumberRepository;

    @DisplayName("Test MAPPING SEARCH RESULT")
    @Test
    void testPrimeNumbersSearch() {
        //Given
        Long[] infNums = {3L, 2L, 1L, 0L};

        final Set<Long> expectedPrims = new HashSet<Long>(Arrays.asList(infNums));

        Long[] supNums = {5L, 7L, 11L, 13L, 17L};

        final Set<Long> expecteduPPrims = new HashSet<Long>(Arrays.asList(supNums));

        //WHEN
        Mockito.when(primeNumberRepository.save(any()))
                .thenReturn(null);
        Mockito.when(searchResultRepository.save(any()))
                .thenReturn(null);

        //THEN
        final SearchResultDto searchResultDto = primeNumbersSearchService.mappSearchResult(4L);
        assertNotNull(searchResultDto);
        assertEquals(4L, searchResultDto.getSearchNumber());
        assertEquals(expectedPrims, searchResultDto.getDownStreamPrimeNumbers());
        assertEquals(expecteduPPrims, searchResultDto.getUpStreamPrimeNumbers());
        assertEquals(59L, searchResultDto.getSumValue());
        assertEquals(6.555555555555555, searchResultDto.getAverageValue());
    }

    @DisplayName("Test restore search history")
    @Test
    void restoreSearchHistory() {
        //GIVEN
        Mockito.when(searchResultRepository.findAll())
                .thenReturn(this.mockSearchResults());

        //WHEN
        final List<SearchResultDto> searchResults = primeNumbersSearchService.getLastTenSearchHistory();

        //THEN
        assertNotNull(searchResults);
        assertEquals(10, searchResults.size());
        assertEquals(6L, searchResults.get(0).getSearchNumber());
        assertEquals(3L, searchResults.get(9).getSearchNumber());

    }


    private List<PrimeNumbersSearchResult> mockSearchResults() {
        final List<PrimeNumbersSearchResult> results = new ArrayList<>();
        results.add(mockSearchResult(new Date("December 17, 2021 03:24:00"), 5L));
        results.add(mockSearchResult(new Date("December 18, 2021 04:24:00"), 6L));
        results.add(mockSearchResult(new Date("December 1, 2021 03:25:00"), 1L));
        results.add(mockSearchResult(new Date("December 2, 2021 03:24:00"), 2L));
        results.add(mockSearchResult(new Date("December 3, 2021 03:24:00"), 3L));
        results.add(mockSearchResult(new Date("December 4, 2021 03:24:00"), 4L));
        results.add(mockSearchResult(new Date("December 5, 2021 03:24:00"), 5L));
        results.add(mockSearchResult(new Date("December 21, 2021 03:24:01"), 6L));
        results.add(mockSearchResult(new Date("December 17, 2021 01:24:00"), 7L));
        results.add(mockSearchResult(new Date("December 19, 2021 03:24:00"), 8L));
        results.add(mockSearchResult(new Date("December 20, 2021 03:24:00"), 9L));
        results.add(mockSearchResult(new Date("December 21, 2021 03:24:00"), 10L));
        results.add(mockSearchResult(new Date("December 22, 2020 03:24:00"), 11L));

        return results;
    }

    private PrimeNumbersSearchResult mockSearchResult(Date date, Long number) {
        final PrimeNumbersSearchResult primeNumbersSearchResult = new PrimeNumbersSearchResult();
        primeNumbersSearchResult.setSearchNumber(number);
        primeNumbersSearchResult.setSearchDate(date);
        return primeNumbersSearchResult;

    }

}