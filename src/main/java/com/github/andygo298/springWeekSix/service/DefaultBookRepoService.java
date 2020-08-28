package com.github.andygo298.springWeekSix.service;

import com.github.andygo298.springWeekSix.model.BookFilter;
import com.github.andygo298.springWeekSix.model.BookDto;
import com.github.andygo298.springWeekSix.model.ReportDto;
import com.github.andygo298.springWeekSix.repository.BookRepository;
import com.github.andygo298.springWeekSix.util.FilterQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultBookRepoService implements BookRepoService {

    private BookRepository bookBookRepository;

    public DefaultBookRepoService(BookRepository bookBookRepository) {
        this.bookBookRepository = bookBookRepository;
    }

    @Override
    @Transactional
    public List<ReportDto> findAllByBookFilter(BookDto bookDto) {
        String query;
        FilterQueryBuilder filterQueryBuilder = FilterQueryBuilder.builder()
                .addAuthors(bookDto.getAuthorIds())
                .addGenres(bookDto.getGenreIds())
                .addDate(bookDto.getFromDate(), bookDto.getToDate());
        if (filterQueryBuilder.getCount().intValue() == 0) {
            return null;
        } else query = filterQueryBuilder.getResultQuery();
        return bookBookRepository.findAllByBookFilter(bookDto, query);
    }

    @Override
    @Transactional
    public List<Long> getAuthorIdsByNames(BookFilter bookFilter) {
        if (bookFilter.getAuthorNames() != null) {
            return bookBookRepository.getAuthorIdsByNames(bookFilter.getAuthorNames());
        } else return null;
    }

    @Override
    @Transactional
    public List<Long> getGenreIdsByNames(BookFilter bookFilter) {
        if (bookFilter.getGenreNames() != null) {
            return bookBookRepository.getGenreIdsByNames(bookFilter.getGenreNames());
        } else return null;
    }
}
