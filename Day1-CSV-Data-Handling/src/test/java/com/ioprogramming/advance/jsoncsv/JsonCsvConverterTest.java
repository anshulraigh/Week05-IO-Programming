package com.ioprogramming.advance.jsoncsv;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

class JsonCsvConverterTest {
    private static final String JSON_TEST_FILE = "test_students.json";
    private static final String CSV_TEST_FILE = "test_students.csv";
    private static final String CONVERTED_JSON_FILE = "test_students_converted.json";

    @BeforeEach
    void setUp() throws IOException {
        List<JsonCsvConverter.Student> students = Arrays.asList(
                new JsonCsvConverter.Student(1, "John Doe", 20, "A"),
                new JsonCsvConverter.Student(2, "Jane Smith", 22, "B")
        );

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(JSON_TEST_FILE), students);
    }

    @AfterEach
    void tearDown() {
        new File(JSON_TEST_FILE).delete();
        new File(CSV_TEST_FILE).delete();
        new File(CONVERTED_JSON_FILE).delete();
    }

    @Test
    void testConvertJsonToCsv() throws IOException {
        JsonCsvConverter.convertJsonToCsv();

        assertTrue(Files.exists(Paths.get(CSV_TEST_FILE)));

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(CSV_TEST_FILE))) {
            String header = reader.readLine();
            String row1 = reader.readLine();
            String row2 = reader.readLine();

            assertEquals("ID,Name,Age,Grade", header);
            assertEquals("1,John Doe,20,A", row1);
            assertEquals("2,Jane Smith,22,B", row2);
        }
    }

    @Test
    void testConvertCsvToJson() throws IOException {
        JsonCsvConverter.convertJsonToCsv(); // First convert JSON to CSV
        JsonCsvConverter.convertCsvToJson(); // Then convert CSV back to JSON

        assertTrue(Files.exists(Paths.get(CONVERTED_JSON_FILE)));

        ObjectMapper objectMapper = new ObjectMapper();
        List<JsonCsvConverter.Student> students = Arrays.asList(objectMapper.readValue(new File(CONVERTED_JSON_FILE), JsonCsvConverter.Student[].class));

        assertEquals(2, students.size());
        assertEquals("John Doe", students.get(0).getName());
        assertEquals("Jane Smith", students.get(1).getName());
    }
}
