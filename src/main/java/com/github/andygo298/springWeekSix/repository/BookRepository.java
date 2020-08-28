package com.github.andygo298.springWeekSix.repository;

import com.github.andygo298.springWeekSix.model.BookDto;
import com.github.andygo298.springWeekSix.model.ReportDto;

import java.util.List;

public interface BookRepository {

    List<Long> getAuthorIdsByNames(List<String> authorNames);

    List<Long> getGenreIdsByNames(List<String> genreNames);

    List<ReportDto> findAllByBookFilter(BookDto bookDto, String query);

}
