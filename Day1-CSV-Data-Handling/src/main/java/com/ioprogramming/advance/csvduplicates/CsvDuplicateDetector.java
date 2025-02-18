package com.ioprogramming.advance.csvduplicates;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CsvDuplicateDetector {
    private static final String FILE_PATH = "employees.csv";

    public static void main(String[] args) {
        detectDuplicates();
    }

    public static void detectDuplicates() {
        Map<String, String> records = new HashMap<>();
        Set<String> duplicates = new HashSet<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length > 1) {
                    String id = details[0].trim();
                    if (records.containsKey(id)) {
                        duplicates.add(line);
                    } else {
                        records.put(id, line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        if (duplicates.isEmpty()) {
            System.out.println("No duplicate records found.");
        } else {
            System.out.println("Duplicate records found:");
            duplicates.forEach(System.out::println);
        }
    }
}
