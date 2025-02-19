package com.json.hands_on.convert_csv_to_json;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ConvertCsvToJson {
    public static void main(String[] args) {
        // CSV file path (ensure this file exists in the project directory)
        String csvFilePath = "data.csv";

        try {
            // Read all lines from the CSV file
            List<String> lines = Files.readAllLines(Paths.get(csvFilePath));

            // Check if the CSV file has at least a header row
            if (lines.isEmpty()) {
                System.out.println("CSV file is empty.");
                return;
            }

            // The first line is assumed to be the header containing keys
            String headerLine = lines.get(0);
            String[] headers = headerLine.split(",");

            // Create a JSON array to store the CSV records as JSON objects
            JSONArray jsonArray = new JSONArray();

            // Process each subsequent line as a record
            for (int i = 1; i < lines.size(); i++) {
                String[] values = lines.get(i).split(",");
                JSONObject jsonObject = new JSONObject();

                // Map header keys to their corresponding values
                for (int j = 0; j < headers.length && j < values.length; j++) {
                    jsonObject.put(headers[j].trim(), values[j].trim());
                }
                jsonArray.put(jsonObject);
            }

            // Print the resulting JSON array (pretty print with 2-space indentation)
            System.out.println(jsonArray.toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
