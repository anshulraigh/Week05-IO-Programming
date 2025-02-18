package com.ioprogramming.intermediate.csvsorter;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class CsvSalarySorter {
    public static void main(String[] args) {
        sortAndPrintTopSalaries("employees.csv");
    }

    public static void sortAndPrintTopSalaries(String filePath) {
        List<String[]> employees = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 3) {
                    employees.add(details);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        employees.sort((a, b) -> Double.compare(
                Double.parseDouble(b[2].trim()), Double.parseDouble(a[2].trim())
        ));

        System.out.println("Top 5 highest-paid employees:");
        employees.stream().limit(5).forEach(e ->
                System.out.println("Name: " + e[0] + ", Department: " + e[1] + ", Salary: " + e[2])
        );
    }
}
