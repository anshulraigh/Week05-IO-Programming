package com.ioprogramming.advance.csvtoobject;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

class CsvToStudentConverterTest {
    private static final String TEST_FILE = "test_students.csv";

    @BeforeEach
    void setUp() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            writer.write("John Doe,20,85.5\n");
            writer.write("Jane Smith,22,90.2\n");
            writer.write("Alice Johnson,19,78.8\n");
        }
    }

    @AfterEach
    void tearDown() {
        new File(TEST_FILE).delete();
    }

    @Test
    void testReadStudentsFromCsv() {
        List<Student> students = CsvToStudentConverter.readStudentsFromCsv();
        assertEquals(3, students.size());
        assertTrue(students.toString().contains("John Doe"));
        assertTrue(students.toString().contains("Jane Smith"));
        assertTrue(students.toString().contains("Alice Johnson"));
    }
}
