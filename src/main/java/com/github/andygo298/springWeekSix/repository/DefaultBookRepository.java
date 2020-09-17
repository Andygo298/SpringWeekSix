package com.github.andygo298.springWeekSix.repository;

import com.github.andygo298.springWeekSix.model.BookDto;
import com.github.andygo298.springWeekSix.model.ReportDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DefaultBookRepository implements BookRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public DefaultBookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public List<Long> getAuthorIdsByNames(List<String> authorNames) {
        final String query = "SELECT author.id FROM author WHERE author.name IN (:names)";
        SqlParameterSource parameters = new MapSqlParameterSource("names", authorNames);
        return namedJdbcTemplate.query(query, parameters, (rs, rowNum) -> rs.getLong(1));
    }

    @Override
    public List<Long> getGenreIdsByNames(List<String> genreNames) {
        final String query = "SELECT genre.id FROM genre WHERE genre.name IN (:names)";
        SqlParameterSource parameters = new MapSqlParameterSource("names", genreNames);
        return namedJdbcTemplate.query(query, parameters, (rs, rowNum) -> rs.getLong(1));
    }

    @Override
    public List<ReportDto> findAllByBookFilter(BookDto bookDto, String query) {

        return jdbcTemplate.query(query, (resultSet, i) -> ReportDto.builder()
                .bookName(resultSet.getString(1))
                .creationDate((resultSet.getDate(2)).toLocalDate())
                .genreName(resultSet.getString(3))
                .authorName(resultSet.getString(4))
                .build());
    }
}
