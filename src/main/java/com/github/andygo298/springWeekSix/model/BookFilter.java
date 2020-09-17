package com.github.andygo298.springWeekSix.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookFilter {

    private List<String> authorNames;
    private List<String> genreNames;
    private LocalDate fromDate;
    private LocalDate toDate;

}
