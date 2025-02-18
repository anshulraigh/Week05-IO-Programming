package com.ioprogramming.advance.csvmerge;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;

class CsvMergerTest {
    private static final String TEST_FILE1 = "test_students1.csv";
    private static final String TEST_FILE2 = "test_students2.csv";
    private static final String OUTPUT_TEST_FILE = "test_merged_students.csv";

    @BeforeEach
    void setUp() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE1))) {
            writer.write("1,John Doe,20\n");
            writer.write("2,Jane Smith,22\n");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE2))) {
            writer.write("1,85,A\n");
            writer.write("2,90,B\n");
        }
    }

    @AfterEach
    void tearDown() {
        new File(TEST_FILE1).delete();
        new File(TEST_FILE2).delete();
        new File(OUTPUT_TEST_FILE).delete();
    }

    @Test
    void testMergeCsvFiles() throws IOException {
        CsvMerger.mergeCsvFiles();

        assertTrue(Files.exists(Paths.get(OUTPUT_TEST_FILE)));

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(OUTPUT_TEST_FILE))) {
            String header = reader.readLine();
            String row1 = reader.readLine();
            String row2 = reader.readLine();

            assertEquals("ID,Name,Age,Marks,Grade", header);
            assertEquals("1,John Doe,20,85,A", row1);
            assertEquals("2,Jane Smith,22,90,B", row2);
        }
    }
}
