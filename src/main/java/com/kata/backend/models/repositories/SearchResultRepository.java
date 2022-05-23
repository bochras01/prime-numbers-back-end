package com.kata.backend.models.repositories;

import com.kata.backend.models.PrimeNumbersSearchResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchResultRepository extends JpaRepository<PrimeNumbersSearchResult, Long> {

   PrimeNumbersSearchResult findBySearchNumber(Long  searchNumber);
}

