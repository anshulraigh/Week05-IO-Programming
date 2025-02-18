package com.ioprogramming.intermediate.csvsearch;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;

class EmployeeSearchTest {
    private static final String TEST_FILE = "test_employees.csv";

    @BeforeEach
    void setUp() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            writer.write("John Doe,IT,50000\n");
            writer.write("Jane Smith,HR,55000\n");
        }
    }

    @AfterEach
    void tearDown() {
        new File(TEST_FILE).delete();
    }

    @Test
    void testSearchEmployee_Found() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        EmployeeSearch.searchEmployee("John Doe");

        String output = outContent.toString().trim();
        assertTrue(output.contains("Department: IT"));
        assertTrue(output.contains("Salary: 50000"));
    }

    @Test
    void testSearchEmployee_NotFound() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        EmployeeSearch.searchEmployee("Alice");

        assertTrue(outContent.toString().trim().contains("Employee not found."));
    }
}
