package com.ioprogramming.advance.dbtocsv;

import java.io.*;
import java.sql.*;
import java.nio.file.*;

public class DbToCsvExporter {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DATABASE_USER = "your_username";
    private static final String DATABASE_PASSWORD = "your_password";
    private static final String OUTPUT_FILE = "employee_report.csv";

    public static void main(String[] args) {
        fetchAndWriteToCsv();
    }

    public static void fetchAndWriteToCsv() {
        String query = "SELECT employee_id, name, department, salary FROM employees";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query);
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(OUTPUT_FILE))) {

            writer.write("Employee ID,Name,Department,Salary\n");

            while (rs.next()) {
                String employeeId = rs.getString("employee_id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                double salary = rs.getDouble("salary");

                writer.write(employeeId + "," + name + "," + department + "," + salary + "\n");
            }

            System.out.println("CSV report generated: " + OUTPUT_FILE);
        } catch (SQLException | IOException e) {
            System.err.println("Error fetching or writing data: " + e.getMessage());
        }
    }
}
