package com.github.andygo298.springWeekSix.service.util;

import com.github.andygo298.springWeekSix.model.ReportDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WriteDataToCsv {

    public static void writeObjectToCSV(PrintWriter writer, List<ReportDto> report) {
        try (
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("bookName", "genreName", "authorName", "creationDate"))
        ) {
            for (ReportDto element : report) {
                List<String> data = List.of(
                        element.getBookName(),
                        element.getGenreName(),
                        element.getAuthorName(),
                        element.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy"))
                );

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            System.out.println("Writing CSV error!");
            e.printStackTrace();
        }
    }
}
