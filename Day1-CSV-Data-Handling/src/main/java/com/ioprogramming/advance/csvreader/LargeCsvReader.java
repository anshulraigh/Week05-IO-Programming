package com.ioprogramming.advance.csvreader;

import java.io.*;
import java.nio.file.*;

public class LargeCsvReader {
    private static final String FILE_PATH = "large_data.csv";
    private static final int CHUNK_SIZE = 100;

    public static void main(String[] args) {
        processLargeCsvFile();
    }

    public static void processLargeCsvFile() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            String line;
            int count = 0;
            int chunkCount = 0;

            while ((line = reader.readLine()) != null) {
                count++;
                if (count % CHUNK_SIZE == 0) {
                    chunkCount++;
                    System.out.println("Processed " + (chunkCount * CHUNK_SIZE) + " records so far...");
                }
            }

            if (count % CHUNK_SIZE != 0) {
                System.out.println("Total records processed: " + count);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
