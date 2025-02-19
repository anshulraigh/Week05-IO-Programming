package com.json.practice.validate_json;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidateJson {
    public static void main(String[] args) {
        // A valid JSON string (matches the User structure)
        String validJson = "{ \"name\": \"John Doe\", \"email\": \"john@example.com\", \"age\": 30 }";
        // An invalid JSON string (missing the 'age' field)
        String invalidJson = "{ \"name\": \"John Doe\", \"email\": \"john@example.com\" }";

        System.out.println("Valid JSON check: " + validateJsonStructure(validJson));
        System.out.println("Invalid JSON check: " + validateJsonStructure(invalidJson));
    }

    /**
     * Validates the JSON structure by trying to deserialize it into a User object.
     * @param json the JSON string to validate.
     * @return true if the JSON structure is valid, false otherwise.
     */
    public static boolean validateJsonStructure(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.readValue(json, User.class);
            return true;
        } catch (Exception e) {
            System.out.println("Validation error: " + e.getMessage());
            return false;
        }
    }
}
