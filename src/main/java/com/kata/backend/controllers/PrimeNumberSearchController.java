package com.kata.backend.controllers;

import com.kata.backend.models.dto.SearchResultDto;
import com.kata.backend.services.PrimeNumbersSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
@Validated
public class PrimeNumberSearchController {

    @Autowired
    PrimeNumbersSearchService primeNumbersSearchService;

    @PostMapping("/prime-numbers/{number}")

    public ResponseEntity<SearchResultDto> searchPrimeNumbers(@PathVariable("number") @Min(0) long number) {
        return new ResponseEntity<SearchResultDto>(this.primeNumbersSearchService.mappSearchResult(number), HttpStatus.OK);
    }
}
