package com.ioprogramming.basic.rowcounter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVRowCounter {

    /**
     * Reads a CSV file and counts the number of records excluding the header row.
     *
     * @param filename the CSV file path
     * @return the number of records (excluding the header)
     * @throws IOException if an I/O error occurs
     */
    public static int countRows(String filename) throws IOException {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Read header and ignore
            String header = br.readLine();
            if (header == null) {
                return 0; // Empty file
            }
            // Count remaining lines
            while (br.readLine() != null) {
                count++;
            }
        }
        return count;
    }
}
