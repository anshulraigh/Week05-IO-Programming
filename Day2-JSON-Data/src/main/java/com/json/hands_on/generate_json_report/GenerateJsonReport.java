package com.json.hands_on.generate_json_report;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GenerateJsonReport {
    public static void main(String[] args) {
        // JDBC URL for H2 in-memory database
        String jdbcUrl = "jdbc:mysql://localhost:3000/generateResponse";
        String username = "root";
        String password = "Anshulrai@05";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement()) {

            // Create a sample table and insert records
            String createTable = "CREATE TABLE employees (id INT PRIMARY KEY, name VARCHAR(255), email VARCHAR(255), age INT)";
            statement.execute(createTable);

            String insert1 = "INSERT INTO employees VALUES (1, 'Anshul', 'anshul@example.com', 30)";
            String insert2 = "INSERT INTO employees VALUES (2, 'Aman', 'aman@example.com', 25)";
            String insert3 = "INSERT INTO employees VALUES (3, 'Harshil', 'harshil@example.com', 35)";
            statement.execute(insert1);
            statement.execute(insert2);
            statement.execute(insert3);

            // Query all records from the employees table
            String query = "SELECT * FROM employees";
            ResultSet rs = statement.executeQuery(query);

            // Build a JSON array report from the result set
            JSONArray jsonArray = new JSONArray();
            while (rs.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", rs.getInt("id"));
                jsonObject.put("name", rs.getString("name"));
                jsonObject.put("email", rs.getString("email"));
                jsonObject.put("age", rs.getInt("age"));
                jsonArray.put(jsonObject);
            }

            // Print the JSON report with pretty printing
            System.out.println(jsonArray.toString(4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
