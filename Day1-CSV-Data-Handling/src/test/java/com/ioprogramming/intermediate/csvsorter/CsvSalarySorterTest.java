package com.ioprogramming.intermediate.csvsorter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

class CsvSalarySorterTest {
    private static final String TEST_FILE = "test_employees.csv";

    @BeforeEach
    void setUp() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            writer.write("John Doe,IT,50000\n");
            writer.write("Jane Smith,HR,60000\n");
            writer.write("Alice Johnson,IT,70000\n");
            writer.write("Bob Brown,Finance,80000\n");
            writer.write("Charlie White,Marketing,90000\n");
            writer.write("David Black,HR,100000\n");
        }
    }

    @AfterEach
    void tearDown() {
        new File(TEST_FILE).delete();
    }

    @Test
    void testSortAndPrintTopSalaries() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CsvSalarySorter.sortAndPrintTopSalaries(TEST_FILE);

        String output = outContent.toString().trim();
        assertTrue(output.contains("David Black,HR,100000"));
        assertTrue(output.contains("Charlie White,Marketing,90000"));
        assertTrue(output.contains("Bob Brown,Finance,80000"));
        assertTrue(output.contains("Alice Johnson,IT,70000"));
        assertTrue(output.contains("Jane Smith,HR,60000"));
    }
}
