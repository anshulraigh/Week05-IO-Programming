package com.json.hands_on.generate_json_report;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import static org.junit.jupiter.api.Assertions.*;

public class GenerateJsonReportTest {

    @Test
    public void testGenerateJsonReport() throws Exception {
        // JDBC URL for H2 in-memory database
        String jdbcUrl = "jdbc:mysql://localhost:3000/generateResponse";
        String username = "root";
        String password = "Anshulrai@05";

        JSONArray jsonArray = new JSONArray();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement()) {

            // Create table and insert sample records
            String createTable = "CREATE TABLE employees (id INT PRIMARY KEY, name VARCHAR(255), email VARCHAR(255), age INT)";
            statement.execute(createTable);

            String insert1 = "INSERT INTO employees VALUES (1, 'Alice', 'alice@example.com', 30)";
            String insert2 = "INSERT INTO employees VALUES (2, 'Bob', 'bob@example.com', 25)";
            String insert3 = "INSERT INTO employees VALUES (3, 'Charlie', 'charlie@example.com', 35)";
            statement.execute(insert1);
            statement.execute(insert2);
            statement.execute(insert3);

            // Query all records
            String query = "SELECT * FROM employees";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", rs.getInt("id"));
                jsonObject.put("name", rs.getString("name"));
                jsonObject.put("email", rs.getString("email"));
                jsonObject.put("age", rs.getInt("age"));
                jsonArray.put(jsonObject);
            }
        }

        // Validate JSON report content
        assertEquals(3, jsonArray.length());

        JSONObject firstRecord = jsonArray.getJSONObject(0);
        assertEquals("Alice", firstRecord.getString("name"));
        assertEquals(30, firstRecord.getInt("age"));

        JSONObject secondRecord = jsonArray.getJSONObject(1);
        assertEquals("Bob", secondRecord.getString("name"));
        assertEquals(25, secondRecord.getInt("age"));

        JSONObject thirdRecord = jsonArray.getJSONObject(2);
        assertEquals("Charlie", thirdRecord.getString("name"));
        assertEquals(35, thirdRecord.getInt("age"));
    }
}
