package com.github.andygo298.springWeekSix.util;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FilterQueryBuilder {

    private StringBuffer resultQuery = new StringBuffer(
            "SELECT book.name, book.creation_year, genre.name, author.name FROM book " +
                    "INNER JOIN genre on genre_id = genre.id " +
                    "INNER JOIN author on author_id = author.id ");
    @Getter
    private AtomicInteger count = new AtomicInteger(0);


    public FilterQueryBuilder addAuthors(List<Long> obj) {
        if (obj != null) {
            if (count.intValue() == 0) {
                resultQuery.append("WHERE ");
            } else {
                resultQuery.append("AND ");
            }
            count.getAndIncrement();
            resultQuery.append("author.id IN (")
                    .append(obj.stream().map(String::valueOf).collect(Collectors.joining(",")))
                    .append(')');
            return this;
        }
        return this;
    }

    public FilterQueryBuilder addGenres(List<Long> obj) {
        if (obj != null) {
            if (count.intValue() == 0) {
                resultQuery.append("WHERE ");
            } else {
                resultQuery.append(" AND ");
            }
            count.getAndIncrement();
            resultQuery.append("genre.id IN (")
                    .append(obj.stream().map(String::valueOf).collect(Collectors.joining(",")))
                    .append(')');
            return this;
        }
        return this;
    }

    public FilterQueryBuilder addDate(LocalDate objFrom, LocalDate objTo) {
        if (objFrom != null && objTo != null) {
            if (count.intValue() == 0) {
                resultQuery.append("WHERE ");
            } else {
                resultQuery.append(" AND ");
            }
            count.getAndIncrement();
            resultQuery.append("(YEAR(creation_year) between ")
                    .append(objFrom.getYear())
                    .append(" AND ")
                    .append(objTo.getYear())
                    .append(')');
            return this;
        }
        return this;
    }

    public static FilterQueryBuilder builder() {
        return new FilterQueryBuilder();
    }

    public String getResultQuery() {
        return this.resultQuery.toString();
    }
}
