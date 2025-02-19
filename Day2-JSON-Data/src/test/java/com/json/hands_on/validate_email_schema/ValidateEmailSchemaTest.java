package com.json.hands_on.validate_email_schema;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidateEmailSchemaTest {

    // Helper method to load the JSON Schema
    private Schema loadSchema() {
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
        JSONObject jsonSchema = new JSONObject(new JSONTokener(schemaStr));
        return SchemaLoader.load(jsonSchema);
    }

    @Test
    public void testValidEmail() {
        Schema schema = loadSchema();
        JSONObject validJson = new JSONObject("{ \"email\": \"jane.doe@example.com\" }");

        // Assert that no exception is thrown for valid email
        assertDoesNotThrow(() -> schema.validate(validJson));
    }

    @Test
    public void testInvalidEmail() {
        Schema schema = loadSchema();
        JSONObject invalidJson = new JSONObject("{ \"email\": \"invalid-email\" }");

        // Assert that validation throws an exception for invalid email
        Exception exception = assertThrows(Exception.class, () -> schema.validate(invalidJson));
        assertNotNull(exception.getMessage());
    }
}
