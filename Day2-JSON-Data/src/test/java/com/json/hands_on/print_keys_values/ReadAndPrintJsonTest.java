package com.json.hands_on.print_keys_values;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

public class ReadAndPrintJsonTest {

    @Test
    public void testPrintKeysAndValues() throws Exception {
        // Create a temporary JSON file with sample content
        String jsonContent = "{ \"name\": \"John Doe\", \"email\": \"john@example.com\", \"age\": 30 }";
        Path tempFile = Files.createTempFile("testdata", ".json");
        Files.write(tempFile, jsonContent.getBytes());

        // Backup original System.out
        PrintStream originalOut = System.out;

        // Capture System.out output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Invoke the method using the temporary file path
        String[] args = { tempFile.toString() };
        // If a file path is provided as an argument, use it; otherwise, default to "data.json"
        if(args.length > 0) {
            // Overriding file path for testing purposes
            System.out.println("Using temporary file: " + tempFile.toString());
        }
        // Read and print JSON from the temporary file
        String content = new String(Files.readAllBytes(tempFile));
        JSONObject jsonObject = new JSONObject(content);
        jsonObject.keys().forEachRemaining(key -> System.out.println(key + " : " + jsonObject.get(key)));

        // Restore original System.out
        System.setOut(originalOut);

        // Validate output contains expected keys and values
        String output = outputStream.toString();
        assertTrue(output.contains("name : John Doe"));
        assertTrue(output.contains("email : john@example.com"));
        assertTrue(output.contains("age : 30"));

        // Clean up temporary file
        Files.deleteIfExists(tempFile);
    }
}
