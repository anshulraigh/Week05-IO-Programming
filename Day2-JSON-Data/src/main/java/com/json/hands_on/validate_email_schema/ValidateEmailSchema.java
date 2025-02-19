package com.json.hands_on.validate_email_schema;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ValidateEmailSchema {
    public static void main(String[] args) {
        // JSON Schema definition for an object with an "email" field using the "email" format
        String schemaStr = "{\n" +
                "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"properties\": {\n" +
                "    \"email\": {\n" +
                "      \"type\": \"string\",\n" +
                "      \"format\": \"email\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"required\": [\"email\"]\n" +
                "}";

        // Load the JSON Schema
        JSONObject jsonSchema = new JSONObject(new JSONTokener(schemaStr));
        Schema schema = SchemaLoader.load(jsonSchema);

        // Example JSON with a valid email
        String validJsonStr = "{ \"email\": \"john.doe@example.com\" }";
        JSONObject validJson = new JSONObject(validJsonStr);

        // Example JSON with an invalid email
        String invalidJsonStr = "{ \"email\": \"not-an-email\" }";
        JSONObject invalidJson = new JSONObject(invalidJsonStr);

        // Validate the valid JSON
        try {
            schema.validate(validJson);
            System.out.println("Valid JSON email passed validation.");
        } catch (Exception e) {
            System.out.println("Valid JSON email failed validation: " + e.getMessage());
        }

        // Validate the invalid JSON
        try {
            schema.validate(invalidJson);
            System.out.println("Invalid JSON email passed validation (unexpected).");
        } catch (Exception e) {
            System.out.println("Invalid JSON email failed validation: " + e.getMessage());
        }
    }
}
