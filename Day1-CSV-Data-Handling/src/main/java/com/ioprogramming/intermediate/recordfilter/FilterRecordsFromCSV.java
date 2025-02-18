package com.ioprogramming.intermediate.recordfilter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Reads a CSV file and filters students who have scored more than 80 marks.
 */
public class FilterRecordsFromCSV {

    /**
     * Reads and filters students scoring more than 80.
     *
     * @param filename the CSV file path
     * @return
     * @throws IOException if an I/O error occurs
     */
    public static List<String> filterHighScoringStudents(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // Read header
            if (line == null) {
                System.out.println("CSV file is empty.");
                return null;
            }
            System.out.println("Students scoring more than 80 marks:");
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    String name = data[1].trim();
                    int marks = Integer.parseInt(data[3].trim());
                    if (marks > 80) {
                        System.out.println(name + " - " + marks);
                    }
                }
            }
        }
        return null;
    }
}
