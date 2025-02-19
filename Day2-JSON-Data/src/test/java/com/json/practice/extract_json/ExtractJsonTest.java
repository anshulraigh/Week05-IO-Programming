package com.json.practice.extract_json;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExtractJsonTest {

    @Test
    public void testExtractNameAndEmail() throws Exception {
        // Create a temporary JSON file for testing
        String jsonContent = "{ \"name\": \"John Doe\", \"email\": \"john.doe@example.com\", \"age\": 30 }";
        Path tempFile = Files.createTempFile("testdata", ".json");
        Files.write(tempFile, jsonContent.getBytes());

        // Read the content of the temporary file
        String content = new String(Files.readAllBytes(tempFile));
        JSONObject jsonObject = new JSONObject(content);

        // Extract fields
        String name = jsonObject.getString("name");
        String email = jsonObject.getString("email");

        // Validate the extracted values
        assertEquals("John Doe", name);
        assertEquals("john.doe@example.com", email);

        // Clean up temporary file
        Files.deleteIfExists(tempFile);
    }
}
