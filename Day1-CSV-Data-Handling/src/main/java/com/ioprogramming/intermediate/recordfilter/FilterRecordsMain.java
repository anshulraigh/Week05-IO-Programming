package com.ioprogramming.intermediate.recordfilter;

import java.io.IOException;

/**
 * Main class to run the filter program.
 */
public class FilterRecordsMain {
    public static void main(String[] args) {
        String filename = "students.csv"; // Change this to actual file path

        try {
            FilterRecordsFromCSV.filterHighScoringStudents(filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
