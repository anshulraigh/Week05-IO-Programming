package com.ioprogramming.advance.csvmerge;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CsvMerger {
    private static final String FILE1_PATH = "students1.csv";
    private static final String FILE2_PATH = "students2.csv";
    private static final String OUTPUT_FILE = "merged_students.csv";

    public static void main(String[] args) {
        mergeCsvFiles();
    }

    public static void mergeCsvFiles() {
        Map<String, String[]> studentData = new HashMap<>();

        try (BufferedReader reader1 = Files.newBufferedReader(Paths.get(FILE1_PATH))) {
            String line;
            while ((line = reader1.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 3) {
                    studentData.put(details[0].trim(), details);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file1: " + e.getMessage());
            return;
        }

        try (BufferedReader reader2 = Files.newBufferedReader(Paths.get(FILE2_PATH))) {
            String line;
            while ((line = reader2.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 3) {
                    String id = details[0].trim();
                    if (studentData.containsKey(id)) {
                        String[] firstFileData = studentData.get(id);
                        studentData.put(id, new String[]{firstFileData[0], firstFileData[1], firstFileData[2], details[1], details[2]});
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file2: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(OUTPUT_FILE))) {
            writer.write("ID,Name,Age,Marks,Grade\n");
            for (String[] details : studentData.values()) {
                writer.write(String.join(",", details) + "\n");
            }
            System.out.println("Merged file created: " + OUTPUT_FILE);
        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
        }
    }
}
