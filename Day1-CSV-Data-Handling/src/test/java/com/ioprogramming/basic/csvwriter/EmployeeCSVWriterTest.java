package com.ioprogramming.basic.csvwriter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeCSVWriterTest {

    private static final String TEST_FILENAME = "test_employees.csv";

    @BeforeEach
    public void setUp() throws IOException {
        // Prepare a temporary CSV file by writing 5 employee records
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(101, "Anshul", "Engineering", 75000));
        employees.add(new Employee(102, "Harshil", "Marketing", 68000));
        employees.add(new Employee(103, "Aman", "Sales", 72000));
        employees.add(new Employee(104, "Deepak", "Finance", 80000));
        employees.add(new Employee(105, "Anshul", "HR", 65000));

        EmployeeCSVWriter.writeCSV(TEST_FILENAME, employees);
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Delete the temporary file after the test
        Files.deleteIfExists(Paths.get(TEST_FILENAME));
    }

    @Test
    public void testCSVFileExists() {
        File file = new File(TEST_FILENAME);
        assertTrue(file.exists(), "CSV file should exist after writing.");
    }

    @Test
    public void testCSVFileContent() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(TEST_FILENAME));
        // Expect 6 lines: 1 header + 5 records
        assertEquals(6, lines.size(), "CSV file should have 6 lines (header + 5 records).");

        // Verify header
        assertEquals("ID,Name,Department,Salary", lines.get(0).trim());

        // Verify first employee record (example check)
        assertEquals("101,Anshul,Engineering,75000.0", lines.get(1).trim());
    }
}
