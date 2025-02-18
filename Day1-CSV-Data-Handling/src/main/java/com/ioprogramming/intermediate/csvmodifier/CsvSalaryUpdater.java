package com.ioprogramming.intermediate.csvmodifier;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CsvSalaryUpdater {
    public static String INPUT_FILE = "employees.csv";
    public static String OUTPUT_FILE = "updated_employees.csv";

    public static void main(String[] args) {
        updateSalaries();
    }

    public static void updateSalaries() {
        List<String[]> updatedRecords = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(INPUT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 3 && details[1].trim().equalsIgnoreCase("IT")) {
                    double salary = Double.parseDouble(details[2].trim());
                    salary *= 1.10; // Increase by 10%
                    details[2] = String.format("%.2f", salary);
                }
                updatedRecords.add(details);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(OUTPUT_FILE))) {
            for (String[] record : updatedRecords) {
                writer.write(String.join(",", record));
                writer.newLine();
            }
            System.out.println("Updated salaries saved to " + OUTPUT_FILE);
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}
