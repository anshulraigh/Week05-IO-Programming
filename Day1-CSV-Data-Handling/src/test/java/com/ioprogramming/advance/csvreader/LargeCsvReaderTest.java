package com.ioprogramming.advance.csvreader;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;

class LargeCsvReaderTest {
    private static final String TEST_FILE = "test_large_data.csv";

    @BeforeEach
    void setUp() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            for (int i = 1; i <= 250; i++) {
                writer.write("Row " + i + "\n");
            }
        }
    }

    @AfterEach
    void tearDown() {
        new File(TEST_FILE).delete();
    }

    @Test
    void testProcessLargeCsvFile() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        LargeCsvReader.processLargeCsvFile();

        String output = outContent.toString().trim();
        assertTrue(output.contains("Processed 100 records so far..."));
        assertTrue(output.contains("Processed 200 records so far..."));
        assertTrue(output.contains("Total records processed: 250"));
    }
}
