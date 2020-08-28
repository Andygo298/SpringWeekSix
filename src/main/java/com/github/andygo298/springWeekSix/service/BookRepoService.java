package com.github.andygo298.springWeekSix.service;

import com.github.andygo298.springWeekSix.model.BookFilter;
import com.github.andygo298.springWeekSix.model.BookDto;
import com.github.andygo298.springWeekSix.model.ReportDto;

import java.util.List;

public interface BookRepoService {

    List<ReportDto> findAllByBookFilter(BookDto book);

    List<Long> getAuthorIdsByNames(BookFilter bookFilter);

    List<Long> getGenreIdsByNames(BookFilter bookFilter);

}
