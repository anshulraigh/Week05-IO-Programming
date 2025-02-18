package com.ioprogramming.advance.csvvalidator;

import java.io.*;
import java.nio.file.*;
import java.util.regex.*;

public class CsvDataValidator {
    private static final String FILE_PATH = "employees.csv";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PHONE_REGEX = "^[0-9]{10}$";

    public static void main(String[] args) {
        validateCsvData();
    }

    public static void validateCsvData() {
        Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
        Pattern phonePattern = Pattern.compile(PHONE_REGEX);

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length != 3) {
                    System.out.println("Invalid row format: " + line);
                    continue;
                }

                String email = details[1].trim();
                String phone = details[2].trim();

                boolean isValidEmail = emailPattern.matcher(email).matches();
                boolean isValidPhone = phonePattern.matcher(phone).matches();

                if (!isValidEmail || !isValidPhone) {
                    System.out.println("Invalid data -> Email: " + email + ", Phone: " + phone);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
