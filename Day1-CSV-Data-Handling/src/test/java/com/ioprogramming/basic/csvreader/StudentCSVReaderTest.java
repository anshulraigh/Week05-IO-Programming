package com.ioprogramming.basic.csvreader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentCSVReaderTest {

    private static final String TEST_FILENAME = "test_students.csv";

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary CSV file with student records using specified names.
        try (FileWriter writer = new FileWriter(TEST_FILENAME)) {
            writer.write("101, Anshul, 20, 85.5\n");
            writer.write("102, Harshil, 21, 90.0\n");
            writer.write("103, Aman, 19, 78.3\n");
        }
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Delete the temporary file after the test
        Files.deleteIfExists(Paths.get(TEST_FILENAME));
    }

    @Test
    public void testReadCSV() throws IOException {
        List<Student> students = StudentCSVReader.readCSV(TEST_FILENAME);
        assertEquals(3, students.size());

        Student student1 = students.get(0);
        assertEquals(101, student1.getId());
        assertEquals("Anshul", student1.getName());
        assertEquals(20, student1.getAge());
        assertEquals(85.5, student1.getMarks(), 0.001);

        Student student2 = students.get(1);
        assertEquals(102, student2.getId());
        assertEquals("Harshil", student2.getName());
        assertEquals(21, student2.getAge());
        assertEquals(90.0, student2.getMarks(), 0.001);

        Student student3 = students.get(2);
        assertEquals(103, student3.getId());
        assertEquals("Aman", student3.getName());
        assertEquals(19, student3.getAge());
        assertEquals(78.3, student3.getMarks(), 0.001);
    }
}
