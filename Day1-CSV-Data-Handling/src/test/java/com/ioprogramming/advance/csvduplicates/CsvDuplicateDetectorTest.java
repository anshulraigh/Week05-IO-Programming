package com.ioprogramming.advance.csvduplicates;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

class CsvDuplicateDetectorTest {
    private static final String TEST_FILE = "test_employees.csv";

    @BeforeEach
    void setUp() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            writer.write("101,John Doe,IT\n");
            writer.write("102,Jane Smith,HR\n");
            writer.write("103,Alice Brown,Finance\n");
            writer.write("101,John Doe,IT\n"); // Duplicate entry
            writer.write("104,David Wilson,Sales\n");
            writer.write("102,Jane Smith,HR\n"); // Duplicate entry
        }
    }

    @AfterEach
    void tearDown() {
        new File(TEST_FILE).delete();
    }

    @Test
    void testDetectDuplicates() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CsvDuplicateDetector.detectDuplicates();

        String output = outContent.toString().trim();
        assertTrue(output.contains("Duplicate records found:"));
        assertTrue(output.contains("101,John Doe,IT"));
        assertTrue(output.contains("102,Jane Smith,HR"));
    }
}
