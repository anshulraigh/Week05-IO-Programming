package com.json.iplcensorship;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.util.List;
import java.util.Map;

public class IPLCsvProcessor {
    public static void processCsv(String inputFilePath, String outputFilePath) throws Exception {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();

        MappingIterator<Map<String, String>> iterator = csvMapper.readerFor(Map.class)
                .with(csvSchema)
                .readValues(new File(inputFilePath));

        List<Map<String, String>> matches = iterator.readAll();

        for (Map<String, String> match : matches) {
            match.put("team1", IPLDataProcessor.maskTeamName(match.get("team1")));
            match.put("team2", IPLDataProcessor.maskTeamName(match.get("team2")));
            match.put("player_of_the_match", IPLDataProcessor.redactPlayer(match.get("player_of_the_match")));
        }

        csvMapper.writer(csvSchema.withHeader()).writeValue(new File(outputFilePath), matches);
    }

    public static void main(String[] args) throws Exception {
        processCsv("E:/Capg/Week5_IO_Programming/Day2/src/main/resources/input.csv", "E:/Capg/Week5_IO_Programming/Day2/src/main/resources/output.csv");
    }
}
