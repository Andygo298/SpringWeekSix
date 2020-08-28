package com.github.andygo298.springWeekSix.repository;

import com.github.andygo298.springWeekSix.IntegrationTestBase;
import com.github.andygo298.springWeekSix.model.BookDto;
import com.github.andygo298.springWeekSix.model.ReportDto;
import com.github.andygo298.springWeekSix.util.FilterQueryBuilder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultBookRepositoryTest extends IntegrationTestBase {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testGetAuthorIdsByNames() {
        final List<String> test = List.of("Andrei", "Viktor");
        final List<Long> expectedLIst = List.of(1L, 4L);
        List<Long> authorIdsByNames = bookRepository.getAuthorIdsByNames(test);
        assertNotNull(authorIdsByNames);
        assertEquals(expectedLIst, authorIdsByNames);
    }

    @Test
    void testGetGenreIdsByNames() {
        final List<String> test = List.of("Biograpy", "Fantasy");
        final List<Long> expectedLIst = List.of(1L, 3L);
        List<Long> authorIdsByNames = bookRepository.getGenreIdsByNames(test);
        assertNotNull(authorIdsByNames);
        assertEquals(expectedLIst, authorIdsByNames);
    }

    @Test
    void testAllByBookFilter() {
        final String filterQuery = FilterQueryBuilder.builder()
                .addAuthors(List.of(1L))
                .addGenres(null)
                .addDate(LocalDate.of(2005, 1, 1), LocalDate.of(2011, 1, 1))
                .getResultQuery();
        final BookDto bookDto = BookDto.builder()
                .authorIds(List.of(1L))
                .genreIds(null)
                .fromDate(LocalDate.of(2005, 1, 1))
                .toDate(LocalDate.of(2011, 1, 1))
                .build();
        ReportDto expectedReportDto = ReportDto.builder()
                .authorName("Andrei")
                .genreName("Technical writing")
                .bookName("Learn C++")
                .creationDate(LocalDate.of(2009, 6, 11))
                .build();
        List<ReportDto> allByBookFilter = bookRepository.findAllByBookFilter(bookDto, filterQuery);
        assertNotNull(allByBookFilter);
        assertEquals(expectedReportDto, allByBookFilter.get(0));
    }
}