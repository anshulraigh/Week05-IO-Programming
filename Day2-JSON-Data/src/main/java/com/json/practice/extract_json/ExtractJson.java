package com.json.practice.extract_json;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExtractJson {
    public static void main(String[] args) {
        // Path to the JSON file
        String filePath = "data.json"; // Ensure this file exists in the project directory

        try {
            // Read the content of the JSON file
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Parse the JSON content
            JSONObject jsonObject = new JSONObject(content);

            // Extract specific fields: name and email
            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");

            // Print the extracted values
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
