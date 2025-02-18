package com.ioprogramming.basic.rowcounter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String filename = "students.csv"; // Change this to the actual file path

        try {
            int rowCount = CSVRowCounter.countRows(filename);
            System.out.println("Total number of records (excluding header): " + rowCount);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
