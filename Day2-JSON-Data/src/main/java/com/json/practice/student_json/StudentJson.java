package com.json.practice.student_json;

import org.json.JSONArray;
import org.json.JSONObject;

public class StudentJson {
    public static void main(String[] args) {
        // Creating a JSON Object for Student
        JSONObject student = new JSONObject();
        student.put("name", "Anshul Rai");
        student.put("age", 22);

        // Adding subjects as an array
        JSONArray subjects = new JSONArray();
        subjects.put("Mathematics");
        subjects.put("Computer Science");
        subjects.put("Physics");

        student.put("subjects", subjects);

        // Printing JSON Object
        System.out.println(student.toString(4)); // Pretty print with indentation
    }
}
