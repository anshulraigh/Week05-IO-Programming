package com.json.iplcensorship;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;
import java.util.Map;

public class IPLJsonProcessor {
    public static void processJson(String inputFilePath, String outputFilePath) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String, Object>> matches = objectMapper.readValue(new File(inputFilePath), List.class);

        for (Map<String, Object> match : matches) {
            match.put("team1", IPLDataProcessor.maskTeamName((String) match.get("team1")));
            match.put("team2", IPLDataProcessor.maskTeamName((String) match.get("team2")));
            match.put("player_of_the_match", IPLDataProcessor.redactPlayer((String) match.get("player_of_the_match")));
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFilePath), matches);
    }

    public static void main(String[] args) throws Exception {
        processJson("E:/Capg/Week5_I)_Programming/Day2/src/main/resources/input.json", "src/main/resources/output.json");
    }
}
