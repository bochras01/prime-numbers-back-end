package com.kata.backend.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prime_numbers")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="primeNumberId")
public class PrimeNumber implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long primeNumberId;

    @Column(name = "prime_number_value")
    private long primeNumberValue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "searchId", nullable = false)
    private SearchResult searchNumber;
}

