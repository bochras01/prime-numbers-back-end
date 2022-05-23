package com.kata.backend.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.naming.directory.SearchResult;

public interface SearchResultRepository extends JpaRepository<SearchResult, Long> {
}
