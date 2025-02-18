package com.ioprogramming.advance.jsoncsv;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class JsonCsvConverter {
    private static final String JSON_FILE_PATH = "students.json";
    private static final String CSV_FILE_PATH = "students.csv";

    public static void main(String[] args) {
        convertJsonToCsv();
        convertCsvToJson();
    }

    public static void convertJsonToCsv() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Read JSON file
            List<Student> students = Arrays.asList(objectMapper.readValue(new File(JSON_FILE_PATH), Student[].class));

            // Write to CSV
            try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH))) {
                writer.writeNext(new String[]{"ID", "Name", "Age", "Grade"});
                for (Student student : students) {
                    writer.writeNext(new String[]{String.valueOf(student.getId()), student.getName(), String.valueOf(student.getAge()), student.getGrade()});
                }
            }

            System.out.println("Converted JSON to CSV: " + CSV_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error during conversion: " + e.getMessage());
        }
    }

    public static void convertCsvToJson() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))) {
            List<Student> students = new ArrayList<>();
            String line;
            reader.readLine(); // Skip header row
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                students.add(new Student(Integer.parseInt(columns[0]), columns[1], Integer.parseInt(columns[2]), columns[3]));
            }

            // Convert to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("students_converted.json"), students);

            System.out.println("Converted CSV to JSON: students_converted.json");
        } catch (IOException e) {
            System.err.println("Error during conversion: " + e.getMessage());
        }
    }

    // Student class to map the data
    static class Student {
        private int id;
        private String name;
        private int age;
        private String grade;

        public Student(int id, String name, int age, String grade) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.grade = grade;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getGrade() {
            return grade;
        }
    }
}
