package com.kata.backend.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "searchs")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="searchId")
public class SearchResult implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long searchId;

    @Column(name = "search_number")
    private long searchNumber;

    @OneToMany(mappedBy = "searchNumber", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<PrimeNumber> primeNumbers;

    @Column(name = "sum")
    private long sumValue;

    @Column(name = "average")
    private double averageValue;

    @Column(name = "search_date")
    private Date searchDate;

    @Column(name = "user_id")
    private long userId;

}
