package com.github.andygo298.springWeekSix.controller;

import com.github.andygo298.springWeekSix.model.BookDto;
import com.github.andygo298.springWeekSix.model.BookFilter;
import com.github.andygo298.springWeekSix.model.ReportDto;
import com.github.andygo298.springWeekSix.service.BookRepoService;
import com.github.andygo298.springWeekSix.service.util.WriteDataToCsv;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/report")
public class BookController {

    private final BookRepoService bookRepoService;

    public BookController(BookRepoService bookRepoService) {
        this.bookRepoService = bookRepoService;
    }

    @GetMapping("/downloadCsv")
    public void downloadCSV(@RequestBody BookFilter bookFilter, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=customers.csv");
        List<Long> authorIdsByNames = bookRepoService.getAuthorIdsByNames(bookFilter);
        List<Long> genreIdsByNames = bookRepoService.getGenreIdsByNames(bookFilter);
        BookDto bookDto = BookDto.builder()
                .authorIds(authorIdsByNames)
                .genreIds(genreIdsByNames)
                .fromDate(bookFilter.getFromDate())
                .toDate(bookFilter.getToDate())
                .build();
        List<ReportDto> reportList = bookRepoService.findAllByBookFilter(bookDto);
        if (reportList == null){
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }
        WriteDataToCsv.writeObjectToCSV(response.getWriter(), reportList);
    }
}
