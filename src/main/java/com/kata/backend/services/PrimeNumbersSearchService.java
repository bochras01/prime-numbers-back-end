package com.kata.backend.services;

import com.kata.backend.models.PrimeNumber;
import com.kata.backend.models.PrimeNumbersSearchResult;
import com.kata.backend.models.dto.SearchResultDto;
import com.kata.backend.models.repositories.PrimeNumberRepository;
import com.kata.backend.models.repositories.SearchResultRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.kata.backend.utils.NumberUtils.findFiveDownStreamPrimeNumbers;
import static com.kata.backend.utils.NumberUtils.findFiveUpStreamPrimeNumbers;

@Service
public class PrimeNumbersSearchService {
    private SearchResultRepository primeNumbersSearchRepository;
    private PrimeNumberRepository primeNumberRepository;


    public PrimeNumbersSearchService(SearchResultRepository primeNumbersSearchRepository, PrimeNumberRepository primeNumberRepository) {
        this.primeNumbersSearchRepository = primeNumbersSearchRepository;
        this.primeNumberRepository = primeNumberRepository;

    }

    public SearchResultDto mappSearchResult(long number) {
        final PrimeNumbersSearchResult searchResult = new PrimeNumbersSearchResult();
        searchResult.setSearchNumber(number);
        primeNumbersSearchRepository.save(searchResult);
        final Set<PrimeNumber> upStream = findFiveUpStreamPrimeNumbers(searchResult);
        if (!upStream.isEmpty())
            upStream.forEach(result -> this.primeNumberRepository.save(result));
        searchResult.setPrimeNumbers(upStream);
        final Set<PrimeNumber> downStream = findFiveDownStreamPrimeNumbers(searchResult);
        if (!downStream.isEmpty())
            downStream.forEach(result -> this.primeNumberRepository.save(result));
        searchResult.getPrimeNumbers().addAll(downStream);
        searchResult.setSearchDate(new Date());
        searchResult.setSumValue(searchResult.getPrimeNumbers().stream().mapToLong(PrimeNumber::getPrimeNumberValue)
                .sum());
        searchResult.setAverageValue(searchResult.getPrimeNumbers().stream().mapToLong(PrimeNumber::getPrimeNumberValue)
                .average().getAsDouble());
        primeNumbersSearchRepository.save(searchResult);
        return SearchResultDto.mapSearchEntityToSearchDto(searchResult);
    }

    public List<SearchResultDto> getLastTenSearchHistory() {
        final List<PrimeNumbersSearchResult> allSearchHistory = this.primeNumbersSearchRepository.findAll();
        if (allSearchHistory.isEmpty())
            return null;

        allSearchHistory.sort(Comparator.comparing(PrimeNumbersSearchResult::getSearchDate).reversed());
        if (allSearchHistory.size() > 10) {
            List<PrimeNumbersSearchResult> firstTenSearchsa = allSearchHistory.stream().limit(10
            ).collect(Collectors.toList());
            return firstTenSearchsa.stream().map(SearchResultDto::mapSearchEntityToSearchDto).collect(Collectors.toList());
        }

        return allSearchHistory.stream().map(SearchResultDto::mapSearchEntityToSearchDto).collect(Collectors.toList());

    }
}
