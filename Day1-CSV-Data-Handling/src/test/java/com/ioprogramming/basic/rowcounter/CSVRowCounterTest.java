package com.ioprogramming.basic.rowcounter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class CSVRowCounterTest {

    private static final String TEST_FILENAME = "test_data.csv";

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary CSV file with a header and 4 records
        try (FileWriter writer = new FileWriter(TEST_FILENAME)) {
            writer.write("ID,Name,Age,Marks\n");
            writer.write("1,Anshul,22,85\n");
            writer.write("2,Harshil,23,78\n");
            writer.write("3,Aman,21,90\n");
            writer.write("4,Deepak,22,88\n");
        }
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILENAME));
    }

    @Test
    public void testCountRows() throws IOException {
        int count = CSVRowCounter.countRows(TEST_FILENAME);
        assertEquals(4, count, "Row count should be 4 (excluding header).");
    }

    @Test
    public void testCountRows_EmptyFile() throws IOException {
        Files.write(Paths.get(TEST_FILENAME), new byte[0]); // Create an empty file
        int count = CSVRowCounter.countRows(TEST_FILENAME);
        assertEquals(0, count, "Row count should be 0 for an empty file.");
    }
}
