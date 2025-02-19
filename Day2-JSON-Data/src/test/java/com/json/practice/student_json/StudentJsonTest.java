package com.json.practice.student_json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentJsonTest {

    @Test
    public void testStudentJsonObject() {
        JSONObject student = new JSONObject();
        student.put("name", "Anshul Rai");
        student.put("age", 22);

        JSONArray subjects = new JSONArray();
        subjects.put("Mathematics");
        subjects.put("Computer Science");
        subjects.put("Physics");

        student.put("subjects", subjects);

        // Assertions
        assertEquals("Anshul Rai", student.getString("name"));
        assertEquals(22, student.getInt("age"));
        assertTrue(student.getJSONArray("subjects").toList().contains("Mathematics"));
        assertTrue(student.getJSONArray("subjects").toList().contains("Computer Science"));
        assertTrue(student.getJSONArray("subjects").toList().contains("Physics"));
    }
}
