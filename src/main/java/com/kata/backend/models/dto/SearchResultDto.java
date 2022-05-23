package com.kata.backend.models.dto;

import com.kata.backend.models.PrimeNumbersSearchResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultDto implements Serializable {
    private Set<Long> upStreamPrimeNumbers;
    private Set<Long> downStreamPrimeNumbers;
    private Long searchNumber;
    private long sumValue;
    private double averageValue;

    public static SearchResultDto mapSearchEntityToSearchDto(PrimeNumbersSearchResult searchResult) {
        if (searchResult == null)
            return null;
        SearchResultDto searchResultDto = new SearchResultDto();
        searchResultDto.setSearchNumber(searchResult.getSearchNumber());
        searchResultDto.setUpStreamPrimeNumbers(searchResult.getPrimeNumbers().stream().filter(num -> num.getPrimeNumberValue() > searchResult.getSearchNumber()).map(num -> num.getPrimeNumberValue()).collect(Collectors.toSet()));
        searchResultDto.setDownStreamPrimeNumbers(searchResult.getPrimeNumbers().stream().filter(num -> num.getPrimeNumberValue() < searchResult.getSearchNumber()).map(num -> num.getPrimeNumberValue()).collect(Collectors.toSet()));
        searchResultDto.setAverageValue(searchResult.getAverageValue());
        searchResultDto.setSumValue(searchResult.getSumValue());
        return searchResultDto;
    }
}
