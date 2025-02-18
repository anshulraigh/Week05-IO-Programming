package com.ioprogramming.advance.csvtoobject;

import java.io.*;
import java.nio.file.*;
import java.util.*;

class Student {
    private String name;
    private int age;
    private double grade;

    public Student(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", grade=" + grade + "}";
    }
}

public class CsvToStudentConverter {
    private static final String FILE_PATH = "students.csv";

    public static void main(String[] args) {
        List<Student> students = readStudentsFromCsv();
        students.forEach(System.out::println);
    }

    public static List<Student> readStudentsFromCsv() {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 3) {
                    String name = details[0].trim();
                    int age = Integer.parseInt(details[1].trim());
                    double grade = Double.parseDouble(details[2].trim());
                    students.add(new Student(name, age, grade));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return students;
    }
}
