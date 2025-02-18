package com.ioprogramming.basic.csvwriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCSVWriter {

    /**
     * Writes employee data to a CSV file.
     *
     * @param filename the file name to write the data
     * @param employees list of Employee objects to write
     * @throws IOException if an I/O error occurs
     */
    public static void writeCSV(String filename, List<Employee> employees) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            // Optionally, write header line
            writer.write("ID,Name,Department,Salary\n");
            for (Employee emp : employees) {
                writer.write(emp.toString() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        // Create a list of at least 5 employee records
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(101, "Anshul", "Engineering", 75000));
        employees.add(new Employee(102, "Harshil", "Marketing", 68000));
        employees.add(new Employee(103, "Aman", "Sales", 72000));
        employees.add(new Employee(104, "Deepak", "Finance", 80000));
        employees.add(new Employee(105, "Aditya", "HR", 65000)); // Example: second record with name Anshul

        String filename = "C:\\Users\\Public\\CapgeminiHandsOn\\Week5-IO-Programming\\Day1-CSV-Data-Handling\\src\\main\\java\\com\\ioprogramming\\basic\\csvwriter\\example.csv";
        try {
            writeCSV(filename, employees);
            System.out.println("CSV file '" + filename + "' written successfully.");
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }
}
