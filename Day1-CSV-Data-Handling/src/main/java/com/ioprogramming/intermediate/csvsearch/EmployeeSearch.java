package com.ioprogramming.intermediate.csvsearch;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class EmployeeSearch {
    private static final String FILE_PATH = "employees.csv";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java EmployeeSearch <employee_name>");
            return;
        }

        String employeeName = args[0];
        searchEmployee(employeeName);
    }

    public static void searchEmployee(String name) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 3 && details[0].trim().equalsIgnoreCase(name)) {
                    System.out.println("Department: " + details[1].trim());
                    System.out.println("Salary: " + details[2].trim());
                    return;
                }
            }
            System.out.println("Employee not found.");
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
