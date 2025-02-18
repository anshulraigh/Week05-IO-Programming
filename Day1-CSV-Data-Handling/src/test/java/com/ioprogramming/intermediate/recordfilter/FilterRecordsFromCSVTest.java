package com.ioprogramming.intermediate.recordfilter;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FilterRecordsFromCSVTest {

    @Test
    void testFilterHighScoringStudents() throws IOException {
        String testCsv = "test_students.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(testCsv))) {
            writer.println("ID,Name,Age,Marks");
            writer.println("1,Anshul,20,85");
            writer.println("2,Harshil,22,75");
            writer.println("3,Aman,21,90");
            writer.println("4,Deepak,23,65");
        }

        List<String> highScorers;
        highScorers = FilterRecordsFromCSV.filterHighScoringStudents(testCsv);

        assertEquals(2, highScorers.size());
        assertTrue(highScorers.contains("Anshul - 85"));
        assertTrue(highScorers.contains("Aman - 90"));

        new File(testCsv).delete();
    }
}
