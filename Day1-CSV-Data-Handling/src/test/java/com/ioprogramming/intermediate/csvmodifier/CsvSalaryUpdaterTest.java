package com.ioprogramming.intermediate.csvmodifier;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

class CsvSalaryUpdaterTest {
    private static final String TEST_INPUT_FILE = "test_employees.csv";
    private static final String TEST_OUTPUT_FILE = "test_updated_employees.csv";

    @BeforeEach
    void setUp() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_INPUT_FILE))) {
            writer.write("John Doe,IT,50000\n");
            writer.write("Jane Smith,HR,55000\n");
            writer.write("Alice Johnson,IT,60000\n");
        }
        CsvSalaryUpdater.INPUT_FILE = TEST_INPUT_FILE;
        CsvSalaryUpdater.OUTPUT_FILE = TEST_OUTPUT_FILE;
    }

    @AfterEach
    void tearDown() {
        new File(TEST_INPUT_FILE).delete();
        new File(TEST_OUTPUT_FILE).delete();
    }

    @Test
    void testUpdateSalaries() throws IOException {
        CsvSalaryUpdater.updateSalaries();

        List<String> lines = Files.readAllLines(Paths.get(TEST_OUTPUT_FILE));

        assertEquals("John Doe,IT,55000.00", lines.get(0));
        assertEquals("Jane Smith,HR,55000", lines.get(1));
        assertEquals("Alice Johnson,IT,66000.00", lines.get(2));
    }
}
