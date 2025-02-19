package com.json.hands_on.merge_json_files;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class MergeJsonFilesTest {

    @Test
    public void testMergeJsonObjects() throws Exception {
        // Sample JSON content for file1 and file2
        String jsonContent1 = "{ \"name\": \"Alice\", \"age\": 25 }";
        String jsonContent2 = "{ \"email\": \"alice@example.com\", \"city\": \"Wonderland\" }";

        // Create temporary files for testing
        Path tempFile1 = Files.createTempFile("file1", ".json");
        Path tempFile2 = Files.createTempFile("file2", ".json");
        Files.write(tempFile1, jsonContent1.getBytes());
        Files.write(tempFile2, jsonContent2.getBytes());

        // Parse both temporary files into JSONObjects
        JSONObject json1 = new JSONObject(new String(Files.readAllBytes(tempFile1)));
        JSONObject json2 = new JSONObject(new String(Files.readAllBytes(tempFile2)));

        // Merge the two JSON objects using the helper method
        JSONObject mergedJson = MergeJsonFiles.mergeJsonObjects(json1, json2);

        // Validate that the merged JSON contains keys from both files
        assertEquals("Alice", mergedJson.getString("name"));
        assertEquals(25, mergedJson.getInt("age"));
        assertEquals("alice@example.com", mergedJson.getString("email"));
        assertEquals("Wonderland", mergedJson.getString("city"));

        // Clean up temporary files
        Files.deleteIfExists(tempFile1);
        Files.deleteIfExists(tempFile2);
    }
}
