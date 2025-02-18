package com.ioprogramming.basic.csvreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentCSVReader {

    /**
     * Reads a CSV file containing student details (ID, Name, Age, Marks)
     * and returns a list of Student objects.
     *
     * @param filename the CSV file path
     * @return list of Student objects
     * @throws IOException if an I/O error occurs
     */
    public static List<Student> readCSV(String filename) throws IOException {
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            // Optionally, skip header if present
            // br.readLine();
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length < 4) {
                    System.out.println("Skipping invalid record: " + line);
                    continue;
                }
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int age = Integer.parseInt(parts[2].trim());
                    double marks = Double.parseDouble(parts[3].trim());
                    students.add(new Student(id, name, age, marks));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping record with invalid number format: " + line);
                }
            }
        }
        return students;
    }

    /**
     * Prints the student records in a structured format.
     *
     * @param students list of Student objects
     */
    public static void printStudents(List<Student> students) {
        for (Student student : students) {
            System.out.printf("ID: %d, Name: %s, Age: %d, Marks: %.2f%n",
                    student.getId(), student.getName(), student.getAge(), student.getMarks());
        }
    }

    public static void main(String[] args) {
        String filename = "C:\\Users\\Public\\CapgeminiHandsOn\\Week5-IO-Programming\\Day1-CSV-Data-Handling\\src\\main\\java\\com\\ioprogramming\\basic\\csvreader\\students.csv";
        try {
            List<Student> students = readCSV(filename);
            printStudents(students);
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
    }
}
