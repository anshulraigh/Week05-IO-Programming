package com.json.hands_on.convert_csv_to_json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

public class ConvertCsvToJsonTest {

    @Test
    public void testCsvToJsonConversion() throws Exception {
        // Sample CSV content with a header and two data rows
        String csvContent = "name,age,email\n" +
                "Anshul,25,anshul@example.com\n" +
                "Harshil,25,harshil@example.com\n" +
                "Aman,30,aman@example.com";

        // Create a temporary CSV file for testing
        Path tempFile = Files.createTempFile("testData", ".csv");
        Files.write(tempFile, csvContent.getBytes());

        // Read CSV data from the temporary file
        // Simulate the same logic as in the main method
        // Read all lines
        java.util.List<String> lines = Files.readAllLines(tempFile);
        assertFalse(lines.isEmpty(), "CSV file should not be empty");

        String headerLine = lines.get(0);
        String[] headers = headerLine.split(",");

        JSONArray jsonArray = new JSONArray();
        for (int i = 1; i < lines.size(); i++) {
            String[] values = lines.get(i).split(",");
            JSONObject jsonObject = new JSONObject();
            for (int j = 0; j < headers.length && j < values.length; j++) {
                jsonObject.put(headers[j].trim(), values[j].trim());
            }
            jsonArray.put(jsonObject);
        }

        // Validate the JSON conversion
        assertEquals(2, jsonArray.length());
        JSONObject firstRecord = jsonArray.getJSONObject(0);
        assertEquals("Alice", firstRecord.getString("name"));
        assertEquals("25", firstRecord.getString("age"));
        assertEquals("alice@example.com", firstRecord.getString("email"));

        JSONObject secondRecord = jsonArray.getJSONObject(1);
        assertEquals("Bob", secondRecord.getString("name"));
        assertEquals("30", secondRecord.getString("age"));
        assertEquals("bob@example.com", secondRecord.getString("email"));

        // Clean up the temporary file
        Files.deleteIfExists(tempFile);
    }
}
