package com.kata.backend.models.repositories;

import com.kata.backend.models.PrimeNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimeNumberRepository extends JpaRepository<PrimeNumber, Long> {
}
