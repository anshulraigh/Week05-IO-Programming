package com.ioprogramming.advance.dbtocsv;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.sql.*;

class DbToCsvExporterTest {
    private static final String OUTPUT_TEST_FILE = "test_employee_report.csv";
    private static final String DATABASE_URL = "jdbc:h2:mem:testdb";
    private static final String DATABASE_USER = "sa";
    private static final String DATABASE_PASSWORD = "";

    @BeforeEach
    void setUp() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE employees (employee_id INT, name VARCHAR(255), department VARCHAR(255), salary DECIMAL)");
            stmt.execute("INSERT INTO employees VALUES (1, 'John Doe', 'IT', 60000), (2, 'Jane Smith', 'HR', 55000)");
        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE employees");
        }
    }

    @Test
    void testFetchAndWriteToCsv() throws IOException {
        DbToCsvExporter.fetchAndWriteToCsv();

        assertTrue(Files.exists(Paths.get(OUTPUT_TEST_FILE)));

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(OUTPUT_TEST_FILE))) {
            String header = reader.readLine();
            String row1 = reader.readLine();
            String row2 = reader.readLine();

            assertEquals("Employee ID,Name,Department,Salary", header);
            assertEquals("1,John Doe,IT,60000.0", row1);
            assertEquals("2,Jane Smith,HR,55000.0", row2);
        }
    }
}
