package com.github.andygo298.springWeekSix.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDto {

    private String bookName;
    private String genreName;
    private String authorName;
    private LocalDate creationDate;

}
