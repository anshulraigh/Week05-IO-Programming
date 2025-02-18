package com.ioprogramming.advance.csvvalidator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;

class CsvDataValidatorTest {
    private static final String TEST_FILE = "test_employees.csv";

    @BeforeEach
    void setUp() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            writer.write("John Doe,john.doe@example.com,1234567890\n");
            writer.write("Jane Smith,jane.smith@company,987654321\n"); // Invalid email & phone
            writer.write("Alice Johnson,alice.johnson@example.com,abcdefghij\n"); // Invalid phone
        }
    }

    @AfterEach
    void tearDown() {
        new File(TEST_FILE).delete();
    }

    @Test
    void testValidateCsvData() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CsvDataValidator.validateCsvData();

        String output = outContent.toString().trim();
        assertTrue(output.contains("Invalid data -> Email: jane.smith@company, Phone: 987654321"));
        assertTrue(output.contains("Invalid data -> Email: alice.johnson@example.com, Phone: abcdefghij"));
    }
}
