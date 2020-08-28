package com.github.andygo298.springWeekSix.service;

import com.github.andygo298.springWeekSix.model.BookDto;
import com.github.andygo298.springWeekSix.model.BookFilter;
import com.github.andygo298.springWeekSix.model.ReportDto;
import com.github.andygo298.springWeekSix.repository.BookRepository;
import com.github.andygo298.springWeekSix.util.FilterQueryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultBookRepoServiceTest {

    @Mock
    BookRepository bookRepository;
    @InjectMocks
    DefaultBookRepoService defaultBookRepoService;

    @Test
    void testGetAuthorIdsByNames() {
        List<Long> ids = List.of(1L, 4L);
        BookFilter bookFilter = BookFilter.builder().authorNames(List.of("Andrei", "Viktor")).build();
        when(bookRepository.getAuthorIdsByNames(bookFilter.getAuthorNames())).thenReturn(ids);
        List<Long> authorIdsByNames = defaultBookRepoService.getAuthorIdsByNames(bookFilter);
        assertEquals(ids, authorIdsByNames);
    }

    @Test
    void testGetGenreIdsByNames() {
        List<Long> ids = List.of(1L, 3L);
        BookFilter bookFilter = BookFilter.builder().genreNames(List.of("Biograpy", "Fantasy")).build();
        when(bookRepository.getGenreIdsByNames(bookFilter.getGenreNames())).thenReturn(ids);
        List<Long> genreIdsByNames = defaultBookRepoService.getGenreIdsByNames(bookFilter);
        assertEquals(ids, genreIdsByNames);
    }

    @Test
    void findAllByBookFilter() {
        String filterQuery = FilterQueryBuilder.builder()
                .addAuthors(List.of(1L))
                .addGenres(null)
                .addDate(LocalDate.of(2005, 1, 1), LocalDate.of(2011, 1, 1))
                .getResultQuery();
        BookDto bookDto = BookDto.builder()
                .authorIds(List.of(1L))
                .genreIds(null)
                .fromDate(LocalDate.of(2005, 1, 1))
                .toDate(LocalDate.of(2011, 1, 1))
                .build();
        ReportDto reportDto = ReportDto.builder()
                .authorName("Andrei")
                .genreName("Technical writing")
                .bookName("Learn C++")
                .creationDate(LocalDate.of(2009, 6, 11))
                .build();
        List<ReportDto> expected = List.of(reportDto);
        when(bookRepository.findAllByBookFilter(bookDto, filterQuery)).thenReturn(expected);
        List<ReportDto> allByBookFilter = defaultBookRepoService.findAllByBookFilter(bookDto);
        assertNotNull(allByBookFilter);
        assertEquals(expected, allByBookFilter);
    }

}