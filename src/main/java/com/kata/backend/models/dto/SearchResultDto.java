package com.kata.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

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
}
